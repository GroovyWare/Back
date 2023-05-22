package com.groovy.ware.pass.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.groovy.ware.pass.dto.PassDto;
import com.groovy.ware.pass.entity.Pass;
import com.groovy.ware.pass.repository.PassRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PassService {
	
	/* 의존성 주입 */
	private final ModelMapper modelMapper;
	private final PassRepository passRepository;
	
	public PassService(PassRepository passRepository, ModelMapper modelMapper) {
		
		this.passRepository = passRepository;
		this.modelMapper = modelMapper;
	}
		

	
	/* 회원권 등록 */
	@Transactional
	public void insertPass(PassDto passDto) {
		
		log.info("[PassService] insertPass start =====================================");
		log.info("[PassService] passDto : {}", passDto);
		
		passRepository.save(modelMapper.map(passDto, Pass.class));
		
		log.info("[PassService] insertPass end =====================================");
	}
	
	
	/* 회원권 조회*/
	public Page<PassDto> findPassList(int page){
		
		log.info("[PassService] findPassList start =====================================");
		
		Pageable pageable = PageRequest.of(page - 1, 6, Sort.by("passCode").descending());
		
		Page<Pass> passList = passRepository.findAll(pageable);
		Page<PassDto> passDtoList = passList.map(pass -> modelMapper.map(pass, PassDto.class));
		
		log.info("[PassService] findPassList.getContent() : {}", passDtoList.getContent());
		
		log.info("[PassService] findPassList end =====================================");
		
		return passDtoList;
	}
	
	/* 회원권 수정 */
	@Transactional
	public void modifyPass(PassDto passDto, Long passCode) {
		log.info("[PassService] modifyPass start =====================================");
		log.info("[PassService] passDto : {}", passDto);
		
		Pass originPass = passRepository.findById(passCode).orElseThrow();
		
		originPass.modify(
				
				passDto.getPassType(), 
				passDto.getPassPrice(), 
				passDto.getPassAmount(), 
				passDto.getPassEtc()
				
		);
		
	}
	
	/* 회원권 삭제 */
	@Transactional
	public void deletePass(Long passCode) {
		log.info("[PassService] deletePass start =====================================");

		passRepository.deleteById(passCode);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
