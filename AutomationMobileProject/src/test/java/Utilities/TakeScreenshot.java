package Utilities;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class TakeScreenshot {
    public static void take_ScreenShot() {
        File screen_shot = new File(((TakesScreenshot) DriverUtils.getDriver()).getScreenshotAs(OutputType.BASE64));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String fileName = "Screenshots" + File.separator + "screenshot_" + timestamp + ".png";
        File destination = new File(fileName);
        try {
            FileUtils.copyFile(screen_shot, destination);
            System.out.println("Screenshot has been saved at this path=  " + destination.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void takeScreenAndAttachInReport() {
        try {
            String imagePath, pathForLogger;
            String scFileName = "ScreenShot_" + System.currentTimeMillis();
            String screenShotFilePath = ConfigReader.getValue("screenshotPath") + "\\" + scFileName + ".png";
            imagePath = HTMLReportUtil.testFailTakeScreenshot(screenShotFilePath);

            InputStream is = new FileInputStream(imagePath);
            byte[] imageBytes = IOUtils.toByteArray(is);

            String base64 = Base64.getEncoder().encodeToString(imageBytes);
//            pathForLogger = TestRunner.logger.addBase64ScreenShot("data:image;base64," + base64);

//            TestRunner.logger.log(LogStatus.PASS, pathForLogger);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws AWTException, InterruptedException {
        while (true) {
            new Robot().mouseMove(500, 300);
            Thread.sleep(3000);
            new Robot().mouseMove(300, 500);
            Thread.sleep(3000);
        }
    }
}
