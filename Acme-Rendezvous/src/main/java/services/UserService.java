package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Rendezvouse;
import domain.User;

@Service
@Transactional
public class UserService {
	
	// Managed repository -----------------------------------------------------
	
	private UserRepository userRepository;
	
	// Supporting services ----------------------------------------------------
	
	// Constructors -----------------------------------------------------------
	
	public UserService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public User create() {
		
		User result;
		UserAccount userAccount;
		Authority authority;
		Collection<Rendezvouse> rendezvousesCreated;
		Collection<Rendezvouse> rendezvousesAssisted;
		
		result = new User();
		userAccount = new UserAccount();
		authority = new Authority();
		rendezvousesCreated = new ArrayList<>();
		rendezvousesAssisted = new ArrayList<>();
		
		authority.setAuthority(Authority.USER);
		userAccount.addAuthority(authority);
		result.setRendezvousesCreated(rendezvousesCreated);
		result.setRendezvousesAssisted(rendezvousesAssisted);
		
		return result;
		
	}
	
	
	// Other business methods----------------------------------

		public User findByPrincipal() {

			User result;
			UserAccount userAccount;

			userAccount = LoginService.getPrincipal();
			Assert.notNull(userAccount);
			result = this.userRepository.findByUserAccountId(userAccount.getId());
			Assert.notNull(result);

			return result;
		}

		public void checkPrincipal() {

			final UserAccount userAccount = LoginService.getPrincipal();
			Assert.notNull(userAccount);

			final Collection<Authority> authorities = userAccount.getAuthorities();
			Assert.notNull(authorities);

			final Authority auth = new Authority();
			auth.setAuthority(Authority.USER);

			Assert.isTrue(authorities.contains(auth));
		}
	

}
