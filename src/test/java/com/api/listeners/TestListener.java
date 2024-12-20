package com.api.listeners;

import com.api.utility.ExtentReportUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    // This will run before the entire suite starts
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started..!!");
        // Initialize the report with the name of the report file
        ExtentReportUtility.setupSparkReporter("ExtentReport.html");
    }

    // This will run before each test method
    public void onTestStart(ITestResult result) {
        logger.info("Test Started: " + result.getMethod().getMethodName());
        logger.info("Description: " + result.getMethod().getDescription());

        // Create a new test in the report
        ExtentReportUtility.createExtentTest(result.getMethod().getMethodName());
    }

    // This will run when a test method passes
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: " + result.getMethod().getMethodName());

        // Log the test result as PASS in the report
        ExtentReportUtility.getTest().pass("Test Passed: " + result.getMethod().getMethodName());
    }

    // This will run when a test method fails
    public void onTestFailure(ITestResult result) {
        logger.error("Test Failed: " + result.getMethod().getMethodName());

        // Log the test failure in the report along with the error message and stack trace
        ExtentReportUtility.getTest().fail("Test Failed: " + result.getMethod().getMethodName());
        ExtentReportUtility.getTest().fail(result.getThrowable());
    }

    // This will run when a test method is skipped
    public void onTestSkipped(ITestResult result) {
        logger.info("Test Skipped: " + result.getMethod().getMethodName());

        // Log the test skip in the report
        ExtentReportUtility.getTest().skip("Test Skipped: " + result.getMethod().getMethodName());
    }

    // This will run after the entire suite finishes
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed..!!");

        // Flush the report at the end of the test suite
        ExtentReportUtility.flushReport();
    }
}
