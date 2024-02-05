package core.testBase;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            int maxTry = Keywords.RETRY_COUNT_FOR_FAILED_TEST;
            if (count < maxTry) {
                count++;
                return true;
            } else {
                result.setStatus(ITestResult.FAILURE);
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
