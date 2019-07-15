/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.serviceImpl
 * @FileName: EmployeeTitleServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2019
 * @Modified_By avishek.das @Last_On 14-Jul-2019 2:12:31 PM
 */

package com.aashayein.title.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashayein.title.dto.EmployeeTitleTO;
import com.aashayein.title.entities.EmployeeTitle;
import com.aashayein.title.exception.DatabindingException;
import com.aashayein.title.repository.EmployeeTitleRepository;
import com.aashayein.title.service.EmployeeTitleService;
import com.aashayein.title.util.ConvertEntityToTransferObject;
import com.aashayein.title.util.ConvertTransferObjectToEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeTitleServiceImpl implements EmployeeTitleService {

	@Autowired
	private EmployeeTitleRepository employeeTitleRepository;

	@Autowired
	private ConvertEntityToTransferObject convertEntityToTransferObject;

	@Autowired
	private ConvertTransferObjectToEntity convertTransferObjectToEntity;

	@Override
	@Transactional
	public List<EmployeeTitleTO> getJobTitles() {
		List<EmployeeTitleTO> employeeTitles = new ArrayList<EmployeeTitleTO>();

		List<EmployeeTitle> employeeTitle = employeeTitleRepository.findByOrderByTitleNameAsc();

		if (!employeeTitle.isEmpty()) {
			for (EmployeeTitle title : employeeTitle) {
				employeeTitles.add(convertEntityToTransferObject.convertEmployeeTitleToEmployeeTitleTO(title));
			}
		}

		return employeeTitles;
	}

	@Override
	@Transactional
	public EmployeeTitleTO getEmployeeTitleById(Integer employeeTitleId) {

		EmployeeTitle employeeTitle = null;
		EmployeeTitleTO employeeTitleTO = null;

		employeeTitle = employeeTitleRepository.findByTitleId(employeeTitleId);

		if (employeeTitle != null) {
			employeeTitleTO = convertEntityToTransferObject.convertEmployeeTitleToEmployeeTitleTO(employeeTitle);
		}

		return employeeTitleTO;
	}

	@Override
	@Transactional
	public EmployeeTitleTO addEmployeeTitle(EmployeeTitleTO employeeTitleTo) {

		EmployeeTitle title = convertTransferObjectToEntity.convertEmployeeTitleTOToEmployeeTitle(employeeTitleTo);

		return convertEntityToTransferObject.convertEmployeeTitleToEmployeeTitleTO(employeeTitleRepository.save(title));
	}

	@Override
	@Transactional
	public EmployeeTitleTO updateEmployeeTitle(EmployeeTitleTO employeeTitleTo) {

		EmployeeTitle employeeTitle = null;
		EmployeeTitleTO employeeTitleTO = null;

		employeeTitle = employeeTitleRepository.findByTitleId(employeeTitleTo.getTitleId());

		if (employeeTitle != null) {
			employeeTitle.setTitleName(employeeTitleTo.getTitleName());
			employeeTitle = employeeTitleRepository.save(employeeTitle);

			employeeTitleTO = convertEntityToTransferObject.convertEmployeeTitleToEmployeeTitleTO(employeeTitle);
		}

		return employeeTitleTO;
	}

	@Override
	@Transactional
	public EmployeeTitleTO deleteEmployeeTitle(Integer employeeTitleId) {

		EmployeeTitle employeeTitle = null;
		EmployeeTitleTO employeeTitleTO = null;

		employeeTitle = employeeTitleRepository.findByTitleId(employeeTitleId);

		if (employeeTitle != null) {
			employeeTitle.setArchive((byte) 1);
			employeeTitle = employeeTitleRepository.save(employeeTitle);

			employeeTitleTO = convertEntityToTransferObject.convertEmployeeTitleToEmployeeTitleTO(employeeTitle);
		}

		return employeeTitleTO;
	}

	@Override
	@Transactional
	public EmployeeTitleTO activeEmployeeTitle(Integer employeeTitleId) {

		EmployeeTitle employeeTitle = null;
		EmployeeTitleTO employeeTitleTO = null;

		employeeTitle = employeeTitleRepository.findByTitleId(employeeTitleId);

		if (employeeTitle != null) {
			employeeTitle.setArchive((byte) 0);
			employeeTitle = employeeTitleRepository.save(employeeTitle);

			employeeTitleTO = convertEntityToTransferObject.convertEmployeeTitleToEmployeeTitleTO(employeeTitle);
		}

		return employeeTitleTO;
	}

	@Override
	public Boolean isValidEmployeeTitleId(String id) throws DatabindingException {
		Boolean valid = false;
		List<String> messages = new ArrayList<String>();

		try {
			Integer.parseInt(id);
			valid = true;
		} catch (NumberFormatException e) {
			log.error("Invalid EmployeeTitle Id");
			messages.add("Invalid EmployeeTitle Id");

			// Throw Databinding Exception with error messages
			throw new DatabindingException(messages);
		}

		return valid;

	}

}
