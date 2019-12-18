package pl.pas.pas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pas.pas.model.Tickets.Ticket;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.service.TicketService;
import pl.pas.pas.service.TrainService;
import pl.pas.pas.service.UserService;

import javax.validation.Valid;

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
        return "Ticket/index";

    }

    @GetMapping("/add")
    public String addSite(Model model){

        model.addAttribute("users",userService.getUsers());
        model.addAttribute("trains",trainService.getTrains());
        model.addAttribute("ticket",new Ticket());
        return "Ticket/create";

    }

    @PostMapping("/add")
    public String addTrain(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "Ticket/create";
        }
        ticketService.addTicket(ticket);
        return "redirect:/tickets";

    }
}
