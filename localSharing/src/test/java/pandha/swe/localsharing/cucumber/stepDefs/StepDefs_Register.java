package pandha.swe.localsharing.cucumber.stepDefs;

import pandha.swe.localsharing.SeleniumTestMethods;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs_Register {

	private SeleniumTestMethods method = new SeleniumTestMethods();

	@Given("^I am on the Register Page$")
	public void I_am_on_the_Register_Page() throws Throwable {
		method.checkLogoutAndIfLoggedinLogout();
		method.goToSite("register");
		method.checkPage("register");
	}

	@When("^I enter \"([^\"]*)\" as my first name$")
	public void I_enter_as_my_first_name(String arg1) throws Throwable {
		method.register_write_firstname(arg1);

	}

	@When("^I enter \"([^\"]*)\" as my surname$")
	public void I_enter_as_my_surname(String arg1) throws Throwable {
		method.register_write_surname(arg1);
	}

	@When("^I enter \"([^\"]*)\" as street$")
	public void I_enter_as_street(String arg1) throws Throwable {
		method.register_write_street(arg1);
	}

	@When("^I enter \"([^\"]*)\" as street number$")
	public void I_enter_as_street_number(String arg1) throws Throwable {
		method.register_write_housnumber(arg1);
	}

	@When("^I enter \"([^\"]*)\" as zip code$")
	public void I_enter_as_zip_code(String arg1) throws Throwable {
		method.register_write_zipcode(arg1);
	}

	@When("^I enter \"([^\"]*)\" as city$")
	public void I_enter_as_city(String arg1) throws Throwable {
		method.register_write_city(arg1);
	}

	@When("^I enter \"([^\"]*)\" as phone number$")
	public void I_enter_as_phone_number(String arg1) throws Throwable {
		method.register_write_phone(arg1);
	}

	@When("^I enter the password \"([^\"]*)\" twice$")
	public void I_enter_the_password_twice(String arg1) throws Throwable {
		method.register_write_password1(arg1);
		method.register_write_password2(arg1);
	}

	@Then("^I can see the login page$")
	public void I_can_see_the_login_page() throws Throwable {
		method.checkPage("login");
	}

	@Then("^I see a error message for already used emails$")
	public void I_see_a_error_message_for_already_used_emails()
			throws Throwable {
		method.register_error_email();
	}

	@Then("^I can try to register again$")
	public void I_can_try_to_register_again() throws Throwable {
		method.checkPage("register");
	}

	@When("^I enter the password \"([^\"]*)\" not twice$")
	public void I_enter_the_password_not_twice(String arg1) throws Throwable {
		method.register_write_password1(arg1);
		method.register_write_password2("anderesPasswort");
	}

	@Then("^I see a error message for wrong password$")
	public void I_see_a_error_message_for_wrong_password() throws Throwable {
		method.register_error_password();
	}

	@When("^I enter \"([^\"]*)\" as email$")
	public void I_enter_as_email(String email) throws Throwable {
		method.register_write_email(email);
	}

	@When("^I click on \"([^\"]*)\" button on the Register page$")
	public void I_click_on_button_on_the_Register_page(String arg1)
			throws Throwable {
		method.register_click_register();
	}

}
