package pl.pas.pas.repo;

import org.springframework.stereotype.Repository;
import pl.pas.pas.model.Tickets.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TicketRepo implements IRepo<Ticket>{

        public TicketRepo() {
        }

        private List<Ticket> tickets = new ArrayList<>();

        public void add(Ticket t){
            tickets.add(t);
        }

        public Optional<Ticket> getById(UUID id){
            return tickets.stream()
                    .filter(user -> user.getId().equals(id))
                    .findFirst();
        }
        public List<Ticket> getAll(){
            return tickets;
        }

        public void delete(UUID id){
            Optional<Ticket> t = getById(id);
            t.ifPresent(ticket -> tickets.remove(ticket));

        }

        public void update(UUID id, Ticket tupdate){
            Optional<Ticket> t = getById(id);
            if (t.isPresent()){
                tickets.set(tickets.indexOf(t),tupdate);
            }
        }
}
