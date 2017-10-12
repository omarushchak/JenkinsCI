package com.epam.lab.reporting.utils;

import org.apache.log4j.Logger;
import org.testng.*;
import org.testng.xml.*;

import java.util.List;

public class TestNGListener implements ITestListener, IReporter {
    private static final Logger LOGGER = Logger.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info("test starts " + iTestResult.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.info("test passed " + iTestResult.getTestName() + " during time " + (iTestResult.getEndMillis() - iTestResult.getStartMillis()));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOGGER.info("test failed " + iTestResult.getTestName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOGGER.info("test skipped " + iTestResult.getTestName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOGGER.info("test starts " + iTestContext.getName() +" on " + iTestContext.getStartDate());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOGGER.info("test ends " + iTestContext.getName() +" on" + iTestContext.getEndDate());
    }

    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {

    }
}