package ua.goit.ProductStore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.goit.ProductStore.model.Manufacturer;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ManufacturerRepository extends CrudRepository<Manufacturer, UUID> {
    Set<Manufacturer> findByName(String name);

    @Query("FROM Manufacturer m LEFT JOIN FETCH m.products")
    Iterable<Manufacturer> findAll();

    @Query("FROM Manufacturer m LEFT JOIN FETCH m.products WHERE m.id = (:id)")
    Optional<Manufacturer> findById(@Param("id") UUID id);

}
