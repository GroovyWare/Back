package com.groovy.ware.history.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.common.ResponseDto;
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;
import com.groovy.ware.history.dto.HistoryDto;
import com.groovy.ware.history.service.HistoryService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="*", allowedHeaders = "*")
@Slf4j
@RestController
@RequestMapping("/history")
public class HistoryController {
	
	/* 의존성 주입 */
	
	private final HistoryService historyService;
	
	public HistoryController(HistoryService historyService) {
		
		this.historyService = historyService;
	}
	
	
	
	/* 회원 개인 이력 상세 조회 */
	@GetMapping("/log/{memCode}")
	public ResponseEntity<ResponseDto> findMemberHistoryDetail(
			@RequestParam(name="page", defaultValue="1") int page, @PathVariable Long memCode) {
		
		log.info("[HistoryController] : findMemberDetail start ==================================== ");
		log.info("[HistoryController] : page : {}", page);
		log.info("[HistoryController] : memCode : {}", memCode);
		
		Page<HistoryDto> historyDtoList = historyService.findMemberHistoryDetail(page, memCode);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(historyDtoList);
		
		log.info("[HistoryController] : pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(historyDtoList.getContent());
		
		
		log.info("[HistoryController] : historyDtoList.getContent : {} ", historyDtoList.getContent());
		log.info("[HistoryController] : findMemberDetail end ==================================== ");
		
		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "회원 이력 조회 성공", responseDtoWithPaging));
	}
	
	/* 회원의 회원권 추가 */
	@PostMapping("/add")
	public ResponseEntity<ResponseDto> memberAddPass(@ModelAttribute HistoryDto historyDto){
		
		historyService.memberAddPass(historyDto);
		
		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "회원의 회원권 추가등록 성공"));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
