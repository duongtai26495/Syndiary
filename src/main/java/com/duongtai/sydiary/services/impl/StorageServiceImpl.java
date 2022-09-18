package com.duongtai.sydiary.services.impl;

import com.duongtai.sydiary.configs.Snippets;
import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.User;
import com.duongtai.sydiary.services.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static com.duongtai.sydiary.configs.MyUserDetail.getUsernameLogin;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path storageFolder = Paths.get("uploads/images/");
    private final Path storageFolderProfile = Paths.get("uploads/profile");
    @Autowired
    private UserServiceImpl userService;

    public StorageServiceImpl() {
        try {
            Files.createDirectories(storageFolder);
            Files.createDirectories(storageFolderProfile);
        }catch (Exception e){
            throw new RuntimeException(Snippets.CANNOT_INITIALIZE_STORAGE,e);
        }
    }
    private boolean isImageFile (MultipartFile file){
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        assert fileExtension != null;
        return Arrays.asList(new String[] {"png","jpg","jpeg","bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public ResponseEntity<ResponseObject> storeFile(MultipartFile file, String username) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException(Snippets.FAILED_STORE_EMPTY_FILE);
            }
            if (!isImageFile(file)) {
                throw new RuntimeException(Snippets.YOU_CAN_ONLY_UPLOAD_IMAGE);
            }
            float fileSize = file.getSize() / 1_000_000.0f;
            if (fileSize > 5.0f) {
                throw new RuntimeException(Snippets.SIZE_UPLOAD_FILE);
            }
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-", "");
            generatedFileName = generatedFileName + "." + fileExtension;


            if( !username.equals("noname") && username.equals(getUsernameLogin())){
                //username not null and equals with token
                Path destinationFilePath = this.storageFolderProfile.resolve(
                                Paths.get(generatedFileName))
                        .normalize().toAbsolutePath();
                if (!destinationFilePath.getParent().equals(this.storageFolderProfile.toAbsolutePath())){
                    throw new RuntimeException(Snippets.CANNOT_STORE_OUSIDE);
                }

                try(InputStream inputStream = file.getInputStream()){
                    Files.copy(inputStream,destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                }
                System.out.println("Save image to user");
                User user = new User();
                user.setUsername(username);
                user.setProfile_image(generatedFileName);
                userService.editByUsername(user);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(Snippets.SUCCESS,Snippets.UPLOAD_PROFILE_IMAGE_SUCCESS +" in user "+ username, generatedFileName)
                );
            }else{
                //username null
                Path destinationFilePath = this.storageFolder.resolve(
                                Paths.get(generatedFileName))
                        .normalize().toAbsolutePath();
                if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())){
                    throw new RuntimeException(Snippets.CANNOT_STORE_OUSIDE);
                }

                try(InputStream inputStream = file.getInputStream()){
                    Files.copy(inputStream,destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(Snippets.SUCCESS,Snippets.UPLOAD_IMAGE_SUCCESS, generatedFileName)
                );
            }

        }catch (IOException e){
            throw new RuntimeException(Snippets.STORE_FILE_FAILED,e);
        }


    }


    @Override
    public Stream<Path> loadAllImage() {
        return null;
    }

    @Override
    public ResponseEntity<byte[]> readFile(String fileName) {
        try {
            Path file = storageFolder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(bytes);
            }else{
                return ResponseEntity.noContent().build();
            }
        }catch (IOException e){
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<byte[]> readProfileImage(String fileName) {
        try {
            Path file = storageFolderProfile.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(bytes);
            }else{
                return ResponseEntity.noContent().build();
            }
        }catch (IOException e){
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public void deleteFile() {

    }
}
