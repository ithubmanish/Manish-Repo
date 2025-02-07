package Modules;

import Runner.TestRunner;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;

public class DemoModules {
    public static void clickHome() {
        try {
            TestRunner.logger.log(LogStatus.PASS,"Click Home");
            TestRunner.log.info("Click Home");
        } catch (Throwable e) {
            TestRunner.failMsg=e.getMessage();
            Assert.fail(String.valueOf(e));
        }
    }

    public static void validateHome() {
        try {
            TestRunner.logger.log(LogStatus.PASS,"Validate Home");
            TestRunner.log.info("Validate Home");
        } catch (Throwable e) {
            TestRunner.failMsg=e.getMessage();
            Assert.fail(String.valueOf(e));
        }
    }
}
