package com.sliit.betterwellness.controller;

import com.sliit.betterwellness.dto.CounsellorDTO;
import com.sliit.betterwellness.service.CounsellorService;
import com.sliit.betterwellness.utills.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/better-wellness")
@Slf4j
public class CounsellorController {


	private final CounsellorService counsellorService;

	@Autowired
	public CounsellorController(CounsellorService counsellorService) {
		this.counsellorService = counsellorService;
	}

	@PostMapping("/counsellor")
	public ResponseEntity<CounsellorDTO> updateUser(@RequestBody CounsellorDTO counsellorDTO, @RequestHeader Map<String, String> headers){
	//	log.info("Counsellor save request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.CREATED).body(counsellorService.saveCounsellor(counsellorDTO));
	}

	@GetMapping("/counsellor")
	public ResponseEntity<List<CounsellorDTO>> searchUser(@RequestParam(name = "search", required = false, defaultValue = "") String search, @RequestHeader Map<String, String> headers){
	//	log.info("Counsellor search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.OK).body(counsellorService.searchCounsellor(search));
	}

	@GetMapping("/counsellor/{counsellorId}")
	public ResponseEntity<CounsellorDTO> searchUser(@PathVariable int counsellorId, @RequestHeader Map<String, String> headers){
	//	log.info("Counsellor get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.OK).body(counsellorService.getCounsellor(counsellorId));
	}

	@DeleteMapping("/counsellor/{counsellorId}")
	public ResponseEntity<CounsellorDTO> deleteUser(@PathVariable int counsellorId, @RequestHeader Map<String, String> headers){
	//	log.info("Counsellor delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.CREATED).body(counsellorService.deleteCounsellor(counsellorId));
	}
}
