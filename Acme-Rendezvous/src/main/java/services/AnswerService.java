
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import domain.Answer;

@Service
@Transactional
public class AnswerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AnswerRepository	answerRepository;


	// Supporting services ----------------------------------------------------
	//	@Autowired
	//	private UserService userService;

	// Constructors -----------------------------------------------------------

	public AnswerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Answer create() {
		Answer result;
		//User user;

		//user = this.userService.findByPrincipal();
		result = new Answer();

		//result.setUser(user);
		return result;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> result;
		result = this.answerRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Answer findOne(int answerId) {
		Answer result;
		result = this.answerRepository.findOne(answerId);
		return result;
	}

	public Answer save(Answer answer) {
		Assert.notNull(answer);
		Answer result;
		result = this.answerRepository.save(answer);
		Assert.notNull(answer);
		return result;
	}

	public void delete(Answer answer) {
		assert answer != null;
		assert answer.getId() != 0;
		Assert.isTrue(this.answerRepository.exists(answer.getId()));
		this.answerRepository.delete(answer);
	}

	// Other business methods -------------------------------------------------

}
