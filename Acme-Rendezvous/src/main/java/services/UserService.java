package services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;

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
	
	
	

}
