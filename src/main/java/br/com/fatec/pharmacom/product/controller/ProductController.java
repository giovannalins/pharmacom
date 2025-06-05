package br.com.fatec.pharmacom.product.controller;

import java.io.IOException;

import br.com.fatec.pharmacom.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProductController {
	
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
    	System.out.println(inputName.getText());
    	System.out.println(inputDescription.getText());
    	System.out.println(inputPrice.getText());
        App.setRoot("fxml/Home");
    }
}
