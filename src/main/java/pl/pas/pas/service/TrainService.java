package pl.pas.pas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.repo.TrainRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainService {

    private TrainRepo trainRepo;

    @Autowired
    public TrainService(TrainRepo trainRepo) {
        this.trainRepo = trainRepo;
    }

    public void addTrain(Train t){
        trainRepo.add(t);
    }

    public List<Train> getTrains(){
        return trainRepo.getAll();
    }

    public void updateTrain(UUID id, Train tupdate){
        Optional<Train> t = trainRepo.getTrainById(id);
        if (t.isPresent()){
            t.get().setName(tupdate.getName());
            trainRepo.update(id,t.get());
        }
    }
    public void delete(UUID id){
        trainRepo.delete(id);
    }


}
