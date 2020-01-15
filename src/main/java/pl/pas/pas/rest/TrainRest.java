package pl.pas.pas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pas.pas.model.Trains.ExpressTrain;
import pl.pas.pas.model.Trains.PassengerTrain;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.service.TrainService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trains")
public class TrainRest {

    private final TrainService trainService;

    @Autowired
    public TrainRest(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    public List<Train> getAll(){
        return trainService.getTrains();
    }

    @GetMapping(path = "{id}")
    public Train get(@PathVariable UUID id){
        return trainService.getTrain(id);
    }

    @PostMapping("/express")
    public ResponseEntity postE(@Valid @RequestBody ExpressTrain train, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        trainService.addTrain(train);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/passenger")
    public ResponseEntity postP(@Valid @RequestBody PassengerTrain train, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        trainService.addTrain(train);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping("/express/{id}")
    public ResponseEntity putE(@PathVariable UUID id,@Valid @RequestBody ExpressTrain train,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        train.setTrainId(id);
        trainService.updateTrain(train);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/passenger/{id}")
    public ResponseEntity putP(@PathVariable UUID id,@Valid @RequestBody PassengerTrain train,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        train.setTrainId(id);
        trainService.updateTrain(train);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity del(@PathVariable UUID id){
        trainService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/sort/{text}")
    public List<Train> sort(@PathVariable String text){
        return trainService.sort(text);
    }
}
