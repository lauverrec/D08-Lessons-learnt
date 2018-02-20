
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
import services.RendezvouseService;
import services.UserService;
import controllers.AbstractController;
import domain.Answer;
import domain.Question;
import domain.Rendezvouse;
import domain.User;

@Controller
@RequestMapping("/question/user")
public class QuestionUserController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private QuestionService		questionService;

	@Autowired
	private UserService			userService;

	@Autowired
	private RendezvouseService	rendezvouseService;

	@Autowired
	private AnswerService		answerService;


	//Constructor--------------------------------------------------------

	public QuestionUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Question> questions;

		questions = this.questionService.findAllQuestionsByUser();

		result = new ModelAndView("Question/list");
		result.addObject("questions", questions);
		return result;
	}
	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int questionId) {
		ModelAndView result;
		Question question = new Question();

		question = this.questionService.findOne(questionId);

		result = new ModelAndView("Question/display");
		result.addObject("Question", question);
		result.addObject("requestURI", "question/user/display.do");

		return result;
	}
	//Creation-----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Question question;
		question = this.questionService.create();
		result = this.createEditModelAndView(question);
		result.addObject("requestURI", "question/user/create.do");
		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Question question, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(question);
		else
			try {
				this.questionService.save(question);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				if (oops.getMessage().equals("could not execute statement; SQL [n/a]; constraint [null]" + "; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"))
					result = this.createEditModelAndView(question, "question.rendezvouses");
				else
					result = this.createEditModelAndView(question, "question.commit.error");
			}

		return result;
	}

	//Edition -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int questionId) {
		ModelAndView result;
		Question question;
		Collection<Answer> answers;
		User user;

		answers = this.answerService.findAllAnswerByQuestionId(questionId);
		user = this.userService.findByPrincipal();
		question = this.questionService.findOne(questionId);
		Assert.notNull(question);
		Assert.isTrue(this.questionService.findAllQuestionsByUser().contains(question), "Cannot commit this operation because that is not your question");
		Assert.isTrue(answers.size() == 0, "Cannot commit this operation because this question already contains an answer");
		result = this.createEditModelAndView(question);
		result.addObject("requestURI", "question/user/edit.do");
		result.addObject("user", user);
		result.addObject("answer", answers);
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Question question, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(question);
		else
			try {
				this.questionService.save(question);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(question, "question.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Question question, final BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.questionService.delete(question);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(question, "question.commit.error");
		}

		return result;
	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Question question) {
		assert question != null;
		ModelAndView result;
		result = this.createEditModelAndView(question, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Question question, final String message) {

		assert question != null;
		ModelAndView result;
		Collection<Rendezvouse> rendezvouses;

		rendezvouses = this.rendezvouseService.findRendezvousesCreatedByUser();

		result = new ModelAndView("Question/edit");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("Question", question);
		result.addObject("message", message);

		return result;

	}

}
