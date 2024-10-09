package testCase;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testBase.TestBase;
import utility.ListenerClass;

@Listeners(ListenerClass.class)

public class TestCase extends TestBase {


	@Test (priority = 1, enabled = true,
			description = "Test to verify that user should be able to login with valid credentials only")
	public void TC01_TitleValidation() {

		d1.titleValidation();

	}
	
	
//Class Closing Brace	
}
