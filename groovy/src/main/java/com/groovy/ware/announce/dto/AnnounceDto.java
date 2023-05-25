package com.groovy.ware.announce.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groovy.ware.common.dto.FileDto;
import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class AnnounceDto {
	
	private Long annCode;				// 공지사항 코드
	private String annTitle;			// 제목
	private Date annDate;				// 작성일
	private EmployeeDto employee;		// 직원 코드
	private String annContent;			// 내용
	
	@JsonIgnore
	private MultipartFile announceImage;
	
//	private List<FileDto> files;
	private List<FileDto> files = new ArrayList<>();
	
	public void setEmployee(EmployeeDto employeeDto) {
        ModelMapper modelMapper = new ModelMapper();
        this.employee = modelMapper.map(employeeDto, EmployeeDto.class);
    }

}
