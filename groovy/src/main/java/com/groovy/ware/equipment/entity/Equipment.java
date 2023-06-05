package com.groovy.ware.equipment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "GRV_EQUIPMENT")
@SequenceGenerator(name="EQUIPMENT_SEQ_GENERATOR", sequenceName="SEQ_EQP_CODE", initialValue=1, allocationSize=1)
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EQUIPMENT_SEQ_GENERATOR")
    @Column(name = "EQP_CODE")
    private Long eqpCode;

    @Column(name = "EQP_TITLE")
    private String eqpTitle;
    
    @Column(name = "EQP_INSPECTOR")
    private String eqpInspector;

    @Column(name = "EQP_PURCHASE")
    private Date eqpPurchase;
    
    @Column(name = "EQP_DATE")
    private Date eqpDate;

    @Column(name = "EQP_STATUS")
    private String eqpStatus;

    @ManyToOne
    @JoinColumn(name="EMP_CODE")
    private Employee employee;
    
    public void update(String eqpTitle, String eqpInspector, Date eqpDate, String eqpStatus) {
        this.eqpTitle = eqpTitle;
        this.eqpInspector = eqpInspector;
        this.eqpDate = eqpDate;
        this.eqpStatus = eqpStatus;
    }

}
