package pl.pas.pas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.pas.pas.model.Tickets.Ticket;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.service.TicketService;
import pl.pas.pas.service.TrainService;
import pl.pas.pas.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
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
        return "Ticket/index";

    }

    @GetMapping("/add")
    public String addSite(Model model){

       // model.addAttribute("users",userService.getUsers());
        model.addAttribute("users",ticketService.getAllActiveUser());
        //model.addAttribute("trains",trainService.getTrains());
        model.addAttribute("trains",ticketService.getAllNoAllocatedTrains());
        model.addAttribute("ticket",new Ticket());
        model.addAttribute("user",new User());
        model.addAttribute("train",new Train());
        return "Ticket/create";

    }

    @PostMapping("/add")
    public String addTrain(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, @Valid @ModelAttribute("train") Train train, BindingResult bindingResult1,@Valid @ModelAttribute("user") User user, BindingResult bindingResult2, Model model){
        if(bindingResult.hasErrors()){
            //model.addAttribute("users",userService.getUsers());
            model.addAttribute("users",ticketService.getAllActiveUser());
            //model.addAttribute("trains",trainService.getTrains());
            model.addAttribute("trains",ticketService.getAllNoAllocatedTrains());
            return "Ticket/create";
            //return "redirect:/tickets/add";
        }
        ticket.setTrain(train);
        ticket.setUser(user);
        ticketService.addTicket(ticket);
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
}
