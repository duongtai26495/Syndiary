package com.duongtai.sydiary.services;

import com.duongtai.sydiary.entities.Diary;
import com.duongtai.sydiary.entities.ResponseObject;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface DiaryService {
    ResponseEntity<ResponseObject> createDiary(Diary diary);

    ResponseEntity<ResponseObject> editDiaryById(Diary diary);

    ResponseEntity<ResponseObject> getDiaryById(Long id);

    ResponseEntity<ResponseObject> deleteDiaryById(Long id);

    ResponseEntity<ResponseObject> findAllByAuthUsername();

    //for web MVC
    List<Diary> getAllByAuthUsername();
    Diary getDiaryByIdforWeb(Long id);
    
    boolean saveNewDiary(Diary diary);
}
