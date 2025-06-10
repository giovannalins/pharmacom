package br.com.fatec.pharmacom.customer.repository;

import java.util.List;
import java.util.UUID;

import br.com.fatec.pharmacom.config.JpaManager;
import br.com.fatec.pharmacom.customer.model.CustomerModel;
import jakarta.persistence.EntityManager;

public class CustumerRepository {
	private EntityManager em = JpaManager.getEntityManager();
	public void create(CustomerModel customer) {
		JpaManager.executeAsync(() -> {
            em.getTransaction().begin();
            em.merge(customer);
            em.getTransaction().commit();
        });
		
	}

	public CustomerModel findById(UUID id) {
		em.find(CustomerModel.class, id);
		return new CustomerModel();
	}

	public List<CustomerModel> findByName(String name) {
		String jpql = "select c from TBL_CUSTOMER c where lower(c.name) like lower(:name)";
	    return em.createQuery(jpql, CustomerModel.class)
	             .setParameter("nome", "%" + name + "%")
	             .getResultList();
	}
}
