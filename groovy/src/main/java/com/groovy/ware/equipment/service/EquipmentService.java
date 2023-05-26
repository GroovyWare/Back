package com.groovy.ware.equipment.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.EmployeeRepository;
import com.groovy.ware.equipment.dto.EquipmentDto;
import com.groovy.ware.equipment.entity.Equipment;
import com.groovy.ware.equipment.repository.EquipmentRepository;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EmployeeRepository employeeRepository;

    public EquipmentService(EquipmentRepository equipmentRepository, EmployeeRepository employeeRepository) {
        this.equipmentRepository = equipmentRepository;
        this.employeeRepository = employeeRepository;
    }

    // 모든 기구의 리스트를 반환하는 메서드
    @Transactional(readOnly = true)
    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    // 주어진 eqpCode에 해당하는 기구를 반환하는 메서드
    @Transactional(readOnly = true)
    public Equipment getEquipment(Long eqpCode) {
        Optional<Equipment> equipment = equipmentRepository.findById(eqpCode);
        if (equipment.isPresent()) {
            return equipment.get();
        } else {
            throw new IllegalArgumentException("Invalid equipment code: " + eqpCode);
        }
    }
    
    // 주어진 Equipment를 저장하고 저장된 Equipment를 반환하는 메서드
    @Transactional
    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Transactional
    public Equipment createEquipment(EquipmentDto equipmentDto) {
        // 현재 로그인한 사용자를 가져옵니다. 
        // Authentication 객체를 사용하거나 SecurityContext를 사용하여 가져올 수 있습니다.
        // 아래는 Spring Security를 이용해서 현재 로그인한 사용자를 가져오는 예제 코드입니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDto employeeDto = (EmployeeDto) authentication.getPrincipal();
        Long empCode = employeeDto.getEmpCode();

        // 직원의 코드로 직원을 찾습니다.
        Employee employee = employeeRepository.findById(empCode)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

        Equipment equipment = new Equipment();
        equipment.setEqpTitle(equipmentDto.getEqpTitle());
        equipment.setEqpStatus("NEW");
        equipment.setEqpDate(null);

        // 현재 날짜와 시간을 구해 eqpPurchase에 설정
        Date currentDate = new Date();
        equipment.setEqpPurchase(currentDate);

        // 직원의 정보를 equipment에 설정
        equipment.setEmployee(employee);

        return saveEquipment(equipment);
    }



    // 주어진 eqpCode에 해당하는 기구의 정보를 수정하고 업데이트한 뒤 반환하는 메서드
    @Transactional
    public Equipment updateEquipment(Long eqpCode, EquipmentDto equipmentDto) {
        Equipment equipment = getEquipment(eqpCode);
        equipment.setEqpTitle(equipmentDto.getEqpTitle());
        equipment.setEqpPurchase(equipmentDto.getEqpPurchase());
        equipment.setEqpDate(equipmentDto.getEqpDate());
        equipment.setEqpStatus(equipmentDto.getEqpStatus());
        return equipmentRepository.save(equipment);
    }

    // 주어진 eqpCode에 해당하는 기구를 삭제하는 메서드
    @Transactional
    public void deleteEquipment(Long eqpCode) {
        if (equipmentRepository.existsById(eqpCode)) {
            equipmentRepository.deleteById(eqpCode);
        } else {
            throw new IllegalArgumentException("Invalid equipment code: " + eqpCode);
        }
    }
}
