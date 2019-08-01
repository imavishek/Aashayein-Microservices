/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.repository
 * @FileName: EmployeeModuleRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 10:14:40 PM
 */

package com.aashayein.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashayein.role.entities.EmployeeModule;

public interface EmployeeModuleRepository extends JpaRepository<EmployeeModule, Integer> {

	List<EmployeeModule> findByOrderByModuleNameAsc();
}
