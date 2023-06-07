package com.groovy.ware.employee.service;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.groovy.ware.common.exception.UserNotFoundException;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;
	
	public CustomUserDetailService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		return employeeRepository.findByEmpId(userId) 
				.map(user -> addAuthorities(user)) 
				.orElseThrow(() -> new UserNotFoundException(userId + "를 찾을 수 없습니다."));
	}

	private EmployeeDto addAuthorities(Employee employee) {
		
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

		employeeDto.setAuthorities(employeeDto.getAuths().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth().getAuthName())).collect(Collectors.toList()));

		return employeeDto;
	}
	
	
	
	
	
	
	
	
}
