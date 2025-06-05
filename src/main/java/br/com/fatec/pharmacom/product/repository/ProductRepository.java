package br.com.fatec.pharmacom.product.repository;

import java.util.UUID;

import br.com.fatec.pharmacom.product.model.ProductModel;

public class ProductRepository{

	public void save(ProductModel product) {
		// TODO Save the product in the db
		
	}

	public ProductModel findById(UUID id) {
		// TODO Search the product by id
		return new ProductModel();
	}

	public ProductModel findByName(String name) {
		// TODO Search the product by name
		return new ProductModel();
	}
	
}