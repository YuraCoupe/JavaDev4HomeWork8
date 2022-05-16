package ua.goit.ProductStore.model;

import java.util.Set;
import java.util.UUID;

public class Role {
    private UUID id;
    private String name;
    private Set<User> users;
}
