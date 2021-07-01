package com.gaurav.CommonUtils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry mechanism for retrying failed test cases
 */
public class RetryFailedTest implements IRetryAnalyzer {

    int count = 1;
    int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < maxRetryCount) {
            count++;
            return true;
        }
        return false;
    }
}
