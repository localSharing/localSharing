package pandha.swe.localsharing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTestMethods {

	private WebDriver driver;

	private static String baseUrl = "http://localhost:8090/";

	@Before
	public void setUp() throws Exception {

		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();

		String browserName = caps.getBrowserName();

		String browserVersion = caps.getVersion();

		System.out.println("Selenium Test gestartet mit" + browserName + " "
				+ browserVersion);

	}

	public void goToLoginSite() {
		driver.findElement(By.id("btnLogin")).click();

	}

	public void checkLogoutAndIfLoggedinLogout() {
		goToSite("login");

		if (!(getFullUrl("login").equals(driver.getCurrentUrl()))) {
			// Logged in -> Log out
			driver.findElement(By.id("btnLogin")).click();
		}

	}

	public void checkIfIamLoggedIn() {
		// Eingeloggt User werden zur StartPage weiter geleitet, unagemeldete
		// user müssen sich erst anmelden
		checkPage("startPage");
	}

	public void login_writeUsername(String username) {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
	}

	public void login_writePassword(String password) {
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);

	}

	public void click_Button(String id) {
		driver.findElement(By.id(id)).click();
	}

	public void click_Link(String id) {
		driver.findElement(By.linkText(id)).click();
	}

	public void checkPage(String url) {
		// Parameter entfernen
		String actualUrl = driver.getCurrentUrl();

		if (actualUrl.contains("?")) {
			int index = actualUrl.indexOf("?");
			actualUrl = actualUrl.substring(0, index);
		}

		assertEquals(getFullUrl(url), actualUrl);
	}

	public void checkNotPage(String url) {
		assertFalse(getFullUrl(url).equals(driver.getCurrentUrl()));
	}

	public void goToHomePage() {
		goToSite("");
	}

	public void goToSite(String url) {
		driver.get(getFullUrl(url));
	}

	private String getFullUrl(String urlPart) {

		if (urlPart.startsWith("/")) {
			urlPart = urlPart.substring(1);
		}

		return baseUrl + urlPart;
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	public SeleniumTestMethods() {
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkIfIamNotLoggedIn() {
		// TODO Auto-generated method stub

	}

}
