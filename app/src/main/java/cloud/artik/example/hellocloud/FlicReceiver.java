package cloud.artik.example.hellocloud;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cloud.artik.api.MessagesApi;
import cloud.artik.api.UsersApi;
import cloud.artik.client.ApiCallback;
import cloud.artik.client.ApiClient;
import cloud.artik.client.ApiException;
import cloud.artik.model.MessageAction;
import cloud.artik.model.MessageIDEnvelope;
import io.flic.lib.FlicBroadcastReceiver;
import io.flic.lib.FlicButton;

/**
 * Created by Tushar on 17-Jul-16.
 */
public class FlicReceiver extends FlicBroadcastReceiver{
    public static final String DEVICE_ID = "****";
    private static final String TAG = "FlicButton";
    private MessagesApi mMessagesApi = null;
    public static final String KEY_ACCESS_TOKEN = "****";
    private UsersApi mUsersApi = null;
    private String mAccessToken;

    private void setupArtikCloudApi() {
        mAccessToken = KEY_ACCESS_TOKEN;

        ApiClient mApiClient = new ApiClient();
        mApiClient.setAccessToken(mAccessToken);
        mApiClient.setDebugging(true);

        mUsersApi = new UsersApi(mApiClient);
        mMessagesApi = new MessagesApi(mApiClient);
    }
    @Override
    protected void onRequestAppCredentials(Context context) {
        config.setFlicCredentials();
    }

    @Override
    public void onButtonSingleOrDoubleClickOrHold(Context context, FlicButton button, boolean wasQueued, int timeDiff, boolean isSingleClick, boolean isDoubleClick, boolean isHold) {
            try {
                setupArtikCloudApi();
                postMsg(isSingleClick, isDoubleClick, isHold, "FlicButton1" + button.getButtonId());
            }
            catch (Exception e) {
            Log.e("Artik-Flic", "Exception", e);
            }
//            Notification notification = new Notification.Builder(context)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle("Button was pressed")
//                    .setContentText("Pressed last time at " + new Date())
//                    .build();
//            ((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, notification);
    }
    public void postMsg(boolean isSingleClick, boolean isDoubleClick, boolean isHold, String deviceId) {
        final String tag = TAG + " sendMessageActionAsync";

        HashMap<String, Object> data = new HashMap<String, Object>();

        Long datetime = System.currentTimeMillis();
//        data.put("isDoubleClick", 0);
//        data.put("isSingleClick", 0);
//        data.put("isHold", 0);
//        if(isSingleClick)
//            data.put("isSingleClick", 1);
//        if(isDoubleClick)
//            data.put("isDoubleClick", 1);
//        if(isHold)
//            data.put("isHold", 1);
        data.put("DeviceId", deviceId);
        //data.put("description", "Run");
        data.put("isSingleClick", isSingleClick);
        data.put("isDoubleClick", isDoubleClick);
        data.put("isHold", isHold);
        data.put("timestamp", datetime);

        MessageAction msg = new MessageAction();
        msg.setSdid(DEVICE_ID);
        msg.setData(data);

        try {
            mMessagesApi.sendMessageActionAsync(msg, new ApiCallback<MessageIDEnvelope>() {
                @Override
                public void onFailure(ApiException exc, int i, Map<String, List<String>> stringListMap) {
                    processFailure(tag, exc);
                }

                @Override
                public void onSuccess(MessageIDEnvelope result, int i, Map<String, List<String>> stringListMap) {
                    Log.v(tag, " onSuccess response to sending message = " + result.getData().toString());
                }

                @Override
                public void onUploadProgress(long bytes, long contentLen, boolean done) {
                }

                @Override
                public void onDownloadProgress(long bytes, long contentLen, boolean done) {
                }
            });
        } catch (ApiException exc) {
            processFailure(tag, exc);
        }
    }


    static void showErrorOnUIThread(final String text, final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(activity.getApplicationContext(), text, duration);
                toast.show();
            }
        });
    }

    private void processFailure(final String context, ApiException exc) {
        String errorDetail = " onFailure with exception" + exc;
        Log.w(context, errorDetail);
        exc.printStackTrace();
    }

    @Override
    public void onButtonRemoved(Context context, FlicButton button) {
        Log.d("yo", "removed");
        Toast.makeText(context, "Button was removed", Toast.LENGTH_SHORT).show();
    }
}