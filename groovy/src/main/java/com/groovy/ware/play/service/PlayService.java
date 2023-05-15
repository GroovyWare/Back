package com.groovy.ware.play.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
	private HistoryRepository historyRepository;
	private ModelMapper modelMapper;
	private EmployeeRepository employeeRepository;
	private MemberRepository memberRepository;
	
	public PlayService(PlayRepository playRepository, ModelMapper modelMapper, HistoryRepository historyRepository, EmployeeRepository employeeRepository, MemberRepository memberRepository) {
		this.playRepository = playRepository;
		this.modelMapper = modelMapper;
		this.historyRepository = historyRepository;
		this.employeeRepository = employeeRepository;
		this.memberRepository = memberRepository;
	}
	
	public Page<MemberDto> selectMemberList(int page, Long empCode) {
		
		Pageable pageable = PageRequest.of(page -1,  10, Sort.by("empCode").descending());
		
		History findMemberCode = historyRepository.findByEmployee(empCode);
		
		Page<Member> playMemberList = memberRepository.findByEmpCode(pageable, findMemberCode.getMemCode());
		Page<MemberDto> playDtoMemberList = playMemberList.map(row -> modelMapper.map(row, MemberDto.class));
		
		return playDtoMemberList;
	}

}
