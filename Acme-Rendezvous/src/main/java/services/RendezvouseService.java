
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RendezvouseRepository;
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


	// Constructors -----------------------------------------------------------

	public RendezvouseService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Rendezvouse create() {
		Rendezvouse result;
		Collection<User> assistants;
		Collection<Rendezvouse> similarRendezvouses;
		similarRendezvouses = new ArrayList<Rendezvouse>();
		assistants = new ArrayList<User>();

		result = new Rendezvouse();
		result.setAssistants(assistants);
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
		user = this.userService.findByPrincipal();
		rendezvouse.getAssistants().add(user);
		result = this.rendezvousRepository.save(rendezvouse);
		user.getRendezvousesCreated().add(result);
		return result;
	}

	public void delete(final Rendezvouse rendezvouse) {
		User user;
		user = this.userService.findByPrincipal();
		Assert.isTrue(user.getRendezvousesCreated().contains(rendezvouse));
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
}
