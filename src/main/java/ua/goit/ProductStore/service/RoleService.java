package ua.goit.ProductStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.ProductStore.exception.RoleNotFoundException;
import ua.goit.ProductStore.exception.UserNotFoundException;
import ua.goit.ProductStore.model.Role;
import ua.goit.ProductStore.model.User;
import ua.goit.ProductStore.repository.RoleRepository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> findAll() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    public Role findById (UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(String.format("User with id - %s does not exist", id)));
    }

    public Role getUserRole() {
        return roleRepository.getUserRole();
    }
}
