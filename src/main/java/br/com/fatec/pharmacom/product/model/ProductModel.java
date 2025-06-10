package br.com.fatec.pharmacom.product.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TBL_PRODUCT")
public class ProductModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false)
	private UUID id;
	private String name;
	private String description;
	private BigDecimal price;
	@Column(updatable = false)
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;
	private Boolean isActive;
	private String imageUrl;
	
	public ProductModel(String name, String description, BigDecimal price, LocalDateTime updatedAt, LocalDateTime createdAt, Boolean isActive) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.setIsActive(isActive);
	}
	
	public ProductModel(String name, String description, BigDecimal price, LocalDateTime updatedAt, Boolean isActive) {
		this.name = name;
		this.description = description;
		this.price = price;
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
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
