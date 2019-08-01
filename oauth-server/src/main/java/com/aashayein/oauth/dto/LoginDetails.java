/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.dto
 * @FileName: LoginDetails.java
 * @Author: Avishek Das
 * @CreatedDate: 27-07-2019
 * @Modified_By avishek.das @Last_On 27-Jul-2019 2:48:06 PM
 */

package com.aashayein.oauth.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.aashayein.oauth.entities.Employee;
import com.aashayein.oauth.entities.EmployeeModule;

public class LoginDetails implements UserDetails {

	private static final long serialVersionUID = 5150859311549782026L;

	private final Employee user;

	public LoginDetails(Employee user) {
		this.user = user;
	}

	public Employee getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		Set<EmployeeModule> modules = user.getRole().getModules();
		for (EmployeeModule employeeModule : modules) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + employeeModule.getModuleName()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
