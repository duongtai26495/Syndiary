package com.duongtai.sydiary.services.impl;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.services.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
           return authentication.getName();
        }
        return null;
    }

    private boolean isImageFile(MultipartFile file){
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] {"png","jpg","jpeg","bmp"}).contains(fileExtension.trim().toLowerCase());
    }
    @Override
    public String storeFile(MultipartFile file) {
        try {
            if(file.isEmpty()){
                throw new RuntimeException("Failed to store empty file");
            }
            if (!isImageFile(file)){
                throw new RuntimeException("You can only upload image file");
            }

            float fileSize = file.getSize() / 1_000_000.0f;
            if(fileSize > 5.0f){
                throw new RuntimeException("File must be <= 5mb");
            }

            String username = getUsername();

            final Path storageFolder = Paths.get("uploads/"+username+"/profile");
            try {
                Files.createDirectories(storageFolder);
            }catch (IOException e){
                throw new RuntimeException("Cannot initialize storage",e);
            }

            String file_extension = "png";
            String generatedFileName = username+"_profile_image";
            generatedFileName = generatedFileName+"."+file_extension;
            Path destinationPath = storageFolder.resolve(
                    Paths.get(generatedFileName))
                    .normalize().toAbsolutePath();
            if(!destinationPath.getParent().equals(storageFolder.toAbsolutePath())){
                throw new RuntimeException(
                    "Cannot store file outside current directory"
                );
            }
            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        }catch (IOException e){
            throw new RuntimeException("Failed to store image", e);
        }

    }

    @Override
    public Stream<Path> loadAllFile() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseObject> readFile() {
        Path path = Paths.get("uploads/"+getUsername()+"/profile").toAbsolutePath();
        String profile_avatar = path+"\\"+getUsername()+"_profile_image.png";
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("SUCCESS","Get avatar done",profile_avatar)
        );
    }


    @Override
    public void deleteFile() {

    }
}
