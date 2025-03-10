package com.sliit.betterwellness.service;

import com.sliit.betterwellness.dto.CounsellorDTO;
import com.sliit.betterwellness.entity.Counsellor;
import com.sliit.betterwellness.entity.Customer;
import com.sliit.betterwellness.repository.CounsellorRepository;
import com.sliit.betterwellness.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CounsellorService {

	private final CounsellorRepository counsellorRepository;

	@Autowired
	public CounsellorService(CounsellorRepository counsellorRepository) {
		this.counsellorRepository = counsellorRepository;
	}

	public CounsellorDTO saveCounsellor(CounsellorDTO counsellorDTO){
		Counsellor counsellor = counsellorRepository.byUserName(counsellorDTO.getUsername().toLowerCase());
		if (counsellor == null) {
			counsellorDTO.setUsername(counsellorDTO.getUsername().toLowerCase());
			counsellorDTO.setCreatedAt(new Date());
			counsellorDTO.setUpdatedAt(new Date());
		} else {
			counsellorDTO = counsellor.toDto();
			counsellorDTO.setUpdatedAt(new Date());
		}
		counsellorDTO.setLastLoginAt(new Date());
		this.counsellorRepository.save(counsellorDTO.toEntity());
		counsellor = counsellorRepository.byUserName(counsellorDTO.getUsername());
		return counsellor.toDto();
	}

	public List<CounsellorDTO> searchCounsellor(String search){
		List<Counsellor> counsellors = counsellorRepository.search(search);
		return counsellors.stream()
				.map(Counsellor::toDto)
				.toList();
	}
	public CounsellorDTO getCounsellor(int id){
		Optional<Counsellor> customerDTOOptional = counsellorRepository.findById(id);
		if(customerDTOOptional.isPresent()) {
			return customerDTOOptional.get().toDto();
		}
		throw new RuntimeException();
	}
	public CounsellorDTO deleteCounsellor(int id){
		Optional<Counsellor> customerDTOOptional = counsellorRepository.findById(id);
		if(customerDTOOptional.isPresent()) {
			Counsellor customer = customerDTOOptional.get();
			counsellorRepository.deleteById(id);
			return customer.toDto();
		}
		throw new RuntimeException();
	}
}
