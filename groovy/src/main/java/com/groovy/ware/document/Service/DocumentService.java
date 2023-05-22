package com.groovy.ware.document.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.groovy.ware.document.Entity.Document;
import com.groovy.ware.document.dto.DocumentDto;
import com.groovy.ware.document.repository.DocumentRepository;

@Service
public class DocumentService {

	private final DocumentRepository documentRepository;
	private final ModelMapper modelMapper;

	public DocumentService(DocumentRepository documentRepository, ModelMapper modelMapper) {
		this.documentRepository = documentRepository;
		this.modelMapper = modelMapper;
	}

	/* 선택한 제목에 맞는 양식 찾아오기 */
	public DocumentDto setDocument(String docTitle) {

		Document document = documentRepository.findByDocTitle(docTitle);
		DocumentDto documentDto = modelMapper.map(document, DocumentDto.class);

		return documentDto;
	}

	/* 양식 추가 */
	public void addDocument(DocumentDto documentDto) {
		
		documentRepository.save(modelMapper.map(documentDto, Document.class));
		
	}

	/* 양식 목록 찾기 */
	public List<DocumentDto> searchDocTitle() {
		
		List<Document> document = documentRepository.findAll();
		List<DocumentDto> documentDto = document.stream().map(row -> modelMapper.map(row, DocumentDto.class)).collect(Collectors.toList());
		
		return documentDto;
	}

}
