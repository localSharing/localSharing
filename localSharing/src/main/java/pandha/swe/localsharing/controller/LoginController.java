package pandha.swe.localsharing.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value = "/login")
	public String getLoginPage(Principal user) {

		if (user != null) {
			return "redirect:startPage";
		} else {
			return "login";
		}

	}

}
