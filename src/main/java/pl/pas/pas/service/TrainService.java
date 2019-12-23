package pl.pas.pas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pas.pas.model.Firms.InterCity;
import pl.pas.pas.model.Firms.Regio;
import pl.pas.pas.model.Firms.TLK;
import pl.pas.pas.model.Trains.ExpressTrain;
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

    public Train getTrain(UUID id){
        Optional<Train> t = trainRepo.getById(id);
        if (t.isPresent()){
            return t.get();
        }
        return new Train();
    }

    public void updateTrain( Train tupdate){
        switch (tupdate.getFirm().getName()){
            case "TLK":
                tupdate.setFirm(new TLK());
                break;
            case "InterCity":
                tupdate.setFirm(new InterCity());
                break;
            case "Regio":
                tupdate.setFirm(new Regio());
                break;
        }
        Train t = getTrain(tupdate.getTrainId());
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
        Optional t = trainRepo.getById(id);
        if(t.isPresent()){
            trainRepo.delete(t.get());
        }

    }


}
