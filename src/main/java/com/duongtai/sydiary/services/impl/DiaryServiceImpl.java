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
    public synchronized ResponseEntity<ResponseObject> createDiary(Diary diary) {
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
                return ResponseEntity.status(HttpStatus.OK).body(
                  new ResponseObject(Snippets.SUCCESS,Snippets.DIARY_CREATE_SUCCESS,diaryRepository.save(diary))
                );
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ResponseObject(Snippets.FAILED,Snippets.USER_DO_NOT_LOGIN,null)
        );
    }

    @Override
    public synchronized ResponseEntity<ResponseObject> editDiaryById(Diary diary) {
        if (getUsernameLogin() != null){
            if (diaryRepository.existsById(diary.getId())){
                Diary getDiary = diaryRepository.findById(diary.getId()).get();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
                getDiary.setTitle(diary.getTitle());
                getDiary.setContent(diary.getContent());
                getDiary.setColor(diary.getColor());
                getDiary.setLast_edited(sdf.format(date));
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(Snippets.SUCCESS, Snippets.DIARY_EDITED, diaryRepository.save(getDiary))
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(Snippets.FAILED, Snippets.DIARY_NOT_FOUND,null)
            );
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ResponseObject(Snippets.FAILED,Snippets.USER_DO_NOT_LOGIN,null)
        );


    }

    @Override
    public ResponseEntity<ResponseObject> getDiaryById(Long id) {
        if (diaryRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(Snippets.SUCCESS, Snippets.FOUNDED, diaryRepository.findById(id).get())
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Snippets.FAILED, Snippets.DIARY_NOT_FOUND, diaryRepository.findById(id).get())
        );
    }

    @Override
    public synchronized ResponseEntity<ResponseObject> deleteDiaryById(Long id) {
        if (getUsernameLogin() != null) {
            if (diaryRepository.existsById(id)) {
                diaryRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(Snippets.SUCCESS, Snippets.DIARY_DELETED, null )
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(Snippets.FAILED, Snippets.DIARY_NOT_FOUND,null)
            );
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ResponseObject(Snippets.FAILED,Snippets.USER_DO_NOT_LOGIN,null)
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
                getList.sort((o1, o2) -> o1.getLast_edited().compareTo(o2.getLast_edited()));
                Collections.reverse(getList);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(Snippets.SUCCESS, String.format(Snippets.LIST_DIARY_BY,getUsernameLogin()), getList )
                );
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ResponseObject(Snippets.FAILED,Snippets.USER_DO_NOT_LOGIN,null)
        );
    }

    //For web app

	@Override
	public List<Diary> getAllByAuthUsername() {
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

	@Override
	public Diary getDiaryByIdforWeb(Long id) {
		 if (diaryRepository.existsById(id)){
	            return diaryRepository.findById(id).get();
	        }
	      return null;
	}
    
    

}
