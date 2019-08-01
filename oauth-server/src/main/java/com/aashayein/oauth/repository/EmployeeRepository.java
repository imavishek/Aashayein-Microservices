/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.repository
 * @FileName: EmployeeRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 27-07-2019
 * @Modified_By avishek.das @Last_On 27-Jul-2019 3:03:11 PM
 */

package com.aashayein.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashayein.oauth.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByEmail(String email);
}
