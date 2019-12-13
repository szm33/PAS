package pl.pas.pas.model.Trains;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.pas.pas.model.Firms.Firm;

import java.util.*;

public class Train {

    private UUID id;
    private String name;

    private List<Boolean> seats;

    private Firm firm;
    public Train(@JsonProperty UUID id, @JsonProperty String name, int numberOfSeats, Firm firm) {
        this.id = id;
        this.name = name;
        this.seats=new ArrayList<>(Arrays.asList(new Boolean[10]));
        Collections.fill(this.seats, Boolean.FALSE);
        this.firm = firm;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public List<Boolean> getSeats() {
        return seats;
    }

    public void setSeats(List<Boolean> seats) {
        this.seats = seats;
    }


}
