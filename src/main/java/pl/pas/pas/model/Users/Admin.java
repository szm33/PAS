package pl.pas.pas.model.Users;

import java.util.UUID;

public class Admin extends User {
    public Admin(String name, UUID id, boolean isActive) {
        super(name, "Admin", id, isActive);
    }
}
