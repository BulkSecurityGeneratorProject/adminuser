package com.rishi.adminuser.controller;

import java.net.URLDecoder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rishi.adminuser.controller.dto.LoginAuthenticateDTO;
import com.rishi.adminuser.controller.dto.ManagedUserDTO;
import com.rishi.adminuser.entity.User;
import com.rishi.adminuser.repositories.UserRepository;
import com.rishi.adminuser.service.UserServiceImpl;

/**
 * @author raushanr
 *
 */

/**
 * Rest controller for user account
 */

@RestController
@RequestMapping("/api")
public class AccountResource {

	private final Logger log = LoggerFactory.getLogger(AccountResource.class);

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserServiceImpl userServiceImpl;

	/**
	 * POST /register : register the user.
	 *
	 * @param managedUserDTO
	 *            the managed user DTO
	 * @param request
	 *            the HTTP request
	 * @return the ResponseEntity with status 201 (Created) if the user is
	 *         registered or 400 (Bad Request) if the login or e-mail is already
	 *         in use
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = {MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> registerAccount(
			@Valid @RequestBody ManagedUserDTO managedUserDTO,
			HttpServletRequest request) {

		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		
		return userRepository.findOneByLogin(managedUserDTO.getLogin())
				.map(user -> new ResponseEntity<> ("login already in use",textPlainHeaders,HttpStatus.BAD_REQUEST))
				.orElseGet(() -> userRepository.findOneByEmail(managedUserDTO.getEmail())
				.map(user -> new ResponseEntity<>("email already in use", textPlainHeaders,HttpStatus.BAD_REQUEST))
				.orElseGet(() -> {
					log.info("before account created");
					userServiceImpl.createUserAccount(managedUserDTO);
					log.debug("account created successfully");
					return new ResponseEntity<>("account created successfully", HttpStatus.CREATED);
				})
			);
	}
	
	@RequestMapping(value = "/account/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> accountAuthentication(
			@RequestBody MultiValueMap<String, Object> multiValueMap) {
		
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		
		LoginAuthenticateDTO authenticateDTO = new LoginAuthenticateDTO();
		if (multiValueMap == null
				|| (multiValueMap.get("j_username") == null && multiValueMap
						.get("j_password") == null)
				|| (multiValueMap.get("j_username") != null && multiValueMap
						.get("j_password") == null)
				|| (multiValueMap.get("j_password") != null && multiValueMap
						.get("j_username") == null)) {
			log.debug("application/x-www-form-urlencoded cannot be without username & password");
			return new ResponseEntity<String>("please provide username & password", HttpStatus.BAD_REQUEST);
		} else {
			log.info("checking user authentication..");
			authenticateDTO.setJ_username(URLDecoder.decode(multiValueMap.get("j_username").get(0)
					.toString()));
			authenticateDTO.setJ_password(URLDecoder.decode(multiValueMap.get("j_password").get(0)
					.toString()));
			return userServiceImpl.validateUsername(authenticateDTO)
					.map(user ->{
						if(userServiceImpl.validatePassword(user,authenticateDTO)){
							return new ResponseEntity<String>("user account authenticated",HttpStatus.ACCEPTED);
						} else{
							log.debug("password incorrect");
							return new ResponseEntity<String>("password not validated",HttpStatus.BAD_REQUEST);
							}
						}).orElse(new ResponseEntity<String>("incorrect username", HttpStatus.BAD_REQUEST));
		}

	}
	
}
