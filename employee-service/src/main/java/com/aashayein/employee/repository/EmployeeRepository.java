/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.repository
 * @FileName: EmployeeRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 4:10:53 PM
 */

package com.aashayein.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashayein.employee.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByOrderByRecordCreatedDesc();

	Employee findTopByOrderByEmployeeIdDesc();

	Employee findByEmployeeId(Integer employeeId);

	Employee findByEmail(String email);

	Employee findByMobileNumber(String mobileNumber);

	Employee findByTokenUUID(String tokenUUID);

}
