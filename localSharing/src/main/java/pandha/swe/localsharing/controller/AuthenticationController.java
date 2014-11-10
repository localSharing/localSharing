package pandha.swe.localsharing.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pandha.swe.localsharing.dto.BenutzerRegisterDto;
import pandha.swe.localsharing.service.BenutzerService;

@Controller
public class AuthenticationController {

	@Autowired
	private BenutzerService benutzerService;

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(
			@RequestParam(value = "name", required = false, defaultValue = "Benutzer") String name,
			Model model) {
		model.addAttribute("name", name);

		return "login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public String registerForm(Model model) {
		model.addAttribute("newUser", new BenutzerRegisterDto());
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public String submitRegisterForm(
			@ModelAttribute("newUser") @Valid BenutzerRegisterDto newUser,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "register";
		} else if (!newUser.getPassword1().equals(newUser.getPassword2())) {
			result.addError(new ObjectError("password1",
					"Passwörter stimmen nicht überein"));
			return "register";
		}

		benutzerService.registerBenutzer(newUser);

		model.addAttribute("message",
				"Successfully saved person: " + newUser.toString());
		return "register";
	}
}
