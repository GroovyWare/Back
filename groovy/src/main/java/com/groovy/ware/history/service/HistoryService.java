package com.groovy.ware.history.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.groovy.ware.history.dto.HistoryDto;
import com.groovy.ware.history.entity.History;
import com.groovy.ware.history.repository.HistoryRepository;
import com.groovy.ware.member.entity.Member;
import com.groovy.ware.member.repository.MemberRepository;
import com.groovy.ware.pass.repository.PassRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoryService {
	
	/* 의존성 주입 */
	private final ModelMapper modelMapper;
	private final HistoryRepository historyRepository;


	public HistoryService(HistoryRepository historyRepository, ModelMapper modelMapper) {
		
		this.modelMapper = modelMapper;
		this.historyRepository = historyRepository;
	}
	
	
	
	/* 회원 개인 이력 상세 조회 */
	public Page<HistoryDto> findMemberHistoryDetail(int page, Long memCode) {
		
		log.info("[HistoryService] findMemberDetail start ==================");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("resHistory").descending());
		
		log.info("[HistoryService] memCode : {}", memCode);

		
		Page<History> historyList = historyRepository.findByMemCode(pageable, memCode);
		Page<HistoryDto> historyDtoList = historyList.map(history -> modelMapper.map(history, HistoryDto.class));

		
		log.info("[HistoryService] historyDtoList : {}", historyDtoList.getContent());
		log.info("[HistoryService] findMemberDetail start ==================");
		
		return historyDtoList;
	}
	


}
