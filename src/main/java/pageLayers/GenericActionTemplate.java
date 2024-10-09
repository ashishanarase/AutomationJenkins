package pageLayers;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import testBase.TestBase;

public class GenericActionTemplate extends TestBase {
	
	public static final int maxWaitTime = 30;

	JavascriptExecutor jScript = (JavascriptExecutor) driver;

	// Constructor to initialize WebDriver, WebDriverWait, and ExtentTest
	public GenericActionTemplate() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime)); // You can adjust the timeout duration   
	}


	public void titleValidation (String expectedTitle) {

		wait.until(ExpectedConditions.titleContains(expectedTitle));

		String actualTitle = driver.getTitle();		

		//	Assert.assertEquals(actualTitle, expectedTitle);

		if (actualTitle.contains(expectedTitle)) {
			extentTest.get().log(Status.PASS, "Tab title validated successfully");
		}
		else {
			extentTest.get().log(Status.WARNING, "Tab title validation failed : Not matching with expected title");
		}
	}

}