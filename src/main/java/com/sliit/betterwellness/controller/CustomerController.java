package com.sliit.betterwellness.controller;

import com.sliit.betterwellness.dto.CustomerDTO;
import com.sliit.betterwellness.service.CustomerService;
import com.sliit.betterwellness.utills.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-service")
public class CustomerController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/customer")
	public ResponseEntity<CustomerDTO> updateUser(@RequestBody CustomerDTO customerDTO, @RequestHeader Map<String, String> headers){
		logger.info("[IN] Customer save request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		CustomerDTO response = customerService.saveCustomer(customerDTO);
		logger.info("[OUT] Customer save request correlation-id : {}, Request {}, Response {}", headers.get(Constants.HEADER_CORRELATION_ID), customerDTO, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/customer")
	public ResponseEntity<List<CustomerDTO>> searchUser(@RequestParam(name = "search", required = false, defaultValue = "") String search, @RequestHeader Map<String, String> headers){
		logger.info("[IN] Customer search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		List<CustomerDTO> response = customerService.searchCustomer(search);
		logger.info("[OUT] Customer search request correlation-id : {}, Response {}", headers.get(Constants.HEADER_CORRELATION_ID), response);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CustomerDTO> searchUser(@PathVariable int customerId, @RequestHeader Map<String, String> headers){
		logger.info("[IN] Customer get request correlation-id : {}, customerId {}", headers.get(Constants.HEADER_CORRELATION_ID), customerId);
		CustomerDTO response = customerService.getCustomer(customerId);
		logger.info("[OUT] Customer search request correlation-id : {}, Response {}", headers.get(Constants.HEADER_CORRELATION_ID), response);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<CustomerDTO> deleteUser(@PathVariable int customerId, @RequestHeader Map<String, String> headers){
		logger.info("[IN] Customer delete request correlation-id : {}, customerId {}", headers.get(Constants.HEADER_CORRELATION_ID), customerId);
		CustomerDTO response = customerService.deleteCustomer(customerId);
		logger.info("[OUT] Customer delete request correlation-id : {}, Response {}", headers.get(Constants.HEADER_CORRELATION_ID), response);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
