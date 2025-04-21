package com.sliit.betterwellness.controller;

import com.sliit.betterwellness.dto.CounsellorDTO;
import com.sliit.betterwellness.dto.CounsellorsDTO;
import com.sliit.betterwellness.service.CounsellorService;
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
public class CounsellorController {

	private static final Logger log = LogManager.getLogger(CounsellorController.class);
	private final CounsellorService counsellorService;

	@Autowired
	public CounsellorController(CounsellorService counsellorService) {
		this.counsellorService = counsellorService;
	}

	@PostMapping("/counsellor")
	public ResponseEntity<CounsellorDTO> updateUser(@RequestBody CounsellorDTO counsellorDTO, @RequestHeader Map<String, String> headers){
		log.info("[IN] Counsellor save request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		CounsellorDTO response = counsellorService.saveCounsellor(counsellorDTO);
		log.info("[OUT] Counsellor save request correlation-id : {}, Request {}, Response {}", headers.get(Constants.HEADER_CORRELATION_ID), counsellorDTO, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/counsellor")
	public ResponseEntity<CounsellorsDTO> searchUser(@RequestParam(name = "search", required = false, defaultValue = "") String search, @RequestHeader Map<String, String> headers){
		log.info("[IN] Counsellor search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		CounsellorsDTO response = new CounsellorsDTO();
		response.setCounsellors(counsellorService.searchCounsellor(search));
		log.info("[OUT] Counsellor search request correlation-id : {}, Response {}", headers.get(Constants.HEADER_CORRELATION_ID), response);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/counsellor/{counsellorId}")
	public ResponseEntity<CounsellorDTO> searchUser(@PathVariable int counsellorId, @RequestHeader Map<String, String> headers){
		log.info("[IN] Counsellor get request correlation-id : {}, counsellorId {}", headers.get(Constants.HEADER_CORRELATION_ID), counsellorId);
		CounsellorDTO response = counsellorService.getCounsellor(counsellorId);
		log.info("[OUT] Counsellor get request correlation-id : {}, counsellorId {}, response {}", headers.get(Constants.HEADER_CORRELATION_ID), counsellorId, response);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/counsellor/{counsellorId}")
	public ResponseEntity<CounsellorDTO> deleteUser(@PathVariable int counsellorId, @RequestHeader Map<String, String> headers){
		log.info("[IN] Counsellor delete request correlation-id : {}, counsellorId {}", headers.get(Constants.HEADER_CORRELATION_ID), counsellorId);
		CounsellorDTO response = counsellorService.deleteCounsellor(counsellorId);
		log.info("[OUT] Counsellor delete request correlation-id : {}, counsellorId {}", headers.get(Constants.HEADER_CORRELATION_ID), counsellorId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
