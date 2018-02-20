
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;
import forms.AdministratorForm;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private AdministratorService	administratorService;


	//Constructor--------------------------------------------------------

	public AdministratorController() {
		super();
	}

	//Create------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Administrator administrator;

		administrator = this.administratorService.create();
		Assert.notNull(administrator);
		result = this.createEditModelAndView(administrator);
		return result;
	}

	//Edition------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);
		result = this.createEditModelAndView(administrator);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(AdministratorForm administratorForm, final BindingResult bindingResult) {
		ModelAndView result;
		final Administrator administrator;

		administratorForm = this.administratorService.reconstruct(administratorForm, bindingResult);
		administrator = administratorForm.getAdministrator();
		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(administrator);
		else
			try {
				Assert.isTrue(administratorForm.getAdministrator().getUserAccount().getPassword().equals(administratorForm.getPasswordCheck()), "password does not match");
				this.administratorService.save(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("password does not match"))
					result = this.createEditModelAndView(administrator, "administrator.commit.error.passwordDoesNotMatch");
				if (oops.getMessage().equals("could not execute statement; SQL [n/a]; constraint [null]" + "; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"))
					result = this.createEditModelAndView(administrator, "administrator.commit.error.duplicateProfile");
				else
					result = this.createEditModelAndView(administrator, "administrator.commit.error");
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Administrator administrator) {

		ModelAndView result;
		result = this.createEditModelAndView(administrator, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String message) {
		AdministratorForm administratorForm;
		ModelAndView result;

		administratorForm = new AdministratorForm();
		administratorForm.setAdministrator(administrator);
		result = new ModelAndView("administrator/edit");
		result.addObject("administratorForm", administratorForm);
		result.addObject("message", message);

		return result;

	}

}
