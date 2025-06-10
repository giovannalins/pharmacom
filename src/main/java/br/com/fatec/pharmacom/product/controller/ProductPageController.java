package br.com.fatec.pharmacom.product.controller;

import br.com.fatec.pharmacom.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class ProductPageController {

	
	@FXML
    private StackPane contentPane;
	
	@FXML
    public void initialize() {
		loadContent("fxml/components/ProductList");
    }
	
	private void loadContent(String fxmlFile) {
		try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlFile + ".fxml"));
            Node node = loader.load();
            contentPane.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@FXML
    private void switchToHome() throws IOException {
        App.setRoot("fxml/Home");
    }
	
	@FXML
    private void handleListProducts() {
        loadContent("fxml/components/ProductList");
    }
    
    @FXML
    private void handleNewProduct() {
    	loadContent("fxml/components/NewProductForm");
    }
    
    @FXML
    private void handleUpdateProduct() {
    	loadContent("fxml/components/NewProductForm");
    }
}
