package pl.pas.pas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pas.pas.model.Tickets.Ticket;
import pl.pas.pas.model.Trains.Train;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.repo.IRepo;
import pl.pas.pas.repo.TicketRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public void addTicket(Ticket t) throws Exception{
        if(trainService.getTrain(t.getTrain().getTrainId())==null || trainService.getTrain(t.getTrain().getTrainId()).getTicket() != null){
            throw new Exception("train error");
        }
        t.setUser(userService.getUser(t.getUser().getUserId()));
        t.setTrain(trainService.getTrain(t.getTrain().getTrainId()));
        t.getUser().getTickets().add(t);
        t.getTrain().setTicket(t);
        ticketRepo.add(t);
    }

    public void deleteTicket(UUID id){
       Ticket t = getTicket(id);
        if(t.getEndingDate() == null){
            ticketRepo.delete(t);
            t.getTrain().setTicket(null);
            t.getUser().getTickets().remove(t);
        }
    }

    public List<Ticket> getTickets(){
        return ticketRepo.getAll();
    }

    public List<Train> getAllNoAllocatedTrains(){
        List<Train> t = new ArrayList<>();
        for (Train train:trainService.getTrains()
             ) {
//            boolean flag = true;
//            for (Ticket ticket:getTickets()
//                 ) {
//                if(train == ticket.getTrain() && ticket.getEndingDate() == null){
//                    flag = false;
//                }
//            }
//            if(flag){
//                t.add(train);
//            }
            if(train.getTicket() == null){
                t.add(train);
            }

        }
        return t;
    }

    public List<User>getAllActiveUser(){
        List<User> users = new ArrayList<>();
        for (User user :userService.getUsers()
             ) {
            if(user.getIsActive()){
                users.add(user);
            }
        }
        return users;
    }

    public Ticket getTicket(UUID id){
        Optional<Ticket> ticket = ticketRepo.getById(id);
        if(ticket.isPresent()){
            return ticket.get();
        }
        return new Ticket();

    }

    public void endTicket(UUID id){
        Ticket t = getTicket(id);
        if(!t.getStartingDate().isAfter(LocalDate.now())){
            t.setEndingDate( LocalDate.now());
            if(t.getTrain() != null)
            t.getTrain().setTicket(null);
        }
    }

    public List<Ticket>sortUsers(String text){
        return ((TicketRepo)ticketRepo).sortUser(text);
    }

    public List<Ticket>sortTrains(String text){
        return ((TicketRepo)ticketRepo).sortTrain(text);
    }

    public User getUserByEmail(String email){
        return userService.getUser(email);
    }
}
