
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.Authority;
import domain.Comment;
import domain.User;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CommentRepository		commentRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService				userService;

	@Autowired
	private AdministratorService	administratorService;


	// Constructors------------------------------------------------------------
	public CommentService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------

	public Comment create() {
		Comment result;
		Collection<Comment> replys;
		User user;

		result = new Comment();
		replys = new ArrayList<Comment>();
		user = this.userService.findByPrincipal();

		result.setReplys(replys);
		result.setUser(user);

		return result;

	}
	public Comment save(Comment comment) {

		Assert.notNull(comment);
		User userConnected;

		Comment result;

		userConnected = this.userService.findByPrincipal();

		Assert.isTrue(comment.getUser().getUserAccount().getAuthorities().contains(Authority.USER));
		Assert.isTrue(comment.getUser().equals(userConnected));
		Assert.isTrue(comment.getId() == 0);

		//TODO: Los comentarios deben tener un rendezvous que se haya confirmado la asistencia.

		result = this.commentRepository.save(comment);

		return result;

	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);

		Assert.isTrue(this.commentRepository.findOne(comment.getId()) != null);

		this.administratorService.checkPrincipal();

	}
	public Comment findOne(int commentId) {
		Assert.isTrue(commentId != 0);
		Comment result;

		result = this.commentRepository.findOne(commentId);
		return result;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = this.commentRepository.findAll();

		return result;
	}
}