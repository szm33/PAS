package pl.pas.pas.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class login {

    @GetMapping("login")
    public String login(){
        return "User/login";
    }

    @GetMapping("home")
    public String home(){return "Base/home";}

}
