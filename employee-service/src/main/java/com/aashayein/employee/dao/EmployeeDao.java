/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.dao
 * @FileName: EmployeeDao.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 4:19:32 PM
 */

package com.aashayein.employee.dao;

import java.util.List;

import com.aashayein.employee.entities.Employee;

public interface EmployeeDao {

	List<Employee> getAllEmployees();

	Employee getEmployeeById(Integer employeeId);

	Employee getEmployeeByUsername(String email);

	Employee getEmployeeByMobileNumber(String mobileNumber);

	Employee getEmployeeByToken(String token);
}
