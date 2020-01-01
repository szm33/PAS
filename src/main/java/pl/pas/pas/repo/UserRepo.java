package pl.pas.pas.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.pas.pas.model.Users.Client;
import pl.pas.pas.model.Users.ResourcesManager;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.model.Users.Admin;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepo implements IRepo<User> {

//    @Autowired
//    public UserRepo(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//
//    }

//    @PostConstruct
//    private void postConstruct(){
//        users.add(new Admin("Franek", UUID.randomUUID(), true,"admin", passwordEncoder.encode("admin")));
//        users.add(new Client("Kamil", UUID.randomUUID(), true,"client", passwordEncoder.encode("client")));
//        users.add(new ResourcesManager("Maciek", UUID.randomUUID(), true,"manager",passwordEncoder.encode("manager")));
//    }

    private List<User> users = new ArrayList<>();
//    private PasswordEncoder passwordEncoder;

    public void add(User u) {
        synchronized (this) {
            users.add(u);
        }
    }

    public Optional<User> getById(UUID id) {
        return users.stream().filter(user -> user.getUserId().equals(id)).findFirst();
    }

    public Optional<User> getByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
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
                user.get().setEmail(u.getEmail());
                user.get().setPassword(u.getPassword());
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
