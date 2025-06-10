package br.com.fatec.pharmacom.product.controller;

import java.io.IOException;
import java.math.BigDecimal;

import br.com.fatec.pharmacom.App;
import br.com.fatec.pharmacom.product.dto.ProductNewDto;
import br.com.fatec.pharmacom.product.service.ProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class ProductController {
	
	
	private ProductService service = new ProductService();
	
	@FXML
    private TextField inputName;
	
	@FXML
	private TextField inputDescription;
	
	@FXML
	private TextField inputPrice;
	
	@FXML
	private Button btnSave;
	
    @FXML
    private void saveProduct() throws IOException {
    	String name = inputName.getText();
    	var description = inputDescription.getText();
    	var price = new BigDecimal(inputPrice.getText());
    	service.createNewProduct(new ProductNewDto(name, description, price, true));
        App.setRoot("fxml/Home");
    }
}
