package ua.goit.ProductStore.modell.exceprion;

public class ManufacturerNotFoundException extends RuntimeException {

    public ManufacturerNotFoundException(String message) {
        super(message);
    }

    public ManufacturerNotFoundException() {
        super();
    }

}