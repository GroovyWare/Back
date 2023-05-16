package com.groovy.ware.announce.service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.groovy.ware.announce.dto.AnnounceDto;
import com.groovy.ware.announce.entity.Announce;
import com.groovy.ware.announce.repository.AnnounceRepository;
import com.groovy.ware.common.dto.FileDto;
import com.groovy.ware.common.entity.File;
import com.groovy.ware.common.repository.FileRepository;
import com.groovy.ware.util.FileUploadUtils;

@Service
public class AnnounceService {

    private final AnnounceRepository announceRepository;
//    private final EmployeeRepository employeeRepository;
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper; 

    @Value("${image.image-url}")
    private String IMAGE_URL;
    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    public AnnounceService(AnnounceRepository announceRepository,
			/* EmployeeRepository employeeRepository, */
                           FileRepository fileRepository,
                           ModelMapper modelMapper) {
        this.announceRepository = announceRepository;
//        this.employeeRepository = employeeRepository;
        this.fileRepository = fileRepository;
        this.modelMapper = modelMapper;
    }

    public Page<Announce> getAnnounces(Pageable pageable) {
        return announceRepository.findAll(pageable);
    }

    public Optional<Announce> getAnnounce(Long annCode) {
        return announceRepository.findById(annCode);
    }

    @Transactional
    public void createAnnounce(AnnounceDto announceDto) {
    	
    	String imageName = UUID.randomUUID().toString().replace("-", "");
    	
        try {
            String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, announceDto.getAnnounceImage());
            FileDto fileSavedName = new FileDto();
            fileSavedName.setFileSavedName(replaceFileName);
            announceDto.setFileSavedName(fileSavedName);

            announceRepository.save(modelMapper.map(announceDto, Announce.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateAnnounce(AnnounceDto announceDto) {
        Announce originAnnounce = announceRepository.findById(announceDto.getAnnCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 코드의 공지사항이 없습니다. annCode=" + announceDto.getAnnCode()));

        try {
            /* 이미지를 변경하는 경우 */
            if (announceDto.getFileSavedName() != null) {
                /* 새로 입력 된 이미지 저장 */
                String imageName = UUID.randomUUID().toString().replace("-", "");
                String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, announceDto.getAnnounceImage());

                /* 기존에 저장 된 이미지 삭제 */
                FileUploadUtils.deleteFile(IMAGE_DIR, originAnnounce.getFileSavedName().getFileSavedName());

                /* DB에 저장 될 imageUrl 값을 수정 */
                File file = new File();
                file.setFileOriginalName(announceDto.getFileOriginalName().getFileOriginalName());
                file.setFileSavedName(replaceFileName);
                fileRepository.save(file);
                originAnnounce.setFileSavedName(file);
            }

            /* 이미지를 변경하지 않는 경우에는 별도의 처리가 필요 없음 */

            /* 조회했던 기존 엔티티의 내용을 수정 */
            originAnnounce.update(
            		announceDto.getAnnTitle(),
            		announceDto.getAnnContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAnnounce(Long annCode) {
        announceRepository.deleteById(annCode);
    }

    public Page<Announce> searchAnnounces(String condition, String keyword, Pageable pageable) {
        switch (condition) {
            case "annTitle":
                return announceRepository.findByAnnTitleContaining(keyword, pageable);
            case "empCode":
                return announceRepository.findByEmpCode(keyword, pageable);
            case "annContent":
                return announceRepository.findByAnnContentContaining(keyword, pageable);
            case "annTitleAndContent":
                return announceRepository.findByAnnTitleContainingOrAnnContentContaining(keyword, keyword, pageable);
            default:
                throw new IllegalArgumentException("Invalid search condition: " + condition);
        }
    }

}
