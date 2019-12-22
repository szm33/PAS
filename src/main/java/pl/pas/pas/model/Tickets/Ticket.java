package pl.pas.pas.model.Tickets;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import pl.pas.pas.model.Trains.*;
import pl.pas.pas.model.Users.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;




public class Ticket {

    private UUID ticketId;
    private User user;
    private Train train;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@Future(message = "Date must be in the future")
    @NotNull(message = "Date cant be empty")
    private LocalDate startingDate;

    private LocalDate endingDate;

    public Ticket(UUID id, User user, Train train, LocalDate startingDate, LocalDate endingDate) {
        this.ticketId = id;
        this.user = user;
        this.train = train;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Ticket(){
        this.ticketId = UUID.randomUUID();
        //this.user = new User();
        //this.train = new Train();
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
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


    @Component
    public class LocalDateFutureValidator implements ConstraintValidator<Future, LocalDate> {

        @Override
        public void initialize(Future future) {
        }

        @Override
        public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
            LocalDate today = LocalDate.now();
            return localDate.isEqual(today) || localDate.isAfter(today);
        }
    }
}
