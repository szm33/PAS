package pl.pas.pas.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pas.pas.model.Users.User;
import pl.pas.pas.repo.UserRepo;

import java.util.Optional;

@Service
public class UserMappingService implements UserDetailsService {

    public UserMappingService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepo.getByEmail(s);
        UserMapping userMapping = null;
        if(user.isPresent()){
            userMapping = new UserMapping(user.get());
        }

        return userMapping;
    }
}
