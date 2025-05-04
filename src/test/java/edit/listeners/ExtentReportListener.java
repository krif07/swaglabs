package edit.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import edit.driver.DriverFactory;
import edit.utils.DocumentHelper;
import edit.utils.ReadProperties;
import edit.utils.ScreenshotHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {
    private ExtentReports extent;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String OUTPUT_FOLDER = "./test-results/";
    private static final String FILE_NAME = "ExtentReport.html";

    @Override
    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportFileName = FILE_NAME.replace(".html", "_" + timeStamp + ".html");

        File dir = new File(OUTPUT_FOLDER);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(OUTPUT_FOLDER + reportFileName);
        htmlReporter.config().setDocumentTitle("Automation Test Results");
        htmlReporter.config().setReportName("Purchase Order Tests");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", ReadProperties.getEnvironment());
        extent.setSystemInfo("Browser", ReadProperties.getBrowserType());
        extent.setSystemInfo("OS", System.getProperty("os.name"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test completado correctamente");
        if(ReadProperties.getTakePictures().equals("EVERY_STEP")) {
            String screenPath = ScreenshotHelper.captureScreenshot(
                    DriverFactory.getDriver(), result.getMethod().getMethodName());
            if(screenPath != null) {
                test.get().addScreenCaptureFromPath(screenPath, "Screenshot Pass");
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test fallido");
        test.get().fail(result.getThrowable());

        String screenshotPath = ScreenshotHelper.captureScreenshot(
                DriverFactory.getDriver(), result.getMethod().getMethodName());
        if(screenshotPath != null) {
            test.get().addScreenCaptureFromPath(screenshotPath, "Screenshot Failed");
            DocumentHelper.createDocument(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test omitido");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}