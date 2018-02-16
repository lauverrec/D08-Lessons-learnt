
package controllers.administrator;

import java.util.ArrayList;
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

import services.AnnouncementService;
import controllers.AbstractController;
import domain.Announcement;

@Controller
@RequestMapping("/announcement/administrator")
public class AnnouncementAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private AnnouncementService	announcementService;


	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Announcement> announcements;

		announcements = new ArrayList<>(this.announcementService.findAll());

		result = new ModelAndView("announcement/list");
		result.addObject("announcements", announcements);
		result.addObject("requestURI", "announcement/administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int announcementId) {
		ModelAndView result;
		Announcement announcement;

		announcement = this.announcementService.findOne(announcementId);
		Assert.notNull(announcement);
		result = this.createEditModelAndView(announcement);
		return result;
	}

	// Delete -----------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Announcement announcement, BindingResult binding) {
		ModelAndView result;
		int rendezvouseId = announcement.getRendezvouse().getId();

		if (binding.hasErrors())
			result = this.createEditModelAndView(announcement);
		else
			try {
				this.announcementService.delete(announcement);
				result = new ModelAndView("redirect:list.do?rendezvouseId=" + rendezvouseId);
			} catch (Throwable oops) {
				result = this.createEditModelAndView(announcement, "announcement.commit.error");
			}
		return result;
	}

	// Save -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Announcement announcement, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(announcement);
		else
			try {
				this.announcementService.save(announcement);
				result = new ModelAndView("redirect:list.do?rendezvouseId=" + announcement.getRendezvouse().getId());
			} catch (Throwable oops) {
				result = this.createEditModelAndView(announcement, "announcement.commit.error");
			}
		return result;
	}

	//Auxiliares ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(Announcement announcement) {

		Assert.notNull(announcement);
		ModelAndView result;
		result = this.createEditModelAndView(announcement, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Announcement announcement, String messageCode) {
		assert announcement != null;

		ModelAndView result;

		result = new ModelAndView("announcement/delete");
		result.addObject("announcement", announcement);
		result.addObject("message", messageCode);
		return result;

	}
}
