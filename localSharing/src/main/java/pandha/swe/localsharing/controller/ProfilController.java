package pandha.swe.localsharing.controller;

import static pandha.swe.localsharing.util.VornameAngebotsseiteWandler.erzeugeVornameFuerAngebotsseite;

import java.security.Principal;
import java.util.List;

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
import pandha.swe.localsharing.model.Bewertung;
import pandha.swe.localsharing.model.dto.BenutzerDTO;
import pandha.swe.localsharing.model.dto.BewertungDTO;
import pandha.swe.localsharing.model.enums.Rollen;
import pandha.swe.localsharing.service.AngebotService;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.BewertungService;
import pandha.swe.localsharing.service.FileService;

@Controller
public class ProfilController {

	@Autowired
	private AngebotService angebotService;

	@Autowired
	private BenutzerService benutzerService;

	@Autowired
	private BewertungService bewertungService;

	@Autowired
	private FileService fileService;

	@RequestMapping(method = RequestMethod.GET, value = "/profil")
	public String showProfil(Model model, Principal principal) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);
		return "redirect:/profil/" + user.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/profil/{id}")
	public String showProfilAndererUser(Model model, Principal principal,
			@PathVariable("id") String id) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);
		BenutzerDTO userDTO;

		if (Long.valueOf(id).equals(user.getId())) {
			userDTO = benutzerService.benutzer_TO_BenutzerDTO(user);
			model.addAttribute("besitzer", true);
			model.addAttribute("titel", "Dein");
		}

		else {
			userDTO = benutzerService.benutzer_TO_BenutzerDTO(benutzerService
					.findById(Long.valueOf(id)));
			model.addAttribute("besitzer", false);
			model.addAttribute("titel",
					erzeugeVornameFuerAngebotsseite(userDTO.getVorname()));
		}

		model.addAttribute("user", userDTO);
		List<Bewertung> bewertungen = bewertungService
				.findAllByEmpfaengerId(Long.valueOf(id));
		List<BewertungDTO> bewertungenDTO = bewertungService
				.list_Bewertung_TO_BewertungDTO(bewertungen);

		model.addAttribute("bewertungen", bewertungenDTO);
		return "profil";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/profilEdit")
	public String showProfilEdit(Model model, Principal principal) {

		BenutzerDTO user = benutzerService
				.benutzer_TO_BenutzerDTO(benutzerService
						.getUserByPrincipal(principal));

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

	@RequestMapping(method = RequestMethod.GET, value = "/disable/{id}")
	public String disableUser(Principal principal, @PathVariable("id") String id) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		if (!benutzerService.hatBenutzerRolle(user, Rollen.ADMIN)) {
			return "redirect:../profil/" + id;
		}

		Benutzer userToDisable = benutzerService.findById(Long.valueOf(id));
		if (userToDisable == null) {
			return "redirect:../startPage";
		}

		userToDisable.setEnabled(Boolean.FALSE);
		benutzerService.update(userToDisable);

		return "redirect:../profil/" + id;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/enable/{id}")
	public String enableUser(Principal principal, @PathVariable("id") String id) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		if (!benutzerService.hatBenutzerRolle(user, Rollen.ADMIN)) {
			return "redirect:../profil/" + id;
		}

		Benutzer userToEnable = benutzerService.findById(Long.valueOf(id));
		if (userToEnable == null) {
			return "redirect:../startPage";
		}

		userToEnable.setEnabled(Boolean.TRUE);
		benutzerService.update(userToEnable);

		return "redirect:../profil/" + id;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/profile")
	public String showAllUsers(Principal principal, Model model) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		if (!benutzerService.hatBenutzerRolle(user, Rollen.ADMIN)) {
			return "redirect:../startPage";
		}

		model.addAttribute("userList", benutzerService.findAllDTO());

		return "profile";
	}

}