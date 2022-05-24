package ua.goit.ProductStore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.goit.ProductStore.model.Product;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, UUID> {
    @Query("FROM Product p LEFT JOIN FETCH p.manufacturer")
    Iterable<Product> findAll();

    @Query("FROM Product p LEFT JOIN FETCH p.manufacturer WHERE p.id = (:id)")
    Optional<Product> findById(@Param("id") UUID id);
}
