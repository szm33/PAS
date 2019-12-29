package pl.pas.pas.model.Users;

import java.util.UUID;

public class Admin extends User {
    public Admin(String name, UUID id, boolean isActive, String email, String password) {
        super(name, "Admin", id, isActive, email, password);
    }
}
