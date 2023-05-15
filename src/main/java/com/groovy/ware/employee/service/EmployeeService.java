package com.groovy.ware.employee.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groovy.ware.common.dto.FileDto;
import com.groovy.ware.common.entity.File;
import com.groovy.ware.common.repository.FileRepository;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.EmployeeRepository;
import com.groovy.ware.util.FileUploadUtils;


import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	private FileRepository fileRepository;
	private ModelMapper modelMapper;
	
	public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, FileRepository fileRepository) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
		this.fileRepository = fileRepository;
	}
	
	@Value("${image.image-url}")
	private String IMAGE_URL;	
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	
	
	/* 직원 목록 조회 */
	public Page<EmployeeDto> selectEmployeeList(int page) {
		
		log.info("[EmployeeService] selectEmployeeList start ============================================");
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("empCode").descending());
		
		Page<Employee> employeeList = employeeRepository.findAll(pageable);
		Page<EmployeeDto> employeeDtoList = employeeList.map(employee -> modelMapper.map(employee, EmployeeDto.class));
		
		//employeeDtoList.forEach(employee -> employee.file.setFileOriginalName(IMAGE_URL + employee.file.setFileOriginalName));
		
		log.info("[EmployeeService] selectEmployeeList end ============================================");
		
		return employeeDtoList;
	}
	
	/* 직원 등록 */
	@Transactional
	public void insertEmployee(EmployeeDto employeeDto) {
		
		log.info("[EmployeeService] registEmployee start ============================================");
		log.info("[EmployeeService] registEmployee employeeDto : {}", employeeDto);
		
		String imageName = UUID.randomUUID().toString().replace("-", ""); 
		
		try {
	
			String originalName = employeeDto.getImgUrl().getOriginalFilename();
			String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, employeeDto.getImgUrl());
			
			FileDto fileDto = new FileDto();
			fileDto.setFileDiv("프로필");
			fileDto.setFileOriginalName(originalName);
			fileDto.setFileSavedName(replaceFileName);
//			employeeDto.setFile(fileDto);
			fileDto.setEmployee(employeeDto);
			log.info("[EmployeeService] registEmployee fileDto : {}", fileDto);

			log.info("[EmployeeService] registEmployee employeeDto : {}", employeeDto);
			
//			employeeRepository.save(modelMapper.map(employeeDto, Employee.class));
			fileRepository.save(modelMapper.map(fileDto, File.class));
			
		} catch (IOException | java.io.IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/* 직원 상세 조회 + 프로필 사진 처리 추가하기 */
	public EmployeeDto selectEmployee(Long empCode) {
		
		log.info("[EmployeeService] selectEmployee start ============================================");
		log.info("[EmployeeService] selectEmployee empCode : {}", empCode);
		
		Employee employee = employeeRepository.findById(empCode)
												.orElseThrow(() -> new IllegalArgumentException("해당 코드의 직원이 없습니다."));
		
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		//EmployeeDto.fileDto.setImgUrl(IMAGE_URL + employee.file,setImgUrl);
			
		log.info("[EmployeeService] selectEmployee end ==============================================");
		
		return employeeDto;
	}


	
	/* 직원 정보 수정 */
	@Transactional
	public void updateEmployee(EmployeeDto employeeDto) {
		
		log.info("[EmployeeService] updateEmployee start ===============================================");
		log.info("[EmployeeService] employeeDto : {}", employeeDto);
		
		Employee originEmployee = employeeRepository.findById(employeeDto.getEmpCode())
		
				.orElseThrow(() -> new IllegalArgumentException("해당 코드의 직원이 없습니다."));
//		try {
//		
//			if(employeeDto.getImgUrl() != null) {
//				
//				String imageName = UUID.randomUUID().toString().replace("-", "");
//				
//				String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, employeeDto.getImgUrl());		
//					
//				FileUploadUtils.deleteFile(IMAGE_DIR, originEmployee.getFile().getFileSavedName());
//				
//				originEmployee.getFile().setFileSavedName(replaceFileName);
//				originEmployee.getFile().setFileOriginalName(employeeDto.getImgUrl().getOriginalFilename());
//			
//			}
//		
//			originEmployee.update(
//					employeeDto.getEmpName(),
//					employeeDto.getEmpPhone(),
//					employeeDto.getEmpEmail(),
//					employeeDto.getEmpAddress(),
//					employeeDto.getEmpExDate(),
//					modelMapper.map(employeeDto.getDept(), Department.class),
//					modelMapper.map(employeeDto.getPosition(), Position.class),
//					modelMapper.map(employeeDto.getFile(), File.class)
//					);
//			
//		} catch (IOException | java.io.IOException e) {
//			e.printStackTrace();
//		}

	
}

	public EmployeeDto getEmployeeByEmpCode(Long empCode) {
		return null;
	}
}
