
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Rendezvouse;
import domain.User;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AdministratorRepository	administratorRepository;


	// Simple CRUD methods------------------------------------------------
	public Administrator create() {
		Administrator result;
		UserAccount userAccount;
		Authority authority;

		result = new Administrator();
		userAccount = new UserAccount();
		authority = new Authority();

		authority.setAuthority(Authority.ADMINISTRATOR);
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		return result;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;
		result = this.administratorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Administrator findOne(final int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrator result;
		result = this.administratorRepository.findOne(administratorId);
		return result;
	}

	public Administrator save(final Administrator administrator) {

		Assert.notNull(administrator);
		final Administrator result;
		final Md5PasswordEncoder encoder;
		final String passwordHash;

		if (administrator.getId() == 0) {
			final String password = administrator.getUserAccount().getPassword();
			encoder = new Md5PasswordEncoder();
			passwordHash = encoder.encodePassword(password, null);
			administrator.getUserAccount().setPassword(passwordHash);
		}
		result = this.administratorRepository.save(administrator);

		Assert.notNull(result);

		return result;
	}

	// Other business methods------------------------------------------------------
	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.administratorRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	public void checkPrincipal() {

		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		final Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		final Authority auth = new Authority();
		auth.setAuthority(Authority.ADMINISTRATOR);

		Assert.isTrue(authorities.contains(auth));
	}

	public Double[] findAvgStddevOfTheNumOfRendezvouseCreatedPerUser() {
		Double[] result;
		result = this.administratorRepository.findAvgStddevOfTheNumOfRendezvouseCreatedPerUser();
		Assert.notNull(result);
		return result;
	}

	public Double findRatioUsersWithRendezvousesAndNotRendezvouses() {
		Double result;
		result = this.administratorRepository.findRatioUsersWithRendezvousesAndNotRendezvouses();
		Assert.notNull(result);
		return result;
	}

	public Double findAvgStddevOfTheNumOfAssistansPerRendezvouse() {
		Double result;
		result = this.administratorRepository.findAvgStddevOfTheNumOfAssistansPerRendezvouse();
		Assert.notNull(result);
		return result;
	}

	public Double[] findAvgStddevOfTheNumOfRendezvouseAssitedPerUser() {
		Double[] result;
		result = this.administratorRepository.findAvgStddevOfTheNumOfRendezvouseAssitedPerUser();
		Assert.notNull(result);
		return result;
	}

	public Collection<User> findTop10RendezvouseWithRSVPd() {
		Collection<User> result;
		final Page<User> resPage;
		final Pageable pageable;

		pageable = new PageRequest(0, 10);
		resPage = this.administratorRepository.findTop10RendezvouseWithRSVPd(pageable);
		result = resPage.getContent();
		return result;
	}

	public Double[] findAvgStddevOfTheNumOfAnnouncementsPerRendezvous() {
		Double[] result;
		result = this.administratorRepository.findAvgStddevOfTheNumOfAnnouncementsPerRendezvous();
		Assert.notNull(result);
		return result;
	}

	public Collection<Rendezvouse> findRendezvousesWithMore75PerCent() {
		Collection<Rendezvouse> result;
		result = this.administratorRepository.findRendezvousesWithMore75PerCent();
		Assert.notNull(result);
		return result;
	}

	public Collection<Rendezvouse> findRendezvousesWithAreLinked() {
		Collection<Rendezvouse> result;
		result = this.administratorRepository.findRendezvousesWithAreLinked();
		Assert.notNull(result);
		return result;
	}

	public Double[] findAvgStddevOfTheNumOfQuestionsPerRendezvous() {
		Double[] result;
		result = this.administratorRepository.findAvgStddevOfTheNumOfQuestionsPerRendezvous();
		Assert.notNull(result);
		return result;
	}

	public Double[] findAvgStddevOfTheNumOfAnswerToQuestionsPerRendezvous() {
		Double[] result;
		result = this.administratorRepository.findAvgStddevOfTheNumOfAnswerToQuestionsPerRendezvous();
		Assert.notNull(result);
		return result;
	}

	public Double[] findAvgStddevOfTheNumOfRepliesPerComment() {
		Double[] result;
		result = this.administratorRepository.findAvgStddevOfTheNumOfRepliesPerComment();
		Assert.notNull(result);
		return result;
	}

}
