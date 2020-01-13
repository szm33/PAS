package pl.pas.pas.model.Trains;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;
import pl.pas.pas.model.Firms.Firm;

import java.util.UUID;

public class ExpressTrain extends Train {

    public int getCarriage() {
        return carriage;
    }

    public void setCarriage(int carriage) {
        this.carriage = carriage;
    }

    @Range(min = 1, message = "Invalid Carriage number, must be greater than 0")
    private int carriage;

    public ExpressTrain(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm") Firm firm, @JsonProperty("carriage") int carriage) {
        super( name, seats, firm);
        this.carriage = carriage;
    }

    public ExpressTrain(){}
}
