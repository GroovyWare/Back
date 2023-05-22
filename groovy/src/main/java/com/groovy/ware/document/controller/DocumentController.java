package com.groovy.ware.document.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.common.ResponseDto;
import com.groovy.ware.document.Service.DocumentService;
import com.groovy.ware.document.dto.DocumentDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/document")
@Slf4j
public class DocumentController {
	
	private final DocumentService documentService;
	
	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;
	}
	
	/* 양식명에 맞는 기안서 찾아오기 */
	@GetMapping("/set")
	public ResponseEntity<ResponseDto> setDocument(@RequestParam String docTitle){
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 완료", documentService.setDocument(docTitle)));
	}
	
	/* 새로운 양식 등록 */
	@PostMapping("/add")
	public ResponseEntity<ResponseDto> addDocument(@RequestBody DocumentDto documentDto){
		
		documentService.addDocument(documentDto);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "등록 완료"));
	}
	
	/* 양식명 목록 찾기 */ 
	@GetMapping("/search")
	public ResponseEntity<ResponseDto> searchDocTitle(){
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 완료", documentService.searchDocTitle()));
	}
	
}
