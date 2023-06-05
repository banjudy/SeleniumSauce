package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public ChromeOptions ops;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ops = new ChromeOptions();
			ops.addArguments("--remote-allow-origins=*");

			if (browserName.contains("headless")) {
				ops.addArguments("headless");
			}
			
			driver = new ChromeDriver(ops);
			driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440,900)); //full screen
		} else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Firefox\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToLandingPage();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeDriver() {
		driver.close();
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filepath) throws IOException {
		//read Json to String
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		
		//String to HashMap
		ObjectMapper om = new ObjectMapper();
		List<HashMap<String,String>> data = om.readValue(
					jsonContent, new TypeReference<List<HashMap<String, String>>>() {
					});
		return data;
	}
	
	//take screenshot while testing and save to reports folder as .png with method name
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}
	
	

}
