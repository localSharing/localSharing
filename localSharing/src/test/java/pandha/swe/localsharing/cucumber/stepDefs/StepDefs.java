package pandha.swe.localsharing.cucumber.stepDefs;

import static org.junit.Assert.assertEquals;
import pandha.swe.localsharing.SeleniumTestMethods;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {

	private SeleniumTestMethods method = new SeleniumTestMethods();

	@Given("^I am not logged in yet$")
	public void I_am_not_logged_in_yet() throws Throwable {
		method.checkLogoutAndIfLoggedinLogout();
	}

	@Then("^I am not logged in$")
	public void I_am_not_logged_in() throws Throwable {
		method.checkLogoutAndIfLoggedinLogout();
	}

	@Given("^I am on the login Page$")
	public void I_am_on_the_login_Page() throws Throwable {
		method.goToSite("startPage");
		method.checkPage("login");
	}

	@When("^I enter \"([^\"]*)\" as username$")
	public void I_enter_as_username(String email) throws Throwable {
		method.write_InElementWithID("username", email);
	}

	@When("^I enter \"([^\"]*)\" as Password$")
	public void I_enter_as_Password(String password) throws Throwable {
		method.write_InElementWithID("password", password);
	}

	@When("^I click on \"([^\"]*)\"$")
	public void I_click_on(String arg1) throws Throwable {

		method.click_ElementWithText(arg1);

	}

	@Then("^I can see the mainpage$")
	public void I_can_see_the_mainpage() throws Throwable {
		method.checkPage("startPage");
	}

	@Then("^I can try to login again$")
	public void I_can_try_to_login_again() throws Throwable {
		method.checkPage("login");
	}

	@When("^I do not enter any credentials$")
	public void I_do_not_enter_any_credentials() throws Throwable {
		method.write_InElementWithID("username", "");
		method.write_InElementWithID("password", "");
	}

	@Then("^I can see the register page$")
	public void I_can_see_the_register_page() throws Throwable {
		method.checkPage("register");
	}

	@Given("^I am on the Register Page$")
	public void I_am_on_the_Register_Page() throws Throwable {
		method.checkLogoutAndIfLoggedinLogout();
		method.goToSite("register");
		method.checkPage("register");
	}

	@When("^I enter \"([^\"]*)\" as my first name$")
	public void I_enter_as_my_first_name(String arg1) throws Throwable {
		method.write_InElementWithID("vorname", arg1);

	}

	@When("^I enter \"([^\"]*)\" as my surname$")
	public void I_enter_as_my_surname(String arg1) throws Throwable {
		method.write_InElementWithID("nachname", arg1);
	}

	@When("^I enter \"([^\"]*)\" as street$")
	public void I_enter_as_street(String arg1) throws Throwable {
		method.write_InElementWithID("strasse", arg1);
	}

	@When("^I enter \"([^\"]*)\" as street number$")
	public void I_enter_as_street_number(String arg1) throws Throwable {
		method.write_InElementWithID("hausnummer", arg1);
	}

	@When("^I enter \"([^\"]*)\" as zip code$")
	public void I_enter_as_zip_code(String arg1) throws Throwable {
		method.write_InElementWithID("plz", arg1);
	}

	@When("^I enter \"([^\"]*)\" as city$")
	public void I_enter_as_city(String arg1) throws Throwable {
		method.write_InElementWithID("stadt", arg1);
	}

	@When("^I enter \"([^\"]*)\" as phone number$")
	public void I_enter_as_phone_number(String arg1) throws Throwable {
		method.write_InElementWithID("telefonNummer", arg1);
	}

	@When("^I enter the password \"([^\"]*)\" twice$")
	public void I_enter_the_password_twice(String arg1) throws Throwable {
		method.write_InElementWithID("password1", arg1);
		method.write_InElementWithID("password2", arg1);
	}

	@Then("^I can see the login page$")
	public void I_can_see_the_login_page() throws Throwable {
		method.checkPage("login");
	}

	@Then("^I see a error message for already used emails$")
	public void I_see_a_error_message_for_already_used_emails()
			throws Throwable {
		method.check_errorMessage_BY_CssAlertDanger("Email wird bereits verwendet!");
	}

	@Then("^I can try to register again$")
	public void I_can_try_to_register_again() throws Throwable {
		method.checkPage("register");
	}

	@When("^I enter the password \"([^\"]*)\" not twice$")
	public void I_enter_the_password_not_twice(String arg1) throws Throwable {
		method.write_InElementWithID("password1", arg1);
		method.write_InElementWithID("password2", "anderesPasswort");
	}

	@Then("^I see a error message for wrong password$")
	public void I_see_a_error_message_for_wrong_password() throws Throwable {
		method.check_errorMessage_BY_CssAlertDanger("Passwörter stimmen nicht überein!");
	}

	@When("^I enter \"([^\"]*)\" as email$")
	public void I_enter_as_email(String email) throws Throwable {
		method.write_InElementWithID("email", email);
	}

	@When("^I click on \"([^\"]*)\" button on the Register page$")
	public void I_click_on_button_on_the_Register_page(String arg1)
			throws Throwable {
		method.click_ElementWithText(arg1);
	}

	@Given("^I am logged in as \"([^\"]*)\" with password \"([^\"]*)\"$")
	public void I_am_logged_in_as_with_password(String arg1, String arg2)
			throws Throwable {
		method.checkLogoutAndIfLoggedinLogout();
		method.login(arg1, arg2);
	}

	@Given("^I am on the start page$")
	public void I_am_on_the_start_page() throws Throwable {
		method.goToSite("startPage");
	}

	@When("^I click on \"([^\"]*)\" on the start page$")
	public void I_click_on_on_the_start_page(String arg1) throws Throwable {
		method.checkPage("startPage");
		method.click_ElementWithText(arg1);
	}

	@When("^I click on \"([^\"]*)\" on my profil page$")
	public void I_click_on_on_my_profil_page(String arg1) throws Throwable {
		method.click_ElementWithText(arg1);
	}

	@When("^I change my firstname to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_firstname_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("vorname", arg1);
	}

	@When("^I change my surname to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_surname_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("nachname", arg1);
	}

	@When("^I change my city to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_city_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("stadt", arg1);
	}

	@When("^I change my street number to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_street_number_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("hausnummer", arg1);
	}

	@When("^I change my phone number to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_phone_number_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("telefonNummer", arg1);
	}

	@When("^I change my zip code to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_zip_code_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("plz", arg1);
	}

	@When("^I change my street to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_street_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("strasse", arg1);
	}

	@When("^I click on \"([^\"]*)\" on my edit profil page$")
	public void I_click_on_on_my_edit_profil_page(String arg1) throws Throwable {
		method.click_ElementWithText(arg1);
	}

	@Then("^I can see my profile page$")
	public void I_can_see_my_profile_page() throws Throwable {
		method.checkPage("profil");
	}

	@Then("^I can see my firstname is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_firstname_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.assert_ElementText_By_Id(arg1, "vorname");
	}

	@Then("^I can see my surname is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_surname_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.assert_ElementText_By_Id(arg1, "nachname");
	}

	@Then("^I can see my city is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_city_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.assert_ElementText_By_Id(arg1, "stadt");
	}

	@Then("^I can see my street number is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_street_number_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.assert_ElementText_By_Id(arg1, "hausnummer");
	}

	@Then("^I can see my phone number is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_phone_number_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.assert_ElementText_By_Id(arg1, "telefon");
	}

	@Then("^I can see my street is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_street_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.assert_ElementText_By_Id(arg1, "strasse");
	}

	@Then("^I can see my zip code is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_zip_code_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.assert_ElementText_By_Id(arg1, "plz");
	}

	@Then("^I can see my email is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_email_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.assert_ElementText_By_Id(arg1, "email");
	}

	@Then("^I can see my edited Information has been saved$")
	public void I_can_see_my_edited_Information_has_been_saved()
			throws Throwable {
		method.profile_over_startpage();
	}

	@When("^I click on \"([^\"]*)\" on my offer page$")
	public void I_click_on_on_my_offer_page(String arg1) throws Throwable {
		method.click_ElementWithText(arg1);
	}

	@When("^I enter \"([^\"]*)\" as titel$")
	public void I_enter_as_titel(String arg1) throws Throwable {
		method.write_InElementWithID("titel", arg1);
	}

	@When("^I choose \"([^\"]*)\" as startdate on the new offer page$")
	public void I_choose_as_startdate_on_the_new_offer_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("startDatum", arg1);
	}

	@When("^I enter \"([^\"]*)\" as description$")
	public void I_enter_as_description(String arg1) throws Throwable {
		method.write_InElementWithID("beschreibung", arg1);
	}

	@Then("^I can see my offer page$")
	public void I_can_see_my_offer_page() throws Throwable {
		method.checkPage("angebote/3");
	}

	@When("^I click on \"([^\"]*)\" on the new offer page$")
	public void I_click_on_on_the_new_offer_page(String arg1) throws Throwable {

		method.click_ElementWithText(arg1);

	}

	@When("^I click on \"([^\"]*)\" on the offer page$")
	public void I_click_on_on_the_offer_page(String arg1) throws Throwable {

		method.click_ElementWithText(arg1);
	}

	@When("^I enter \"([^\"]*)\" as category on the new borrow offer page$")
	public void I_enter_as_category_on_the_new_borrow_offer_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("kategorie", arg1);
	}

	@When("^I choose \"([^\"]*)\" as end date on the new borrow offer page$")
	public void I_choose_as_end_date_on_the_new_borrow_offer_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("endDatum", arg1);
	}

	@When("^I enter \"([^\"]*)\" days as duration on the new borrow offer page$")
	public void I_enter_days_as_duration_on_the_new_borrow_offer_page(
			String arg1) throws Throwable {
		method.write_InElementWithID("dauer", arg1);
	}

	@When("^I change the title to \"([^\"]*)\" on the borrow offer edit page$")
	public void I_change_the_title_to_on_the_borrow_offer_edit_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("titel", arg1);
	}

	@When("^I change the description to \"([^\"]*)\" on the borrow offer edit page$")
	public void I_change_the_description_to_on_the_borrow_offer_edit_page(
			String arg1) throws Throwable {
		method.write_InElementWithID("beschreibung", arg1);
	}

	@When("^I change the startdate to \"([^\"]*)\" on the borrow offer edit page$")
	public void I_change_the_startdate_to_on_the_borrow_offer_edit_page(
			String arg1) throws Throwable {
		method.write_InElementWithID("startDatum", arg1);
	}

	@When("^I change the endate to \"([^\"]*)\" on the borrow offer edit page$")
	public void I_change_the_endate_to_on_the_borrow_offer_edit_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("endDatum", arg1);
	}

	@When("^I change the duration to \"([^\"]*)\" days on the borrow offer edit page$")
	public void I_change_the_duration_to_days_on_the_borrow_offer_edit_page(
			String arg1) throws Throwable {
		method.write_InElementWithID("dauer", arg1);
	}

	@When("^I click on \"([^\"]*)\" on the edit offer page$")
	public void I_click_on_on_the_edit_offer_page(String arg1) throws Throwable {
		method.click_ElementWithText(arg1);

	}

	@Then("^I can see a borrow offer with \"([^\"]*)\" as title on my offer page$")
	public void I_can_see_a_borrow_offer_with_as_title_on_my_offer_page(
			String arg1) throws Throwable {

		assertEquals(true, method.isElementWithTextOnThePage(arg1));

	}

	@When("^I click on the borrow offer with \"([^\"]*)\" as title on my offer page$")
	public void I_click_on_the_borrow_offer_with_as_title_on_my_offer_page(
			String arg1) throws Throwable {

		method.click_tableRow(arg1);

	}

	@Then("^I can not see a borrow offer with \"([^\"]*)\" as title on my offer page$")
	public void I_can_not_see_a_borrow_offer_with_as_title_on_my_offer_page(
			String arg1) throws Throwable {
		assertEquals(false, method.isElementWithTextOnThePage(arg1));
	}

	@Before
	public void up() throws Exception {
		method.setUp();
	}

	@After
	public void tearDown() throws Exception {
		method.tearDown();
	}

	@When("^I choose \"([^\"]*)\" as end date on the new service offer page$")
	public void I_choose_as_end_date_on_the_new_service_offer_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("endDatum", arg1);
	}

	@Then("^I can see a service offer with \"([^\"]*)\" as title on my offer page$")
	public void I_can_see_a_service_offer_with_as_title_on_my_offer_page(
			String arg1) throws Throwable {
		assertEquals(true, method.isElementWithTextOnThePage(arg1));
	}

	@When("^I click on the service offer with \"([^\"]*)\" as title on my offer page$")
	public void I_click_on_the_service_offer_with_as_title_on_my_offer_page(
			String arg1) throws Throwable {
		method.click_tableRow(arg1);
	}

	@When("^I change the title to \"([^\"]*)\" on the service offer edit page$")
	public void I_change_the_title_to_on_the_service_offer_edit_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("titel", arg1);
	}

	@When("^I change the description to \"([^\"]*)\" on the service offer edit page$")
	public void I_change_the_description_to_on_the_service_offer_edit_page(
			String arg1) throws Throwable {
		method.write_InElementWithID("beschreibung", arg1);
	}

	@When("^I change the startdate to \"([^\"]*)\" on the service offer edit page$")
	public void I_change_the_startdate_to_on_the_service_offer_edit_page(
			String arg1) throws Throwable {
		method.write_InElementWithID("startDatum", arg1);
	}

	@When("^I change the endate to \"([^\"]*)\" on the service offer edit page$")
	public void I_change_the_endate_to_on_the_service_offer_edit_page(
			String arg1) throws Throwable {
		method.write_InElementWithID("endDatum", arg1);
	}

	@Then("^I can not see a service offer with \"([^\"]*)\" as title on my offer page$")
	public void I_can_not_see_a_service_offer_with_as_title_on_my_offer_page(
			String arg1) throws Throwable {

		assertEquals(false, method.isElementWithTextOnThePage(arg1));
	}

	@Then("^I can see a exchange offer with \"([^\"]*)\" as title on my offer page$")
	public void I_can_see_a_exchange_offer_with_as_title_on_my_offer_page(
			String arg1) throws Throwable {
		assertEquals(true, method.isElementWithTextOnThePage(arg1));
	}

	@When("^I click on the exchange offer with \"([^\"]*)\" as title on my offer page$")
	public void I_click_on_the_exchange_offer_with_as_title_on_my_offer_page(
			String arg1) throws Throwable {
		method.click_tableRow(arg1);
	}

	@When("^I change the title to \"([^\"]*)\" on the exchange offer edit page$")
	public void I_change_the_title_to_on_the_exchange_offer_edit_page(
			String arg1) throws Throwable {
		method.write_InElementWithID("titel", arg1);
	}

	@When("^I change the description to \"([^\"]*)\" on the exchange offer edit page$")
	public void I_change_the_description_to_on_the_exchange_offer_edit_page(
			String arg1) throws Throwable {
		method.write_InElementWithID("beschreibung", arg1);
	}

	@When("^I change the startdate to \"([^\"]*)\" on the exchange offer edit page$")
	public void I_change_the_startdate_to_on_the_exchange_offer_edit_page(
			String arg1) throws Throwable {
		method.write_InElementWithID("startDatum", arg1);
	}

	@Then("^I can not see a exchange offer with \"([^\"]*)\" as title on my offer page$")
	public void I_can_not_see_a_exchange_offer_with_as_title_on_my_offer_page(
			String arg1) throws Throwable {
		assertEquals(false, method.isElementWithTextOnThePage(arg1));
	}

	@When("^I enter \"([^\"]*)\" as category on the new exchange offer page$")
	public void I_enter_as_category_on_the_new_exchange_offer_page(String arg1)
			throws Throwable {
		method.write_InElementWithID("kategorie", arg1);
	}

}
