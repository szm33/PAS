package pl.pas.pas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.pas.pas.model.Tickets.Ticket;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.model.Trains.TrainType;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.service.TicketService;
import pl.pas.pas.service.TrainService;
import pl.pas.pas.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/tickets")
@Controller
public class TicketController {

    private TicketService ticketService;
    private TrainService trainService;
    private UserService userService;

    @Autowired
    public TicketController(TicketService ticketService, TrainService trainService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.trainService = trainService;
    }

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("tickets",ticketService.getTickets());
        model.addAttribute("textUser",new TrainType(""));
        model.addAttribute("textTrain",new TrainType(""));
        return "Ticket/index";

    }

    @GetMapping("/add")
    public String addSite(Model model, Authentication authentication){

        if(authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
            List<User> users = new ArrayList<>();
            users.add(ticketService.getUserByEmail(authentication.getName()));
            model.addAttribute("users", users);
        }
        else{
            model.addAttribute("users",ticketService.getAllActiveUser());
        }
        model.addAttribute("trains",ticketService.getAllNoAllocatedTrains());
        model.addAttribute("ticket",new Ticket());
        model.addAttribute("user",new User());
        model.addAttribute("train",new Train());
        return "Ticket/create";

    }

    @PostMapping("/add")
    public String addTrain(Authentication authentication,@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, @Valid @ModelAttribute("train") Train train, BindingResult bindingResult1,@Valid @ModelAttribute("user") User user, BindingResult bindingResult2, Model model){
        if(bindingResult.hasErrors()){

            if(authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
                List<User> users = new ArrayList<>();
                users.add(ticketService.getUserByEmail(authentication.getName()));
                model.addAttribute("users", users);
            }
            else{
                model.addAttribute("users",ticketService.getAllActiveUser());
            }
            model.addAttribute("trains",ticketService.getAllNoAllocatedTrains());
            return "Ticket/create";
        }
        ticket.setTrain(train);
        ticket.setUser(user);
        ticketService.addTicket(ticket);
        if(authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
            return "redirect:/home";
        }
        return "redirect:/tickets";

    }
    @GetMapping("/end/{id}")
    public String end(@PathVariable UUID id){
        ticketService.endTicket(id);
        return "redirect:/tickets";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable UUID id){
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }

    @GetMapping("sortUser")
    public String sortUser(@ModelAttribute("textUser") TrainType text, Model model){
        if(text.getType() == ""){
            model.addAttribute("tickets",ticketService.getTickets());
        }
        else{
            model.addAttribute("tickets",ticketService.sortUsers(text.getType()));
        }
        model.addAttribute("textUser",text);
        model.addAttribute("textTrain",new TrainType(""));
        return "Ticket/index";
    }

    @GetMapping("sortTrain")
    public String sortTrain(@ModelAttribute("textTrain") TrainType text, Model model){
        if(text.getType() == ""){
            model.addAttribute("tickets",ticketService.getTickets());
        }
        else{
            model.addAttribute("tickets",ticketService.sortTrains(text.getType()));
        }
        model.addAttribute("textTrain",text);
        model.addAttribute("textUser",new TrainType(""));
        return "Ticket/index";
    }


}
