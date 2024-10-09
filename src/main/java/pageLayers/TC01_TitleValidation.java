package pageLayers;

import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import testBase.TestBase;

public class TC01_TitleValidation extends TestBase {

	public TC01_TitleValidation() {

		PageFactory.initElements(driver, this);	

	}

	//------------ Xpath Repository -------------
	// Using @FindBy to locate a single element by ID, Xpath etc
	


	//---------------------Methods-----------------
	public void titleValidation() {

		try {

			driver.get(amazonUrl);

			action.titleValidation(amazonTitle);
			
			Thread.sleep(2000);
			
			driver.get(youtubeUrl);

			action.titleValidation(youtubeTitle);
			
			Thread.sleep(2000);
			
			driver.get(googleUrl);

			action.titleValidation(googleTitle);
			
			Thread.sleep(2000);
			
			driver.get(facebookUrl);

			action.titleValidation(facebookTitle);
			
			Thread.sleep(2000);
			
			extentTest.get().log(Status.PASS, "Method executed successfully : titleValidation();");

		} 
		catch (Exception e) {
			extentTest.get().log(Status.FAIL, "Method failed : titleValidation();");
			throw new RuntimeException("Re-throwing login runtime exception", e); // Re-throw the exception to be caught globally
		}
	}

	
//Class Brace
}