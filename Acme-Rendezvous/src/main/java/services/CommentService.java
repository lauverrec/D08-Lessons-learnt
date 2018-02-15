
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CommentRepository	commentRepository;


	// Supporting services ----------------------------------------------------

	// Constructors------------------------------------------------------------
	public CommentService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------

	public Comment create() {
		Comment result;
		result = new Comment();
		Collection<Comment> replys;

		replys = new ArrayList<Comment>();
		//TODO: Falta asociar quien ha escrito este comentario.

		result.setReplys(replys);

		return result;

	}

	public Comment save(Comment comment) {

		Assert.notNull(comment);

		Comment result;

		//TODO: Comprobar que el que lo va a guardar es el mismo que lo ha creado.
		//TODO: Solo pueden escribirlos usuarios.
		//TODO: No se puede actualizar un comentario.
		Assert.isTrue(comment.getId() == 0);

		//TODO: Los comentarios deben tener un rendezvous que se haya confirmado la asistencia.

		result = this.commentRepository.save(comment);

		return result;

	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);

		Assert.isTrue(this.commentRepository.findOne(comment.getId()) != null);
		//TODO: Comprobar que el que va a borrar un comentario es un administador.

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
