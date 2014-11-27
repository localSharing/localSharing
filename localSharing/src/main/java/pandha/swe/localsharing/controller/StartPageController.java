package pandha.swe.localsharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pandha.swe.localsharing.service.AusleihartikelService;

@Controller
public class StartPageController {
	
	@Autowired
	AusleihartikelService as;

	@RequestMapping("/startPage")
	public String startPage() {
		return "startPage";
	}

}
