package com.rishi.adminuser.service;

/**
 * @author raushanr
 *
 */

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.rishi.adminuser.controller.dto.LoginAuthenticateDTO;
import com.rishi.adminuser.controller.dto.ManagedUserDTO;
import com.rishi.adminuser.entity.Authority;
import com.rishi.adminuser.entity.User;
import com.rishi.adminuser.repositories.AuthorityRepository;
import com.rishi.adminuser.repositories.UserRepository;
import com.rishi.adminuser.security.AuthoritiesConstants;
import com.rishi.adminuser.service.util.PasswordGenerator;



@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService{
	
	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Inject
	private UserRepository userRepository;
	
	@Inject
	private AuthorityRepository authorityRepository;
	

	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void updateUser(User user){
		saveUser(user);
	}

	public void deleteUserById(Long id){
		userRepository.delete(id);
	}

	public void deleteAllUsers(){
		userRepository.deleteAll();
	}

	public List<User> findAllUsers(){
		return userRepository.findAll();
	}

	public void createUserAccount(ManagedUserDTO managedUserDTO) {
		
		Preconditions.checkArgument(null != managedUserDTO, "No user inputs found for user registration!! Cannot proceed further with registration");
		Preconditions.checkArgument(null!=managedUserDTO.getFirstName() && !managedUserDTO.getFirstName().isEmpty(), "First name cannot be blank!! Cannot proceed further with user registration");
		Preconditions.checkArgument(null!=managedUserDTO.getLastName() && !managedUserDTO.getLastName().isEmpty(), "Last name cannot be blank!! Cannot proceed further with user registration");
		Preconditions.checkArgument(null!=managedUserDTO.getEmail() && !managedUserDTO.getEmail().isEmpty(), "email name cannot be blank!! Cannot proceed further with user registration");
		
		User user = null;
		
		user = this.buildUserInfo(managedUserDTO);
		
		log.debug("Before User save for '{}'",user.getEmail());
		userRepository.save(user);
		log.debug("Created Information for User: {}", user);
		
	}
	
	/**
	 * This method is responsible for populating the User Entity for saving of user 
	 * 
	 * @param managedUserDTO 
	 * 			- Incoming request object for registration 
	 * 
	 * @return User
	 * 			- Populated User object from the incoming params
	 */
	private User buildUserInfo(ManagedUserDTO managedUserDTO) {
		
		User user = new User();
		Set<Authority> authorities = new HashSet<>();
		Authority authority = this.authorityRepository.findOne(AuthoritiesConstants.USER);
		Preconditions.checkArgument(null!=authority, "Unable to find Default User Role in system!! Cannot proceed further with user registration");
		authorities.add(authority);
		user.setAuthorities(authorities);
		
		String encryptedPassword = null;
		try {
			encryptedPassword = PasswordGenerator.encode(managedUserDTO.getPassword());
		} catch (NoSuchAlgorithmException e) {
			log.debug("No such algorithm for generating password");
			e.printStackTrace();
		}
		user.setPassword(encryptedPassword);
		user.setLogin(managedUserDTO.getEmail().toLowerCase());
		user.setFirstName( managedUserDTO.getFirstName());
		user.setLastName(managedUserDTO.getLastName());
		user.setEmail(managedUserDTO.getEmail().toLowerCase());
		user.setCreatedBy("system");
		return user;
	}

	/**
	 *  This method is responsible for validating the username authentication 
	 * 
	 * @param authenticateDTO
	 */
	
	public Optional<User> validateUsername(LoginAuthenticateDTO authenticateDTO) {
		
		Preconditions.checkArgument(null != authenticateDTO, "No authentication detail found ! cannot proceed without username");
		Preconditions.checkArgument(null != authenticateDTO.getJ_username(), "username cannot be null");
		
		
		String login = authenticateDTO.getJ_username();
		
		return userRepository.findOneByLogin(login)
		.map(user -> {
			log.info("username validated");
			return user;
		});
		
	}
	
	public boolean validatePassword(User user, LoginAuthenticateDTO authenticateDTO){
		
		Preconditions.checkArgument(null != user, "No authentication detail found ! cannot proceed without username");
		Preconditions.checkArgument(null != user.getPassword(), "password cannot be null");
		Preconditions.checkArgument(null != authenticateDTO, "No authentication detail found ! cannot proceed without username");
		Preconditions.checkArgument(null != authenticateDTO.getJ_password(), "password cannot be null");
		
		String password = null;
		try {
			password = PasswordGenerator.encode(authenticateDTO.getJ_password());
		} catch (NoSuchAlgorithmException e) {
			log.debug("No such algorithm for generating password");
			e.printStackTrace();
		}
		if(user.getPassword().equals(password)){
			log.info("username & password verified successfully");
			return true;
		}
		return false;
		
		
		
	}

}
