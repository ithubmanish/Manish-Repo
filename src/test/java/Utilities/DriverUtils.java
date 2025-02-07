package Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class DriverUtils {

    public static AndroidDriver driver=null;

    public static AppiumDriverLocalService service;
    public static AppiumServiceBuilder builder;

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static AppiumDriverLocalService getService(){
        return service;
    }

    public static AppiumServiceBuilder getBuilder(){
        return builder;
    }

}
