
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvouseService;
import domain.Rendezvouse;
import domain.User;

@Controller
@RequestMapping("/rendezvous")
public class RendezvousController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private RendezvouseService	rendezvouseService;


	//Constructor--------------------------------------------------------

	public RendezvousController() {
		super();
	}

	//Listing-----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int userId) {

		ModelAndView result;
		Collection<Rendezvouse> rens;

		rens = this.rendezvouseService.findRendezvousesAssitedByUser2(userId);

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvous", rens);
		result.addObject("requestURI", "rendezvous/list.do");

		return result;

	}

	@RequestMapping(value = "/list-unregister", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Rendezvouse> rendezvous;

		rendezvous = this.rendezvouseService.findAll();

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/list-unregister.do");

		return result;

	}

	@RequestMapping(value = "/listAssistants", method = RequestMethod.GET)
	public ModelAndView list2(@RequestParam int rendezvousId) {

		ModelAndView result;
		Collection<User> assistants;

		assistants = this.rendezvouseService.findAllAssistantsByRendezvous(rendezvousId);

		result = new ModelAndView("user/list");
		result.addObject("users", assistants);
		result.addObject("requestURI", "rendezvous/listAssistants.do");

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

}
