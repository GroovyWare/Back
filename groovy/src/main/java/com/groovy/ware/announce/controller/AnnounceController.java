package com.groovy.ware.announce.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.groovy.ware.announce.dto.AnnounceDto;
import com.groovy.ware.announce.entity.Announce;
import com.groovy.ware.announce.service.AnnounceService;
import com.groovy.ware.common.ResponseDto;
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/announce")
public class AnnounceController {

    private final AnnounceService announceService;
    
    public AnnounceController(AnnounceService announceService) {
        this.announceService = announceService;
    }

    /* 공지사항 목록 */
    @GetMapping
    public ResponseEntity<ResponseDto> getAnnounces(@RequestParam(name="page", defaultValue="1") int page,
            @RequestParam(name="size", defaultValue="10") int size) {
        log.info("[AnnounceController] : getAnnounces start ==================================== ");
        log.info("[AnnounceController] : page : {}", page);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Announce> announces = announceService.getAnnounces(pageable);

        PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(announces);

        log.info("[AnnounceController] : pageInfo : {}", pageInfo);

        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
        responseDtoWithPaging.setPageInfo(pageInfo);
        responseDtoWithPaging.setData(announces.getContent());

        log.info("[AnnounceController] : getAnnounces end ==================================== ");

        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK, "공지사항 목록 조회가 완료되었습니다.", responseDtoWithPaging));
    }
    
    /* 공지사항 검색 */
    @GetMapping("/search")
    public ResponseEntity<ResponseDto> searchAnnounces(@RequestParam String condition, @RequestParam String keyword, Pageable pageable) {
        Page<Announce> announces = announceService.searchAnnounces(condition, keyword, pageable);
        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK, "공지사항 검색이 완료되었습니다.", announces));
    }
    
    /* 공지사항 조회 */
    @GetMapping("/{annCode}")
    public ResponseEntity<ResponseDto> getAnnounce(@PathVariable Long annCode) {
        Optional<Announce> announce = announceService.getAnnounce(annCode);
        if (announce.isPresent()) {
            return ResponseEntity.ok()
                    .body(new ResponseDto(HttpStatus.OK, "공지사항 조회가 완료되었습니다.", announce.get()));
        } else {
            return ResponseEntity.notFound()
                    .build();
        }
    }
    
    @PostMapping
    public ResponseEntity<ResponseDto> createAnnounce(@ModelAttribute AnnounceDto announceDto, @RequestParam(required = false) MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            // 파일이 존재하는 경우에 대한 처리 로직
            String filename = multipartFile.getOriginalFilename();
            // 파일 업로드 및 저장 로직 등을 수행합니다.
        }

        announceService.createAnnounce(announceDto, multipartFile);
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "공지사항이 등록되었습니다."));
    }

    /* 공지사항 수정 */
    @PutMapping("/{annCode}")
    public ResponseEntity<ResponseDto> updateAnnounce(@PathVariable Long annCode, @RequestBody AnnounceDto announceDto, @RequestParam(required = false) MultipartFile multipartFile) {
        announceDto.setAnnCode(annCode);

        if (multipartFile != null && !multipartFile.isEmpty()) {
            // 파일이 존재하는 경우에 대한 처리 로직
            String filename = multipartFile.getOriginalFilename();
            // 파일 업로드 및 저장 로직 등을 수행합니다.
        }

        announceService.updateAnnounce(announceDto);
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "공지사항이 수정되었습니다."));
    }

    /* 공지사항 삭제 */
    @DeleteMapping("/{annCode}")
    public ResponseEntity<ResponseDto> deleteAnnounce(@PathVariable Long annCode) {
        announceService.deleteAnnounce(annCode);
        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK, "공지사항이 삭제되었습니다."));
    }

}