
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
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
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1000);
		result = new Comment();
		replys = new ArrayList<Comment>();
		user = this.userService.findByPrincipal();

		result.setReplys(replys);
		result.setUser(user);
		result.setWrittenMoment(moment);

		return result;

	}
	public Comment save(Comment comment) {
		Date moment;
		User userConnected;

		moment = new Date();
		userConnected = this.userService.findByPrincipal();

		Assert.notNull(comment);
		Assert.isTrue(comment.getRendezvouse().getAssistants().contains(userConnected));

		Comment result;

		moment = new Date(System.currentTimeMillis() - 1000);

		this.userService.checkPrincipal();
		Assert.isTrue(comment.getUser().equals(userConnected));
		Assert.isTrue(comment.getId() == 0);

		//TODO: Los comentarios deben tener un rendezvous que se haya confirmado la asistencia.
		comment.setWrittenMoment(moment);
		result = this.commentRepository.save(comment);

		return result;

	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);

		Assert.isTrue(this.commentRepository.findOne(comment.getId()) != null);

		this.administratorService.checkPrincipal();

		if (comment.getReplys().size() != 0)
			for (Comment c : comment.getReplys())
				this.commentRepository.delete(c);

		this.commentRepository.delete(comment);

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

	//Other methods bussisnes

	public Collection<Comment> commentsOfThisRendezvouse(int rendezvouseId) {
		Collection<Comment> commentsOfThisRendezvouse;

		commentsOfThisRendezvouse = this.commentRepository.commentsOfThisRendezvouse(rendezvouseId);

		return commentsOfThisRendezvouse;
	}

	public Collection<Comment> findAllCommentsByRendezvousId(int rendezvousId) {
		Collection<Comment> result;
		result = this.commentRepository.findAllCommentsByRendezvousId(rendezvousId);
		return result;

	}

	public Collection<Comment> commentsOfThisRendezvouseWithCommentNull(int rendezvouseId) {
		Collection<Comment> commentsOfThisRendezvouse;

		commentsOfThisRendezvouse = this.commentRepository.commentsOfThisRendezvouseWithCommentNull(rendezvouseId);

		return commentsOfThisRendezvouse;
	}

	public Collection<Comment> commentTofindAllCommentsByRendezvousId(int renzvousId) {
		Collection<Comment> result;
		result = this.commentRepository.commentTofindAllCommentsByRendezvousId(renzvousId);
		return result;
	}
}
