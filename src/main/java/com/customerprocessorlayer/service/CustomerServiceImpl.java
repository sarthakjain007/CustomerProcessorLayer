package com.customerprocessorlayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerprocessorlayer.dto.CustomerDTO;
import com.customerprocessorlayer.dto.CustomerInputDTO;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	GraphQLWebClient graphQLWebClient;

	@Override
	public CustomerDTO getCustomer(Integer id) {
		String queryStr = "query{\r\n" + "    customerById(customerId:" + id + "){\r\n" + "        firstName\r\n"
				+ "        lastName\r\n" + "        email\r\n" + "    }\r\n" + "}";

		GraphQLRequest request = GraphQLRequest.builder().query(queryStr).build();
		GraphQLResponse response = graphQLWebClient.post(request).block();
		CustomerDTO customer = response.get("customerById", CustomerDTO.class);
		return customer;

	}

	@Override
	public List<CustomerDTO> getCustomers() {
		String queryStr = "query{\r\n" + "    customerDetails {\r\n" + "        firstName\r\n" + "        lastName\r\n"
				+ "        email\r\n" + "    }\r\n" + "}";

		GraphQLRequest request = GraphQLRequest.builder().query(queryStr).build();
		GraphQLResponse response = graphQLWebClient.post(request).block();

		List<CustomerDTO> customers = response.getList("customerDetails", CustomerDTO.class);
		return customers;

	}

	@Override
	public List<CustomerDTO> getFirstNCustomes(Integer limit) {
		String queryStr = "query{\r\n" + "    firstNCustomers(limit:" + limit + "){\r\n" + "        firstName\r\n"
				+ "        lastName\r\n" + "        email\r\n" + "    }\r\n" + "}";
		GraphQLRequest request = GraphQLRequest.builder().query(queryStr).build();
		GraphQLResponse response = graphQLWebClient.post(request).block();

		List<CustomerDTO> customers = response.getList("customerDetails", CustomerDTO.class);
		return customers;
	}

	@Override
	public CustomerDTO createCustomer(CustomerInputDTO customerInputDTO) {
		String mutationStr = "mutation{\n" + "  createCustomer(customerInputDTO:{\n" + "    firstName:\" "
				+ customerInputDTO.getFirstName() + " \",\n" + "    lastName:\" " + customerInputDTO.getLastName()
				+ " \",\n" + "    emailId:\"  " + customerInputDTO.getEmail() + "  \",\n" + "    phoneNumber:\" "
				+ customerInputDTO.getPhoneNumber() + " \"\n" + "  }) {\n" + "    customerId\n" + "  }\n" + "}\n";

		GraphQLRequest graphQLRequest = GraphQLRequest.builder().query(mutationStr).build();
		GraphQLResponse graphQLResponse = graphQLWebClient.post(graphQLRequest).block();

		CustomerDTO customer = graphQLResponse.get("createCustomer", CustomerDTO.class);
		return customer;
	}

}
