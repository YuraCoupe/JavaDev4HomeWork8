package ua.goit.ProductStore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.goit.ProductStore.model.User;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("FROM User u LEFT JOIN FETCH u.roles")
    Set<User> findAll();

    @Query("FROM User u LEFT JOIN FETCH u.roles WHERE u.id = (:id)")
    Optional<User> findById(@Param("id") UUID id);

    @Query("FROM User u LEFT JOIN FETCH u.roles r WHERE r.name = 'ROLE_ADMIN'")
    Set<User> findUsersWithAdministratorRole();

    @Query("FROM User u LEFT JOIN FETCH u.roles r WHERE r.name = 'ROLE_USER'")
    Set<User> findUsersWithUserRole();

}
