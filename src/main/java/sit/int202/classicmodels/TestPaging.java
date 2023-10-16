package sit.int202.classicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import sit.int202.classicmodels.entities.Employee;

import java.util.List;

public class TestPaging {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("classic-models");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Employee.findAll");
        int start = 1;
        int pageSize = 5;
        query.setMaxResults(pageSize);
        while (true) {
            query.setFirstResult(start);
            List<Employee> employees = (List<Employee>) query.getResultList();
            if (employees.isEmpty()) {
                break;
            }
            start += pageSize;
            displayEmployee(employees);
        }
        em.close();
    }

    private static void displayEmployee(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.printf("%4d %-10s %-12s %-20s\n",
                    employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        }
        System.out.println("---");
    }
}
