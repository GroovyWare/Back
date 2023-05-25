package com.groovy.ware.employee.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groovy.ware.common.dto.FileDto;

import lombok.Data;

@Data
public class EmployeeDto implements UserDetails {
	
	private Long empCode;	
	private String empId;
	private String empPassword;
	private String empName;
	private String empPhone;
	private String empEmail;
	private String empAddress;
	private Date empEntDate;
	private Date empExDate;
	private String EmpStatus;
	private DepartmentDto dept;
	private PositionDto position;
	private FileDto file;
	private List<EmpAuthDto> auths;
	
	@JsonIgnore
	private MultipartFile imgUrl;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return empPassword;
	}

	@Override
	public String getUsername() {
		return empId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
