package pandha.swe.localsharing.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.BenutzerDTO;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.FileService;

import static pandha.swe.localsharing.util.VornameAngebotsseiteWandler.erzeugeVornameFuerAngebotsseite;

@Controller
public class ProfilController {

	@Autowired
	private BenutzerService benutzerService;

	@Autowired
	private FileService fileService;

	@RequestMapping(method = RequestMethod.GET, value = "/profil")
	public String showProfil(Model model, Principal principal) {

		BenutzerDTO user = 
				benutzerService.benutzer_TO_BenutzerDTO(
						benutzerService.getUserByPrincipal(principal));

		model.addAttribute("user", user);
		model.addAttribute("besitzer", true);
		model.addAttribute("titel", "Dein");
		return "profil";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/profil/{id}")
	public String showProfilAndererUser(
			Model model,
			Principal principal,
			@PathVariable("id") String id) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);
		
		if (Long.valueOf(id).equals(user.getId())) {
			return "redirect:../profil";
		}
		
		BenutzerDTO gesuchterUser = 
				benutzerService.benutzer_TO_BenutzerDTO(
						benutzerService.findById(Long.valueOf(id)));
		if (gesuchterUser == null) {
			return "redirect:startPage";
		}
		model.addAttribute("besitzer", false);
		model.addAttribute("user", gesuchterUser);
		model.addAttribute("titel", erzeugeVornameFuerAngebotsseite(gesuchterUser.getVorname()));
		return "profil";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/profilEdit")
	public String showProfilEdit(Model model, Principal principal) {

		BenutzerDTO user = 
				benutzerService.benutzer_TO_BenutzerDTO(
						benutzerService.getUserByPrincipal(principal));

		model.addAttribute("user", user);
		return "profilEdit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/profilEdit")
	public String editProfil(
			@ModelAttribute("user") @Valid BenutzerDTO user,
			Principal principal,
			@RequestParam(value = "userImage", required = false) MultipartFile image) {

		Benutzer editedUser = benutzerService.benutzerDTO_TO_Benutzer(user,
				principal);

		benutzerService.update(editedUser);

		if (image != null && !image.isEmpty()) {
			fileService.save(editedUser, image);
		}

		return "redirect:profil";
	}

}