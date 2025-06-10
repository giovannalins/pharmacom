package br.com.fatec.pharmacom.product.viewmodel;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import br.com.fatec.pharmacom.product.dto.ProductNewDto;
import br.com.fatec.pharmacom.product.dto.ProductUpdateDto;
import br.com.fatec.pharmacom.product.model.ProductModel;
import br.com.fatec.pharmacom.product.service.ProductService;
import javafx.collections.ObservableList;

public class ProductViewModel {

	private final ProductService service;
	

	public ProductViewModel() {
		this.service = new ProductService();
	}

	public void createProduct(String name, String description, BigDecimal unitValue, Boolean isActive) {
		var product = new ProductNewDto(name, description, unitValue, isActive);
		this.service.createNewProduct(product);
	}

	public void updateProduct(UUID id, String name, String description, BigDecimal unitValue, Boolean isActive) {
		var product = new ProductUpdateDto(name, description, unitValue, isActive);
		this.service.updateProduct(id, product);
	}

	public void inactivateProduct(UUID id) {
		this.service.inactivateProduct(id);
	}

	public void activateProduct(UUID id) {
		this.service.activateProduct(id);
	}
	
	public List<ProductModel> paginateProducts(int page, int size){
		System.out.println(page);
		System.out.println(size);
		return this.service.paginateProducts(page, size);
	}

	public ObservableList<ProductModel> loadAllProducts() {
		return this.service.loadAllProducts();
	}

	public ProductModel findProductByName(String name) {
		return this.service.findByName(name);
	}

	public ProductModel findProductById(UUID id) {
		return this.service.findById(id);
	}

	public int getTotalPageProducts(int itemsPerPage) {
		return this.service.getTotalPageProducts(itemsPerPage);

	}
}