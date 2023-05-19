package com.groovy.ware.document.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping("/save")
	public ResponseEntity<ResponseDto> saveVacationHtml(@RequestBody DocumentDto documentDto){
		
		documentService.saveVacationHtml(documentDto);
		log.info("{}", documentDto);
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "저장 완료"));
	}
	
	@GetMapping("/set")
	public ResponseEntity<ResponseDto> setDocument(){
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 완료", documentService.setDocument()));
	}
	
	

}
