
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.Question;

@Service
@Transactional
public class QuestionService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private QuestionRepository	questionRepository;


	// Supporting services ----------------------------------------------------
	//	@Autowired
	//	private UserService userService;

	// Constructors -----------------------------------------------------------

	public QuestionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Question create() {
		Question result;
		//User user;

		//user = this.userService.findByPrincipal();
		result = new Question();

		//result.setUser(user);
		return result;
	}

	public Collection<Question> findAll() {
		Collection<Question> result;
		result = this.questionRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Question findOne(int questionId) {
		Question result;
		result = this.questionRepository.findOne(questionId);
		return result;
	}

	public Question save(Question question) {
		Assert.notNull(question);
		Question result;
		result = this.questionRepository.save(question);
		Assert.notNull(question);
		return result;
	}

	public void delete(Question question) {
		assert question != null;
		assert question.getId() != 0;
		Assert.isTrue(this.questionRepository.exists(question.getId()));
		this.questionRepository.delete(question);
	}

	// Other business methods -------------------------------------------------

}
