/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.daoImpl
 * @FileName: EmployeeDaoImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 4:56:45 PM
 */

package com.aashayein.employee.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aashayein.employee.dao.EmployeeDao;
import com.aashayein.employee.entities.Employee;
import com.aashayein.employee.repository.EmployeeRepository;

public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		// return employeeRepository.findAll(new Sort(Sort.Direction.DESC,
		// "recordCreated"));
		return employeeRepository.findByOrderByRecordCreatedDesc();
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) {
		return null;
	}

	@Override
	public Employee getEmployeeByUsername(String email) {
		return null;
	}

	@Override
	public Employee getEmployeeByMobileNumber(String mobileNumber) {
		return null;
	}

	@Override
	public Employee getEmployeeByToken(String token) {
		return null;
	}

}
