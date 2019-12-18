package pl.pas.pas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pas.pas.model.Tickets.Ticket;
import pl.pas.pas.repo.IRepo;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private IRepo ticketRepo;

    @Autowired
    public TicketService(IRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public void addTicket(Ticket t){
        ticketRepo.add(t);
    }

    public void deleteTicket(UUID id){
        ticketRepo.delete(id);
    }

    public List<Ticket> getTickets(){
        return ticketRepo.getAll();
    }
}
