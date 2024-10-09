package utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.TestBase;

public class ListenerClass extends TestBase implements ITestListener {
	   
    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
        CommonMethods.setDriver(driverInstance); // Set the driver for CommonMethods
    }

    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports before any test starts
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(userDirectory+"Report\\ExtentReport_"+DataProvider.getCurrentTimeStamp()+".html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Demo Automation Project");
        htmlReporter.config().setReportName("Demo Automation Report_"+DataProvider.getCurrentTimeStamp());

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "AshishAnarase");
        extent.setSystemInfo("User Name", "ashish.anarase");
        extent.setSystemInfo("Environment", "Demo Automation");
    }

    @Override
    public void onFinish(ITestContext context) {
        // Close ExtentReports after all tests are done
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Start a new extentTest for each test method
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);

        test.log(Status.INFO, "Test Started : " + result.getMethod().getMethodName());
        test.log(Status.INFO, "Test Description : " + result.getMethod().getDescription());
        extentTest.get().log(Status.PASS, "Navigated to the login page of WebApp");
 
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "TestCase Passed : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "TestCase Failed : " + result.getName());
        extentTest.get().log(Status.FAIL, "TestCase Failed : " + result.getThrowable());

        try {
        	   
            String filePathDestination = CommonMethods.takeScreenShot(driver, result.getName()); // This should return the relative path
            extentTest.get().fail("Screenshot attached : ", MediaEntityBuilder.createScreenCaptureFromPath(filePathDestination).build());
            
        } catch (Exception e) {
        	extentTest.get().log(Status.WARNING, "An error occurred while attaching the screenshot to report : " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "TestCase Skipped : " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional implementation
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }
}