package ua.goit.ProductStore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.ProductStore.model.User;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
}
