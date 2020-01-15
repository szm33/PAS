package pl.pas.pas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.pas.pas.model.Trains.ExpressTrain;
import pl.pas.pas.model.Trains.PassengerTrain;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.model.Trains.TrainType;
import pl.pas.pas.service.FirmService;
import pl.pas.pas.service.TicketService;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/trains")
@Controller
public class TrainController {

    @Autowired
    public TrainController( TicketService ticketService, FirmService firmService, RestTemplate restTemplate) {
       // this.trainService = trainService;
        this.ticketService = ticketService;
        this.firmService = firmService;
        this.restTemplate = restTemplate;
    }

    //private TrainService trainService;
    private TicketService ticketService;
    private FirmService firmService;
    private RestTemplate restTemplate;

    private String restURL = "https://localhost:8443/api/v1/trains/";

    private List<Train> getTrains(){
        return restTemplate.exchange(restURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Train>>() {
                }).getBody();
    }

    private List<Train> sort(String text){
        return restTemplate.exchange(restURL + "sort/" + text,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Train>>() {
                }).getBody();
    }

    private void postPassengerTrain(Train train){
        restTemplate.postForObject(restURL + "passenger",train,PassengerTrain.class);
    }
    private void postExpressTrain(Train train){
        restTemplate.postForObject(restURL + "express",train,ExpressTrain.class);
    }
    private void putExpressTrain(Train train){
        restTemplate.put(restURL + "express/" + train.getTrainId().toString(),train,ExpressTrain.class);
    }
    private void putPassengerTrain(Train train){
        restTemplate.put(restURL + "passenger/" + train.getTrainId().toString(),train,PassengerTrain.class);
    }
    private void deleteTrain(UUID id){
        restTemplate.delete(restURL + id.toString());
    }

    private Train getTrain(UUID id){
        ExpressTrain train= restTemplate.getForObject(restURL + id.toString(), ExpressTrain.class);
        if(train.getCarriage() == 0){
            return restTemplate.getForObject(restURL + id.toString(), PassengerTrain.class);
        }
        return train;
    }



//    @GetMapping
//    public List<Train> getAllTrains(Model model){
//        return trainService.getTrains();
//    }
    @GetMapping
    public String s(Model model){

       // model.addAttribute("trains",trainService.getTrains());
        model.addAttribute("trains", getTrains());
        model.addAttribute("text",new TrainType(""));
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
            model.addAttribute("trainType",new TrainType("express"));
            return "Train/create";
        }
        postExpressTrain(train);
        //trainService.addTrain(train);
        return "redirect:/trains";

    }

    @PostMapping("/add/passenger")
    public String addTrain(@Valid @ModelAttribute("train") PassengerTrain train, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("firms",firmService.getFirms());
            model.addAttribute("trainType",new TrainType("passenger"));
            return "Train/create";
        }
        //trainService.addTrain(train);
        postPassengerTrain(train);
        return "redirect:/trains";

    }


    @GetMapping("/edit/{id}")
    public String editSite(@PathVariable UUID id, Model model){
        //Train t = trainService.getTrain(id);
        Train t = getTrain(id);
        if(t instanceof ExpressTrain){
            model.addAttribute("trainType",new TrainType("express"));
        }
        else if(t instanceof PassengerTrain){
            model.addAttribute("trainType",new TrainType("passenger"));
        }
        model.addAttribute("train",getTrain(id));
        //model.addAttribute("train",trainService.getTrain(id));
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
        putExpressTrain(train);
        //trainService.updateTrain(train);
        return "redirect:/trains";
    }

    @PostMapping("/edit/{id}/passenger")
    public String editPassenger( UUID id, @Valid @ModelAttribute("train") PassengerTrain train, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            train.setTrainId(id);
            model.addAttribute("train",train);
            model.addAttribute("trainType",new TrainType("passenger"));
            model.addAttribute("firms",firmService.getFirms());
            return "Train/edit";
        }
        train.setTrainId(id);
        putPassengerTrain(train);
        //trainService.updateTrain(train);
        return "redirect:/trains";
    }

    @GetMapping("/train/{id}")
    public String info(@PathVariable UUID id, Model model){
        model.addAttribute("train",getTrain(id));
       // model.addAttribute("train",trainService.getTrain(id));
        model.addAttribute("ticket", ticketService.getTicket(getTrain(id).getTicketID()));
        return "Train/info";
    }



    @GetMapping("/delete/{id}")
    public String delTrain(@PathVariable UUID id){
        if(getTrain(id).getTicketID() != null){
            ticketService.getTicket(getTrain(id).getTicketID()).setTrain(null);
            //ticketService.getTicket(trainService.getTrain(id).getTicketID()).setTrain(null);
        }
        deleteTrain(id);
        // trainService.delete(id);
        return "redirect:/trains";
    }

    @GetMapping("sort")
    public String sort(@ModelAttribute("text") TrainType text, Model model){
        if(text.getType() == ""){
            model.addAttribute("trains",getTrains());
        }
        else{
            model.addAttribute("trains",sort(text.getType()));
           // model.addAttribute("trains",trainService.sort(text.getType()));
        }
        model.addAttribute("text",text);
        return "Train/index";
    }

}
