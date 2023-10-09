package sit.int202.classicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sit.int202.classicmodels.entities.Office;

public class TestJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("classic-models");
        System.out.println(emf);
        EntityManager em = emf.createEntityManager();
        System.out.println(em);
        Office office = em.find(Office.class, "1");
        System.out.println(office.getOfficeCode() + office.getAddressLine1());
    }
}
