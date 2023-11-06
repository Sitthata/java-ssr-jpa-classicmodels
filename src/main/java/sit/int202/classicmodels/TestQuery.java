package sit.int202.classicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sit.int202.classicmodels.entities.Employee;
import java.util.List;

public class TestQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("classic-models");
        EntityManager em = emf.createEntityManager();

        List<Employee> employeeList = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

    }
}
