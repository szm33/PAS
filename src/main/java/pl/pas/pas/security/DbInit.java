package pl.pas.pas.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pas.pas.model.Firms.InterCity;
import pl.pas.pas.model.Firms.Regio;
import pl.pas.pas.model.Firms.TLK;
import pl.pas.pas.model.Tickets.Ticket;
import pl.pas.pas.model.Trains.ExpressTrain;
import pl.pas.pas.model.Trains.PassengerTrain;
import pl.pas.pas.model.Users.Admin;
import pl.pas.pas.model.Users.Client;
import pl.pas.pas.model.Users.ResourcesManager;
import pl.pas.pas.repo.TicketRepo;
import pl.pas.pas.repo.TrainRepo;
import pl.pas.pas.repo.UserRepo;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private TrainRepo trainRepo;
    private TicketRepo ticketRepo;

    public DbInit(UserRepo userRepo, PasswordEncoder passwordEncoder,TrainRepo trainRepo,TicketRepo ticketRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.ticketRepo = ticketRepo;
        this.trainRepo = trainRepo;
    }

    @Override
    public void run(String... args) {

        userRepo.add(new Admin("Franek", UUID.randomUUID(), true,"admin", passwordEncoder.encode("admin")));
        userRepo.add(new Client("Kamil", UUID.randomUUID(), true,"client", passwordEncoder.encode("client")));
        userRepo.add(new Client("Kamil", UUID.randomUUID(), true,"client1", passwordEncoder.encode("client")));
        userRepo.add(new ResourcesManager("Maciek", UUID.randomUUID(), true,"manager",passwordEncoder.encode("manager")));


        trainRepo.add(new ExpressTrain(UUID.randomUUID(), "pociag1", 10, new Regio(),5));
        trainRepo.add(new PassengerTrain(UUID.randomUUID(), "pociag2", 20, new InterCity()));
        trainRepo.add(new ExpressTrain(UUID.randomUUID(), "pociag3", 30, new TLK(),2));
        trainRepo.add(new PassengerTrain(UUID.randomUUID(), "pociag4", 30, new TLK()));

        for (int i = 0; i < 3; i++) {
//            ticketRepo.add(new Ticket(UUID.randomUUID(),userRepo.getAll().get(i),trainRepo.getAll().get(i), LocalDate.of(1111,1,1),null));
//            ticketRepo.getAll().get(i).getTrain().setTicket(ticketRepo.getAll().get(i));
//            ticketRepo.getAll().get(i).getUser().getTickets().add(ticketRepo.getAll().get(i));
        }
    }
}