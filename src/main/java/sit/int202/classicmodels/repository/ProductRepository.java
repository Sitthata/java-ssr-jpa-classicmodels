package sit.int202.classicmodels.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sit.int202.classicmodels.entities.Product;
import sit.int202.classicmodels.util.EntityManagerBuilder;

import java.util.List;

public class ProductRepository {
    private static int PAGE_SIZE = 10;
    public int getDefaultPageSize() {
        return PAGE_SIZE;
    }
    private EntityManager em;

    private EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = EntityManagerBuilder.getEntityManager();
        }
        return em;
    }

    public Product findById(String productCode) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            return entityManager.find(Product.class, productCode);
        } catch (Exception e) {
            // Log the exception
            System.out.println(e.getMessage());
            throw new RuntimeException("Cannot find product with id " + productCode);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }


    public List<Product> findAll(int page, int pageSize) {
        int startPosition = (page - 1) * pageSize;
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNamedQuery("Product.FindAll");
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        List<Product> productList = query.getResultList();
        entityManager.close();
        return productList;
    }

    public int countAll() {
        EntityManager entityManager = getEntityManager();
        int number = ((Number)
                entityManager.createNamedQuery("Product.count").getSingleResult()).intValue();
        entityManager.close();
        return number;
    }
}


