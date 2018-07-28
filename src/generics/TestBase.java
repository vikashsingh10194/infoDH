package generics;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase implements Constants {

	public WebDriver driver;

	static {
		System.setProperty(CHROME_KEY, CHROME_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
	}

	@Parameters("browser")
	@BeforeMethod
	public void launchApplication(String browserName) throws MalformedURLException {

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.get(Utils.getPropertyValue(CONFIG_PATH, "URL"));
		driver.manage().timeouts().implicitlyWait(Long.parseLong(Utils.getPropertyValue(CONFIG_PATH, "ITO")),
				TimeUnit.SECONDS);

	}

	@AfterMethod(alwaysRun = true)
	public void closeApplication(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			Utils.takeScreenshot(driver, result.getName());

		}
		driver.quit();
	}
}
