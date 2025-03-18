package com.sliit.betterwellness.controller;

import com.sliit.betterwellness.dto.CustomerDTO;
import com.sliit.betterwellness.service.CustomerService;
import com.sliit.betterwellness.utills.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/better-wellness/user")
@Slf4j
public class CustomerController {


	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/customer")
	public ResponseEntity<CustomerDTO> updateUser(@RequestBody CustomerDTO customerDTO, @RequestHeader Map<String, String> headers){
	//	log.info("Customer save request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customerDTO));
	}

	@GetMapping("/customer")
	public ResponseEntity<List<CustomerDTO>> searchUser(@RequestParam(name = "search", required = false, defaultValue = "") String search, @RequestHeader Map<String, String> headers){
	//	log.info("Customer search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.OK).body(customerService.searchCustomer(search));
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CustomerDTO> searchUser(@PathVariable int customerId, @RequestHeader Map<String, String> headers){
	//	log.info("Customer get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(customerId));
	}

	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<CustomerDTO> deleteUser(@PathVariable int customerId, @RequestHeader Map<String, String> headers){
	//	log.info("Customer delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.deleteCustomer(customerId));
	}
}
