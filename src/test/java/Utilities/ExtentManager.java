package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.util.HashMap;
import java.util.Map;
public class ExtentManager {
    static ExtentReports extent;
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();

    public static ExtentReports getReporterObject() {
        if (extent == null) {  // Singleton pattern — only one report instance
            String path = System.getProperty("user.dir") + "/reports/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);

            // ==========================
            // 🔧 Configuration Section
            // ==========================
            reporter.config().setTheme(Theme.DARK); // 🌑 Dark Mode
            reporter.config().setDocumentTitle("Automation Test Results");
            reporter.config().setReportName("Test Execution Report - QA Environment");
            reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
            reporter.config().setOfflineMode(true);
            reporter.config().setProtocol(Protocol.HTTP);

            // ==========================
            // 🧭 Multi-Tab Support (Fixed)
            // ==========================
            reporter.viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[]{
                            ViewName.DASHBOARD,
                            ViewName.TEST,
                            ViewName.CATEGORY,
                            ViewName.DEVICE,
                            ViewName.AUTHOR,
                            ViewName.EXCEPTION,
                            ViewName.LOG
                    })
                    .apply();

            // ==========================
            // 📋 Attach to ExtentReports
            // ==========================
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // Add environment/system info
            extent.setSystemInfo("Tester", " Shorooq Elawi");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }

        return extent;
    }


    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }
    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = getReporterObject().createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}


