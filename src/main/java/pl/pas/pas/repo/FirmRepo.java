package pl.pas.pas.repo;

import org.springframework.stereotype.Repository;
import pl.pas.pas.model.Firms.Firm;
import pl.pas.pas.model.Firms.InterCity;
import pl.pas.pas.model.Firms.Regio;
import pl.pas.pas.model.Firms.TLK;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FirmRepo implements IRepo<Firm>{

    public FirmRepo() {
        firms.add(new TLK());
        firms.add(new Regio());
        firms.add(new InterCity());


    }

    private List<Firm> firms = new ArrayList<>();

    public void add(Firm u){
        firms.add(u);
    }

    public Optional<Firm> getById(UUID id){
        return null;
    }
    public List<Firm> getAll(){
        return firms;
    }

    public void delete(UUID id){
        Optional<Firm> u = getById(id);
        u.ifPresent(firm -> firms.remove(firm));

    }

    public void update(UUID id, Firm uupdate){

    }
}
