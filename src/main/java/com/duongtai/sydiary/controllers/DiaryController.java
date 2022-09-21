package com.duongtai.sydiary.controllers;

import com.duongtai.sydiary.configs.Snippets;
import com.duongtai.sydiary.entities.Diary;
import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.services.impl.DiaryServiceImpl;

import static com.duongtai.sydiary.configs.MyUserDetail.getUsernameLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    	return ResponseEntity.status(HttpStatus.OK).body(
    			new ResponseObject(Snippets.SUCCESS, Snippets.DIARY_CREATE_SUCCESS, diaryService.createDiary(diary)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getDiaryById(@PathVariable Long id){
    	Diary diary = diaryService.getDiaryById(id);
        if(diary != null) {
        	return ResponseEntity.status(HttpStatus.OK).body(
        			new ResponseObject(Snippets.SUCCESS, Snippets.DIARY_FOUND,diary));
        }
    	return ResponseEntity.status(HttpStatus.OK).body(
    			new ResponseObject(Snippets.FAILED, Snippets.DIARY_NOT_FOUND,null));
    }

    @GetMapping("all")
    public ResponseEntity<ResponseObject> getAllByAuth(){
        return ResponseEntity.status(HttpStatus.OK).body(
    			new ResponseObject(Snippets.SUCCESS, Snippets.LIST_DIARY_BY +" "+getUsernameLogin(),diaryService.findAllByAuthUsername()));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable Long id){
        if(diaryService.deleteDiaryById(id)) {
        	return ResponseEntity.status(HttpStatus.OK).body(
        			new ResponseObject(Snippets.SUCCESS, Snippets.DIARY_DELETED, null));
        }
    	return ResponseEntity.status(HttpStatus.OK).body(
    			new ResponseObject(Snippets.SUCCESS, Snippets.DIARY_FOUND,null));
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<ResponseObject> editById(@PathVariable Long id, @RequestBody Diary diary){
        diary.setId(id);
        if(diaryService.getDiaryById(id) != null) {
        return ResponseEntity.status(HttpStatus.OK).body(
    			new ResponseObject(Snippets.SUCCESS, Snippets.DIARY_EDITED,diaryService.editDiaryById(diary)));
    }
        return ResponseEntity.status(HttpStatus.OK).body(
    			new ResponseObject(Snippets.FAILED, Snippets.DIARY_NOT_FOUND,null));
    }
}
