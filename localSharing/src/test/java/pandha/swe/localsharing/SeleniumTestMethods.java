package pandha.swe.localsharing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTestMethods {

	private WebDriver driver;

	private String baseUrl, browserName, browserVersion;

	public void setUp() throws Exception {

		driver = new FirefoxDriver();

		baseUrl = "http://localhost:8090";

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();

		browserName = caps.getBrowserName();

		browserVersion = caps.getVersion();

		System.out.println("Selenium Test gestartet mit" + browserName + " "
				+ browserVersion);

	}

	public void tearDown() {

		driver.quit();

	}

	public void goToHomePage() {

		driver.get(baseUrl);

	}

}
