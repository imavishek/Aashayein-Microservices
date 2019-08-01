/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.repository
 * @FileName: EmployeeRoleAccessRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 18-07-2019
 * @Modified_By avishek.das @Last_On 18-Jul-2019 2:34:10 PM
 */

package com.aashayein.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aashayein.role.entities.EmployeeRoleAccess;
import com.aashayein.role.entities.RoleId_ModuleId;

@Repository
public interface EmployeeRoleAccessRepository extends JpaRepository<EmployeeRoleAccess, RoleId_ModuleId> {

	@Modifying
	@Query("DELETE FROM EmployeeRoleAccess roleAccess WHERE roleAccess.roleId_ModuleId.roleId=?1")
	void deleteByRoleId(Integer roleId);
}
