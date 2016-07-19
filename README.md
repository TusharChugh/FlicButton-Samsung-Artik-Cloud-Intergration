#Integration of flic button with Samsung Artik Cloud

Flic is listed as one of the most wanted devices to be integrated with Artik cloud platform. So, I went ahead to integrate it. (https://artik.cloud/challenge/most-wanted-devices)

Steps to use the repo:

#Samsung Artik Cloud
1. Sign-up/login on Samsung artik cloud: https://developer.artik.cloud/
2. Go to https://developer.artik.cloud/dashboard/applications and create your own application
3. Add the device as Flicbutton (this is the one which I created and published). Give read and write access.
4. Get the client id from this application
5. Go to https://artik.cloud/my/devices and click on connect another device.
6. Type FlicButton
7. Get DeviceID and access token from the device
8. Clone this repository and paste the credentials in MessageAcitivity, MainActivity and FlicReceiverFiles (remove asterisks)
9. Open it in Android Studio

#Flic Button
1. If you have the flic device then install flic app by the instructions given on their website
2. Get the developer key from https://partners.flic.io/partners/developers/credentials
3. There are nice tutorials in the website, it is recommended to read them
4. Paste the key in MainActivity.java
5. Run the app on your phone through andriod studio (through USB cable)
6. Click on Get Flic button and select the flic
7. Login with your samsung account
8. Just press your flic device (singleclick, doubleclick and hold are supported)
9. Go to https://artik.cloud/my/devices and see the charts. You should see your data streaming live in graphs
10. Congratulations you are done!



Note: The android application has been built in few hours of work and contains the sample code from Samsung artik cloud (https://github.com/artikcloud/tutorial-android-your-first-app) and flic(https://github.com/50ButtonsEach/android-background-example)
