package com.groovy.ware.document.Service;

import java.util.List;

import javax.transaction.Transactional;

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
	

	@Transactional
	public void saveVacationHtml(DocumentDto documentDto) {
		
		documentRepository.save(modelMapper.map(documentDto, Document.class));
		
	}


	public DocumentDto setDocument() {
		
		Document document = documentRepository.findByDocCode(Long.parseLong("1"));
		DocumentDto documentDto = modelMapper.map(document, DocumentDto.class);
		
		return documentDto;
	}

}
