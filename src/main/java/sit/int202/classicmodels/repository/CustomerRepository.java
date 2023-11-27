package sit.int202.classicmodels.repository;

import jakarta.persistence.EntityManager;
import sit.int202.classicmodels.entities.Customer;
import sit.int202.classicmodels.util.EntityManagerBuilder;

import java.util.List;

public class CustomerRepository {

    private EntityManager em;
    public EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = EntityManagerBuilder.getEntityManager();
        }
        return em;
    }

    public Customer findByName(String userAccount) {
        return getEntityManager().createNamedQuery("FIND_USER", Customer.class)
                .setParameter("user_account", userAccount)
                .getSingleResult();
    }

    public List<Customer> findAll() {
        return getEntityManager()
                .createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }
}
