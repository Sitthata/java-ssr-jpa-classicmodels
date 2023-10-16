package sit.int202.classicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import sit.int202.classicmodels.entities.Employee;
import sit.int202.classicmodels.entities.Office;

import java.util.List;
import java.util.Scanner;

public class TestQuery {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("classic-models");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Office.FindByCountry");
        System.out.println("Find office by country that start with: ");
        String country = sc.next();
        query.setParameter("countryParam", country+"%");
        List<Office> offices = (List<Office>) query.getResultList();
        for (Office o : offices) {
            System.out.println(o.getCountry() + ": " + o.getOfficeCode());
        }
        em.close();
    }


}
