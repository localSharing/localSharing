package pandha.swe.localsharing.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;
import pandha.swe.localsharing.service.BenutzerService;

@Controller
public class LoginController {

	@Autowired
	private BenutzerService benutzerService;

	@RequestMapping("/login")
	public String login(
			@RequestParam(value = "name", required = false, defaultValue = "Benutzer") String name,
			Model model) {
		model.addAttribute("name", name);

		Set<BenutzerRolle> rolle = new HashSet<>();
		Benutzer test = new Benutzer(0, "1234", true, Benutzer.Geschlecht.MANN,
				"Test", "TestNachname", "perter", "23", "69115", "heidelberg",
				"user@123.de", "064545", rolle);
		rolle.add(new BenutzerRolle(0, test, BenutzerRolle.Rollen.ADMIN));
		rolle.add(new BenutzerRolle(0, test, BenutzerRolle.Rollen.USER));
		benutzerService.save(test);

		return "login";
	}

}
