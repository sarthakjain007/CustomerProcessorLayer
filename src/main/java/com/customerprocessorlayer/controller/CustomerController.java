package com.customerprocessorlayer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customerprocessorlayer.dto.CustomerInputDTO;
import com.customerprocessorlayer.service.CustomerService;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET, value = "/details")
	public ResponseEntity<Object> getCustomers() {
		try {
			return new ResponseEntity<Object>(customerService.getCustomers(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/limit/{limit}")
	public ResponseEntity<Object> getFirstNCustomers(@PathVariable Integer limit) {
		try {
			return new ResponseEntity<Object>(customerService.getFirstNCustomes(limit), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{customerId}")
	public ResponseEntity<Object> getCustomer(@PathVariable Integer customerId) {
		try {
			return new ResponseEntity<Object>(customerService.getCustomer(customerId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public ResponseEntity<Object> createCustomer(@RequestBody CustomerInputDTO customerAddressRequest) {
		try {
			return new ResponseEntity<Object>(customerService.createCustomer(customerAddressRequest), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
