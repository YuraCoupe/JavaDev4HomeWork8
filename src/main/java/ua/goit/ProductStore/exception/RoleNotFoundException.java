package ua.goit.ProductStore.exception;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException() {
        super();
    }

}