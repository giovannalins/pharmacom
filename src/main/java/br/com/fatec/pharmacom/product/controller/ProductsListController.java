package br.com.fatec.pharmacom.product.controller;

import br.com.fatec.pharmacom.App;
import br.com.fatec.pharmacom.product.model.ProductModel;
import br.com.fatec.pharmacom.product.viewmodel.ProductViewModel;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class ProductsListController {
	
	private static final int ITEMS_PER_PAGE = 15;
	private int totalPages;
	
    @FXML
    private ListView<HBox> listViewProducts;

    @FXML
    private Button btnAtualizar;
    
    @FXML
    private Pagination pagination;
    
    private ProductViewModel viewModel = new ProductViewModel();
    
    @FXML
    public void initialize() {
    	this.totalPages = this.viewModel.getTotalPageProducts(ITEMS_PER_PAGE);
    	// 1. Setup page factory (this connects your loader to the pagination)
        pagination.setPageFactory(this::loadPage);
        
        // 2. Setup reactive listeners (for side effects only)
        setupPageChangeListener();
        
        // 3. Initialize with first page
        initializePagination(); // Will trigger first load automatically
    }
    
    private void initializePagination() {
        pagination.setPageCount(totalPages);
        pagination.setCurrentPageIndex(1);
    }
    
    private void setupPageChangeListener() {
        pagination.currentPageIndexProperty().addListener((obs, oldIdx, newIdx) -> {
            // Example side effects:
            System.out.println("Navigating to page " + (newIdx.intValue() + 1));
            
            // Update custom UI elements
            updatePageLabel(newIdx.intValue() + 1, pagination.getPageCount());
            
            // Disable buttons during load (if you have custom navigation)
            setNavigationDisabled(true);
            
        });
    }
    
    
    public void updatePageLabel(int s, int count) {
    	
    }
    
    public void setNavigationDisabled(Boolean disable) {
    	
    }
    
    private ListView<HBox> loadPage(int pageIndex) {
    	
    	
		List<ProductModel> products = viewModel.paginateProducts(pageIndex, ITEMS_PER_PAGE);
		listViewProducts.getItems().clear();
		products.forEach(product -> {
			try {
				FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/components/ProductItemList" + ".fxml"));
				listViewProducts.getItems().add(this.mountProductItem(product, loader));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		return listViewProducts;
    }
    
    private HBox mountProductItem(ProductModel product, FXMLLoader loader) throws IOException {
    	HBox productItem = loader.load();
        ImageView productImage = (ImageView) productItem.lookup("#productImage");
        Label productName = (Label) productItem.lookup("#productName");
        Label productDescription = (Label) productItem.lookup("#productDescription");
        Label productPrice = (Label) productItem.lookup("#productPrice");
        productName.setText(product.getName());
        productPrice.setText(product.getPrice().toString());
        productDescription.setText(product.getDescription());
        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
        	productImage.setImage(new Image(product.getImageUrl(), 100, 0, false, false));
//            try {
//            } catch (Exception e) {
//                productImage.setImage(new Image("/images/default-product.png"));
//            }
        }
        return productItem;
    }
}
