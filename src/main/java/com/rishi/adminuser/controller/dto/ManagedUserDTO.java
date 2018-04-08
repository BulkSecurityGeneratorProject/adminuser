/**
 * 
 */
package com.rishi.adminuser.controller.dto;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.rishi.adminuser.entity.Authority;
import com.rishi.adminuser.entity.User;

/**
 * @author raushanr
 *
 */
public class ManagedUserDTO {

	 public static final int PASSWORD_MIN_LENGTH = 4;
	 public static final int PASSWORD_MAX_LENGTH = 100;
	 
	 private Long id;
	 
	 @Size(max = 50)
	 private String firstName;
	 
	 @Size(max = 50)
	 private String lastName;
	 
	 @Email
	 @Size(min = 5, max = 100)
	 private String email;
	 
	 @NotNull
	 @Size(min = 1, max = 50)
	 private String login;
	 
	 @NotNull
	 @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
	 private String password;
	 
	 private Set<Authority> authorities;

	 private ZonedDateTime createdDate;

	 private String createdBy;
	 
	public ManagedUserDTO() {
	}

	public ManagedUserDTO(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.authorities = user.getAuthorities();
		this.createdDate = user.getCreatedDate();
		this.createdBy = user.getCreatedBy();
	}
	
	
	
	public ManagedUserDTO(Long id, String firstName, String lastName,
			String email, String login, String password,
			Set<Authority> authorities, ZonedDateTime createdDate,
			String createdBy) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.login = login;
		this.password = password;
		this.authorities = authorities;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	 
	 
}
