package com.groovy.ware.announce.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.groovy.ware.announce.exception.FileUploadException;
import com.groovy.ware.util.FileUploadUtils;

@Service
public class FileService {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    public String saveFile(MultipartFile file) {
        String imageName = UUID.randomUUID().toString().replace("-", "");
        try {
            return FileUploadUtils.saveFile(IMAGE_DIR, imageName, file);
        } catch (IOException e) {
            throw new FileUploadException("Could not save file", e);
        }
    }

    public void deleteFile(String fileName) {
        try {
            FileUploadUtils.deleteFile(IMAGE_DIR, fileName);
        } catch (IOException e) {
            throw new FileUploadException("Could not delete file", e);
        }
    }
}
