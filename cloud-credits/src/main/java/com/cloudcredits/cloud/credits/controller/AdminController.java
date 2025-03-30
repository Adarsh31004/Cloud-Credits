package com.cloudcredits.cloud.credits.controller;

import com.cloudcredits.cloud.credits.model.Task;
import com.cloudcredits.cloud.credits.model.Users;
import com.cloudcredits.cloud.credits.repo.TaskRepository;
import com.cloudcredits.cloud.credits.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/admin")
    public String Admin(Model model){

        List<Users> users = userRepository.findByRole("USER");


        model.addAttribute("users",users);

        return "admin-dash";
    }


    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id,
                          Model model){
        Users users = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
       List<Task> task = taskRepository.findByUser(users);

       model.addAttribute("user",users);
       model.addAttribute("tasks",task);
        return "profile";
    }



    }


