/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.service
 * @FileName: LoginService.java
 * @Author: Avishek Das
 * @CreatedDate: 27-07-2019
 * @Modified_By avishek.das @Last_On 27-Jul-2019 3:01:59 PM
 */

package com.aashayein.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashayein.oauth.dto.LoginDetails;
import com.aashayein.oauth.entities.Employee;
import com.aashayein.oauth.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	// Loading User By Username
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmail(username);

		if (employee == null) {
			log.info("Username Not Found");
			throw new UsernameNotFoundException("Username Not Found");
		}

		return new LoginDetails(employee);
	}

}
