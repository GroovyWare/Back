package com.groovy.ware.equipment.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.equipment.dto.EquipmentDto;
import com.groovy.ware.equipment.entity.Equipment;
import com.groovy.ware.equipment.service.EquipmentService;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final ModelMapper modelMapper;

    public EquipmentController(EquipmentService equipmentService, ModelMapper modelMapper) {
        this.equipmentService = equipmentService;
        this.modelMapper = modelMapper;
    }

    // 모든 기구를 가져오는 메서드
    @GetMapping
    public List<Equipment> getAllEquipments() {
        return equipmentService.getAllEquipments();
    }

    // 특정 기구를 가져오는 메서드
    @GetMapping("/search")
    public Equipment getEquipment(@PathVariable Long eqpCode) {
        return equipmentService.getEquipment(eqpCode);
    }

    // 새로운 기구를 등록하는 메서드
//    @PostMapping("/equipment")
//    public ResponseEntity<Equipment> createEquipment(@RequestBody EquipmentDto equipmentDto) {
//        Equipment equipment = equipmentService.createEquipment(equipmentDto);
//        return ResponseEntity.ok(equipment);
//    }    

    // 기구 정보를 수정하는 메서드
    @PutMapping("/{eqpCode}")
    public Equipment updateEquipment(@PathVariable Long eqpCode, @RequestBody EquipmentDto equipmentDto) {
        Equipment equipment = equipmentService.getEquipment(eqpCode);

        // DTO의 필드를 Equipment 객체에 매핑합니다.
        modelMapper.map(equipmentDto, equipment);

        return equipmentService.saveEquipment(equipment);
    }

    // 기구를 삭제하는 메서드
    @DeleteMapping("/{eqpCode}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long eqpCode) {
        equipmentService.deleteEquipment(eqpCode);
        return ResponseEntity.noContent().build();
    }
}
