package br.com.fatec.pharmacom;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToInventory() throws IOException {
        App.setRoot("fxml/Inventory");
    }
    
    @FXML
    private void switchToProductManagement() throws IOException {
    	App.setRoot("fxml/pages/ProductManagement");
    }
    @FXML
    private void switchToCustomersManagement() throws IOException {
    	App.setRoot("fxml/pages/CustomerManagement");
    }
    @FXML
    private void switchToSuppliersManagement() throws IOException {
    	App.setRoot("fxml/pages/SupplierManagement");
    }
}
