/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.repository
 * @FileName: EmployeeAddressRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 20-07-2019
 * @Modified_By avishek.das @Last_On 20-Jul-2019 12:28:25 AM
 */

package com.aashayein.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashayein.employee.entities.EmployeeAddress;

@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Integer> {

}
