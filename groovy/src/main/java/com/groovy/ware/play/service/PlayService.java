package com.groovy.ware.play.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.groovy.ware.employee.repository.EmployeeRepository;
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
	private ModelMapper modelMapper;
	
	public PlayService(PlayRepository playRepository, ModelMapper modelMapper, EmployeeRepository employeeRepository, MemberRepository memberRepository) {
		this.playRepository = playRepository;
		this.modelMapper = modelMapper;
		this.employeeRepository = employeeRepository;
		this.memberRepository = memberRepository;
	}

	public Page<MemberDto> selectMemberList(int page) {
		
		Pageable pageable = PageRequest.of(page -1,  10, Sort.by("memCode").descending());
	
		Page<Member> playMemberList = memberRepository.findAll(pageable);
		Page<MemberDto> playDtoMemberList = playMemberList.map(member -> modelMapper.map(member, MemberDto.class));
		
		return playDtoMemberList;
	}
	
//	public Page<MemberDto> selectMemberList(int page) {
//		
//		Pageable pageable = PageRequest.of(page -1,  10, Sort.by("playCode").descending());
//		
//		Employee findMember = employeeRepository.findAll().orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));
//		
//		Page<Member> playMemberList = memberRepository.findByEmpCode(pageable, findMember);
//		Page<MemberDto> playDtoMemberList = playMemberList.map(row -> modelMapper.map(row, MemberDto.class));
//		
//		return playDtoMemberList;
//	}

}
