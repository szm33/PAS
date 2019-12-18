package pl.pas.pas.model.Tickets;

import pl.pas.pas.model.Trains.*;
import pl.pas.pas.model.Users.*;

import java.time.LocalDate;
import java.util.UUID;

public class Ticket {

    private UUID id;
    private User user;
    private Train train;
    private LocalDate startingDate;
    private LocalDate endingDate;

    public Ticket(UUID id, User user, Train train, LocalDate startingDate, LocalDate endingDate) {
        this.id = id;
        this.user = user;
        this.train = train;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Ticket(){
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }
}
