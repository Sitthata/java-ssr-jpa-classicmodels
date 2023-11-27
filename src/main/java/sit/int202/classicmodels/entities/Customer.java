package sit.int202.classicmodels.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(
                name = "FIND_USER",
                query = "SELECT c FROM Customer c WHERE concat(c.contactFirstName, ' ', c.contactLastName) = :user_account"
        )
})
@Setter
@Getter
public class Customer {
    @Id
    private Integer customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Integer salesRepEmployeeNumber;
    private BigDecimal creditLimit;
    private String password;
}
