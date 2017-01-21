package de.zalando.project.selenium.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestFailer implements IRetryAnalyzer {
    private int retryCount = 0;

    public boolean retry(ITestResult result) {
        int maxRetryCount = 1;
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
