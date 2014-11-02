package pandha.swe.localsharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StartPageController {

	@RequestMapping("/startPage")
	public String startPage(
			@RequestParam(value = "name", required = false, defaultValue = "Benutzer") String name,
			Model model) {
		model.addAttribute("name", name);

		return "startPage";
	}

}
