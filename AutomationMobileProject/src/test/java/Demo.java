import Utilities.DriverUtils;
import Utilities.KeywordUtils;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

public class Demo {

    public static void main(String[] args) {
        KeywordUtils.startServer();
        try {
            DriverUtils.driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), KeywordUtils.setCapabilities());
        } catch (Throwable e) {
            e.printStackTrace();
            e.getMessage();
        }
//        KeywordUtils.stopServer();
    }
}
