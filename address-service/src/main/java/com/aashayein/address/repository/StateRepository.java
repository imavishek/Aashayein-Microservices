/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.repository
 * @FileName: StateRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 13-07-2019
 * @Modified_By avishek.das @Last_On 13-Jul-2019 11:36:47 PM
 */

package com.aashayein.address.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashayein.address.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	List<State> findByCountryIdOrderByStateNameAsc(Integer countryId);
}
