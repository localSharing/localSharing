package pandha.swe.localsharing.cucumber.stepDefs;

import pandha.swe.localsharing.SeleniumTestMethods;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs_ProfilinformationenBearbeiten {

	private SeleniumTestMethods method = new SeleniumTestMethods();

	@Given("^I am logged in as \"([^\"]*)\" with password \"([^\"]*)\"$")
	public void I_am_logged_in_as_with_password(String arg1, String arg2)
			throws Throwable {
		method.login(arg1, arg2);
	}

	@Given("^I am on the start page$")
	public void I_am_on_the_start_page() throws Throwable {
		method.goToSite("startPage");
	}

	@When("^I click on \"([^\"]*)\" on the start page$")
	public void I_click_on_on_the_start_page(String arg1) throws Throwable {
		switch (arg1) {
		case "Mein Profil":
			method.startpage_click_meinprofil();
			break;

		default:
			break;
		}
	}

	@When("^I click on \"([^\"]*)\" on my profil page$")
	public void I_click_on_on_my_profil_page(String arg1) throws Throwable {
		switch (arg1) {
		case "Bearbeiten":
			method.profil_click_bearbeiten();
			break;

		default:
			break;
		}
	}

	@When("^I change my firstname to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_firstname_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.profiledit_write_vorname(arg1);
	}

	@When("^I change my surname to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_surname_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.profiledit_write_nachname(arg1);
	}

	@When("^I change my city to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_city_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.profiledit_write_stadt(arg1);
	}

	@When("^I change my street number to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_street_number_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.profiledit_write_hausnummer(arg1);
	}

	@When("^I change my phone number to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_phone_number_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.profiledit_write_tel(arg1);
	}

	@When("^I change my zip code to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_zip_code_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.profiledit_write_plz(arg1);
	}

	@When("^I change my street to \"([^\"]*)\" on my edit profil page$")
	public void I_change_my_street_to_on_my_edit_profil_page(String arg1)
			throws Throwable {
		method.profiledit_write_strasse(arg1);
	}

	@When("^I click on \"([^\"]*)\" on my edit profil page$")
	public void I_click_on_on_my_edit_profil_page(String arg1) throws Throwable {
		switch (arg1) {
		case "Speichern":
			method.profiledit_click_save();
			break;
		case "Abbrechen":
			method.profiledit_click_abbrechen();
			break;
		default:
			break;
		}
	}

	@Then("^I can see my profile page$")
	public void I_can_see_my_profile_page() throws Throwable {
		method.checkPage("profil");
	}

	@Then("^I can see my firstname is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_firstname_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.profil_check_vorname(arg1);
	}

	@Then("^I can see my surname is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_surname_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.profil_check_nachname(arg1);
	}

	@Then("^I can see my city is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_city_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.profil_check_stadt(arg1);
	}

	@Then("^I can see my street number is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_street_number_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.profil_check_hausnummer(arg1);
	}

	@Then("^I can see my phone number is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_phone_number_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.profil_check_tel(arg1);
	}

	@Then("^I can see my street is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_street_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.profil_check_strasse(arg1);
	}

	@Then("^I can see my zip code is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_zip_code_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.profil_check_plz(arg1);
	}

	@Then("^I can see my email is \"([^\"]*)\" on my profil page$")
	public void I_can_see_my_email_is_on_my_profil_page(String arg1)
			throws Throwable {
		method.profil_check_email(arg1);
	}

	@Then("^I can see my edited Information has been saved$")
	public void I_can_see_my_edited_Information_has_been_saved()
			throws Throwable {
		method.profile_over_startpage();
	}

}
