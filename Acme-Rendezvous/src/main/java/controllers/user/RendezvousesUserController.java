
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvouseService;
import controllers.AbstractController;
import domain.Rendezvouse;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousesUserController extends AbstractController {

	//service---------------------------------------------------------------------------
	@Autowired
	private RendezvouseService	rendezvouseService;


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
		ModelAndView result;
		result = new ModelAndView("rendezvouse/edit");
		result.addObject("rendezvouse", rendezvouse);
		result.addObject("message", message);
		return result;

	}

}
