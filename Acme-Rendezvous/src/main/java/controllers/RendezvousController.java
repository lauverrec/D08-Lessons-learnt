
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AnswerService;
import services.QuestionService;
import services.RendezvouseService;
import domain.Answer;
import domain.Question;
import domain.Rendezvouse;

@Controller
@RequestMapping("/rendezvous_")
public class RendezvousController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private RendezvouseService	rendezvouseService;

	@Autowired
	private QuestionService		questionService;

	@Autowired
	private AnswerService		answerService;


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

	@RequestMapping(value = "/list-unregister", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Rendezvouse> rendezvous;

		rendezvous = this.rendezvouseService.findAll();

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous_/list-unregister.do");

		return result;

	}

	//Listing-----------------------------------------------------------

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ModelAndView question(int rendezvouseId) {

		ModelAndView result;
		Collection<Question> questions;

		questions = this.questionService.findAllQuestionsByRendezvous(rendezvouseId);

		result = new ModelAndView("Question/display");
		result.addObject("Question", questions);
		result.addObject("requestURI", "rendezvous_/question.do");

		return result;
	}

	//Listing-----------------------------------------------------------

	@RequestMapping(value = "/answer", method = RequestMethod.GET)
	public ModelAndView answer(int questionId) {

		ModelAndView result;
		Collection<Answer> answers;

		answers = this.answerService.findAllAnswerByQuestionId(questionId);

		result = new ModelAndView("Answer/display");
		result.addObject("Answer", answers);
		result.addObject("requestURI", "rendezvous_/answer.do");

		return result;
	}

}
