
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.UserService;
import controllers.AbstractController;
import domain.Comment;
import domain.User;

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private CommentService	commentService;

	@Autowired
	private UserService		userService;


	//constructor-------------------------------------------------------------------------
	public CommentUserController() {
		super();
	}

	//---------------------LISTING--------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int rendezvouseId) {
		final ModelAndView result;
		Collection<Comment> comments;

		comments = this.commentService.commentsOfThisRendezvouse(rendezvouseId);
		result = new ModelAndView("comment/list");

		result.addObject("comments", comments);
		result.addObject("requestURI", "/comment/user/list.do");

		return result;

	}

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Comment comment;

		comment = this.commentService.create();

		result = this.createEditModelAndView(comment);

		return result;
	}
	// Edition ----------------------------------------------------------------

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Comment comment, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(comment);
		else
			try {
				this.commentService.save(comment);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(comment, "comment.commit.error");
			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);
		return result;
	}

	private ModelAndView createEditModelAndView(final Comment comment, final String message) {
		ModelAndView result;

		User user;
		Collection<Comment> comments;

		user = this.userService.findByPrincipal();

		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("user", user);
		result.addObject("message", message);

		return result;
	}

}