package com._axislabs.Attendance_System;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class indexcontroller {
	
	 @Autowired
	    private JwtUtil JwtUtil;
	
    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }
    
    @PostMapping("/login")
    public String loginRequest(@RequestParam String username, @RequestParam String password, Model model) throws IOException {
    	ObjectMapper objectMapper = new ObjectMapper();
    	List<Map<String, String>> users = objectMapper.readValue(new File("src/main/resources/user.json"),
                new TypeReference<List<Map<String, String>>>() {});

        boolean isAuthenticated = users.stream()
                .anyMatch(user -> user.get("userName").equals(username) && user.get("password").equals(password));
        if (isAuthenticated) {
            String token = JwtUtil.generateToken(username);
            
            System.out.println(username);
            model.addAttribute("username", username);
            model.addAttribute("token", token);
            return "login";
        } else {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        
        }
    }
}
