package com.sliit.betterwellness.service;

import com.sliit.betterwellness.dto.CustomerDTO;
import com.sliit.betterwellness.entity.Customer;
import com.sliit.betterwellness.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public CustomerDTO saveCustomer(CustomerDTO customerDTO){

		Customer customer = customerRepository.byUserName(customerDTO.getUsername().toLowerCase());
		if (customer == null) {
			customerDTO.setUsername(customerDTO.getUsername().toLowerCase());
			customerDTO.setCreatedAt(new Date());
			customerDTO.setUpdatedAt(new Date());
		} else {
			customerDTO = customer.toDto();
			customerDTO.setUpdatedAt(new Date());
		}
		customerDTO.setLastLoginAt(new Date());
		this.customerRepository.save(customerDTO.toEntity());
		customer = customerRepository.byUserName(customerDTO.getUsername());
		return customer.toDto();
	}

	public List<CustomerDTO> searchCustomer(String search){
		List<Customer> customers = customerRepository.search(search);
		return customers.stream()
				.map(Customer::toDto)  // Using the toDto() method
				.toList();
	}
	public CustomerDTO getCustomer(int id){
		Optional<Customer> customerDTOOptional = customerRepository.findById(id);
		if(customerDTOOptional.isPresent()) {
			return customerDTOOptional.get().toDto();
		}
		throw new RuntimeException();
	}
	public CustomerDTO deleteCustomer(int id){
		Optional<Customer> customerDTOOptional = customerRepository.findById(id);
		if(customerDTOOptional.isPresent()) {
			Customer customer = customerDTOOptional.get();
			customerRepository.deleteById(id);
			return customer.toDto();
		}
		throw new RuntimeException();
	}
}
