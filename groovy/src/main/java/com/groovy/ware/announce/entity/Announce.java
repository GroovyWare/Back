package com.groovy.ware.announce.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.groovy.ware.common.entity.File;
import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_ANNOUNCE")
@SequenceGenerator(name="ANNOUNCE_SEQ_GENERATOR", sequenceName="SEQ_ANN_CODE", initialValue=1, allocationSize=1)
public class Announce {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ANNOUNCE_SEQ_GENERATOR")
    @Column(name="ANN_CODE")
    private Long annCode; 			// 공지사항 코드

    @Column(name="ANN_TITLE")
    private String annTitle; 		// 제목

    @Column(name="ANN_DATE")
    private Date annDate; 			// 작성일

    @ManyToOne
    @JoinColumn(name="EMP_CODE")
    private Employee employee;		// 직원 코드

    @Column(name="ANN_CONTENT")
    private String annContent;		// 내용

    @OneToMany(mappedBy="announce")
    @JsonManagedReference
    private List<File> files;
    
    @PrePersist
    public void prePersist() {
        annDate = new Date();
    }

    public void update(String annTitle, String annContent) {
        this.annTitle = annTitle;
        this.annContent = annContent;
    }
}
