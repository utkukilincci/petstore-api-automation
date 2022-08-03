package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static helpers.LogUtils.*;

public class TestListener implements ITestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        try {
            LOGGER.info("TEST STARTED: {}", iTestResult.getName());
        } catch (ArrayIndexOutOfBoundsException oobe) {
            LOGGER.error("Cannot log test name!");
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logInfo("Test Finished Successfully: " + iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logError("Test Failed: " + iTestResult.getName() + " Exception is: " + iTestResult.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        if (iTestResult.getParameters().length == 0) {
            logWarn("Test Has Been Skipped: " + iTestResult.getName());
        } else {
            iTestResult.setStatus(2);
            iTestResult.setParameters(new String[]{});
        }
    }
}