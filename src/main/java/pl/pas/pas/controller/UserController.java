package pl.pas.pas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.service.UserService;

import java.util.UUID;

@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;


    @GetMapping
    public String showAll(Model model){
        model.addAttribute("users",userService.getUsers());
        return "User/index";
    }

    @GetMapping("/state/{id}")
    public String changeState(@PathVariable UUID id, Model model){
        userService.changeState(id);
        return "redirect:/users";

    }
    @GetMapping("/add")
    public String addSite(Model model){
        model.addAttribute("user",new User());
        return "User/create";

    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, Model model){
        userService.addUser(user);
        return "redirect:/users";

    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable UUID id, Model model){
        return "redirect:/users";

    }
}
