package pl.pas.pas.model.Trains;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;
import pl.pas.pas.model.Firms.Firm;
import pl.pas.pas.model.Tickets.Ticket;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

public class Train {

    private UUID trainId;
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name must have between 2-20 letters")
    private String name;
//    @Size(min = 1,message = "Minimum 1 seat")
//    public List<Seat> seats;
    @Range(min = 1, message = "Invalid Seats number, must be greater than 0")
    private int seats;
    private Firm firm;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    private Ticket ticket;

    public Train( UUID id,  String name, int seats, Firm firm) {
        this.trainId = id;
        this.name = name;
//        this.seats = new ArrayList<>();
//        for (int i = 0; i < numberOfSeats; i++) {
//            seats.add(new Seat(Integer.toString(i+1)));
//        }
        this.seats = seats;
        this.firm = firm;
    }

    public Train(){
        this.trainId = UUID.randomUUID();
//        seats = new ArrayList<>();
    }

    public UUID getTrainId() {
        return trainId;
    }

    public void setTrainId(UUID trainId) {
        this.trainId = trainId;
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
//public String getFirm() {
//    return firm.getName();
//}

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
//public void setFirm(String name) {
//    this.firm.setName(name);
//}

    public int getSeats(){
//        return seats.size();
        return seats;
    }

    public void setSeats(int s){
//        List<Seat> seats1 = new ArrayList<>();
//        for (int i = 0; i < s; i++) {
//            seats1.add(new Seat(Integer.toString(i+1)));
//        }
//        seats = seats1;
        this.seats = s;
    }

//    public List<Seat> getSeats() {
//        return new ArrayList<>(seats);
//    }

//    public Seat getSeatById(UUID trainId) {
//        return seats.
//    }
//
//    public void setSeats(List<Boolean> seats) {
//        this.seats = seats;
//    }


}
