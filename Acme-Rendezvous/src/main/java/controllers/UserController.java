
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private UserService	userService;


	//Constructor--------------------------------------------------------

	public UserController() {
		super();
	}

	//Listing-----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<User> users;

		users = this.userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("users", users);
		result.addObject("requestURI", "user/list.do");

		return result;

	}

	//Displaying----------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int userId) {

		ModelAndView result;
		User user;

		user = this.userService.findOne(userId);

		result = new ModelAndView("user/display");
		result.addObject("user", user);
		result.addObject("requestURI", "user/display.do");

		return result;
	}

	//Create----------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createUser() {
		ModelAndView result;
		User user;

		user = this.userService.create();

		UserForm cf;
		cf = new UserForm(user);

		result = new ModelAndView("user/edit");
		result.addObject("userForm", cf);

		return result;
	}

	//Edition------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		User user;

		user = this.userService.findByPrincipal();
		UserForm userForm;
		userForm = new UserForm(user);
		result = new ModelAndView("user/edit");
		result.addObject("userForm", userForm);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCustomer(@ModelAttribute("userForm") UserForm userForm, final BindingResult binding) {
		ModelAndView result;
		User user;

		userForm = this.userService.reconstruct(userForm, binding);
		user = userForm.getUser();

		if (user.getId() == 0 && !userForm.getPasswordCheck().equals(userForm.getUser().getUserAccount().getPassword())) {
			result = new ModelAndView("user/edit");
			result.addObject("userForm", userForm);
			result.addObject("message", "user.password.match");

		} else if (binding.hasErrors()) {
			result = new ModelAndView("user/edit");
			result.addObject("userForm", userForm);
		} else if (userForm.getConditions() != null && !userForm.getConditions() && user.getId() == 0) {
			result = new ModelAndView("user/edit");
			result.addObject("userForm", userForm);
			result.addObject("message", "actor.conditions.accept");
		} else
			try {
				//Codificación del password a MD5
				if (user.getId() != 0) {
					User u;
					User userSaved;
					u = this.userService.reconstructPass(user, binding);
					userSaved = this.userService.save(u);
					result = new ModelAndView("redirect:/");
				} else {
					User u;
					u = this.userService.save(user);

					result = new ModelAndView("redirect:/security/login.do");
				}
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("could not execute statement; SQL [n/a]; constraint [null]" + "; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"))
					result = this.createEditModelAndView(user, "user.commit.error.duplicateProfile");
				else {
					result = new ModelAndView("user/edit");
					result.addObject("userForm", userForm);
					result.addObject("message", "user.commit.error");
				}
			}

		return result;
	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final User user) {

		ModelAndView result;
		result = this.createEditModelAndView(user, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final User user, final String message) {

		ModelAndView result;

		result = new ModelAndView("user/edit");
		result.addObject("user", user);
		result.addObject("message", message);
		result.addObject("RequestURI", "user/edit.do");

		return result;

	}

}
