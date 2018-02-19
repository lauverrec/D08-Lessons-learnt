
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
	private CommentService	commentService;


	//constructor-------------------------------------------------------------------------
	public CommentAdministratorController() {
		super();
	}
	//Listing-----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Comment> comments;

		comments = this.commentService.findAll();
		result = new ModelAndView("comment/list");

		result.addObject("comments", comments);
		result.addObject("requestURI", "/comment/administrator/list.do");

		return result;

	}

	//Delete---------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int commentId) {
		ModelAndView result;
		final Comment comment;

		comment = this.commentService.findOne(commentId);
		Assert.notNull(comment);
		try {
			this.commentService.delete(comment);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.listWithMessage("comment.commit.error");
		}

		return result;
	}

	//ancially methods---------------------------------------------------------------------------

	protected ModelAndView listWithMessage(final String message) {
		final ModelAndView result;
		Collection<Comment> comment;
		comment = this.commentService.findAll();
		result = new ModelAndView("comment/list");
		result.addObject("comment", comment);
		result.addObject("requestURI", "/comment/administrator/list.do");
		result.addObject("message", message);
		return result;

	}
}
