package ua.goit.ProductStore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.goit.ProductStore.model.ErrorMessage;
import ua.goit.ProductStore.model.Manufacturer;
import ua.goit.ProductStore.model.User;
import ua.goit.ProductStore.repository.ManufacturerRepository;
import ua.goit.ProductStore.repository.ProductRepository;
import ua.goit.ProductStore.repository.UserRepository;

import java.util.*;

@Component("userValidator")
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> paramClass) {
        return User.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        User user = (User) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "Enter user email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required", "Enter user first name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required", "Enter user last name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Enter user password");

        User userToFind = userRepository.findByEmail(user.getEmail()).orElse(new User());

        if (Objects.isNull(user.getId()) && Objects.nonNull(userToFind.getId())) {
            errors.rejectValue("email", "email.already.exist", "user with this email already exists");
        }

        if (user.getRoles().isEmpty()) {
            errors.rejectValue("roles", "role.required", "role must be assigned");

        }
    }

    public ErrorMessage validateUserToDelete(UUID id) {
        ErrorMessage errorMessage = new ErrorMessage();
        List<String> errors = new ArrayList<>();
        Set<User> admins = userRepository.findAllwithAdminRole();

        if (admins.size() == 1) {
            errors.add(String.format("User with email %s is the one with Admin role. Impossible to delete last Admin user."
                    , userRepository.findById(id).get().getEmail()));
        }
        errorMessage.setErrors(errors);
        return errorMessage;
    }
}