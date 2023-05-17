package com.groovy.ware.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groovy.ware.common.dto.FileDto;
import com.groovy.ware.common.entity.File;
import com.groovy.ware.common.exception.DuplicatedEmpIdException;
import com.groovy.ware.common.exception.UserNotFoundException;
import com.groovy.ware.common.repository.FileRepository;
import com.groovy.ware.employee.dto.EmpAuthDto;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Department;
import com.groovy.ware.employee.entity.EmpAuthPK;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.entity.Position;
import com.groovy.ware.employee.repository.EmployeeRepository;
import com.groovy.ware.util.FileUploadUtils;

import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	private FileRepository fileRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;
	
	public EmployeeService(EmployeeRepository employeeRepository, FileRepository fileRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.fileRepository = fileRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
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
		
		log.info("[EmployeeService] selectEmployeeList end ============================================");
		
		return employeeDtoList;
	}
	
	/* 직원 상세 조회 */
	public EmployeeDto selectEmployee(Long empCode) {
		
		log.info("[EmployeeService] selectEmployee start ============================================");
		log.info("[EmployeeService] selectEmployee empCode : {}", empCode);
		
		Employee employee = employeeRepository.findById(empCode)
												.orElseThrow(() -> new IllegalArgumentException("해당 코드의 직원이 없습니다."));
		
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		employeeDto.getFile().setFileSavedName(IMAGE_URL + employeeDto.getFile().getFileSavedName());

		log.info("[EmployeeService] selectEmployee end ==============================================");
		
		return employeeDto;
	}
	
	/* 직원 등록 */
	@Transactional
	public void insertEmployee(EmployeeDto employeeDto) {
		
		log.info("[EmployeeService] registEmployee start ============================================");
		log.info("[EmployeeService] registEmployee employeeDto : {}", employeeDto);
	
		/* 아이디 중복 확인 */
		if(employeeRepository.idCheck(employeeDto.getEmpId()) != null) {
			log.info("[EmployeeService] 이미 해당 아이디가 있습니다.");
			throw new DuplicatedEmpIdException("아이디가 이미 존재합니다.");
		}
		
		/* 패스워드 암호화 처리 */
		employeeDto.setEmpPassword(passwordEncoder.encode(employeeDto.getEmpPassword()));
		
		/* 프로필 사진 처리*/
		String imageName = UUID.randomUUID().toString().replace("-", ""); 
		
		try {
			
			/* 권한 임의 값 전처리 */
			Long initEmpCode = (long) 0;
			Long initAuthCode = (long) 2;
			EmpAuthDto empAuthDto = new EmpAuthDto();
			EmpAuthPK empAuthPK = new EmpAuthPK();
			
			empAuthPK.setEmpCode(initEmpCode);
			empAuthPK.setAuthCode(initAuthCode);
			empAuthDto.setEmpAuthPK(empAuthPK);
			
			List<EmpAuthDto> authList = new ArrayList<EmpAuthDto>();
			authList.add(empAuthDto);
			log.info("authList : {}", authList);
			employeeDto.setAuths(authList);
			
			
			String originalName = employeeDto.getImgUrl().getOriginalFilename();
			String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, employeeDto.getImgUrl());
			
			FileDto fileDto = new FileDto();
			fileDto.setFileDiv("프로필");
			fileDto.setFileOriginalName(originalName);
			fileDto.setFileSavedName(replaceFileName);

			fileDto.setEmployee(employeeDto);
			log.info("[EmployeeService] registEmployee fileDto : {}", fileDto);
			

			
			fileRepository.save(modelMapper.map(fileDto, File.class));
			
		} catch (IOException | java.io.IOException e) {
			e.printStackTrace();
		}
		
	}
		
	
	/* 직원 정보 수정 */
	@Transactional
	public void updateEmployee(EmployeeDto employeeDto) {
		
		log.info("[EmployeeService] updateEmployee start ===============================================");
		log.info("[EmployeeService] employeeDto : {}", employeeDto);
		
		Employee originEmployee = employeeRepository.findById(employeeDto.getEmpCode())
		
				.orElseThrow(() -> new IllegalArgumentException("해당 코드의 직원이 없습니다."));
		try {
		
			if(employeeDto.getImgUrl() != null) {
				
				String imageName = UUID.randomUUID().toString().replace("-", "");
				
				String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, employeeDto.getImgUrl());		
					
				FileUploadUtils.deleteFile(IMAGE_DIR, originEmployee.getFile().getFileSavedName());
				
				originEmployee.getFile().setFileSavedName(replaceFileName);
				originEmployee.getFile().setFileOriginalName(employeeDto.getImgUrl().getOriginalFilename());
							
			}
		
			originEmployee.update(
					employeeDto.getEmpName(),
					employeeDto.getEmpPhone(),
					employeeDto.getEmpEmail(),
					employeeDto.getEmpAddress(),
					employeeDto.getEmpExDate(),
					modelMapper.map(employeeDto.getDept(), Department.class),
					modelMapper.map(employeeDto.getPosition(), Position.class),
					modelMapper.map(employeeDto.getFile(), File.class)
					);
			
		} catch (IOException | java.io.IOException e) {
			e.printStackTrace();
		}

	
}

	/* 내정보 조회*/
	public EmployeeDto selectMyInfo(Long empCode) {
		log.info("[EmployeeService] selectMyInfo empCode : {}", empCode);
		Employee employee = employeeRepository.findById(empCode)
				.orElseThrow(() -> new UserNotFoundException(empCode + "를 찾을 수 없습니다."));
		
		return modelMapper.map(employee, EmployeeDto.class);
	}
	


	


}
