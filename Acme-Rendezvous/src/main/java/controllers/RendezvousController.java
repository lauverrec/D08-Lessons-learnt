
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvouseService;
import domain.Rendezvouse;

@Controller
@RequestMapping("/rendezvous_")
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
	public ModelAndView list(int userId) {

		ModelAndView result;
		Collection<Rendezvouse> users;

		users = this.rendezvouseService.findRendezvousesAssitedByUser2(userId);

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvous", users);
		result.addObject("requestURI", "rendezvous_/list.do");

		return result;

	}

}
