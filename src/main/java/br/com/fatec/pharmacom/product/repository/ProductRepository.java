package br.com.fatec.pharmacom.product.repository;

import java.util.List;
import java.util.UUID;

import br.com.fatec.pharmacom.config.JpaManager;
import br.com.fatec.pharmacom.product.model.ProductModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductRepository{
	
	private final EntityManager entityManager;
	
	public ProductRepository() {
		this.entityManager = JpaManager.getEntityManager();
	}
	
	public void create(ProductModel product) {
		JpaManager.executeAsync(() -> {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(product); // Save or update
            this.entityManager.getTransaction().commit();
        });
	}
	
	public void update(ProductModel product) {
		JpaManager.executeAsync(() -> {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(product);
			this.entityManager.getTransaction().commit();
		});
	}
	
	public Boolean exist( UUID id) {
		try {
			ProductModel product = this.entityManager.getReference(ProductModel.class, id);
			return true;
		}
		catch(EntityNotFoundException e){
			return false;
		}
	}

	public ProductModel findById(UUID id) {
		// TODO Search the product by id
		return new ProductModel();
	}

	public ProductModel findByName(String name) {
		
		
		return new ProductModel();
	}

	public ObservableList<ProductModel> getAllProducts() {
		ObservableList<ProductModel> products = FXCollections.observableArrayList();
		JpaManager.executeAsync(() -> {
            var query = this.entityManager.createQuery("select p from Product p", ProductModel.class);
            products.setAll(query.getResultList());
        });
        return products;
	}

	public List<ProductModel> findAll(int page, int size) {
		TypedQuery<ProductModel> query = this.entityManager.createQuery(
	            "SELECT p FROM ProductModel p ORDER BY p.name", ProductModel.class);
		query.setFirstResult((page) * size);
        query.setMaxResults(size);
        return query.getResultList();
	}
	
	public Long count() {
        return entityManager.createQuery(
            "SELECT COUNT(p) FROM ProductModel p", Long.class)
            .getSingleResult();
    }
	
}