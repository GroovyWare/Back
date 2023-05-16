package com.groovy.ware.play.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.EmployeeRepository;
import com.groovy.ware.history.entity.History;
import com.groovy.ware.history.repository.HistoryRepository;
import com.groovy.ware.member.dto.MemberDto;
import com.groovy.ware.member.entity.Member;
import com.groovy.ware.member.repository.MemberRepository;
import com.groovy.ware.play.repository.PlayRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlayService {
	
	private PlayRepository playRepository;
	private EmployeeRepository employeeRepository;
	private MemberRepository memberRepository;
	private HistoryRepository historyRepository;
	private ModelMapper modelMapper;
	
	public PlayService(PlayRepository playRepository, ModelMapper modelMapper, EmployeeRepository employeeRepository, MemberRepository memberRepository, HistoryRepository historyRepository) {
		this.playRepository = playRepository;
		this.modelMapper = modelMapper;
		this.employeeRepository = employeeRepository;
		this.memberRepository = memberRepository;
		this.historyRepository = historyRepository;
	}
	
	public Page<MemberDto> selectMemberList(int page, String empId) {
		
		log.info("service start==========================");
		
		Pageable pageable = PageRequest.of(page -1,  10, Sort.by("memCode").descending());
		
		Employee findTrainer = employeeRepository.findByEmpId(empId).orElseThrow(() -> new IllegalArgumentException("일치하는 직원 목록이 없습니다."));
		log.info("findTrainer {}", findTrainer);
		
		History findPlayMember = historyRepository.findByEmployee(findTrainer);
		log.info("findPlayMember {}", findPlayMember);
		
		Page<Member> playMemberList = memberRepository.findByMemCode(pageable, findPlayMember.getMemCode());
		Page<MemberDto> playDtoMemberList = playMemberList.map(row -> modelMapper.map(row, MemberDto.class));
		
		log.info("playMemberList {}", playMemberList);
		
		log.info("service start==========================");
		
		return playDtoMemberList;
	}

}
