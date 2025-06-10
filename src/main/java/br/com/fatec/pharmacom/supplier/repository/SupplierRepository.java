package br.com.fatec.pharmacom.supplier.repository;

import java.util.UUID;

import br.com.fatec.pharmacom.config.JpaManager;
import br.com.fatec.pharmacom.supplier.model.SupplierModel;
import jakarta.persistence.EntityManager;

public class SupplierRepository {
	
	public void save(SupplierModel supplier) {
		JpaManager.executeAsync(() -> {
            EntityManager em = JpaManager.getEntityManager();
            em.getTransaction().begin();
            em.merge(supplier);
            em.getTransaction().commit();
        });
		
	}

	public SupplierModel findById(UUID id) {
		// TODO Search the Supplier by id
		return new SupplierModel();
	}

	public SupplierModel findByName(String name) {
		// TODO Search the Supplier by name
		return new SupplierModel();
	}
}
