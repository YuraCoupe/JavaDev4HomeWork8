package ua.goit.ProductStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class ProductStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductStoreApplication.class, args);
    }
}
