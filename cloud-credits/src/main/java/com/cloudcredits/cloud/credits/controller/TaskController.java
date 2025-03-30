package com.cloudcredits.cloud.credits.controller;

import com.cloudcredits.cloud.credits.model.Task;
import com.cloudcredits.cloud.credits.model.Users;
import com.cloudcredits.cloud.credits.repo.TaskRepository;
import com.cloudcredits.cloud.credits.repo.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/create")
    public String create(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        List<Users> users = userRepository.findAll();
        if (userId != null) {
            model.addAttribute("id", userId);
            model.addAttribute("users", users);
        }
        return "createTask";
    }


    @PostMapping("/createTask")
    public String createTask(@RequestParam Long userId, @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam LocalDate dueDate,

                             @RequestParam int priority,
                             @RequestParam Long assignedToUser, HttpSession session
    ) {

        Users user = userRepository.findById(assignedToUser).orElseThrow(() -> new RuntimeException("User not found"));
        Long id = (Long) session.getAttribute("id");
        Users users = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Task task1 = new Task();
        task1.setTitle(title);
        task1.setDescription(description);
        task1.setDueDate(dueDate);
        task1.setPriority(priority);
        task1.setStatus("pending");
        task1.setUser(user);
        task1.setAdminName(users.getUsername());

        taskRepository.save(task1);

        return "redirect:/task";
    }

    @GetMapping("/task")
    public String listTasks(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        Users users = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        List<Task> tasks = taskRepository.findByUser(users);
        model.addAttribute("users", userRepository.findByUsername(users.getUsername()));
        model.addAttribute("alltask", taskRepository.findAll());

        model.addAttribute("tasks", tasks);
        return "task-list"; // Thymeleaf template for task list
    }

    @PostMapping("/update-task/{id}")
    public String updateTask(@PathVariable Long id,

                             @ModelAttribute Task task2) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(task2.getTitle());
        existingTask.setDescription(task2.getDescription());
        existingTask.setPriority(task2.getPriority());
        existingTask.setDueDate(task2.getDueDate());
        existingTask.setAdminResponse(task2.getAdminResponse());
        existingTask.setUserResponse(task2.getUserResponse());


        taskRepository.save(existingTask);

        return "redirect:/task";


    }

    @GetMapping("/task/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        model.addAttribute("tasks", task);
        return "updateTask";
    }


    @GetMapping("/tasks/assign")
    public String showAssignTaskPage(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "assign-task"; // Thymeleaf template name
    }

    @PostMapping("/tasks/assign")
    public String assignTask(@RequestParam("taskId") Long taskId, @RequestParam("userId") Long userId, HttpSession session) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        Users users = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Long id = (Long) session.getAttribute("id");
        Users users1 = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        System.out.println(task);
        System.out.println(users.getUsername());
        task.setUser(users);
        task.setAdminName(users1.getUsername());
        task.setAssignStatus("assigned");
        taskRepository.save(task);
        return "redirect:/tasks/assign"; // Redirect after submission
    }


    @PostMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable Long id) {


        taskRepository.deleteById(id);
        return "redirect:/task";
    }
}
