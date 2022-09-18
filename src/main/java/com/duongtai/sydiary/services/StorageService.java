package com.duongtai.sydiary.services;

import com.duongtai.sydiary.entities.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    public ResponseEntity<ResponseObject> storeFile(MultipartFile file, String username);
    public Stream<Path> loadAllImage();
    public ResponseEntity<byte[]> readFile(String fileName);
    public ResponseEntity<byte[]> readProfileImage(String fileName);
    public void deleteFile();
}
