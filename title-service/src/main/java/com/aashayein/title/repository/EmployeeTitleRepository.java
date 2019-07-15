/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.repository
 * @FileName: EmployeeTitleRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2019
 * @Modified_By avishek.das @Last_On 14-Jul-2019 2:13:25 PM
 */

package com.aashayein.title.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashayein.title.entities.EmployeeTitle;

@Repository
public interface EmployeeTitleRepository extends JpaRepository<EmployeeTitle, Integer> {

	List<EmployeeTitle> findByOrderByTitleNameAsc();

	EmployeeTitle findByTitleId(Integer titleId);
}
