package ua.goit.ProductStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.ProductStore.model.Manufacturer;
import ua.goit.ProductStore.exception.ManufacturerNotFoundException;
import ua.goit.ProductStore.repository.ManufacturerRepository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public Manufacturer findById (UUID id) {
        return manufacturerRepository.findById(id).orElseThrow(() -> new ManufacturerNotFoundException(String.format("Manufacturer with id - %s does not exist", id)));
    }

    public Set<Manufacturer> findAll() {
        return StreamSupport.stream(manufacturerRepository.findAll().spliterator(), false)
                            .collect(Collectors.toSet());
    }

    public void save(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    public void delete(UUID id) {
        manufacturerRepository.deleteById(id);
    }
}
