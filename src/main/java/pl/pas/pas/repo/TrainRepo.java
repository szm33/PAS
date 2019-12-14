package pl.pas.pas.repo;

import org.springframework.stereotype.Repository;
import pl.pas.pas.model.Firms.InterCity;
import pl.pas.pas.model.Firms.Regio;
import pl.pas.pas.model.Firms.TLK;
import pl.pas.pas.model.Trains.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TrainRepo implements IRepo<Train> {

    private List<Train> trains = new ArrayList<>();

    public TrainRepo() {
        trains.add(new Train(UUID.randomUUID(), "pociag1", 10, new Regio()));
        trains.add(new Train(UUID.randomUUID(), "pociag2", 20, new InterCity()));
        trains.add(new Train(UUID.randomUUID(), "pociag3", 30, new TLK()));

    }

    public void add(Train t) {
        trains.add(t);
        //trains.add(new Train(UUID.randomUUID(),name));
    }

    public Optional<Train> getById(UUID id) {
        return trains.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public List<Train> getAll() {
        return new ArrayList<>(trains);
    }

    public void delete(Train t) {
        trains.remove(t);
    }

    public void update(Train t) {
        Optional<Train> train = getById(t.getId());
        if (train.isPresent()) {
            trains.set(trains.indexOf(train), t);
        }
    }
}
