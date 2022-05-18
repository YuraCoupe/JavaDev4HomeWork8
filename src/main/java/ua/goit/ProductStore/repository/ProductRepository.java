package ua.goit.ProductStore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.ProductStore.model.Product;

import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, UUID> {
}
