package pl.pas.pas.model.Firms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class Firm {
    private UUID firmId;
    @NotBlank(message = "Firm cannot be empty")
    @Pattern(regexp = "(TLK)|(Regio)|(InterCity)", message = "Invalid Name")
    private String name;

    public Firm(String name) {
        this.firmId = UUID.randomUUID();
        this.name = name;
    }

    public Firm(){
       this.firmId = UUID.randomUUID();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getFirmId() {
        return firmId;
    }
}
