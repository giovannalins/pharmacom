package br.com.fatec.pharmacom.product.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.com.fatec.pharmacom.product.dto.ProductNewDto;
import br.com.fatec.pharmacom.product.dto.ProductUpdateDto;
import br.com.fatec.pharmacom.product.model.ProductModel;
import br.com.fatec.pharmacom.product.repository.ProductRepository;

public class ProductService {

	private ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public void createNewProduct(ProductNewDto newProduct) {
		var product = newProduct.bindToModel();
		this.repository.save(product);
	}

	public void updateProduct(UUID id, ProductUpdateDto updateProduct) {
		var product = this.repository.findById(id);
		product.setDescription(updateProduct.description());
		product.setName(updateProduct.name());
		product.setUnitValue(updateProduct.unitValue());
		product.setIsActive(updateProduct.isActive());
		product.setUpdatedAt(new Date());
		this.repository.save(product);
	}

	public ProductModel findById(UUID id) {
		return this.repository.findById(id);
	}

	public ProductModel findByName(String name) {
		return this.repository.findByName(name);
	}

	public void inactivateProduct(UUID id) {
		// TODO Auto-generated method stub

	}

	public void activateProduct(UUID id) {
		// TODO Auto-generated method stub

	}

	public List<ProductModel> listProducts() {
		// TODO Auto-generated method stub
		return List.of();
	}

}