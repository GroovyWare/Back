package com.groovy.ware.announce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.groovy.ware.announce.dto.AnnounceDto;
import com.groovy.ware.announce.entity.Announce;
import com.groovy.ware.announce.exception.FileUploadException;
import com.groovy.ware.announce.repository.AnnounceRepository;
import com.groovy.ware.common.dto.FileDto;
import com.groovy.ware.common.entity.File;
import com.groovy.ware.common.repository.FileRepository;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.EmployeeRepository;

@Service
public class AnnounceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnounceService.class);

    private final AnnounceRepository announceRepository;
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;
    private final FileService fileService;
    private final EmployeeRepository employeeRepository;

    public AnnounceService(AnnounceRepository announceRepository,
                           FileRepository fileRepository,
                           ModelMapper modelMapper,
                           FileService fileService,
                           EmployeeRepository employeeRepository) {
        this.announceRepository = announceRepository;
        this.fileRepository = fileRepository;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
        this.employeeRepository = employeeRepository;
    }

    public Page<AnnounceDto> getAnnounces(Pageable pageable) {
        // 게시물 작성 날짜를 기준으로 내림차순 정렬
        PageRequest sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "annDate"));
        Page<Announce> announces = announceRepository.findAll(sortedPageable);

        // Convert Entity to DTO
        List<AnnounceDto> announceDtoList = new ArrayList<>();
        for (Announce announce : announces) {
            AnnounceDto announceDto = modelMapper.map(announce, AnnounceDto.class);
            announceDtoList.add(announceDto);
        }

        return new PageImpl<>(announceDtoList, sortedPageable, announces.getTotalElements());
    }

    public Optional<Announce> getAnnounce(Long annCode) {
        return announceRepository.findById(annCode);
    }

    @Transactional
    public void createAnnounce(AnnounceDto announceDto, MultipartFile multipartFile, Long empCode) {
        try {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                String replaceFileName = fileService.saveFile(multipartFile);

                FileDto fileSavedName = new FileDto();
                fileSavedName.setFileSavedName(replaceFileName);

                List<FileDto> files = new ArrayList<>();
                files.add(fileSavedName);
                announceDto.setFiles(files);
            }

            // Retrieve the employee by their code
            Employee employee = employeeRepository.findById(empCode)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            // Convert DTO to Entity
            Announce announce = modelMapper.map(announceDto, Announce.class);
            announce.setEmployee(employee);  // Set the employee

            announceRepository.save(announce);
        } catch (FileUploadException e) {
            throw e;
        }
    }




    @Transactional
    public void updateAnnounce(AnnounceDto announceDto) {
        Announce originAnnounce = announceRepository.findById(announceDto.getAnnCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 코드의 공지사항이 없습니다. annCode=" + announceDto.getAnnCode()));

        try {
        	if (announceDto.getFiles() != null && !announceDto.getFiles().isEmpty() && 
                announceDto.getFiles().get(0).getFileSavedName() != null) {
                String replaceFileName = fileService.saveFile(announceDto.getAnnounceImage());
                fileService.deleteFile(originAnnounce.getFiles().get(0).getFileSavedName());

                File file = new File();
                file.setFileOriginalName(announceDto.getFiles().get(0).getFileOriginalName());
                file.setFileSavedName(replaceFileName);
                fileRepository.save(file);
                
                List<File> files = new ArrayList<>();
                files.add(file);
                originAnnounce.setFiles(files);
            }

            originAnnounce.update(announceDto.getAnnTitle(), announceDto.getAnnContent());
        } catch (FileUploadException e) {
            throw e;
        }
    }

    public void deleteAnnounce(Long annCode) {
        announceRepository.deleteById(annCode);
    }

    public Page<Announce> searchAnnounces(String condition, String keyword, Pageable pageable) {
        switch (condition) {
            case "annTitle":
                return announceRepository.findByAnnTitleContaining(keyword, pageable);
            // case "employee":
            //     Employee employee = employeeRepository.findByEmpCode(keyword);
            //     return announceRepository.findByEmployee(employee, pageable);
            case "annContent":
                return announceRepository.findByAnnContentContaining(keyword, pageable);
            case "annTitleAndContent":
                return announceRepository.findByAnnTitleContainingOrAnnContentContaining(keyword, keyword, pageable);
            default:
                throw new IllegalArgumentException("Invalid search condition: " + condition);
        }
    }

}