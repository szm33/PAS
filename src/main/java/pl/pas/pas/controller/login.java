package pl.pas.pas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;



@Controller
@RequestMapping("/")
public class login {

    private RestTemplate restTemplate;

    @Autowired
    public login(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("login")
    public String login(){
        return "User/login";
    }

    @GetMapping("home")
    public String home(){return "Base/home";}



}
