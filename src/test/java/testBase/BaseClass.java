package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;// = new ChromeDriver();
	public Logger logger;
	public Properties prop;
	public FileReader data;

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	// public pageObjects.HomePage hp = new pageObjects.HomePage(driver);

	@BeforeClass
	@Parameters({ "os", "browser" })
	public void LaunchBrowser(@Optional("windows") String os, @Optional("chrome") String br) throws IOException {
		data = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		// data = new FileReader(".//src//test//resources//config.properties");
		// data = new
		// FileReader("C:\\Users\\hassa\\OneDrive\\Desktop\\Eclips_WorkSpace\\openCartHybridFramework\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(data);
		logger = LogManager.getLogger(this.getClass());// will create logs for current class
		if (prop.getProperty("env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("no matching browser..");
				return;
			}

		}
		if (prop.getProperty("env").equalsIgnoreCase("gridhub")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// os
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching os");
				return;
			}

			// browser
			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("No matching browser");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(prop.getProperty("url"));
//driver.get("https://demo.nopcommerce.com");
//System.out.println(prop.getProperty("url"));

	}

	@AfterClass
	public void TearDown() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();
	}

	public String randomString() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return randomString;
	}

	public static String randomNumber() {
		String randomNumber = RandomStringUtils.randomNumeric(5);
		return randomNumber;

	}

	public static String randomAlphaNumeric() {
		String st = RandomStringUtils.randomAlphabetic(5);
		String st1 = RandomStringUtils.randomNumeric(5);
		return st + st1;
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
