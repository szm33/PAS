package pl.pas.pas.model.Users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

public class User {

    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "[A-z]*", message = "Invalid Name")
    @Size(min = 2, max = 20, message = "Name must have between 2-20 letters")
    private String name;

    private String type;

    private UUID userId;
    private boolean isActive;

    public User(String name, String type, UUID idu, boolean isActive) {
        this.name = name;
        this.type = type;
        this.userId = idu;
        this.isActive = isActive;
    }

    public User(){
        this.isActive=true;
        this.userId = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public void changeState(){
        this.isActive = !this.isActive;
    }



}
