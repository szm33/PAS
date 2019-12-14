package pl.pas.pas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.repo.IRepo;
import pl.pas.pas.repo.TrainRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainService {

    private IRepo trainRepo;

    @Autowired
    public TrainService(IRepo trainRepo) {
        this.trainRepo = trainRepo;
    }

    public void addTrain(Train t){
        trainRepo.add(t);
    }

    public List<Train> getTrains(){
        return trainRepo.getAll();
    }

    public void updateTrain( Train tupdate){
        trainRepo.update(tupdate);

//        Optional<Train> t = trainRepo.getById(id);
//        if (t.isPresent()){
//            if(tupdate.getName() !="")
//                t.get().setName(tupdate.getName());
//            if(tupdate.getSeats() != null && tupdate.getSeats().size() != 0){
//                t.get().setSeats(tupdate.getSeats());
//            }
//            if(tupdate.getFirm() != null)
//                t.get().setFirm(tupdate.getFirm());
//            trainRepo.update(id,t.get());
//        }
    }
    public void delete(UUID id){
        //ustawienie nulla dla alokacji
        trainRepo.delete(id);
    }


}
