package com.customerprocessorlayer.service;

import java.util.List;

import com.customerprocessorlayer.dto.CustomerDTO;
import com.customerprocessorlayer.dto.CustomerInputDTO;

public interface CustomerService {

	public CustomerDTO getCustomer(Integer id);

	public List<CustomerDTO> getCustomers();

	public List<CustomerDTO> getFirstNCustomes(Integer limit);

	public CustomerDTO createCustomer(CustomerInputDTO customerAddressInputDTO);

}
