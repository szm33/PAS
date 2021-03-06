package pl.pas.pas.repo;

import org.springframework.stereotype.Repository;
import pl.pas.pas.model.Users.Client;
import pl.pas.pas.model.Users.ResourcesManager;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.model.Users.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepo implements IRepo<User> {

    public UserRepo() {
        users.add(new Admin("Franek", UUID.randomUUID(), true));
        users.add(new Client("Kamil", UUID.randomUUID(), true));
        users.add(new ResourcesManager("Maciek", UUID.randomUUID(), true));
    }

    private List<User> users = new ArrayList<>();

    public void add(User u) {
        synchronized (this) {
            users.add(u);
        }
    }

    public Optional<User> getById(UUID id) {
        return users.stream().filter(user -> user.getUserId().equals(id)).findFirst();
    }

    public List<User> getAll() {
        return users;
    }

    public void delete(User u) {
        synchronized (this) {
            users.remove(u);
        }
    }

    public void update(User u) {
        Optional<User> user = getById(u.getUserId());
        if (user.isPresent()) {
//            users.set(users.indexOf(user), u);
            synchronized (this) {
                user.get().setName(u.getName());
            }
        }
    }

    public List<User> sort(String text){
        List<User> sortUsers = new ArrayList<>();
        for (User u: users
        ) {
            if (u.getName().length() >= text.length()) {
                if (u.getName().substring(0, text.length()).equals(text)) {
                    sortUsers.add(u);
                }
            }

        }
        return sortUsers;
    }
}
