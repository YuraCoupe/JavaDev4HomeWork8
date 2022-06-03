package ua.goit.ProductStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.ProductStore.exception.ProductNotFoundException;
import ua.goit.ProductStore.exception.UserNotFoundException;
import ua.goit.ProductStore.model.Manufacturer;
import ua.goit.ProductStore.model.Product;
import ua.goit.ProductStore.model.User;
import ua.goit.ProductStore.repository.UserRepository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<User> findAll() {
        return userRepository.findAll();
    }

    public User findById (UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id - %s does not exist", id)));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }


}
