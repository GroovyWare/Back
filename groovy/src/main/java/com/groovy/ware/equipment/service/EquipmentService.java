package com.groovy.ware.equipment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.groovy.ware.announce.dto.AnnounceDto;
import com.groovy.ware.announce.entity.Announce;
import com.groovy.ware.announce.exception.FileUploadException;
import com.groovy.ware.common.dto.FileDto;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.EmployeeRepository;
import com.groovy.ware.equipment.dto.EquipmentDto;
import com.groovy.ware.equipment.entity.Equipment;
import com.groovy.ware.equipment.repository.EquipmentRepository;

@Service
public class EquipmentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentService.class);

    private final EquipmentRepository equipmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EquipmentService(EquipmentRepository equipmentRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.equipmentRepository = equipmentRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Page<EquipmentDto> getEquipments(Pageable pageable) {
        // 최근 점검 일자를 기준으로 내림차순 정렬
        PageRequest sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "eqpDate"));
        Page<Equipment> equipments = equipmentRepository.findAllByOrderByEqpDateDesc(sortedPageable);

        // Convert Entity to DTO
        List<EquipmentDto> equipmentDtoList = new ArrayList<>();
        for (Equipment equipment : equipments) {
            EquipmentDto equipmentDto = modelMapper.map(equipment, EquipmentDto.class);
            Employee employee = equipment.getEmployee();
            if (employee != null) {
                EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
                equipmentDto.setEmployee(employeeDto);
            }
            equipmentDtoList.add(equipmentDto);
        }

        return new PageImpl<>(equipmentDtoList, sortedPageable, equipments.getTotalElements());
    }

    
    /* 기구 등록 */
    @Transactional
    public void createEquipment(EquipmentDto equipmentDto, Long empCode) {
    	Employee employee = employeeRepository.findById(empCode)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Convert DTO to Entity
    	Equipment equipment = modelMapper.map(equipmentDto, Equipment.class);
    	equipment.setEmployee(employee);  // Set the employee
    	equipment.setEqpStatus("NEW");

        equipmentRepository.save(equipment);
}


    /* 기구 수정 */
    @Transactional
    public void updateEquipment(EquipmentDto equipmentDto) {
        Equipment originEquipment = equipmentRepository.findById(equipmentDto.getEqpCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 코드의 기구가 없습니다. eqpCode=" + equipmentDto.getEqpCode()));

        try {
            originEquipment.update(equipmentDto.getEqpTitle(), equipmentDto.getEqpInspector(), equipmentDto.getEqpDate(), equipmentDto.getEqpStatus());

        } catch (Exception e) {
            LOGGER.error("Failed to update equipment", e);
            throw e;
        }
    }


    /* 기구 삭제 */
    public void deleteEquipment(Long eqpCode) {
        equipmentRepository.deleteById(eqpCode);
    }

    /* 기구 검색 (추가) */
    public Page<EquipmentDto> searchEquipments(String condition, String keyword, Pageable pageable) {
        Page<Equipment> equipments;
        switch(condition) {
            case "eqpTitle":
                equipments = equipmentRepository.findByEqpTitleLike(keyword, pageable);
                break;
            case "eqpStatus":
                equipments = equipmentRepository.findByEqpStatusLike(keyword, pageable);
                break;
                // ... 여기에 다른 조건을 추가할 수 있습니다. ...
            default:
                throw new IllegalArgumentException("Invalid search condition: " + condition);
        }
        return equipments.map(equipment -> modelMapper.map(equipment, EquipmentDto.class));
    }

}