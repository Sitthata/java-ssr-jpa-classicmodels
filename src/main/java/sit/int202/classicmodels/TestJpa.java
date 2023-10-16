package sit.int202.classicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sit.int202.classicmodels.entities.Employee;
import sit.int202.classicmodels.entities.Office;

public class TestJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("classic-models");
        System.out.println(emf);
        EntityManager em = emf.createEntityManager();
        System.out.println(em);
        Office office = em.find(Office.class, "1");
        Employee emp = em.find(Employee.class, 1002);
        if (!emp.getFirstName().equalsIgnoreCase("somchai")) {
            em.getTransaction().begin();
            emp.setFirstName("Somchai");
            em.persist(emp);
            em.getTransaction().commit();
        }
        System.out.println(emp);
    }
}
