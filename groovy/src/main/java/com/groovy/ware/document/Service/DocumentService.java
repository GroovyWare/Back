package com.groovy.ware.document.Service;

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
	

	public void saveVacationHtml(DocumentDto documentDto) {
		
		documentRepository.save(modelMapper.map(documentDto, Document.class));
		
	}

}
