package br.com.fatec.pharmacom.product.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.fatec.pharmacom.product.dto.ProductNewDto;
import br.com.fatec.pharmacom.product.dto.ProductUpdateDto;
import br.com.fatec.pharmacom.product.model.ProductModel;
import br.com.fatec.pharmacom.product.repository.ProductRepository;
import javafx.collections.ObservableList;

public class ProductService {

	private ProductRepository repository;

	public ProductService() {
		this.repository = new ProductRepository();
	}

	public void createNewProduct(ProductNewDto newProduct) {
		var product = newProduct.bindToModel();
		this.repository.create(product);
	}

	public void updateProduct(UUID id, ProductUpdateDto updateProduct) {
		var product = this.repository.findById(id);
		product.setDescription(updateProduct.description());
		product.setName(updateProduct.name());
		product.setPrice(updateProduct.unitValue());
		product.setIsActive(updateProduct.isActive());
		product.setUpdatedAt(LocalDateTime.now());
		this.repository.update(product);
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
	
	public ObservableList<ProductModel> loadAllProducts() {
        return this.repository.getAllProducts();       
    }

	public List<ProductModel> paginateProducts(int page, int size) {
		return this.repository.findAll(page, size);
		
	}

	public int getTotalPageProducts(int itemsPerPage) {
		return (int) Math.ceil((double) this.repository.count() / itemsPerPage);
		
	}

}