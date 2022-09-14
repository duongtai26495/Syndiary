package com.duongtai.sydiary.configs;

import com.duongtai.sydiary.entities.Role;
import com.duongtai.sydiary.services.RoleService;
import com.duongtai.sydiary.services.impl.RoleServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(RoleServiceImpl roleService){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Role role_user = new Role(Snippets.ROLE_USER);
                Role role_admin = new Role(Snippets.ROLE_ADMIN);
                roleService.saveNewRole(role_user);
                roleService.saveNewRole(role_admin);
            }
        };
    }
}
