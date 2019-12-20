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
    private UserService userService;
    private TrainService trainService;

    @Autowired
    public TicketService(IRepo ticketRepo, TrainService trainService, UserService userService) {
        this.ticketRepo = ticketRepo;
        this.userService = userService;
        this.trainService = trainService;
    }

    public void addTicket(Ticket t){
        t.setUser(userService.getUser(t.getUser().getUserId()));
        t.setTrain(trainService.getTrain(t.getTrain().getTrainId()));
        ticketRepo.add(t);
    }

    public void deleteTicket(UUID id){
        ticketRepo.delete(id);
    }

    public List<Ticket> getTickets(){
        return ticketRepo.getAll();
    }
}
