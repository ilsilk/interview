package com.interview.common.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.interview.common.utils.PropertiesUtils.getIntProperty;

public final class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < getIntProperty("max.retry.count")) {
            retryCount++;
            return true;
        }
        return false;
    }
}
