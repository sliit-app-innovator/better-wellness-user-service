package com.sliit.betterwellness.dto;

import com.sliit.betterwellness.entity.Counsellor;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
public class CounsellorDTO {
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String specializations;
	private Date lastLoginAt;
	private Date createdAt;
	private Date updatedAt;

	public Counsellor toEntity() {
		Counsellor counsellor = new Counsellor();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(this, counsellor);
		return counsellor;
	}
}
