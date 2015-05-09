package pandha.swe.localsharing.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.service.BenutzerService;

@Controller
public class StartPageController {
	
	@Autowired
	private BenutzerService benutzerService;
	
	@RequestMapping("/startPage")
	public String startPage(Principal principal, Model model) {
		Benutzer user = benutzerService.getUserByPrincipal(principal);
		model.addAttribute("userid", user.getId());
		return "startPage";
	}

}
