package testBase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageLayers.GenericActionTemplate;
import pageLayers.TC01_TitleValidation;
import utility.ConfigLoader;
import utility.DataProvider;
import utility.ListenerClass;

public class TestBase {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static GenericActionTemplate action;
	public TC01_TitleValidation d1;	


	private static String desiredBrowser = "chrome";       // firefox //chrome
	private static String browserVersionChrome = "127.0.6533.122";
	private static String browserVersionFireFox = "129.0b7";

	protected static String userDirectory;
	protected static String facebookUrl;
	protected static String youtubeUrl;
	protected static String googleUrl;
	protected static String amazonUrl;


	// Fetch title
	protected static String facebookTitle;
	protected static String youtubeTitle;
	protected static String googleTitle;
	protected static String amazonTitle;

	@BeforeSuite
	public void dataGeneration() {
		//Used to generate employee data randomly for each test run
		DataProvider.getNameDetails();

		extent = new ExtentReports();

		// Load environment-specific properties from ConfigLoader
		String environment = System.getProperty("environment", "prod");    //prod //test
		ConfigLoader.loadConfig(environment);
		userDirectory = ConfigLoader.getProperty("userDirectory");
		facebookUrl = ConfigLoader.getProperty("facebookUrl");
		youtubeUrl = ConfigLoader.getProperty("youtubeUrl");
		googleUrl = ConfigLoader.getProperty("googleUrl");
		amazonUrl = ConfigLoader.getProperty("amazonUrl");


		// Fetch title 
		facebookTitle = ConfigLoader.getProperty("facebookTitle");
		youtubeTitle = ConfigLoader.getProperty("youtubeTitle");
		googleTitle = ConfigLoader.getProperty("googleTitle");
		amazonTitle = ConfigLoader.getProperty("amazonTitle");

	}

	@BeforeMethod
	public void startBrowser() {			

		if (desiredBrowser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup(); 			

			driver = new ChromeDriver();		

		} else if (desiredBrowser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().browserVersion(browserVersionFireFox).setup();
			driver = new FirefoxDriver();
		}

		ListenerClass.setDriver(driver); // Set the driver for the listener

		// Initialize object references
		action = new GenericActionTemplate ();
		d1 = new TC01_TitleValidation();		

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(googleUrl);	
	}

	@AfterMethod	
	public void closeBrowser() throws Exception {

		Thread.sleep(2000);
		driver.quit();

	}
}