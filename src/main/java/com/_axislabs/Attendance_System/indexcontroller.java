package com._axislabs.Attendance_System;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;


@Controller
public class indexcontroller {
	
    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }
}
