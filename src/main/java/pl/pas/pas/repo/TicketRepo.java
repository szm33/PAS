package pl.pas.pas.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.pas.pas.model.Tickets.Ticket;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TicketRepo implements IRepo<Ticket> {

    private List<Ticket> tickets = new ArrayList<>();
    private TrainRepo trainRepo;
    private UserRepo userRepo;

    @Autowired
    public TicketRepo(TrainRepo trainRepo, UserRepo userRepo) {
        this.trainRepo = trainRepo;
        this.userRepo = userRepo;
    }


    @PostConstruct
    private void postConstruct(){
        for (int i = 0; i < 3; i++) {
            tickets.add(new Ticket(UUID.randomUUID(),userRepo.getAll().get(i),trainRepo.getAll().get(i),LocalDate.of(1111,1,1),null));
            tickets.get(i).getTrain().setTicket(tickets.get(i));
            tickets.get(i).getUser().getTickets().add(tickets.get(i));
        }
    }


    public void add(Ticket t) {
        tickets.add(t);
    }

    public Optional<Ticket> getById(UUID id) {
        return tickets.stream().filter(user -> user.getTicketId().equals(id)).findFirst();
    }

    public List<Ticket> getAll() {
        return tickets;
    }

    public void delete(Ticket t) {
        tickets.remove(t);
    }

    public void update(Ticket t) {
        Optional<Ticket> ticket = getById(t.getTicketId());
        if (ticket.isPresent())
        {
            tickets.set(tickets.indexOf(ticket), t);
        }
    }
}
