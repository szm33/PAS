package pl.pas.pas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.service.TrainService;

import java.util.ArrayList;
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
//
    @GetMapping("")
    public List<Train> getAll(){
        return trainService.getTrains();
    }

    @GetMapping(path = "{id}")
    public Train get(@PathVariable UUID id){
        return trainService.getTrain(id);
    }

    @PostMapping("")
    public ResponseEntity post(@RequestBody Train train){
        trainService.addTrain(train);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping("")
    public ResponseEntity put(@RequestBody Train train){
        trainService.updateTrain(train);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity del(@PathVariable UUID id){
        trainService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
