package pl.pas.pas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.service.UserService;

import javax.validation.Valid;
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
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "User/create";
        }
        userService.addUser(user);
        return "redirect:/users";

    }

    @GetMapping("/edit/{id}")
    public String editSite(@PathVariable UUID id, Model model){
//        User u = new User();
//        u.setId(id);
//        model.addAttribute("user",u);
        model.addAttribute("user",userService.getUser(id));
        return "User/edit";
    }
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable UUID id, @Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
           // model.addAttribute("user",new User());
            return "User/edit";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }
}
