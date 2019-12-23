package pl.pas.pas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pas.pas.model.Firms.Firm;
import pl.pas.pas.model.Firms.InterCity;
import pl.pas.pas.model.Firms.Regio;
import pl.pas.pas.model.Firms.TLK;
import pl.pas.pas.model.Trains.ExpressTrain;
import pl.pas.pas.model.Trains.PassengerTrain;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.model.Trains.TrainType;
import pl.pas.pas.model.seats.Seat;
import pl.pas.pas.repo.FirmRepo;
import pl.pas.pas.service.FirmService;
import pl.pas.pas.service.TrainService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/trains")
@Controller
public class TrainController {

    @Autowired
    public TrainController(TrainService trainService,FirmService firmService) {
        this.trainService = trainService;
        this.firmService = firmService;
    }

    private TrainService trainService;
    private FirmService firmService;

    @PostMapping
    public void addTrain(Train t){
        trainService.addTrain(t);
    }

//    @GetMapping
//    public List<Train> getAllTrains(Model model){
//        return trainService.getTrains();
//    }
    @GetMapping
    public String s(Model model){
        model.addAttribute("trains",trainService.getTrains());
        return "Train/index";
    }

//    @GetMapping("/add")
//    public String addSite(Model model){
//
//        model.addAttribute("firms",firmService.getFirms());
//        model.addAttribute("train",new ExpressTrain());
//        return "Train/create";
//
//    }
//
//    @PostMapping("/add")
//    public String addTrain(@Valid @ModelAttribute("train") Train train, BindingResult bindingResult, Model model){
//        if(bindingResult.hasErrors()){
//            model.addAttribute("firms",firmService.getFirms());
//            return "Train/create";
//        }
//        trainService.addTrain(train);
//        return "redirect:/trains";
//
//    }

    @GetMapping("/add/express")
    public String addSiteE( Model model){
            model.addAttribute("train",new ExpressTrain());
            model.addAttribute("trainType",new TrainType("express"));
        model.addAttribute("firms",firmService.getFirms());

        return "Train/create";

    }


    @GetMapping("/add/passenger")
    public String addSiteP( Model model){
        model.addAttribute("train",new PassengerTrain());
        model.addAttribute("trainType",new TrainType("passenger"));
        model.addAttribute("firms",firmService.getFirms());

        return "Train/create";

    }

    @PostMapping("/add/express")
    public String addTrain(@Valid @ModelAttribute("train") ExpressTrain train, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("firms",firmService.getFirms());
            //model.addAttribute("train",train);
            return "Train/create";
        }
        trainService.addTrain(train);
        return "redirect:/trains";

    }

    @PostMapping("/add/passenger")
    public String addTrain(@Valid @ModelAttribute("train") PassengerTrain train, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("firms",firmService.getFirms());
            return "Train/create";
        }
        trainService.addTrain(train);
        return "redirect:/trains";

    }


    @GetMapping("/edit/{id}")
    public String editSite(@PathVariable UUID id, Model model){
        Train t = trainService.getTrain(id);
        if(t instanceof ExpressTrain){
            model.addAttribute("trainType",new TrainType("express"));
        }
        else if(t instanceof PassengerTrain){
            model.addAttribute("trainType",new TrainType("passenger"));
        }
        model.addAttribute("train",trainService.getTrain(id));
        model.addAttribute("firms",firmService.getFirms());
        return "Train/edit";
    }
    @PostMapping("/edit/{id}/express")
    public String editExpress(@PathVariable UUID id, @Valid @ModelAttribute("train") ExpressTrain train, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            train.setTrainId(id);
            model.addAttribute("train",train);
            model.addAttribute("trainType",new TrainType("express"));
            model.addAttribute("firms",firmService.getFirms());
            return "Train/edit";
        }
        train.setTrainId(id);
        trainService.updateTrain(train);
        return "redirect:/trains";
    }

    @PostMapping("/edit/{id}/passenger")
    public String editPassenger(@PathVariable UUID id, @Valid @ModelAttribute("train") PassengerTrain train, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            train.setTrainId(id);
            model.addAttribute("train",train);
            model.addAttribute("trainType",new TrainType("passenger"));
            model.addAttribute("firms",firmService.getFirms());
            return "Train/edit";
        }
        train.setTrainId(id);
        trainService.updateTrain(train);
        return "redirect:/trains";
    }

    @GetMapping("/train/{id}")
    public String info(@PathVariable UUID id, Model model){
        model.addAttribute("train",trainService.getTrain(id));
        return "Train/info";
    }

//    @GetMapping("/edit/{id}")
//    public String editSite(@PathVariable UUID id, Model model){
//        model.addAttribute("train",trainService.getTrain(id));
//        model.addAttribute("firms",firmService.getFirms());
//        return "Train/edit";
//    }
//    @PostMapping("/edit/{id}")
//    public String editUser(@PathVariable UUID id, @Valid @ModelAttribute("train") Train train, BindingResult bindingResult, Model model){
//        if(bindingResult.hasErrors()){
//            train.setTrainId(id);
//            model.addAttribute("train",train);
//            model.addAttribute("firms",firmService.getFirms());
//            return "Train/edit";
//        }
//        train.setTrainId(id);
//        trainService.updateTrain(train);
//        return "redirect:/trains";
//    }

    @GetMapping("/delete/{id}")
    public String deleteTrain(@PathVariable UUID id){
        trainService.delete(id);
        return "redirect:/trains";
    }

}
