package com.sliit.betterwellness.entity;

import com.sliit.betterwellness.dto.CounsellorDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@Entity
@Table(name = "counsellor")
public class Counsellor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true, length = 50)
	private String username;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false, length = 4000)
	private String specializations;

	@Column
	private Date lastLoginAt;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	@Column(nullable = false)
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	public CounsellorDTO toDto() {
		CounsellorDTO counsellor = new CounsellorDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(this, counsellor);
		return counsellor;
	}
}
