package com.sliit.betterwellness.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CounsellorsDTO {
	private List<CounsellorDTO> counsellors = new ArrayList<>();

	public List<CounsellorDTO> getCounsellors() {
		return counsellors;
	}

	public void setCounsellors(List<CounsellorDTO> counsellors) {
		this.counsellors = counsellors;
	}
}
