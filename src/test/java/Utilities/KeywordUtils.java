package Utilities;

import Runner.TestRunner;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.example.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import io.cucumber.java.Scenario;

import javax.imageio.stream.ImageInputStream;


public class KeywordUtils {
    static String testCaseDescription;
    static String imagePath;
    static String pathForLogger;

    public static void startServer() {
        try {
            DriverUtils.service = new AppiumServiceBuilder()
                    .usingPort(4723)
                    .build();
            DriverUtils.service.start();
            System.out.println("Appium server started at: " + DriverUtils.service.getUrl());
            DriverUtils.driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), KeywordUtils.setCapabilities());
            System.out.println("Android Application started at: " + DriverUtils.service.getUrl());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void stopServer() {
        try {
            DriverUtils.service.stop();
            System.out.println("Android Application started at: " + DriverUtils.service.getUrl());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    public static Capabilities setCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", ConfigReader.getValue("deviceName"));
        capabilities.setCapability("platformName", ConfigReader.getValue("platformName"));
        capabilities.setCapability("platformVersion", ConfigReader.getValue("platformVersion"));
        capabilities.setCapability("automationName", ConfigReader.getValue("automationName"));
        capabilities.setCapability("appPackage", ConfigReader.getValue("appPackage"));
        capabilities.setCapability("appActivity", ConfigReader.getValue("appActivity"));
        return capabilities;
    }


    public static void cleanDirectory() {
        try {
            String filePath = System.getProperty("user.dir") + File.separator + ConfigReader.getValue("screenshotPath");
            if (new File(filePath).exists()) {
                FileUtils.cleanDirectory(new File(filePath));
            }
            filePath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "HTMLReports";
            if (new File(filePath).exists()) {
                FileUtils.cleanDirectory(new File(filePath));
            }
        } catch (Throwable e) {

        }
    }

    public static void extentReportInitialization() {
        try {
            TestRunner.extent=new ExtentReports(System.getProperty("user.dir")+ConfigReader.getValue("extentReportPath"),true);
            TestRunner.extent.loadConfig(new File(System.getProperty("user.dir")+ConfigReader.getValue("extentConfigFile")));
            LogUtils.infoLog(TestRunner.class, "\n\n+===========================================================================================================+");
            LogUtils.infoLog(TestRunner.class, " Suite started" + " at " + new Date());
            LogUtils.infoLog(TestRunner.class, "\n\n+===========================================================================================================+");
        } catch (Throwable e) {
            e.getMessage();
            e.getCause();
        }
    }

    public static void extentReportClosure() {
        try {
            LogUtils.infoLog(TestRunner.class,"Suite Finished at: "+new Date());
            LogUtils.infoLog(TestRunner.class,"===========================================================");
            TestRunner.extent.flush();
        }
        catch (Throwable e){

        }
    }


    public static byte[] takeScreenshot(String screenshotFilePath) {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverUtils.getDriver()).getScreenshotAs(OutputType.BYTES);
            FileOutputStream fileOuputStream = new FileOutputStream(screenshotFilePath);
            fileOuputStream.write(screenshot);
            fileOuputStream.close();
            return screenshot;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void testCaseDescriptionStart(Scenario scenario) {
        try {
            testCaseDescription = scenario.getName().split("_")[1];
        } catch (Exception e) {
            testCaseDescription = scenario.getName();
        }
        TestRunner.logger = TestRunner.extent.startTest(testCaseDescription);
        try {
            Collection<String> tagsList = scenario.getSourceTagNames();
            if (tagsList.size() > 0) {
                for (String tag : tagsList
                ) {
                    TestRunner.logger.assignCategory(tag);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        LogUtils.infoLog("",
                "\n+----------------------------------------------------------------------------------------------------------------------------+");
        LogUtils.infoLog("", "Mobile Tests Started: " + scenario.getName());

        LogUtils.infoLog("Mobile Test Environment",
                "Mobile Test is executed in OS: IOS " + System.getProperty("udid"));
    }

    public static void endScenario(Scenario scenario) {
                if (scenario.isFailed()) {
                    try {
                        String scFileName = "ScreenShot_" + System.currentTimeMillis();
                        String screenshotFilePath = ConfigReader.getValue("screenshotPath") + "\\" + scFileName + ".png";
                        imagePath = HTMLReportUtil.testFailTakeScreenshot(screenshotFilePath);
                        InputStream is = new FileInputStream(imagePath);
                        byte[] imageBytes = IOUtils.toByteArray(is);
                        Thread.sleep(2000);
                        String base64 = Base64.getEncoder().encodeToString(imageBytes);
                        TestRunner.logger.log(LogStatus.FAIL, HTMLReportUtil.failStringRedColor("Failed at point: " + pathForLogger + TestRunner.failMsg));
                        byte[] screenshot =TakeScreenshot.takeScreenshot(imagePath);
                        scenario.attach(screenshot, "image/png", "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.infoLog("TestEnded", "Closing the application");
                }
                DriverUtils.driver.quit();
                TestRunner.extent.endTest(TestRunner.logger);
    }


    public static void openReport() {
        String htmlReport = System.getProperty("user.dir") + "\\" + ConfigReader.getValue("extentReportPath");
        try {
            Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe \"" + htmlReport);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean click(By locator, String logStep){
        DriverUtils.driver.findElement(locator).click();
        return true;
        

    }


}
