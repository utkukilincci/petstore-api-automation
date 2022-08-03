package base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private static int maxTry = Keywords.RETRY_COUNT_FOR_FAILED_TEST;
    private int count;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
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
