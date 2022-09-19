package com.duongtai.sydiary.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duongtai.sydiary.configs.MyUserDetail;
import com.duongtai.sydiary.services.impl.UserServiceImpl;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class WebController {
	

	@Autowired
	private UserServiceImpl userService;
	
    @GetMapping("")
    public ModelAndView index(ModelMap model, HttpServletResponse response) throws IOException {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = "";
    	if (principal instanceof MyUserDetail) {
    	    username = ((MyUserDetail) principal).getUsername();
    	} else {
    	    username = principal.toString();
    	}
    	if(username.equals("anonymousUser")) {
    		model.addAttribute("warning","unlogin");
            return new ModelAndView("login", model);
    	}
        model.addAttribute("title","Home - Syndiary");
        return new ModelAndView("index", model);
    }
    
    @GetMapping("user")
    public String user(ModelMap model){
        model.addAttribute("title","User - Syndiary");
        return "user";
    }
    
    @GetMapping("register")
    public String register_view(ModelMap model){
    	
        model.addAttribute("title","Register - Syndiary");
        return "register";
    }

    @GetMapping("login")
    public String login_view(ModelMap model){
    	System.out.println(model.getAttribute("warning"));
        model.addAttribute("title","Login - Syndiary");
        return "login";
    }
}
