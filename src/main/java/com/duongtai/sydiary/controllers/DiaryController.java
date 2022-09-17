package com.duongtai.sydiary.controllers;

import com.duongtai.sydiary.configs.MyUserDetail;
import com.duongtai.sydiary.entities.Diary;
import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.services.impl.DiaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/diary/")
public class DiaryController {

    @Autowired
    DiaryServiceImpl diaryService;


    @PostMapping("create")
    public ResponseEntity<ResponseObject> createNew(@RequestBody Diary diary){
        return diaryService.createDiary(diary);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getDiaryById(@PathVariable Long id){
        return diaryService.getDiaryById(id);
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllByAuth(){
        return diaryService.findAllByAuthUsername();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable Long id){
        return diaryService.deleteDiaryById(id);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<ResponseObject> editById(@PathVariable Long id, @RequestBody Diary diary){
        diary.setId(id);
        return diaryService.editDiaryById(diary);
    }

}
