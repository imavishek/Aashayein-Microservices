/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.repository
 * @FileName: AddressRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 13-07-2019
 * @Modified_By avishek.das @Last_On 13-Jul-2019 11:34:44 PM
 */

package com.aashayein.address.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashayein.address.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	List<Country> findByOrderByCountryNameAsc();
}
