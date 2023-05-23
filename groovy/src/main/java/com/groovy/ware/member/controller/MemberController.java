package com.groovy.ware.member.controller;



import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.common.ResponseDto;
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;
import com.groovy.ware.member.dto.MemberDto;
import com.groovy.ware.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="*", allowedHeaders = "*")
@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/* 전체 회원 리스트 */
	@GetMapping("/list")
	public ResponseEntity<ResponseDto> findMemberListAll(@RequestParam(name="page", defaultValue="1") int page){
		
		log.info("[MemberController] : findMemberListAll start ==================================== ");
		log.info("[MemberController] : page : {}", page);
		
		Page<MemberDto> memberDtoList = memberService.findMemberListAll(page);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(memberDtoList);
		
		log.info("[MemberController] : pageInfo : {}", pageInfo);
		
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(memberDtoList.getContent());
		
		
		log.info("[MemberController] : findMemberListAll end ==================================== ");
		
		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "회원 조회가 완료되었습니다.", responseDtoWithPaging));
		
	}
	
	
	/* 회원 상세 조회 */
	@GetMapping("/detail/{memCode}")
	public ResponseEntity<ResponseDto> findMemberDetail(@PathVariable Long memCode){
		
			
		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "회원정보 조회 완료", memberService.findMemberDetail(memCode)));
	}
	
	
		
	/* 회원등록 */
	@PostMapping("/regist")
	public ResponseEntity<ResponseDto> insertMemeber(@ModelAttribute MemberDto memberDto) {
		
		memberService.insertMember(memberDto);
						
		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "회원 등록 성공"));
	}

	
	/* 회원 수정 */
	@PutMapping("/modify")
	public ResponseEntity<ResponseDto> modifyMember(@ModelAttribute MemberDto memberDto) {
		
		memberService.modifyMember(memberDto);
		
		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "회원 수정 성공"));
		
	}
	
 

}
