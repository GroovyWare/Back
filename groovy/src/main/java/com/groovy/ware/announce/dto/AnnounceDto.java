package com.groovy.ware.announce.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groovy.ware.common.dto.FileDto;

import lombok.Data;

@Data
public class AnnounceDto {
	
	private Long annCode;				// 공지사항 코드
	private String annTitle;			// 제목
	private Date annDate;				// 작성일
	private Long empCode;				// 직원 코드
	private String annContent;			// 내용
	private Date annDelDate;			// 삭제 일자
	private String annDelete;			// 삭제 여부
	
	@JsonIgnore
	private MultipartFile announceImage;
	
	private List<FileDto> files;
	
//	private FileDto fileOriginalName;	// 파일 원본명
//	private FileDto fileSavedName;		// 파일 저장명

}
