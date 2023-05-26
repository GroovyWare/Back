package com.groovy.ware.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
	
	public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		
		Path uploadPath = Paths.get(uploadDir);
		
		/* 업로드 경로가 존재하지 않을 경우 경로를 먼저 생성한다. */
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		/* 파일명 리네임 */
		String replaceFileName = fileName + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		
		/* 파일 저장 */
		try(InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(replaceFileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("파일을 저장하지 못하였습니다. filename : " + fileName);
		}
		
		return replaceFileName;
	}

	public static void deleteFile(String uploadDir, String fileName) throws IOException {
		
		Path uploadPath = Paths.get(uploadDir);
		Path filePath = uploadPath.resolve(fileName);
		
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			throw new IOException("파일을 삭제하지 못하였습니다. filename : " + fileName);
		}
		
	}

}









