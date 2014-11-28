package pandha.swe.localsharing.cucumber.stepDefs;

import pandha.swe.localsharing.SeleniumTestMethods;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs_Login {

	private SeleniumTestMethods method = new SeleniumTestMethods();

	@Given("^I am not logged in yet$")
	public void I_am_not_logged_in_yet() throws Throwable {
		method.checkLogoutAndIfLoggedinLogout();
	}

	@Given("^I am on the login Page$")
	public void I_am_on_the_login_Page() throws Throwable {
		method.goToSite("startPage");
		method.checkPage("login");
	}

	@When("^I enter \"([^\"]*)\" as email$")
	public void I_enter_as_email(String email) throws Throwable {
		method.login_writeUsername(email);
	}

	@When("^I enter \"([^\"]*)\" as Password$")
	public void I_enter_as_Password(String password) throws Throwable {
		method.login_writePassword(password);
	}

	@When("^I click on \"([^\"]*)\"$")
	public void I_click_on(String arg1) throws Throwable {

		switch (arg1) {
		case "Login":
			method.click_Button("btnLogin");
			break;

		case "Registrieren":
			method.click_Link(arg1);
			break;

		default:
			break;
		}

	}

	@Then("^I am a logged in user$")
	public void I_am_a_logged_in_user() throws Throwable {
		method.checkIfIamLoggedIn();
	}

	@Then("^I can see the mainpage$")
	public void I_can_see_the_mainpage() throws Throwable {
		method.checkPage("startPage");
	}

	@Then("^I am not logged in$")
	public void I_am_not_logged_in() throws Throwable {
		method.checkIfIamNotLoggedIn();
	}

	@Then("^I can try to login again$")
	public void I_can_try_to_login_again() throws Throwable {
		method.checkPage("login");
	}

	@When("^I do not enter any credentials$")
	public void I_do_not_enter_any_credentials() throws Throwable {
		method.login_writeUsername("");
		method.login_writePassword("");
	}

	@Then("^I can see the register page$")
	public void I_can_see_the_register_page() throws Throwable {
		method.checkPage("register");
	}
}
