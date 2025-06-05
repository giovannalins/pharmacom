package br.com.fatec.pharmacom.supplier.controller;

import java.io.IOException;

import br.com.fatec.pharmacom.App;
import br.com.fatec.pharmacom.supplier.viewmodel.SupplierViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SupplierController {
	
	private SupplierViewModel viewModel = new SupplierViewModel();
	
	
	@FXML
	private TextField inputLegalName;

	@FXML
	private TextField inputTradeName;

	@FXML
	private TextField inputCnpj;

	@FXML
	private TextField inputPhone;

	@FXML
	private TextField inputEmail;

	@FXML
	private TextField inputWebSite;

	@FXML
	private Button btnSave;
	
	@FXML
    private void createSupplier() throws IOException {
		
        App.setRoot("fxml/Home");
    }
	
	@FXML
	private void updatedSupplier() throws IOException {
		App.setRoot("fxml/Home");
	}
	
	@FXML
	private void inactivateSupplier() throws IOException {
		this.viewModel.inactivateSupplier(null);
		App.setRoot("fxml/Home");
	}
}
