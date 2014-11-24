package pandha.swe.localsharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartPageController {

	@RequestMapping("/startPage")
	public String startPage() {

		return "startPage";
	}

}
