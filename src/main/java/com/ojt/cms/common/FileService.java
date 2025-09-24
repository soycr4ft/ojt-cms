package com.ojt.cms.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	@Value("${iupload.path}")
	private String iuploadPath;
	
    public String storeFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        // 원본 파일명
        String originalFilename = file.getOriginalFilename();
        // 파일명 충돌 방지를 위해 UUID 추가
        String saveFileName = java.util.UUID.randomUUID() + "_" + originalFilename;
        // 저장할 경로
        Path uploadDir = Paths.get(iuploadPath);
        Path targetPath = uploadDir.resolve(saveFileName);
        // 디렉토리 없으면 생성
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }        // 실제 파일 저장
        file.transferTo(targetPath.toFile());
        // DB에는 접근 가능한 URL 또는 경로만 저장
        return saveFileName;
    }
}
