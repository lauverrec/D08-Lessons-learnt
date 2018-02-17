
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private CommentService	commenteService;


	//constructor-------------------------------------------------------------------------
	public CommentAdministratorController() {
		super();
	}
	//Listing-----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Comment> comment;
		comment = this.commenteService.findAll();
		result = new ModelAndView("comment/list");
		result.addObject("comment", comment);
		result.addObject("requestURI", "/comment/administrator/list.do");
		return result;

	}

	//Delete---------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int commentId) {
		ModelAndView result;
		final Comment commente;

		commente = this.commenteService.findOne(commentId);
		Assert.notNull(commente);
		try {
			this.commenteService.delete(commente);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.listWithMessage("commente.commit.error");
		}

		return result;
	}

	//ancially methods---------------------------------------------------------------------------

	protected ModelAndView listWithMessage(final String message) {
		final ModelAndView result;
		Collection<Comment> comment;
		comment = this.commenteService.findAll();
		result = new ModelAndView("comment/list");
		result.addObject("comment", comment);
		result.addObject("requestURI", "/comment/administrator/list.do");
		result.addObject("message", message);
		return result;

	}
}
