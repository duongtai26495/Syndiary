package com.duongtai.sydiary.services.impl;

import com.duongtai.sydiary.configs.Snippets;
import com.duongtai.sydiary.entities.*;
import com.duongtai.sydiary.repositories.DiaryRepository;
import com.duongtai.sydiary.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DiaryServiceImpl implements DiaryService {


    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserServiceImpl userService;

    public String getUsernameLogin (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public ResponseEntity<ResponseObject> createDiary(Diary diary) {
        if (getUsernameLogin() != null){
            User user = userService.findByUsername(getUsernameLogin());
            if (user!=null){
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
                if(diary.getTitle().isEmpty()){
                    diary.setTitle("Unnamed Diary");
                }
                diary.setAuthor(user);
                diary.setCreated_at(sdf.format(date));
                diary.setLast_edited(sdf.format(date));
                diary.setActive(1);
                return ResponseEntity.status(HttpStatus.OK).body(
                  new ResponseObject(Snippets.SUCCESS,"Create diary successfully",diaryRepository.save(diary))
                );
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ResponseObject(Snippets.FAILED,Snippets.USER_DO_NOT_LOGIN,null)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> editDiaryById(Diary diary) {
        Diary getDiary = diaryRepository.findById(diary.getId()).get();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
        getDiary.setTitle(diary.getTitle());
        getDiary.setContent(diary.getContent());
        getDiary.setLast_edited(sdf.format(date));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Snippets.SUCCESS, "Diary edited", diaryRepository.save(getDiary))
        );
    }

    @Override
    public ResponseEntity<ResponseObject> getDiaryById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Snippets.SUCCESS, Snippets.FOUNDED, diaryRepository.findById(id).get())
        );
    }

    @Override
    public ResponseEntity<ResponseObject> deleteDiaryById(Long id) {
        diaryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Snippets.SUCCESS, "Diary deleted", null )
        );
    }

    @Override
    public ResponseEntity<ResponseObject> findAllByAuthUsername() {
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
                Collections.reverse(getList);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(Snippets.SUCCESS, "List diary by "+getUsernameLogin(), getList )
                );
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ResponseObject(Snippets.FAILED,Snippets.USER_DO_NOT_LOGIN,null)
        );
    }

}
