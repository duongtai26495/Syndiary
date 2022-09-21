package com.duongtai.sydiary.services;

import com.duongtai.sydiary.entities.Diary;
import com.duongtai.sydiary.entities.ResponseObject;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface DiaryService {
    Diary createDiary(Diary diary);

    Diary editDiaryById(Diary diary);

    Diary getDiaryById(Long id);

    boolean deleteDiaryById(Long id);

    List<Diary> findAllByAuthUsername();

}
