package ua.goit.ProductStore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.ProductStore.model.Role;

import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
}
