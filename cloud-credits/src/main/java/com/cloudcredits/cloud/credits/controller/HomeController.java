package com.cloudcredits.cloud.credits.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping()
    public String home(HttpSession session){
        Long id =(Long) session.getAttribute("id");

        if(id==null){
            return "redirect:/login";
        }

            return "home";

    }

    @GetMapping("/logout")

    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

}
