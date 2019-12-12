package pl.pas.pas.model.Trains;

import pl.pas.pas.model.Firms.Firm;

import java.util.UUID;

public class PassengerTrain extends Train{

    public PassengerTrain(UUID id, String name, int numberOfSeats, Firm firm) {
        super(id, name, numberOfSeats, firm);
    }
}
