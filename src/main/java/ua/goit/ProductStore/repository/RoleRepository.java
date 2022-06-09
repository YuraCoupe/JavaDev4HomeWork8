package ua.goit.ProductStore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.ProductStore.model.Role;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
    Optional<Role> findByName(String name);

    @Query("FROM Role r WHERE r.name = 'ROLE_ADMIN'")
    Role getAdminRole();

    @Query("FROM Role r WHERE r.name = 'ROLE_USER'")
    Role getUserRole();
}
