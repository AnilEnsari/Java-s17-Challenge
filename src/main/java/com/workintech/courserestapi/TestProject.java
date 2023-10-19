package com.workintech.courserestapi;


import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing")
public class TestProject {

    @PostConstruct
    @GetMapping("/")

    public String testProject (){
   return "Project has been installed successfully";
    }
}
