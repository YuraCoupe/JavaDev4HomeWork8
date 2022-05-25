package ua.goit.ProductStore.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Manufacturer manufacturer;

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "id", columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "name")
    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name="manufacturer_id", nullable=false)
    @Valid
    @NotNull
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
