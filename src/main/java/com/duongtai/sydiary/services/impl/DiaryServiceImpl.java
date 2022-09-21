package com.duongtai.sydiary.services.impl;

import com.duongtai.sydiary.configs.Snippets;
import com.duongtai.sydiary.entities.*;
import com.duongtai.sydiary.repositories.DiaryRepository;
import com.duongtai.sydiary.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.duongtai.sydiary.configs.MyUserDetail.getUsernameLogin;

@Service
public class DiaryServiceImpl implements DiaryService {

 
    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserServiceImpl userService;


    @Override
    public synchronized Diary createDiary(Diary diary) {
        if (getUsernameLogin() != null){
            User user = userService.findByUsername(getUsernameLogin());
            if (user!=null){
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
                if(diary.getTitle().isEmpty()){
                    diary.setTitle(Snippets.UNTITLED_DIARY);
                }
                diary.setAuthor(user);
                diary.setCreated_at(sdf.format(date));
                diary.setLast_edited(sdf.format(date));
                diary.setActive(1);
                return diaryRepository.save(diary);
            }
        }
        return null;
    }

    @Override
    public synchronized Diary editDiaryById(Diary diary) {
        if (getUsernameLogin() != null){
            if (diaryRepository.existsById(diary.getId())){
                Diary getDiary = diaryRepository.findById(diary.getId()).get();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
                getDiary.setTitle(diary.getTitle());
                getDiary.setContent(diary.getContent());
                getDiary.setColor(diary.getColor());
                getDiary.setLast_edited(sdf.format(date));
                return diaryRepository.save(getDiary);
            }
           
        }
        return null;


    }

    @Override
    public Diary getDiaryById(Long id) {
        if (diaryRepository.existsById(id)){
           return diaryRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public synchronized boolean deleteDiaryById(Long id) {
        if (getUsernameLogin() != null) {
            if (diaryRepository.existsById(id)) {
            	diaryRepository.deleteById(id);
                return true;
            }
           
        }
       return false;
    }

    @Override
    public List<Diary> findAllByAuthUsername() {
        if (getUsernameLogin()!=null){
            User user = userService.findByUsername(getUsernameLogin());
            if (user!=null){
                List<Diary> diaryList = diaryRepository.findAll();
                List<Diary> getList = new ArrayList<>();
                String username = getUsernameLogin();
                for (Diary diary : diaryList) {
                    if (diary.getAuthor().getUsername().equals(username)){
                        getList.add(diary);
                    }
                }
                getList.sort((o1, o2) -> o1.getLast_edited().compareTo(o2.getLast_edited()));
                Collections.reverse(getList);
                return getList;
            }
        }
        return null;
    }

}
