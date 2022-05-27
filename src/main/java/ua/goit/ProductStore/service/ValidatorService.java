package ua.goit.ProductStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.ProductStore.model.ErrorMessage;
import ua.goit.ProductStore.model.Manufacturer;
import ua.goit.ProductStore.repository.ManufacturerRepository;
import ua.goit.ProductStore.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ValidatorService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ValidatorService(ProductRepository productRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public ErrorMessage validateManufacturerToDelete(UUID id) {
        ErrorMessage errorMessage = new ErrorMessage();
        List<String> errors = new ArrayList<>();
        Manufacturer manufacturer = manufacturerRepository.findById(id).get();
        if (productRepository.findByManufacturerId(id).iterator().hasNext()) {
            errors.add(String.format("Manufacturer %s has products. Delete products before removing the manufacturer", manufacturer.getName()));
        }
        errorMessage.setErrors(errors);
        return errorMessage;
    }
}




