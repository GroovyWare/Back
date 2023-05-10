package com.groovy.ware.member.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.groovy.ware.member.dto.MemberDto;
import com.groovy.ware.member.entity.Member;
import com.groovy.ware.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	
	/* 의존성 주입 */
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;


	public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
		
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
	}
	
	
	/* 1. 회원 전체 조회 리스트 */
	public Page<MemberDto> findMemberAll(int page) {
		
		log.info("[MemberService] findMemberAll start ==================");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("memCode").descending());

		Page<Member> memberList = memberRepository.findAll(pageable);
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));
		
		log.info("[MemberService] findMemberAllList.getContent(): {}", memberDtoList.getContent());
		log.info("[MemberService] findMemberAll end ==================");
		
		return memberDtoList;
	}
	
	
	
	
	
	
	
	
	
	
	

}
