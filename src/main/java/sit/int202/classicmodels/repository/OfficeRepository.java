package sit.int202.classicmodels.repository;

import jakarta.persistence.EntityManager;
import sit.int202.classicmodels.entities.Office;
import sit.int202.classicmodels.util.EntityManagerBuilder;

import java.util.List;

public class OfficeRepository {
    private EntityManager em;
    private EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = EntityManagerBuilder.getEntityManager();
        }
        return em;
    }

    public List<Office> findAll() {
        return getEntityManager().createNamedQuery("Office.FindAll", Office.class).getResultList();
    }

    public Office find(String officeCode) {
        return getEntityManager().find(Office.class, officeCode);
    }

    public boolean create(Office office) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(office);
            em.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean update(Office office) {
        EntityManager em = getEntityManager();
        if (office.getOfficeCode() == null) {
            throw new IllegalArgumentException("Office cannot be updated with null officeCode");
        }

        try {
            em.getTransaction().begin();
            em.merge(office);
            em.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void close() {
        if (em != null && em.isOpen()){
            em.close();
        }
    }
}
