package pl.pas.pas.model.Users;

import java.util.UUID;

public class User {

    private String name;
    private String type;
    private UUID id;
    private boolean isActive;

    public User(String name, String type, UUID id, boolean isActive) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.isActive = isActive;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }



}
