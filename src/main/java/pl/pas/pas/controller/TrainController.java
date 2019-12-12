package pl.pas.pas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.service.TrainService;

import java.util.UUID;

@RequestMapping("/train")
@Controller
public class TrainController {

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    private TrainService trainService;

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
       // model.addAttribute("train",trainService.());
        //model.addAttribute("test","test1");
        return "index";
    }

    @DeleteMapping("del/{id}")
    public void deleteTrain(@PathVariable UUID id){
        trainService.delete(id);
    }

    @PutMapping("update/{id}")
    public void updateTrain(@PathVariable UUID id, @RequestBody Train t){
        trainService.updateTrain(id,t);
    }

}
