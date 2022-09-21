package com.duongtai.sydiary.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.duongtai.sydiary.entities.Diary;
import com.duongtai.sydiary.entities.User;
import com.duongtai.sydiary.services.impl.DiaryServiceImpl;
import com.duongtai.sydiary.services.impl.UserServiceImpl;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class WebController {
	
	@Autowired
	private DiaryServiceImpl diaryService;
	
	@Autowired
	private UserServiceImpl userService;
	
    @GetMapping("")
    public ModelAndView index(ModelMap model) throws IOException {
        model.addAttribute("title","Home - Syndiary");
        model.addAttribute("diaries",diaryService.findAllByAuthUsername());
        model.addAttribute("new_diary", new Diary());
        return new ModelAndView("index", model);
    }
     
    @GetMapping("user")
    public String user(ModelMap model){

        model.addAttribute("new_diary", new Diary());
        model.addAttribute("title","User - Syndiary");
        return "user";
    }
    
    @GetMapping("register")
    public String register_view(ModelMap model){
    	model.addAttribute("user",new User());
        model.addAttribute("title","Register - Syndiary");
        return "register";
    }
    
    @PostMapping("create_user")
    public String createUser(ModelMap model, @ModelAttribute User user) {
    	if(userService.saveUser(user)!=null) {
    		model.addAttribute("register","success");
    		return "login";
    	}
    	model.addAttribute("register","duplicate");
    	model.addAttribute("user",user);
    	return "register";
    }

    @GetMapping("login") 
    public String login_view(ModelMap model){ 
        model.addAttribute("title","Login - Syndiary");
        return "login";
    }
    
    @PostMapping("")
    public ModelAndView saveDiary(ModelMap model, @ModelAttribute Diary diary) throws IOException {

        model.addAttribute("diaries",diaryService.findAllByAuthUsername());
    	if(diaryService.createDiary(diary) != null) {
    		model.addAttribute("new_diary",diary);
    		model.addAttribute("create","success");
    	}

		model.addAttribute("new_diary",diary);
		model.addAttribute("create","failed");
    	return new ModelAndView("index", model);
    }
}
