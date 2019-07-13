/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.serviceImpl
 * @FileName: AddressServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 13-07-2019
 * @Modified_By avishek.das @Last_On 13-Jul-2019 11:33:46 PM
 */

package com.aashayein.address.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashayein.address.dto.CityTO;
import com.aashayein.address.dto.CountryTO;
import com.aashayein.address.dto.StateTO;
import com.aashayein.address.entities.City;
import com.aashayein.address.entities.Country;
import com.aashayein.address.entities.State;
import com.aashayein.address.repository.CityRepository;
import com.aashayein.address.repository.CountryRepository;
import com.aashayein.address.repository.StateRepository;
import com.aashayein.address.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<CountryTO> getCountries() {
		List<CountryTO> countryTo = new ArrayList<CountryTO>();

		List<Country> countries = countryRepository.findByOrderByCountryNameAsc();

		if (!countries.isEmpty()) {
			for (Country country : countries) {
				CountryTO cTo = new CountryTO();

				cTo.setCountryId(country.getCountryId());
				cTo.setCountryName(country.getCountryName());
				cTo.setIsdCode(country.getIsdCode());
				cTo.setIsoCode(country.getIsoCode());

				countryTo.add(cTo);
			}
		}

		return countryTo;
	}

	@Override
	@Transactional
	public List<StateTO> getStatesByCountry(Integer countryId) {
		List<StateTO> stateTo = new ArrayList<StateTO>();

		List<State> states = stateRepository.findByCountryIdOrderByStateNameAsc(countryId);

		if (!states.isEmpty()) {
			for (State state : states) {
				StateTO sTo = new StateTO();

				sTo.setStateId(state.getStateId());
				sTo.setStateName(state.getStateName());

				stateTo.add(sTo);
			}
		}

		return stateTo;
	}

	@Override
	public List<CityTO> getCitiesByState(Integer stateId) {
		List<CityTO> cityTo = new ArrayList<CityTO>();

		List<City> cities = cityRepository.findByStateIdOrderByCityNameAsc(stateId);

		if (!cities.isEmpty()) {
			for (City city : cities) {
				CityTO cTo = new CityTO();

				cTo.setCityId(city.getCityId());
				cTo.setCityName(city.getCityName());

				cityTo.add(cTo);
			}
		}

		return cityTo;
	}

}
