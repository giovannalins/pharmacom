package br.com.fatec.pharmacom.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.fatec.pharmacom.product.model.ProductModel;

public record ProductNewDto(String name, String description, BigDecimal unitValue, Boolean isActive) {
	
	public ProductModel bindToModel (){
		return new ProductModel(name, description, unitValue, new Date(), new Date(), isActive);
	}
}
