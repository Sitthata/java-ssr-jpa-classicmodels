package sit.int202.classicmodels;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sit.int202.classicmodels.entities.Customer;
import sit.int202.classicmodels.entities.Employee;
import sit.int202.classicmodels.repository.CustomerRepository;

import java.util.List;

public class TestQuery {
    public static void main(String[] args) {
        initPassword();
    }

    public static void initPassword() {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 16, 16);
        CustomerRepository repository = new CustomerRepository();
        EntityManager em = repository.getEntityManager();
        em.getTransaction().begin();

        char[] password;
        for(Customer customer : repository.findAll()) {
            password = customer.getCustomerNumber().toString().toCharArray();
            customer.setPassword(argon2.hash(2, 16, 1, password));
        }

        em.getTransaction().commit(); // Commit the transaction
    }

    public static void testQuery() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("classic-models");
        EntityManager em = emf.createEntityManager();

        List<Employee> employeeList = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
