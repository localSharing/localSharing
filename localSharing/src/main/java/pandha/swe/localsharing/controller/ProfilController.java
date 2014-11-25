package pandha.swe.localsharing.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.FileService;

@Controller
public class ProfilController {

	@Autowired
	private BenutzerService benutzerService;

	@Autowired
	private FileService fileService;

	@RequestMapping(method = RequestMethod.GET, value = "/profil")
	public String showProfil(Model model, Principal principal) {

		Benutzer user = getUser(principal);

		model.addAttribute("user", user);
		return "profil";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/profilEdit")
	public String showProfilEdit(Model model, Principal principal) {

		Benutzer user = getUser(principal);

		model.addAttribute("user", user);
		return "profilEdit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/profilEdit")
	public String editProfil(Model model, Principal principal,
			@RequestParam(value = "image", required = false) MultipartFile image) {

		Benutzer user = getUser(principal);

		// benutzerService.update(user);
		if (!image.isEmpty()) {
			fileService.save(user, image);
		}

		return "profilEdit";
	}

	private Benutzer getUser(Principal principal) {
		String email = principal.getName();

		Benutzer user = benutzerService.findByEmail(email);

		return user;
	}
}
