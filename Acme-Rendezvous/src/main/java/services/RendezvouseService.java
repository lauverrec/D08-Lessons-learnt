
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RendezvouseRepository;
import domain.Announcement;
import domain.Comment;
import domain.Question;
import domain.Rendezvouse;
import domain.User;

@Service
@Transactional
public class RendezvouseService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RendezvouseRepository	rendezvousRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private UserService				userService;

	@Autowired
	private AnnouncementService		announcementService;

	@Autowired
	private QuestionService			questionService;

	@Autowired
	private CommentService			commentService;


	// Constructors -----------------------------------------------------------

	public RendezvouseService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Rendezvouse create() {
		Rendezvouse result;
		Collection<User> assistants;
		Collection<Rendezvouse> similarRendezvouses;
		Collection<Announcement> announcements;

		announcements = new ArrayList<Announcement>();
		similarRendezvouses = new ArrayList<Rendezvouse>();
		assistants = new ArrayList<User>();
		result = new Rendezvouse();

		result.setAssistants(assistants);
		result.setAnnouncements(announcements);
		result.setSimilarRendezvouses(similarRendezvouses);
		result.setDeleted(false);

		return result;
	}
	public Collection<Rendezvouse> findAll() {
		Collection<Rendezvouse> result;

		Assert.notNull(this.rendezvousRepository);
		result = this.rendezvousRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Rendezvouse findOne(final int rendezvouseId) {
		Rendezvouse result;

		result = this.rendezvousRepository.findOne(rendezvouseId);
		Assert.notNull(result);

		return result;
	}

	public Rendezvouse save(final Rendezvouse rendezvouse) {
		Assert.notNull(rendezvouse);
		Rendezvouse result;
		User user;
		Date now;

		now = new Date();
		user = this.userService.findByPrincipal();
		if (rendezvouse.getId() == 0)
			rendezvouse.getAssistants().add(user);

		Assert.isTrue(rendezvouse.getOrganisedMoment().after(now), "future");
		result = this.rendezvousRepository.save(rendezvouse);
		if (rendezvouse.getId() == 0)
			user.getRendezvousesCreated().add(result);
		return result;
	}

	public void delete(final Rendezvouse rendezvouse) {
		User user;
		user = this.userService.findUserByRendezvousId(rendezvouse.getId());

		Collection<User> assistant;
		Collection<Question> questions;
		Collection<Comment> comments;
		Collection<Rendezvouse> similarrendezvous;
		Collection<Announcement> announcements;

		similarrendezvous = this.SimilarRendezvouseWhereIS(rendezvouse);
		announcements = this.AnnoucemntofRendezvouse(rendezvouse);
		questions = this.questionService.findAllQuestionsByRendezvous(rendezvouse.getId());
		comments = this.commentService.findAllCommentsByRendezvousId(rendezvouse.getId());

		assistant = this.rendezvousRepository.findAllAssistantsByRendezvous(rendezvouse.getId());
		Assert.isTrue(user.getRendezvousesCreated().contains(rendezvouse));

		user.getRendezvousesCreated().remove(rendezvouse);
		for (Rendezvouse r : similarrendezvous)
			r.getSimilarRendezvouses().remove(rendezvouse);

		for (Comment c : comments)
			this.commentService.delete(c);

		for (Question q : questions)
			this.questionService.delete(q);

		for (Announcement a : announcements)
			//			rendezvouse.getAnnouncements().remove(a);
			this.announcementService.delete(a);
		for (User u : assistant)
			u.getRendezvousesAssisted().remove(rendezvouse);
		this.rendezvousRepository.delete(rendezvouse);

	}
	// Other business methods -------------------------------------------------

	public Collection<Rendezvouse> findRendezvousesCreatedByUser() {
		Collection<Rendezvouse> res;
		User user;
		user = this.userService.findByPrincipal();
		res = this.rendezvousRepository.findRendezvousesCreatedByUser(user.getId());

		return res;

	}

	public Collection<Rendezvouse> findRendezvousesAssitedByUser() {
		Collection<Rendezvouse> res;
		User user;
		user = this.userService.findByPrincipal();
		res = this.rendezvousRepository.findRendezvousesAssitedByUser(user.getId());
		return res;

	}

	public Collection<Rendezvouse> findRendezvousesAssitedByUser2(int userId) {
		Collection<Rendezvouse> res;

		res = this.rendezvousRepository.findRendezvousesAssitedByUser2(userId);
		return res;

	}

	public Collection<User> findAllAssistantsByRendezvous(int rendezvousId) {
		Collection<User> res;

		res = this.rendezvousRepository.findAllAssistantsByRendezvous(rendezvousId);
		return res;

	}

	public Rendezvouse deletevirtual(final Rendezvouse rendezvouse) {
		Rendezvouse result;
		Assert.notNull(rendezvouse);
		boolean aux = true;
		Assert.isTrue(rendezvouse.isDraftMode() == true);
		rendezvouse.setDeleted(aux);
		result = this.rendezvousRepository.save(rendezvouse);
		return result;
	}

	public Collection<Rendezvouse> AllRendezvousesICanAssist() {
		Collection<Rendezvouse> result;
		result = this.rendezvousRepository.AllRendezvousesICanAssist();
		return result;
	}
	public Collection<Rendezvouse> SimilarRendezvouseWhereIS(Rendezvouse rendezvouse) {
		Collection<Rendezvouse> result;
		result = this.rendezvousRepository.SimilarRendezvouseWhereIS(rendezvouse.getId());
		return result;
	}
	public Collection<Announcement> AnnoucemntofRendezvouse(Rendezvouse rendezvouse) {
		Collection<Announcement> res;
		res = this.rendezvousRepository.AnnoucemntofRendezvouse(rendezvouse.getId());

		return res;

	}
}
