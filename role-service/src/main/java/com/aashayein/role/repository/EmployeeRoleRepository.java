/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.repository
 * @FileName: EmployeeRoleRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:10:17 PM
 */

package com.aashayein.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashayein.role.entities.EmployeeRole;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {

	List<EmployeeRole> findByOrderByRoleNameAsc();

	EmployeeRole findByRoleId(Integer roleId);
}
