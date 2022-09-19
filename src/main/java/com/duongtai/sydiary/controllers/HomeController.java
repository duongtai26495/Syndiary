package com.duongtai.sydiary.controllers;

import com.duongtai.sydiary.services.impl.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private StorageServiceImpl storageService;

    @GetMapping("images/{fileName:.+}")
    public ResponseEntity<byte[]> readFile (@PathVariable String fileName){
        return storageService.readFile(fileName);
    }

}
