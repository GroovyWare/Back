package com.groovy.ware.employee.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.groovy.ware.common.exception.LoginFailedException;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.dto.TokenDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.EmployeeRepository;
import com.groovy.ware.jwt.TokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {

	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final ModelMapper modelMapper;
	
	public AuthService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
		this.modelMapper = modelMapper;
	}

	/* 로그인 */
	public TokenDto login(EmployeeDto employeeDto) {
		log.info("[AuthService] login start ==============================================");
		log.info("[AuthService] employeeDto : {}", employeeDto);
		
		// 1. 아이디로 DB에서 해당 유저가 잇는지 조회
		Employee employee = employeeRepository.findByEmpId(employeeDto.getEmpId())
				.orElseThrow(() -> new LoginFailedException("잘못된 아이디 또는 비밀번호입니다."));
		
		// 2. 비밀번호 매칭 확인
		if(!passwordEncoder.matches(employeeDto.getEmpPassword(), employee.getEmpPassword())) {
			throw new LoginFailedException("잘못된 아이디 또는 비밀번호입니다.");
		}
		
		// 3. 토큰 발급
		TokenDto tokenDto = tokenProvider.generateToken(modelMapper.map(employee, EmployeeDto.class));
		log.info("[AuthService] tokenDto : {}", tokenDto);
		
		log.info("[AuthService] login end ==============================================");
		return tokenDto;
	}
	
	
}
