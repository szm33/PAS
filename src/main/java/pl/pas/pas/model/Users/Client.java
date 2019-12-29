package pl.pas.pas.model.Users;

import java.util.UUID;

public class Client extends User {
    public Client(String name, UUID id, boolean isActive, String email, String password) {
        super(name, "Client", id, isActive, email, password);
    }
}
