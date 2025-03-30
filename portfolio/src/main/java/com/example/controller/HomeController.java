package com.example.controller;

import com.example.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        List<Project> projects = Arrays.asList(
                new Project("Hotel Management System", "A Hotel Management System (HMS) is a comprehensive software solution used by hotels, resorts, and other hospitality businesses to manage their operations efficiently. The system is designed to streamline various processes such as room booking, check-in/check-out, guest services, billing, and reporting. It helps improve the guest experience and optimize the management of resources.", "https://github.com/example/project1"),
                new Project("User management system", "A User Management System (UMS) is a software solution designed to manage and control user access, roles, and permissions within an application, website, or platform. It enables administrators to create, modify, delete, and monitor users, ensuring that they have appropriate access based on their roles or responsibilities. UMS is commonly used in various types of applications, including enterprise software, content management systems, and online platforms, to ensure secure and efficient user interactions.", "https://github.com/example/project2")
        );
        model.addAttribute("projects", projects);
        return "index";
    }




}
