/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.serviceImpl
 * @FileName: EmployeeServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 3:58:51 PM
 */

package com.aashayein.employee.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.entities.Employee;
import com.aashayein.employee.repository.EmployeeRepository;
import com.aashayein.employee.service.EmployeeService;
import com.aashayein.employee.util.ConvertEntityToTransferObject;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ConvertEntityToTransferObject convertEntityToTransferObject;

	@Override
	@Transactional
	public List<EmployeeTO> getAllEmployees() {

		List<EmployeeTO> employees = new ArrayList<EmployeeTO>();

		List<Employee> employee = employeeRepository.findByOrderByRecordCreatedDesc();

		if (!employee.isEmpty()) {
			for (Employee emp : employee) {
				employees.add(convertEntityToTransferObject.convertEmployeeToEmployeeTO(emp));
			}
		}

		return employees;
	}

	@Override
	@Transactional
	public EmployeeTO getEmployeeById(Integer employeeId) {

		Employee employee = null;
		EmployeeTO employeeTo = null;

		employee = employeeRepository.findByEmployeeId(employeeId);

		if (employee != null) {
			employeeTo = convertEntityToTransferObject.convertEmployeeToEmployeeTO(employee);
		}

		return employeeTo;
	}

	@Override
	@Transactional
	public EmployeeTO getEmployeeByEmail(String email) {

		Employee employee = null;
		EmployeeTO employeeTo = null;

		employee = employeeRepository.findByEmail(email);

		if (employee != null) {
			employeeTo = convertEntityToTransferObject.convertEmployeeToEmployeeTO(employee);
		}

		return employeeTo;
	}

	@Override
	@Transactional
	public EmployeeTO getEmployeeByMobileNumber(String mobileNumber) {

		Employee employee = null;
		EmployeeTO employeeTo = null;

		employee = employeeRepository.findByMobileNumber(mobileNumber);

		if (employee != null) {
			employeeTo = convertEntityToTransferObject.convertEmployeeToEmployeeTO(employee);
		}

		return employeeTo;
	}

}
