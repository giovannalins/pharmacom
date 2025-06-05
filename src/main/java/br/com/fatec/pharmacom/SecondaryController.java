package br.com.fatec.pharmacom;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class SecondaryController {
	
	
	@FXML
    private HBox headerContainer;
	
	@FXML
	private void initialize() {
		
	}
	
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("fxml/Home");
    }
    
    @FXML
    private void switchToProductManagement() throws IOException {
    	App.setRoot("fxml/pages/ProductManagement");
    }
}