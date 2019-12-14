package pl.pas.pas.model.Firms;

import java.util.UUID;

public class Firm {
    private UUID id;
    private String name;

    public Firm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }
}
