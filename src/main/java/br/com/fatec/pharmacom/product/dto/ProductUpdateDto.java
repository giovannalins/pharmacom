package br.com.fatec.pharmacom.product.dto;

import java.math.BigDecimal;


public record ProductUpdateDto(String name, String description, BigDecimal unitValue, Boolean isActive) {}
