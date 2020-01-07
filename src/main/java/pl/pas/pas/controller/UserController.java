package pl.pas.pas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.pas.pas.model.Trains.TrainType;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.security.ReCaptchaResponse;
import pl.pas.pas.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping
    public String showAll(Model model){
        model.addAttribute("users",userService.getUsers());
        model.addAttribute("text",new TrainType(""));
        return "User/index";
    }

    @GetMapping("/state/{id}")
    public String changeState(@PathVariable UUID id, Model model){
        userService.changeState(id);
        return "redirect:/users";

    }
    @GetMapping("/add")
    public String addSite(Model model, Principal principal){
        model.addAttribute("user",new User());
        if(principal != null){
            return "User/create";
        }
        return "Base/registration";

    }

    @GetMapping("registration")
    public String  reg(Model model){
        model.addAttribute("user",new User());
        return  "Base/registration";
    }

    @PostMapping("registration")
    public String registration( @Valid @ModelAttribute User user, BindingResult bindingResult, Model model, @RequestParam(name = "g-recaptcha-response") String recaptcha){
        String url="https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LdXkcsUAAAAANpKqy-xdtpBjiWbj5IWYo4J_74o&response=" + recaptcha;
        ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST,null, ReCaptchaResponse.class).getBody();


        if(reCaptchaResponse.isSuccess()) {
            if(bindingResult.hasErrors()) {
                return "Base/registration";
            }
            user.setType("Client");
            user.setIsActive(false);
            userService.addUser(user);
            return "redirect:/home";
        }
        return "Base/registration";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){

//        String url="https://www.google.com/recaptcha/api/siteverify";
//        String params = "?secret=6LdXkcsUAAAAANpKqy-xdtpBjiWbj5IWYo4J_74o&response=" + recaptcha;
//        ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST,null, ReCaptchaResponse.class).getBody();
//
//
//        if(reCaptchaResponse.isSuccess()){
//            if(principal == null){
//                if(bindingResult.hasErrors()) {
//                    return "Base/registration";
//                }
//                user.setType("Client");
//                user.setIsActive(false);
//                userService.addUser(user);
//                return "redirect:/home";
//            }
//            else{
                if(bindingResult.hasErrors()){
                    return "User/create";
                }
                userService.addUser(user);
                return "redirect:/users";
//            }
//        }
//        else{
//            if(principal == null){
//                return "Base/registration";
//            }
//            else{
//                return "User/create";
//            }
//
//        }


    }

    @GetMapping("/edit/{id}")
    public String editSite(@PathVariable UUID id, Model model){
        model.addAttribute("user",userService.getUser(id));
        return "User/edit";
    }
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable UUID id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            user.setUserId(id);
            model.addAttribute("user",user);
            return "User/edit";
        }
        user.setUserId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user/{id}")
    public String info(@PathVariable UUID id, Model model){
        model.addAttribute("user",userService.getUser(id));
        return "User/info";
    }

    @GetMapping("sort")
    public String sort(@ModelAttribute("text") TrainType text, Model model){
        if(text.getType() == ""){
            model.addAttribute("users", userService.getUsers());
        }
        else{
            model.addAttribute("users",userService.sort(text.getType()));
        }
        model.addAttribute("text",text);
        return "User/index";
    }




}
