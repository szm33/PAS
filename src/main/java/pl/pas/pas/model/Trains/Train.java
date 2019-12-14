package pl.pas.pas.model.Trains;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.pas.pas.model.Firms.Firm;
import pl.pas.pas.model.seats.Seat;

import java.util.*;

public class Train {

    private UUID id;
    private String name;
    private List<Seat> seats;
    private Firm firm;

    public Train(@JsonProperty UUID id, @JsonProperty String name, int numberOfSeats, Firm firm) {
        this.id = id;
        this.name = name;
        this.seats = new ArrayList<>();
        for (int i = 0; i < numberOfSeats; i++) {
            seats.add(new Seat(Integer.toString(i+1)));
        }
        this.firm = firm;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }

//    public Seat getSeatById(UUID id) {
//        return seats.
//    }
//
//    public void setSeats(List<Boolean> seats) {
//        this.seats = seats;
//    }


}
