
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvouseService;
import services.UserService;
import controllers.AbstractController;
import domain.Rendezvouse;
import domain.User;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousesUserController extends AbstractController {

	//service---------------------------------------------------------------------------
	@Autowired
	private RendezvouseService	rendezvouseService;

	@Autowired
	private UserService			userService;


	//constructor-------------------------------------------------------------------------
	public RendezvousesUserController() {
		super();
	}
	//Listing-----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Rendezvouse> rendezvous;
		rendezvous = this.rendezvouseService.findRendezvousesCreatedByUser();
		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/user/list.do");
		return result;

	}

	//Listing-----------------------------------------------------------

	@RequestMapping(value = "/list-RSVP", method = RequestMethod.GET)
	public ModelAndView list2() {

		ModelAndView result;
		Collection<Rendezvouse> rens;

		rens = this.rendezvouseService.findRendezvousesAssitedByUser();

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvous", rens);
		result.addObject("requestURI", "rendezvous/user/list-RSVP.do");

		return result;

	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int rendezvousId) {
		ModelAndView result;
		Rendezvouse ren = new Rendezvouse();

		ren = this.rendezvouseService.findOne(rendezvousId);

		result = new ModelAndView("rendezvous/display");
		result.addObject("rendezvous", ren);
		result.addObject("requestURI", "rendezvous/user/display.do");

		return result;
	}

	//Creation---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Rendezvouse rendezvouse;

		rendezvouse = this.rendezvouseService.create();
		result = this.createEditModelAndView(rendezvouse);

		return result;
	}
	//Edition--------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int rendezvouseId) {
		ModelAndView result;
		Rendezvouse rendezvouse;
		User user;

		user = this.userService.findByPrincipal();
		rendezvouse = this.rendezvouseService.findOne(rendezvouseId);
		Assert.isTrue(user.getRendezvousesCreated().contains(rendezvouse), "Cannot commit this operation, because it's illegal");
		Assert.isTrue(rendezvouse.isDraftMode() == true, "Cannot commit this operation, because it's illegal");
		Assert.notNull(rendezvouse);
		result = this.createEditModelAndView(rendezvouse);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Rendezvouse rendezvous, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(rendezvous);
		else
			try {
				this.rendezvouseService.save(rendezvous);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(rendezvous, "rendezvouse.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute final Rendezvouse rendezvouse, final BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.rendezvouseService.delete(rendezvouse);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(rendezvouse, "rendezvouse.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "deletevirtual")
	public ModelAndView deletevirtual(@ModelAttribute final Rendezvouse rendezvouse, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(rendezvouse);
		else
			try {
				this.rendezvouseService.deletevirtual(rendezvouse);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(rendezvouse, "rendezvouse.commit.error");
			}

		return result;

	}
	//ancially methods---------------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Rendezvouse rendezvouse) {
		Assert.notNull(rendezvouse);
		ModelAndView result;
		result = this.createEditModelAndView(rendezvouse, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final Rendezvouse rendezvouse, final String message) {
		Assert.notNull(rendezvouse);
		Collection<Rendezvouse> similarRendezvouses;
		ModelAndView result;
		result = new ModelAndView("rendezvous/edit");
		similarRendezvouses = this.rendezvouseService.findRendezvousesCreatedByUser();

		result.addObject("rendezvouse", rendezvouse);
		result.addObject("similarRendezvouses", similarRendezvouses);
		result.addObject("message", message);
		return result;

	}

}
