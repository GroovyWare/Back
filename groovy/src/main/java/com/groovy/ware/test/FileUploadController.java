package com.groovy.ware.test;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FileUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("docxFile") MultipartFile file) {
        try {
        	log.info("controller start ==========================");
        	log.info("file", file.toString());
        	
            // 파일을 임시 디렉터리에 저장
            File tempFile = File.createTempFile("upload-", ".docx");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            	log.info("success");
                fos.write(file.getBytes());
            }

            // 파일 경로 반환
            return ResponseEntity.ok(tempFile.getAbsolutePath());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }
}