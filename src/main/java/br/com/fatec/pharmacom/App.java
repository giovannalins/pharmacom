package br.com.fatec.pharmacom;

import java.io.IOException;
import java.sql.SQLException;

import br.com.fatec.pharmacom.config.H2ConsoleInitializer;
import br.com.fatec.pharmacom.config.JpaManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    @Override
    public void init() throws Exception {
        
    }
    
    @Transactional
    private void init_db() {
    	var em = JpaManager.getEntityManager();
    	EntityTransaction transaction = em.getTransaction();
    	try {
            transaction.begin();
	        em.createNativeQuery("""
	        		INSERT INTO TBL_PRODUCT (ID, CREATEDAT, UPDATEDAT, NAME, DESCRIPTION, PRICE, ISACTIVE, IMAGEURL) VALUES
					(UUID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
					 'Dipirona 1g',
					 'Medicamento indicado para dores intensas e febre alta',
					 5.75, TRUE, 'https://cdn.vnda.com.br/1920x/fmiligrama/2024/02/23/14_28_44_307_14_2_8_812_antiansiedadepassifloramaracuja120mgmulungu80mgcrataegus120mgmelissa120mgcapsulas90capsulas.png'),
					
					(UUID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
					 'Paracetamol 500mg',
					 'Analgésico e antipirético indicado para dores leves a moderadas e febre',
					 4.99, TRUE, 'https://cdn.vnda.com.br/1920x/fmiligrama/2024/02/23/14_28_44_307_14_2_8_812_antiansiedadepassifloramaracuja120mgmulungu80mgcrataegus120mgmelissa120mgcapsulas90capsulas.png'),
					
					(UUID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
					 'Vitamina C 1g',
					 'Suplemento vitamínico para fortalecimento do sistema imunológico',
					 6.20, TRUE, 'https://cdn.vnda.com.br/1920x/fmiligrama/2024/02/23/14_28_44_307_14_2_8_812_antiansiedadepassifloramaracuja120mgmulungu80mgcrataegus120mgmelissa120mgcapsulas90capsulas.png'),
					
					(UUID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
					 'Omeprazol 20mg',
					 'Inibidor de acidez gástrica para tratamento de refluxo e gastrite',
					 8.90, TRUE, 'https://cdn.vnda.com.br/1920x/fmiligrama/2024/02/23/14_28_44_307_14_2_8_812_antiansiedadepassifloramaracuja120mgmulungu80mgcrataegus120mgmelissa120mgcapsulas90capsulas.png'),
					
					(UUID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
					 'Ibuprofeno 600mg',
					 'Anti-inflamatório não esteroide usado para dores e inflamações',
					 9.50, TRUE, 'https://cdn.vnda.com.br/1920x/fmiligrama/2024/02/23/14_28_44_307_14_2_8_812_antiansiedadepassifloramaracuja120mgmulungu80mgcrataegus120mgmelissa120mgcapsulas90capsulas.png'),
					
					(UUID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
					 'Álcool em Gel 70%',
					 'Antisséptico para higienização das mãos',
					 3.50, TRUE, 'https://cdn.vnda.com.br/1920x/fmiligrama/2024/02/23/14_28_44_307_14_2_8_812_antiansiedadepassifloramaracuja120mgmulungu80mgcrataegus120mgmelissa120mgcapsulas90capsulas.png'),
					
					(UUID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
					 'Soro Fisiológico 500ml',
					 'Solução salina usada para limpeza de feridas e hidratação nasal',
					 2.80, TRUE, 'https://cdn.vnda.com.br/1920x/fmiligrama/2024/02/23/14_28_44_307_14_2_8_812_antiansiedadepassifloramaracuja120mgmulungu80mgcrataegus120mgmelissa120mgcapsulas90capsulas.png'),
					
					(UUID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
					 'Termômetro Digital',
					 'Dispositivo para medição da temperatura corporal',
					 18.90, TRUE, 'https://cdn.vnda.com.br/1920x/fmiligrama/2024/02/23/14_28_44_307_14_2_8_812_antiansiedadepassifloramaracuja120mgmulungu80mgcrataegus120mgmelissa120mgcapsulas90capsulas.png'),
					
					(UUID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
					 'Máscara Cirúrgica (pacote com 10)',
					 'Máscaras descartáveis para proteção respiratória',
					 7.99, TRUE, 'https://cdn.vnda.com.br/1920x/fmiligrama/2024/02/23/14_28_44_307_14_2_8_812_antiansiedadepassifloramaracuja120mgmulungu80mgcrataegus120mgmelissa120mgcapsulas90capsulas.png'),
					
					(UUID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
					 'Luva de Procedimento (par)',
					 'Luvas descartáveis para procedimentos não cirúrgicos',
					 1.99, TRUE, 'https://cdn.vnda.com.br/1920x/fmiligrama/2024/02/23/14_28_44_307_14_2_8_812_antiansiedadepassifloramaracuja120mgmulungu80mgcrataegus120mgmelissa120mgcapsulas90capsulas.png');
	        		""").executeUpdate();
        	transaction.commit();
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    @Override
    public void start(Stage stage) throws IOException, SQLException {
    	JpaManager.initialize();
    	init_db();
    	H2ConsoleInitializer.startH2Console();
        scene = new Scene(loadFXML("fxml/Home"), 640, 480);
        scene.getStylesheets().add(getClass().getResource("css/home.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("css/inventory.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("css/product.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    @Override
    public void stop() throws Exception {
        JpaManager.shutdown();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }

}