package com.api.utility;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtility {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    // Setup the SparkReporter for the ExtentReports
    public static void setupSparkReporter(String reportName) {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//" + reportName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    // Create a new ExtentTest
    public static void createExtentTest(String testName) {
        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
    }

    // Get the current thread's ExtentTest
    public static ExtentTest getTest() {
        return extentTest.get();
    }

    // Flush the report to the specified location
    public static void flushReport() {
        extentReports.flush();
    }
}
