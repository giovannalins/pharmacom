package br.com.fatec.pharmacom.product.model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class ProductModel{
	
	private UUID id;
	private String name;
	private String description;
	private BigDecimal unitValue;
	private Date updatedAt;
	private Date createdAt;
	private Boolean isActive;
	
	public ProductModel(String name, String description, BigDecimal unitValue, Date updatedAt, Date createdAt, Boolean isActive) {
		this.name = name;
		this.description = description;
		this.unitValue = unitValue;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.setIsActive(isActive);
	}
	
	public ProductModel(String name, String description, BigDecimal unitValue, Date updatedAt, Boolean isActive) {
		this.name = name;
		this.description = description;
		this.unitValue = unitValue;
		this.updatedAt = updatedAt;
		this.setIsActive(isActive);
	}
	
	public ProductModel() {
		
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getUnitValue() {
		return unitValue;
	}
	
	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
