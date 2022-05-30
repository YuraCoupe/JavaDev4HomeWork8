package ua.goit.ProductStore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.goit.ProductStore.model.ErrorMessage;
import ua.goit.ProductStore.model.Manufacturer;
import ua.goit.ProductStore.repository.ManufacturerRepository;
import ua.goit.ProductStore.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component("manufacturerValidator")
public class ManufacturerValidator implements Validator {

    private final ManufacturerRepository manufacturerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ManufacturerValidator(ManufacturerRepository manufacturerRepository, ProductRepository productRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public boolean supports(Class<?> paramClass) {
        return Manufacturer.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        Manufacturer manufacturer = (Manufacturer) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");

        Set<Manufacturer> manufacturers = manufacturerRepository.findByName(manufacturer.getName());

        if (!manufacturers.isEmpty()) {
            errors.rejectValue("name", "name.already.exist", "Manufacturer with this name already exists");
        }
    }

    public ErrorMessage validateManufacturerToDelete(UUID id) {
        ErrorMessage errorMessage = new ErrorMessage();
        List<String> errors = new ArrayList<>();
        if (productRepository.findByManufacturerId(id).iterator().hasNext()) {
            errors.add(String.format("Manufacturer %s has products. Delete products before removing the manufacturer"
                    , manufacturerRepository.findById(id).get().getName()));
        }
        errorMessage.setErrors(errors);
        return errorMessage;
    }
}