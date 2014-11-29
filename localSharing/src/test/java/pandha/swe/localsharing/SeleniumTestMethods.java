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

	public void register_write_firstname(String arg1) {
		driver.findElement(By.id("vorname")).clear();
		driver.findElement(By.id("vorname")).sendKeys(arg1);
	}

	public void register_write_surname(String arg1) {
		driver.findElement(By.id("nachname")).clear();
		driver.findElement(By.id("nachname")).sendKeys(arg1);
	}

	public void register_write_street(String arg1) {
		driver.findElement(By.id("strasse")).clear();
		driver.findElement(By.id("strasse")).sendKeys(arg1);
	}

	public void register_write_housnumber(String arg1) {
		driver.findElement(By.id("hausnummer")).clear();
		driver.findElement(By.id("hausnummer")).sendKeys(arg1);
	}

	public void register_write_zipcode(String arg1) {
		driver.findElement(By.id("plz")).clear();
		driver.findElement(By.id("plz")).sendKeys(arg1);
	}

	public void register_write_city(String arg1) {
		driver.findElement(By.id("stadt")).clear();
		driver.findElement(By.id("stadt")).sendKeys(arg1);
	}

	public void register_write_phone(String arg1) {
		driver.findElement(By.id("telefonNummer")).clear();
		driver.findElement(By.id("telefonNummer")).sendKeys(arg1);
	}

	public void register_write_email(String arg1) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(arg1);
	}

	public void register_write_password1(String arg1) {
		driver.findElement(By.id("password1")).clear();
		driver.findElement(By.id("password1")).sendKeys(arg1);
	}

	public void register_write_password2(String arg1) {
		driver.findElement(By.id("password2")).clear();
		driver.findElement(By.id("password2")).sendKeys(arg1);
	}

	public void register_select_gender(String arg1) {

	}

	public void register_error_email() {
		assertEquals("Email wird bereits verwendet!",
				driver.findElement(By.cssSelector("div.alert.alert-danger"))
						.getText());
	}

	public void register_error_password() {

		assertEquals("Passwörter stimmen nicht überein!",
				driver.findElement(By.cssSelector("div.alert.alert-danger"))
						.getText());
	}

	public void register_click_register() {
		driver.findElement(By.id("btnRegister")).click();
	}

	public void profil_click_bearbeiten() {
		driver.findElement(By.id("btnProfilBearbeiten")).click();
	}

	public void profil_check_vorname(String string) {
		assertEquals(string, driver.findElement(By.cssSelector("span"))
				.getText());
	}

	public void profil_check_nachname(String string) {
		assertEquals(string, driver.findElement(By.xpath("//td[4]/span"))
				.getText());
	}

	public void profil_check_strasse(String string) {
		assertEquals(string, driver.findElement(By.xpath("//tr[2]/td[2]/span"))
				.getText());
	}

	public void profil_check_hausnummer(String string) {
		assertEquals(string, driver.findElement(By.xpath("//tr[2]/td[4]/span"))
				.getText());
	}

	public void profil_check_plz(String string) {
		assertEquals(string, driver.findElement(By.xpath("//tr[3]/td[2]/span"))
				.getText());
	}

	public void profil_check_stadt(String string) {
		assertEquals(string, driver.findElement(By.xpath("//tr[3]/td[4]/span"))
				.getText());
	}

	public void profil_check_tel(String string) {
		assertEquals(
				string,
				driver.findElement(
						By.xpath("//div[2]/table/tbody/tr/td[2]/span"))
						.getText());
	}

	public void profil_check_email(String string) {
		assertEquals(
				string,
				driver.findElement(
						By.xpath("//div[2]/table/tbody/tr[2]/td[2]/span"))
						.getText());
	}

	public void profiledit_click_abbrechen() {
		driver.findElement(By.id("btnCancel")).click();
	}

	public void profiledit_click_save() {
		driver.findElement(By.id("btnSave")).click();
	}

	public void profiledit_write_vorname(String string) {
		driver.findElement(By.id("vorname")).clear();
		driver.findElement(By.id("vorname")).sendKeys(string);
	}

	public void profiledit_write_nachname(String string) {
		driver.findElement(By.id("nachname")).clear();
		driver.findElement(By.id("nachname")).sendKeys(string);
	}

	public void profiledit_write_strasse(String string) {
		driver.findElement(By.id("strasse")).clear();
		driver.findElement(By.id("strasse")).sendKeys(string);
	}

	public void profiledit_write_hausnummer(String string) {
		driver.findElement(By.id("hausnummer")).clear();
		driver.findElement(By.id("hausnummer")).sendKeys(string);
	}

	public void profiledit_write_plz(String string) {
		driver.findElement(By.id("plz")).clear();
		driver.findElement(By.id("plz")).sendKeys(string);
	}

	public void profiledit_write_stadt(String string) {
		driver.findElement(By.id("stadt")).clear();
		driver.findElement(By.id("stadt")).sendKeys(string);
	}

	public void profiledit_write_tel(String string) {
		driver.findElement(By.id("telefonNummer")).clear();
		driver.findElement(By.id("telefonNummer")).sendKeys(string);
	}

	public void profiledit_write_email(String string) {

	}

	public void login(String arg1, String arg2) {
		goToSite("startPage");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(arg1);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(arg2);

		driver.findElement(By.id("btnLogin")).click();

	}

	public void startpage_click_meinprofil() {
		checkPage("startPage");
		driver.findElement(By.id("btnProfil")).click();
	}

	public void profile_over_startpage() {
		goToSite("startPage");
		startpage_click_meinprofil();
	}

}
