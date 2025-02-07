package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class HTMLReportUtil {
    static String html;

    public static String concatt = ".";

    public static String DummyString;
    public static String DummyString1;

    public static String ImagePathh;

    public static String testFailTakeScreenshot(String imagePath) throws IOException {

        File src = ((TakesScreenshot) DriverUtils.getDriver()).getScreenshotAs(OutputType.FILE);
        File des = new File(imagePath);
        FileUtils.copyFile(src, des);

        // System.out.println(des.getAbsolutePath());
        // System.out.println(des.getCanonicalFile());
        System.out.println(des);

        //return des.getAbsolutePath();
        DummyString = des.getAbsolutePath();
        String path = DummyString;
        String base = System.getProperty("user.dir")+"/ExecutionReports/FailedScreenshots/";
        String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

        return relative;
    }

    public static String testFailMobileTakeScreenshot(String imagePath) throws IOException {

        File src = ((TakesScreenshot) DriverUtils.getDriver()).getScreenshotAs(OutputType.FILE);

        // File des = new File(System.getProperty("user.dir") +
        // "/FailedScreenshots/" + className + ".jpeg");
        File des = new File(imagePath);

        FileUtils.copyFile(src, des);

        // System.out.println(des.getAbsolutePath());
        // System.out.println(des.getCanonicalFile());
        System.out.println(des);

        return des.getAbsolutePath();

        // return des.toString();
    }

    public static String takeFailScreenshot(String imagePath) throws IOException {
//        WebDriver driver = DriverUtils.getDriver() != null ? DriverUtils.getDriver() : GlobalUtil.getDriver();

        if (DriverUtils.driver == null) {
            throw new IllegalStateException("No valid driver found.");
        }

        File src = ((TakesScreenshot) DriverUtils.driver).getScreenshotAs(OutputType.FILE);
        File des = new File(imagePath);
        FileUtils.copyFile(src, des);

        String path = des.getAbsolutePath();
        String base = System.getProperty("User.dir")+"/ExecutionReports/FailedScreenshots/";
        String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

        return relative;
    }

    public static String failStringRedColor(String stepName) {
        html = "<span style='color:red'><b>" + stepName + "</b></span>";
        return html;
    }

    public static String passStringGreenColor(String stepName) {
//		html = "<span style='color:#008000'><b>" + stepName + " - PASSED" + "</b></span>";
        html = "<span style='color:#008000'><b>PASSED - " + stepName + "</b></span>";

        return html;
    }
    public static String infoStringGreenColor(String stepName) {
        html = "<span style='color:#008000'><b>" + stepName + "</b></span>";
        return html;
    }
}
