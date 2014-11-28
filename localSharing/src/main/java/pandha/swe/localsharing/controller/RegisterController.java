package pandha.swe.localsharing.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.BenutzerRegisterDTO;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.FileService;

@Controller
public class RegisterController {

	@Autowired
	private BenutzerService benutzerService;

	@Autowired
	private FileService fileService;

	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public String registerForm(Model model) {
		model.addAttribute("newUser", new BenutzerRegisterDTO());
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public String submitRegisterForm(
			@ModelAttribute("newUser") @Valid BenutzerRegisterDTO newUser,
			BindingResult result,
			Model model,
			@RequestParam(value = "userImage", required = false) MultipartFile image) {

		if (result.hasErrors()) {
			return "register";
		} else if (!newUser.getPassword1().equals(newUser.getPassword2())) {
			result.rejectValue("password1", "error.user",
					"Passwörter stimmen nicht überein!");

			return "register";
		}

		if (benutzerService.findByEmail(newUser.getEmail()) != null) {
			result.rejectValue("email", "error.user",
					"Email wird bereits verwendet!");
			return "register";
		}

		benutzerService.registerBenutzer(newUser);

		if (!image.isEmpty()) {

			Benutzer benutzer = benutzerService.findByEmail(newUser.getEmail());

			fileService.save(benutzer, image);

		}

		model.addAttribute("messageRegSuccess", "Benutzer wurde angelegt!");
		return "redirect:login";
	}

}
