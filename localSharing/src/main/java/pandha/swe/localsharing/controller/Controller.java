package pandha.swe.localsharing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;

public class Controller extends AbstractController {

	private final String requestUrl = "13";
	private String redirectError;
	private String reirectSuccess;

	private Controller() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = requestUrl)
	public String handleRequest() {

		ModelAndView view = new ModelAndView();

		AusleihartikelDTO object = (AusleihartikelDTO) view.getModel().get(
				"angebot");

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		validator.validate(object, null);

		return getRedirectError();

	}

	public String getRedirectError() {
		return redirectError;
	}

	public void setRedirectError(String redirectError) {
		this.redirectError = redirectError;
	}

	public String getReirectSuccess() {
		return reirectSuccess;
	}

	public void setReirectSuccess(String reirectSuccess) {
		this.reirectSuccess = reirectSuccess;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
