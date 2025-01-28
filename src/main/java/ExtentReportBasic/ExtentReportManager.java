package ExtentReportBasic;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {


    public static ExtentReports extent;
    private static ExtentTest test;

    public static void initializeExtentReports( ) {
        if (extent == null) { // Ensure extent is initialized only once
        	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/report/ExtentReport.html");
            htmlReporter.config().setDocumentTitle("Automation Test Report");
            htmlReporter.config().setReportName("Extent Report For LR-LoginPage");
            htmlReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
    }

    public static ExtentTest createTest(String testName) {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports is not initialized. Call initializeExtentReports() first.");
        }
        test = extent.createTest(testName);
        return test;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

}
