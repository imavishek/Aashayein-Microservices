/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.controller
 * @FileName: AddressController.java
 * @Author: Avishek Das
 * @CreatedDate: 05-07-2019
 * @Modified_By avishek.das @Last_On 05-Jul-2019 4:41:00 PM
 */

package com.aashayein.address.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashayein.address.dto.CityTO;
import com.aashayein.address.dto.CountryTO;
import com.aashayein.address.dto.StateTO;
import com.aashayein.address.exception.DatabindingException;
import com.aashayein.address.service.AddressService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/Address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	// Get service provided countries
	@GetMapping(value = "/getCountries")
	public List<CountryTO> getCountries() {

		return addressService.getCountries();

	}

	// Get service provided states
	@GetMapping(value = "/getStatesByCountry/{countryId}")
	public List<StateTO> getStatesByCountry(@PathVariable(name = "countryId") String countryId)
			throws DatabindingException {

		Integer id = null;
		List<String> messages = new ArrayList<String>();

		try {
			id = Integer.parseInt(countryId);

			return addressService.getStatesByCountry(id);
		} catch (NumberFormatException e) {
			log.error("Invalid Country Id");
			messages.add("Invalid Country Id");

			// Throw Databinding Exception with error messages
			throw new DatabindingException(messages);
		}

	}

	// Get service provided cities
	@GetMapping(value = "/getCitiesByState/{stateId}")
	public List<CityTO> getCitiesByState(@PathVariable(name = "stateId") String stateId) throws DatabindingException {

		Integer id = null;
		List<String> messages = new ArrayList<String>();

		try {
			id = Integer.parseInt(stateId);

			return addressService.getCitiesByState(id);
		} catch (NumberFormatException e) {
			log.error("Invalid State Id");
			messages.add("Invalid State Id");

			// Throw Databinding Exception with error messages
			throw new DatabindingException(messages);
		}

	}
}
