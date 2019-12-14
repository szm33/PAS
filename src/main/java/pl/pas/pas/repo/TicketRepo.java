package pl.pas.pas.repo;

import org.springframework.stereotype.Repository;
import pl.pas.pas.model.Tickets.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TicketRepo implements IRepo<Ticket> {

    private List<Ticket> tickets = new ArrayList<>();

    public TicketRepo() {
    }

    public void add(Ticket t) {
        tickets.add(t);
    }

    public Optional<Ticket> getById(UUID id) {
        return tickets.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public List<Ticket> getAll() {
        return tickets;
    }

    public void delete(Ticket t) {
        tickets.remove(t);
    }

    public void update(Ticket t) {
        Optional<Ticket> ticket = getById(t.getId());
        if (ticket.isPresent())
        {
            tickets.set(tickets.indexOf(ticket), t);
        }
    }
}
