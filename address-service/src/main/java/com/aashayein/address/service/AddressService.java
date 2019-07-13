/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.service
 * @FileName: AddressService.java
 * @Author: Avishek Das
 * @CreatedDate: 13-07-2019
 * @Modified_By avishek.das @Last_On 13-Jul-2019 11:24:48 PM
 */

package com.aashayein.address.service;

import java.util.List;

import com.aashayein.address.dto.CityTO;
import com.aashayein.address.dto.CountryTO;
import com.aashayein.address.dto.StateTO;

public interface AddressService {

	List<CountryTO> getCountries();

	List<StateTO> getStatesByCountry(Integer countryId);

	List<CityTO> getCitiesByState(Integer stateId);
}
