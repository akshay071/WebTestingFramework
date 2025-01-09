package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtils {
    private static ExtentReports extent;
    private static ExtentTest test;

    // Method to initialize the ExtentReports instance
    public static void initReports(String reportPath) {
//        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
//        htmlReporter.config().setTheme(Theme.DARK);
//        htmlReporter.config().setDocumentTitle("Automation Test Report");
//        htmlReporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        //extent.attachReporter(htmlReporter);
    }

    // Method to create a test node
    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    // Method to log information in the report
    public static void logInfo(String message) {
        test.info(message);
    }

    public static void logPass(String message) {
        test.pass(message);
    }

    public static void logFail(String message) {
        test.fail(message);
    }

    // Method to flush the report
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
