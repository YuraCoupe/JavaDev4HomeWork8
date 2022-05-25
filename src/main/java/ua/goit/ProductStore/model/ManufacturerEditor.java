package ua.goit.ProductStore.model;

import ua.goit.ProductStore.service.ManufacturerService;
import ua.goit.ProductStore.service.ProductService;

import java.beans.PropertyEditorSupport;
import java.util.UUID;

public class ManufacturerEditor extends PropertyEditorSupport {
    private ManufacturerService manufacturerService;

    public ManufacturerEditor(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text.equals("")) {
            this.setValue(null);
        } else {
            Manufacturer manufacturer = manufacturerService.findById(UUID.fromString(text));
            this.setValue(manufacturer);
        }
    }

    @Override
    public String getAsText() {
        Manufacturer parent = new Manufacturer();
        if (this.getValue() != null) {
            parent = (Manufacturer) this.getValue();
            return parent.getId().toString();
        }
        return "";
    }
}
