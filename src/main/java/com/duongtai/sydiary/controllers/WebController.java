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
        model.addAttribute("diaries",diaryService.getAllByAuthUsername());
        return new ModelAndView("index", model);
    }
     
    @GetMapping("user")
    public String user(ModelMap model){
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
    	if(userService.createUserWebApp(user)) {
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
}
