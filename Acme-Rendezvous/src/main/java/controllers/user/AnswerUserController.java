
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

import services.AnswerService;
import services.QuestionService;
import services.UserService;
import controllers.AbstractController;
import domain.Answer;
import domain.Question;
import domain.User;

@Controller
@RequestMapping("/answer/user")
public class AnswerUserController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private AnswerService	answerService;

	@Autowired
	private QuestionService	questionService;

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
	//Create-----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int questionId) {
		ModelAndView result;
		Answer answer;
		Question question;

		question = this.questionService.findOne(questionId);

		answer = this.answerService.create(question);
		result = this.createEditModelAndView(answer);

		return result;
	}
	//Edition--------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int answerId) {
		ModelAndView result;
		Answer answer;
		User user;

		user = this.userService.findByPrincipal();
		answer = this.answerService.findOne(answerId);
		Assert.notNull(answer);
		Assert.isTrue(this.answerService.findAllAnswersByUserId().contains(answer), "Cannot commit this operation because that is not one of your answers");
		result = this.createEditModelAndView(answer);
		result.addObject("user", user);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Answer answer, BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(answer);
		else
			try {
				this.answerService.save(answer);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(answer, "answer.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Answer answer, BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.answerService.delete(answer);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(answer, "answer.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "deletevirtual")
	public ModelAndView deletevirtual(@ModelAttribute Answer answer, BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(answer);
		else
			try {
				this.answerService.delete(answer);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(answer, "answer.commit.error");
			}

		return result;

	}
	//ancially methods---------------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(Answer answer) {
		Assert.notNull(answer);
		ModelAndView result;
		result = this.createEditModelAndView(answer, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(Answer answer, String message) {
		Assert.notNull(answer);

		ModelAndView result;
		result = new ModelAndView("Answer/edit");

		result.addObject("answer", answer);
		result.addObject("message", message);
		return result;

	}
}
