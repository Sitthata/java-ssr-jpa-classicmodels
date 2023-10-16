package sit.int202.classicmodels.entities;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "offices")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Office.FindAll", query = "select o from Office o"),
                @NamedQuery(name = "Office.FindByCountry", query = "select o from Office o where o.country like :countryParam")
})
public class Office {
    @Id
    private String officeCode;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phone;
    private String territory;
}
