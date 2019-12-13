package pl.pas.pas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pas.pas.model.Users.Admin;
import pl.pas.pas.model.Users.Client;
import pl.pas.pas.model.Users.ResourcesManager;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.repo.IRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private IRepo userRepo;

    @Autowired
    public UserService(IRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(User u){
        switch(u.getType()){
            case "Admin":
                userRepo.add(new Admin(u.getName(),u.getId(),u.getIsActive()));
                break;
            case "ResourcesManager":
                userRepo.add(new ResourcesManager(u.getName(),u.getId(),u.getIsActive()));
                break;
            case "Client":
                userRepo.add(new Client(u.getName(),u.getId(),u.getIsActive()));
                break;
        }
    }

    public List<User> getUsers(){
        return userRepo.getAll();
    }

    public void changeState(UUID id){
        Optional<User> u = userRepo.getById(id);
        if(u.isPresent()){
            u.get().changeState();
        }
    }

    public void updateUser(UUID id, User uupdate){
        Optional<User> u = userRepo.getById(id);
        if (u.isPresent()){
            if(uupdate.getName() !="") {
                u.get().setName(uupdate.getName());
            }
            if(uupdate.getType() != "" ){
                u.get().setType(uupdate.getType());
            }
            u.get().setIsActive(uupdate.getIsActive());
            userRepo.update(id,u.get());
        }
    }
//    public void delete(UUID id){
//        //ustawienie nulla dla alokacji
//        userRepo.delete(id);
//    }
}