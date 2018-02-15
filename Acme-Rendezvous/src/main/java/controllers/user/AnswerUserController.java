
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnswerService;
import services.UserService;
import controllers.AbstractController;
import domain.Answer;
import domain.User;

@Controller
@RequestMapping("/answer/user")
public class AnswerUserController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private AnswerService	answerService;

	@Autowired
	private UserService		userService;


	//Constructor--------------------------------------------------------

	public AnswerUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Answer> answers;

		answers = this.answerService.findAllAnswersByUserId();

		result = new ModelAndView("Answer/list");
		result.addObject("answers", answers);
		return result;
	}

	//Creation-----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Answer answer;
		answer = this.answerService.create();
		result = this.createEditModelAndView(answer);
		result.addObject("requestURI", "answer/user/create.do");
		return result;

	}

	//Edition -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int answerId) {
		ModelAndView result;
		Answer answer;

		User user;

		user = this.userService.findByPrincipal();
		answer = this.answerService.findOne(answerId);
		//Assert.isTrue(manager.getXxxs().contains(answer), "Cannot commit this operation, because it's illegal");
		Assert.notNull(answer);
		result = this.createEditModelAndView(answer);
		result.addObject("requestURI", "answer/user/edit.do");
		result.addObject("user", user);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Answer answer, final BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.answerService.delete(answer);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(answer, "answer.commit.error");
		}

		return result;
	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Answer answer) {
		assert answer != null;
		ModelAndView result;
		result = this.createEditModelAndView(answer, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Answer answer, final String message) {

		assert answer != null;
		ModelAndView result;

		result = new ModelAndView("Answer/edit");
		result.addObject("Answer", answer);
		result.addObject("message", message);

		return result;

	}
}
