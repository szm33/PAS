package pl.pas.pas.repo;

import org.springframework.stereotype.Repository;
import pl.pas.pas.model.Trains.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TrainRepo {

    public TrainRepo() {
        trains.add(new Train(UUID.randomUUID(),"pociag"));
        trains.add(new Train(UUID.randomUUID(),"pociag2"));
        trains.add(new Train(UUID.randomUUID(),"pociag3"));
        trains.add(new Train(UUID.randomUUID(),"button"));
    }

    private List<Train> trains = new ArrayList<>();

    public void add(Train t){
        trains.add(t);
        //trains.add(new Train(UUID.randomUUID(),name));
    }

    public Optional<Train> getTrainById(UUID id){
        return trains.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    public List<Train> getAll(){
        return trains;
    }

    public void delete(UUID id){
        Optional<Train> t = getTrainById(id);
        t.ifPresent(train -> trains.remove(train));

    }
//czy moze inaczej
    public void update(UUID id, Train tupdate){
        Optional<Train> t = getTrainById(id);
        if (t.isPresent()){
            trains.set(trains.indexOf(t),tupdate);
        }
    }
}
