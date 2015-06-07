package pandha.swe.localsharing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTestMethods {

	private FirefoxDriver driver;

	private static String baseUrl = "http://localhost:8090/";

	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

	public void click_Element(By by) {
		driver.findElement(by).click();
	}

	public void click_ElementWithText(String text) {

		String[] tags = { "button", "a" };

		String xpath = "(";

		for (int i = 0; i < tags.length; i++) {

			if (i > 0) {
				xpath += " | ";
			}

			xpath += "//";
			xpath += tags[i];
			xpath += "[contains(normalize-space(.),\"";
			xpath += text;
			xpath += "\")]";

		}

		xpath += ")";

		By by = By.xpath(xpath);
		driver.findElement(by).click();
	}

	public void click_tableRow(String text) {

		String xpath = "(";

		xpath += "//td[(.)/span[contains(normalize-space(.),\"" + text
				+ "\")]]";
		xpath += ")";

		By by = By.xpath(xpath);

		driver.findElement(by).click();

	}

	public void write_InElementWithID(String id, String arg1) {
		By element = By.id(id);

		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(arg1);

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
		// Parameter entfernen
		String actualUrl = driver.getCurrentUrl();

		if (actualUrl.contains("?")) {
			int index = actualUrl.indexOf("?");
			actualUrl = actualUrl.substring(0, index);
		}

		assertFalse(getFullUrl(url).equals(actualUrl));
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

	public SeleniumTestMethods() {
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void check_errorMessage_BY_CssAlertDanger(String message) {
		assertEquals(message,
				driver.findElement(By.cssSelector("div.alert.alert-danger"))
						.getText());
	}

	public String getText_BY(By element) {
		return driver.findElement(element).getText();
	}

	public void assert_ElementText(String arg1, By by) {
		WebElement element = driver.findElement(by);

		assertEquals(arg1, element.getText());
	}

	public void assert_ElementText_By_Id(String arg1, String id) {
		assert_ElementText(arg1, By.id(id));
	}

	public void login(String arg1, String arg2) {
		goToSite("startPage");

		write_InElementWithID("username", arg1);
		write_InElementWithID("password", arg2);

		click_ElementWithText("SIGN IN");

	}

	public void profile_over_startpage() {
		goToSite("startPage");
		click_ElementWithText("Profil");
	}

	public boolean isElementWithTextOnThePage(String text) {

		String xpath = "(";
		xpath += "//*[contains(normalize-space(.),\"" + text + "\")]";
		xpath += ")";

		By by = By.xpath(xpath);

		List<WebElement> findElements = driver.findElements(by);

		if (findElements != null && findElements.size() > 0) {
			return true;
		}

		return false;
	}

	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
