package com.groovy.ware.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="FILES")
@SequenceGenerator(name="FILES_SEQ_GENERATOR", sequenceName="SEQ_FILES_ID", initialValue=1, allocationSize=1)
public class Files {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FILES_SEQ_GENERATOR")
    private Long id;

    private String name;
    
    @Lob
    private byte[] data;

    // getters and setters
}