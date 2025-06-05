package br.com.fatec.pharmacom.customer.controller;

import java.io.IOException;

import br.com.fatec.pharmacom.App;
import javafx.fxml.FXML;

public class CustomerController {

	@FXML
    private void saveCustomer() throws IOException {
        App.setRoot("fxml/Home");
    }
	
}
