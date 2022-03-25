package com.duongtai.sydiary;

import com.duongtai.sydiary.entities.Role;
import com.duongtai.sydiary.repositories.RoleRepository;
import com.duongtai.sydiary.services.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApplicationSyncDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationSyncDiaryApplication.class, args);
	}

}
