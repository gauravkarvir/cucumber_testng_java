
Mobile Device Settings
================================
This document covers settings for  
*Mobile Automation on Emulated and Physical Devices, for Native, Hybrid, and Web App  
*Mobile Automation on Cloud using SauceLabs

Machine Configuration
====================
Configure Windows setup: -   
*Java 8 jdk and set up JAVA_HOME environment variable   
*Git  / SVN  and set up Git environment variable
*Maven and set up M2_HOME environment variable   
*Appium Server: AppiumForWindows.zip from https://bitbucket.org/appium/appium.app/downloads/  
*Android sdk and set up ANDROID_HOME environment variable: - Download Android stand-alone SDK Tools (Mandatory) OR Android Studio (optional)  
**Please note by default Android studio downloads latest Android Emulated devices


Framework setup steps for Starting an Emulated Device
============================
 For the desired android emulated device setup and operating system version e.g Android Kitkat which is 4.4.2, download necessary packages as listed below
 
1)Android SDK manager  
 Open "SDK Manager.exe" located in SDK installed location

Select all checkboxes under "Tools" and "Extras" menu  
Select desired android operating system version which needs to be tested and select all the boxes underneath it. 
 e.g "Android 4.4.2 (API 19)"  
 
Install all these packages 
 
2)Android AVD  
Locate AVD Manager.exe located in SDK installed location

Click on Create a new AVD (Android Virtual Device e.g for Android 4.4.2 device  
Name: "anyName"  
Device: "Nexus 7 (7.02", 1200 * 1920:xhdpi)"    
CPU/ABI: ARM(armeabi-v7a)    
Keyboard: Yes Hardware  
Target:  Android 4.4.2 (API level 19)  
Skin: HVGA  
Camera:  None  
Memory: RAM 1024 VM Heap 32  
Internal Storage: 200 MiB  
SD Card :  Size 100 MiB  
Emulation Options : Un check all  

    

Framework setup steps for connecting to a Physical Device
============================
Connect the physical mobile device

open command prompt or terminal window and enter the below command  
"adb devices"    
The detected device should now be in the list. If the device is still not detected add supported driver e.g Samsung Kies for Samsung phones on your host machine
 
 

Appium Server Configuration
============================
Open Appium Server  
Click On General Settings: -    
Note the server url and port for Appium  

Click On Developer Settings: -  
Select Enabled checkbox  

Start the server by clicking on Play button  


Run Web App Tests on Android Emulated Device or Physical Device using Chrome Browser   
============================  
Open "pom.xml" (For running Web Apps)   
Scroll to Profile section : - Choose desired profile e.g "dev" for running locally  
Set  
           
           <!--platform to run e.g linux64, mac32, win32, win64, Android-->
                            <platform>Android</platform>
           <!--Desired browser to run e.g firefox,chrome,iexplore,phantomjs, appium, sauce -->
                            <browser>appium</browser>

Open WebDriverHelper and configure appium desired capabilites as per the configuration needed. e.g.

```
private static DesiredCapabilities getAppiumDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");
        capabilities.setCapability(MobileCapabilityType.APP, "Chrome");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "42f7ab1fb7b59fab");
        return capabilities;
    }
    ```
    
Run Native App Tests on Android Emulated Device or Physical Device using .apk file   
============================  
Android Native Mobile apps are automated using  
  1) AndroidHelper.class
  2) AndroidObject.class
  3) Appium and Android Driver
  4) Application under test (.apk file)

Android Driver and Appium configuration in AndroidHelper.class
-------------------------------------------------------------------

Provide Appium Server Details URL and Port in this method 

    private static AndroidDriver<WebElement> startAppiumDriver() {
        DesiredCapabilities capabilities = getAppiumDesiredCapabilities();
        try {
            ANDROID_DRIVER = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ANDROID_DRIVER;
    }

Provide APK or application under test file location and other configuration in this method  

    private static DesiredCapabilities getAppiumDesiredCapabilities() {
        File app = new File("D:\\projects\\master_cucumber_testng\\tools\\ContactManager.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.example.android.contactmanager");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".ContactManager");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        //capabilities.setCapability("deviceName", "42f7ab1fb7b59fab");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        return capabilities;
    }


Android Page Objects
-------------------------------------------------------------------
Every Mobile Page  class extends "AndroidObject.class" to make use of the AndroidDriver Object and utility functions.  
  
Location: /home/dev/src/salmonAutomationFramework/src/test/java/com/salmon/test/pageobjects/mobile
Directory structure: Group common Page Objects classes in a single directory e.g Login Functionality Classes in Login Directory      
File Conventions:Every Class file ends with Page.class (Homepage.class)  

Example:   


    public class MobileContactsPage extends AndroidObject {
    
    private By addContactButton = By.name("Add Contact");
    private String contactFormFields = ("android.widget.EditText");
    
    public void clickOnAddContact() {
        getAndroidDriver().findElement(addContactButton).click();
    }

    public List<AndroidElement> getContactFormFields(){
        return getAndroidDriver().findElementsByClassName(contactFormFields);
    }




    