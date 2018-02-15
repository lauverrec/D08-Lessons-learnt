package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvouseService;
import services.UserService;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{
	
	// Services---------------------------------------------------------
	
	@Autowired
	private UserService	userService;
	
	
	//Constructor--------------------------------------------------------
	
	public UserController(){
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
		
		//Edition------------------------------------------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			User user;

			user = this.userService.create();
			Assert.notNull(user);
			result = this.createEditModelAndView(user);

			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final User user, final BindingResult bindingResult) {
			ModelAndView result;

			if (bindingResult.hasErrors())
				result = this.createEditModelAndView(user);
			else
				try {
					this.userService.save(user);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (final Throwable oops) {
					if (oops.getMessage().equals("could not execute statement; SQL [n/a]; constraint [null]" + "; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"))
						result = this.createEditModelAndView(user, "user.commit.error.duplicateProfile");
					else
						result = this.createEditModelAndView(user, "user.commit.error");
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
