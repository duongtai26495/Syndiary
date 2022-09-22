package com.duongtai.sydiary.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.duongtai.sydiary.configs.Snippets;
import com.duongtai.sydiary.configs.SortDiary;
import com.duongtai.sydiary.entities.Diary;
import com.duongtai.sydiary.entities.User;
import com.duongtai.sydiary.services.impl.DiaryServiceImpl;
import com.duongtai.sydiary.services.impl.UserServiceImpl;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

import static com.duongtai.sydiary.configs.MyUserDetail.getUsernameLogin;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class WebController {
	
	@Autowired
	private DiaryServiceImpl diaryService;
	
	@Autowired
	private UserServiceImpl userService;
	
    @GetMapping("")
    public String index_sort(ModelMap model, @PathParam("sort") String sort) throws IOException {
    	List<Diary> diaries = diaryService.findAllByAuthUsername();
    	if(sort == null) {
    		sort = Snippets.LAST_EDITED;
    	}
    	String[] sorts = { Snippets.LAST_EDITED, Snippets.CREATED_AT, Snippets.A_Z,Snippets.Z_A};

    	model.addAttribute("sorts", sorts);
        model.addAttribute("title","Home - Syndiary");
        model.addAttribute("sorted",sort);
        model.addAttribute("diaries",SortDiary.sortByCondition(diaryService.findAllByAuthUsername(), sort));
        model.addAttribute("nav", "home");
        model.addAttribute("new_diary", new Diary());
        return "index";
    }
    @GetMapping("user")
    public String user(ModelMap model){
    	User user = userService.findByUsername(getUsernameLogin());
    	model.addAttribute("user", user);
        model.addAttribute("new_diary", new Diary());
        model.addAttribute("title","User - Syndiary");
        model.addAttribute("nav", "user");
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
    public String login_view(ModelMap model, @PathParam("logout") String logout){
    	model.addAttribute("logout", logout);
        model.addAttribute("title","Login - Syndiary");
        return "login";
    }
    
    @PostMapping("")
    public String saveDiary(ModelMap model, @ModelAttribute Diary diary) throws IOException {
    	diaryService.createDiary(diary);
    	return "redirect:/";
    }
    
    @GetMapping("diary")
    public String editDiary(ModelMap model, @PathParam("id") long id) {
    	Diary diary = diaryService.getDiaryById(id);
    	model.addAttribute("title", diary.getTitle());
    	model.addAttribute("diary", diary);
    	return "editdiary";
    }
    
    @PostMapping("edit_diary")
    public String save_edit_diary(ModelMap model, @PathParam("id") long id, @ModelAttribute Diary diary) {
    	if(diaryService.getDiaryById(id) != null) {
    		if(diaryService.editDiaryById(diary) != null) {
    			model.addAttribute("edit","success");
    			model.addAttribute("diary",diary);
    			return "editdiary";
    		}
    		model.addAttribute("edit","failed");
    		model.addAttribute("diary",diary);
    		return "editdiary";
    	}
    	return "redirect:/";
    }
    
    @PostMapping("delete")
    public String delete_diary(ModelMap model, @PathParam("id") long id) {
    	if(diaryService.getDiaryById(id) != null) {
    		if(!diaryService.deleteDiaryById(id)) {
    			model.addAttribute("delete", "failed");
    			return "index";
    		}
    	}
    	return "redirect:/";
    }
}

