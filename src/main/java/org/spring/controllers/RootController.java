package org.spring.controllers;

import java.util.concurrent.atomic.AtomicLong;
import org.spring.model.Greeting;
import org.spring.model.Users;
import org.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Student
 */
@Controller
public class RootController {

    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private UsersRepository userRepository;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
    
    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("adduser", new Users());
        return "adduser";
    }
    
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute Users user) {
        user.setId(counter.incrementAndGet());
        userRepository.save(user);
        return "users";
    }
    
    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
        return "result";
    }
}
