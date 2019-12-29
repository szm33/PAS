package pl.pas.pas.model.Users;

import java.util.UUID;

public class ResourcesManager extends User {
    public ResourcesManager(String name, UUID id, boolean isActive, String email, String password) {
        super(name, "ResourcesManager", id, isActive, email, password);
    }
}
