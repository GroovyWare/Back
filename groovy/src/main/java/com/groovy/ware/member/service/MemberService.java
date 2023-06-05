package com.groovy.ware.member.service;


import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groovy.ware.history.entity.History;
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



	public MemberService(MemberRepository memberRepository,  ModelMapper modelMapper) {
		
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
	}
	
	
	/* 전체 회원 리스트 */
	public Page<MemberDto> findMemberListAll(int page) {
		
		log.info("[MemberService] findMemberListAll start ==================");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("memCode").descending());
		

		Page<Member> memberList = memberRepository.findAll(pageable);
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));

		log.info("[MemberService] findMemberListAll end ==================");
		
		return memberDtoList;
	}
	

	
	/* 회원 상세 조회 */
	public MemberDto findMemberDetail(Long memCode) {
		
		log.info("[MemberService] : findMemberDetail start ==================================== ");
		log.info("[MemberService] : memCode : {}", memCode);
		
		Member member = memberRepository.findById(memCode).orElseThrow();
		
		MemberDto memberDto = modelMapper.map(member, MemberDto.class);
		
		log.info("[MemberService] : memberDto : {}", memberDto);
		log.info("[MemberService] : findMemberDetail end ==================================== ");
		
		
		return memberDto;
	}
	
	
	/* 회원 등록 */
	@Transactional
	public void insertMember(MemberDto memberDto) {
		
		log.info("[MemberService] : insertMember start ==================================== ");
		log.info("[MemberService] : memberDto : {}", memberDto);

		/* 회원 정보 입력 */
		memberRepository.save(modelMapper.map(memberDto, Member.class));

		log.info("[MemberService] : insertMember end ==================================== ");
		
	}
	

	/* 회원 정보 수정 */
	@Transactional
	public void modifyMember(MemberDto memberDto) {
		
		log.info("[MemberService] : modifyMember start ==================================== ");
		log.info("[MemberService] : memberDto : {}", memberDto);
		
		Member findMember = memberRepository.findById(memberDto.getMemCode()).orElseThrow();
		
		log.info("[MemberService] : originMember : {}", findMember);

		findMember.modify(
				memberDto.getMemName(), 
				memberDto.getMemPhone()
				);
	
		log.info("[MemberService] : modifyMember end ==================================== ");
	}
	
//	/* 회원의 회원권 추가 */
//	@Transactional
//	public void memberAddPass(MemberDto memberDto) {
//		
//		log.info("[MemberService] : modifyMember start ==================================== ");
//		log.info("[MemberService] : memberDto : {}", memberDto);
//		
//		Member findMemberPass = memberRepository.findById(memberDto.getMemCode()).orElseThrow();
//		
//		log.info("[MemberService] : originMember : {}", findMemberPass);
//
//		findMemberPass.addPass(
//				memberDto.getMemCode(),
//				memberDto.getMemDeleteDate(), 
//				memberDto.getMemStartDate(), 
//				memberDto.getMemEndDate(),
//				memberDto.getHistory().stream().map(history -> modelMapper.map(history, History.class)).collect(Collectors.toList())
//				);
//
//
//		log.info("[MemberService] : modifyMember end ==================================== ");
//	}
	
	/* 회원명 검색 목록 조회 */
	public Page<MemberDto> selectMemberListByMemName(int page, String memName){
		
		log.info("[MemberService] : selectMemberListByMemName start ==================================== ");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("memCode").descending());
		
		Page<Member> memberList = memberRepository.findByMemName(pageable, memName);
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));
		
		log.info("[MemberService] : selectMemberListByMemName end ==================================== ");
		
		return memberDtoList;
	}
	
	
	
	
	
	
	
}
