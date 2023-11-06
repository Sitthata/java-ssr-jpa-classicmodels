package sit.int202.classicmodels.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import sit.int202.classicmodels.entities.Employee;
import sit.int202.classicmodels.util.EntityManagerBuilder;

import java.util.List;

public class EmployeeRepository {

    public List<Employee> findEmployeesByOfficeCode(String officeCode) {
        EntityManager em = EntityManagerBuilder.getEntityManager();
        String sql = "SELECT e FROM Employee e WHERE e.office.officeCode = :officeCode";
        try {
            TypedQuery<Employee> query = em.createQuery(
                    sql, Employee.class
            );
            query.setParameter("officeCode", officeCode);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee find(Integer id) {
        EntityManager em = EntityManagerBuilder.getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public List<Employee> findAll() {
        EntityManager em = EntityManagerBuilder.getEntityManager();
        try {
            return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void save(Employee employee) {
        EntityManager em = EntityManagerBuilder.getEntityManager();
        try {
            em.getTransaction().begin();
            if (employee.getId() == null) {
                // create
                em.persist(employee);
            } else {
                // update
                em.merge(employee);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Employee employee) {
        EntityManager em = EntityManagerBuilder.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(employee) ? employee : em.merge(employee));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
