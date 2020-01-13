package pl.pas.pas.model.Trains;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.pas.pas.model.Firms.Firm;

import java.util.UUID;

public class PassengerTrain extends Train{

    public PassengerTrain(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm") Firm firm) {
        super( name, seats, firm);
    }

    public PassengerTrain(){}
}
