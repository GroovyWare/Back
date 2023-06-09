package com.groovy.ware.equipment.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.common.ResponseDto;
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.equipment.dto.EquipmentDto;
import com.groovy.ware.equipment.service.EquipmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    /* 기구 목록 */
    @GetMapping
    public ResponseEntity<ResponseDto> getEquipments(@RequestParam(name="page", defaultValue="1") int page,
            @RequestParam(name="size", defaultValue="10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<EquipmentDto> equipments = equipmentService.getEquipments(pageable);

        PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(equipments);

        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
        responseDtoWithPaging.setPageInfo(pageInfo);
        responseDtoWithPaging.setData(equipments.getContent());

        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK, "기구 목록 조회가 완료되었습니다.", responseDtoWithPaging));
    }

    /* 기구 검색 */
    @GetMapping("/search")
    public ResponseEntity<ResponseDto> searchEquipments(@RequestParam String condition, @RequestParam String keyword, Pageable pageable) {
        Page<EquipmentDto> equipments = equipmentService.searchEquipments(condition, keyword, pageable);
        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK, "기구 검색이 완료되었습니다.", equipments));
    }
    
    /* 기구 등록 */
    @PostMapping
    public ResponseEntity<ResponseDto> createEquipment(@RequestBody EquipmentDto equipmentDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDto employeeDto = (EmployeeDto) authentication.getPrincipal();

        Long empCode = employeeDto.getEmpCode();

        equipmentService.createEquipment(equipmentDto, empCode);
        
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "기구 정보가 등록되었습니다."));
    }

    /* 기구 수정 */
    @PutMapping("/{eqpCode}")
    public ResponseEntity<ResponseDto> updateEquipment(@PathVariable Long eqpCode, @RequestBody EquipmentDto equipmentDto) {
        equipmentDto.setEqpCode(eqpCode);
        equipmentService.updateEquipment(equipmentDto);
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "기구 정보가 수정되었습니다."));
    }

    /* 기구 삭제 */
    @DeleteMapping("/{eqpCode}")
    public ResponseEntity<ResponseDto> deleteEquipment(@PathVariable Long eqpCode) {
        equipmentService.deleteEquipment(eqpCode);
        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK, "기구 정보가 삭제되었습니다."));
    }
}
