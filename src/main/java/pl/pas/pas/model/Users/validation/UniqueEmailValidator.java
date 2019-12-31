package pl.pas.pas.model.Users.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.pas.pas.repo.UserRepo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepo userRepository;



    public void initialize(UniqueEmail constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userRepository.getByEmail(email).isPresent();
    }

}
