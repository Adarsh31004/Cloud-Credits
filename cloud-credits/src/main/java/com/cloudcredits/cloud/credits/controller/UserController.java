package com.cloudcredits.cloud.credits.controller;

import com.cloudcredits.cloud.credits.model.Users;
import com.cloudcredits.cloud.credits.repo.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    private boolean authenticateUser(String username, String password) {
        Users user = userRepository.findByUsername(username);  // Query the user by username

        if (user == null) {
            return false;  // User not found
        }

        // Check if the provided password matches the hashed password stored in the database
        return password.equals(user.getPassword());
    }
    @GetMapping("/register")
    public String regiPage(){
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password ,
                           @RequestParam String confirmPassword,
                           Model model){
        Users users =  new Users();
if(password.equals(confirmPassword)) {
    users.setUsername(username);
    users.setPassword(password);
    users.setRole("USER");
    userRepository.save(users);
    return "redirect:/login";
}
else{
    model.addAttribute("error","password and confirm password donot match");
    return "register";

}

    }

    @PostMapping("/login")
    public String login(@RequestParam String username ,
                        @RequestParam String password, Model model , HttpSession session){
        Users users = userRepository.findByUsername(username);

   if(authenticateUser(username,password)){
       session.setAttribute("id",users.getId());
       session.setAttribute("role",users.getRole());

       return "redirect:/task";

   }
   else {
       model.addAttribute("error","invalid username or password");
return "login";
   }

    }


}
