package com.sliit.betterwellness.dto;

import com.sliit.betterwellness.entity.Counsellor;
import com.sliit.betterwellness.entity.Customer;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
public class CustomerDTO {
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private int age;
	private Date lastLoginAt;
	private Date createdAt;
	private Date updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Customer toEntity() {
		Customer counsellor = new Customer();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(this, counsellor);
		return counsellor;
	}
}
