package pl.pas.pas.repo;

import org.springframework.stereotype.Repository;
import pl.pas.pas.model.Firms.InterCity;
import pl.pas.pas.model.Firms.Regio;
import pl.pas.pas.model.Firms.TLK;
import pl.pas.pas.model.Trains.ExpressTrain;
import pl.pas.pas.model.Trains.PassengerTrain;
import pl.pas.pas.model.Trains.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TrainRepo implements IRepo<Train> {

    private List<Train> trains = new ArrayList<>();

    public TrainRepo() {
//        trains.add(new ExpressTrain(UUID.randomUUID(), "pociag1", 10, new Regio(),5));
//        trains.add(new PassengerTrain(UUID.randomUUID(), "pociag2", 20, new InterCity()));
//        trains.add(new ExpressTrain(UUID.randomUUID(), "pociag3", 30, new TLK(),2));
//        trains.add(new PassengerTrain(UUID.randomUUID(), "pociag4", 30, new TLK()));

    }

    public void add(Train t) {
        if(!getById(t.getTrainId()).isPresent()) {
            synchronized (this) {
                trains.add(t);
            }
        }
        //trains.add(new Train(UUID.randomUUID(),name));
    }

    public Optional<Train> getById(UUID id) {
        return trains.stream().filter(user -> user.getTrainId().equals(id)).findFirst();
    }

    public List<Train> getAll() {
        return new ArrayList<>(trains);
    }

    public void delete(Train t) {
       synchronized (this){
           trains.remove(t);
       }
    }

    public void update(Train t) {
        Optional<Train> train = getById(t.getTrainId());
        if (train.isPresent()) {
            synchronized (this) {
                train.get().setSeats(t.getSeats());
                train.get().setFirm(t.getFirm());
                train.get().setName(t.getName());
                train.get().setTicketID(t.getTicketID());
                if (train.get() instanceof ExpressTrain && t instanceof ExpressTrain) {
                    ((ExpressTrain) train.get()).setCarriage(((ExpressTrain) t).getCarriage());
                }
            }
        }
    }

    public List<Train> sort(String text){
        List<Train> sortTrains = new ArrayList<>();
        for (Train t: trains
        ) {
            if (t.getName().length() >= text.length()) {
                if (t.getName().substring(0, text.length()).equals(text)) {
                    sortTrains.add(t);
                }
            }

        }
        return sortTrains;
    }
}

