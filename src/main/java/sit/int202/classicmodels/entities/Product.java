package sit.int202.classicmodels.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQueries({
        @NamedQuery(name = "Product.FindAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.count", query = "SELECT count(p) as count FROM Product p")
})
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    private String productCode;
    private String productName;
    private String productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer quantityInStock;
}