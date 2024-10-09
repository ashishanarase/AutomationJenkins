package utility;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.Status;

import testBase.TestBase;

public class CommonMethods extends TestBase {
	
	public static void setDriver(WebDriver driverInstance) {
		driver = driverInstance;
	}

	public static String takeScreenShot(WebDriver driver, String screenshotName)  {

		// Cast WebDriver to TakesScreenshot
		TakesScreenshot screenshot = (TakesScreenshot) driver;

		// Capture screenshot as File
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

		// Format the current date and time as a string using the defined format
		String dynamicFileName = screenshotName+DataProvider.getCurrentTimeStamp();

		// Specify the destination directory and file name
		String directoryPath = userDirectory+"Screenshots\\";
		String filePathDestination = directoryPath + dynamicFileName + ".jpg";

		File destFile = new File(filePathDestination);			

		try {	// Copy file to destination
			FileHandler.copy(srcFile, destFile);

			extentTest.get().log(Status.PASS, "Screenshot captured and saved at: " + destFile.getAbsolutePath());


		} catch (Exception e) {
			extentTest.get().log(Status.WARNING,"Failed to capture screenshot : " + e.getMessage());
		}

		return filePathDestination;
	}


}
