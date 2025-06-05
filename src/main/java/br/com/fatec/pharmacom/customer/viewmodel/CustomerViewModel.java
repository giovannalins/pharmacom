package br.com.fatec.pharmacom.customer.viewmodel;

import java.util.UUID;

import br.com.fatec.pharmacom.customer.service.CustumerService;

public class CustomerViewModel {

	private final CustumerService service;
	
	public CustomerViewModel(CustumerService service) {
        this.service = service;
    }
	
	public void createCustomer() {

	}

	public void updateCustomer(UUID id) {

	}

	public void inactivateCustomer(UUID id) {

	}

	public void listCustomers() {

	}
}
