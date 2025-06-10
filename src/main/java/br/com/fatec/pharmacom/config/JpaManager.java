package br.com.fatec.pharmacom.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.fatec.pharmacom.App;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaManager {
    private static EntityManagerFactory emf;
    private static HikariDataSource dataSource;
    private static final ExecutorService dbExecutor = 
        Executors.newFixedThreadPool(5); // Adjust pool size
    
    public static synchronized void initialize() {
        if (emf != null) return;
        
        // Configure HikariCP
        HikariConfig hikariConfig = new HikariConfig();
//        var db_url = ConfigManager.getString("db.url");
//        var db_url = "jdbc:postgresql://localhost:5432/pharmacom";
//        var db_user = "postgres";
//        var db_password = "JjpHack@01";
//        var driver = "org.postgresql.Driver";
        var db_url = "jdbc:h2:mem:pharmacom;DB_CLOSE_DELAY=-1";
        var db_user = "sa";
        var db_password = "";
        var driver = "org.h2.Driver";
        var db_pool = 5;
        hikariConfig.setJdbcUrl(db_url);
        hikariConfig.setUsername(db_user);
        hikariConfig.setPassword(db_password);
        hikariConfig.setMaximumPoolSize(db_pool);
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setIdleTimeout(600000);
        hikariConfig.setMaxLifetime(1800000);
        
        dataSource = new HikariDataSource(hikariConfig);
        
        // Hibernate properties
        Map<String, Object> props = new HashMap<>();
        props.put("jakarta.persistence.nonJtaDataSource", dataSource);
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.show_sql", true);
        props.put("hibernate.format_sql", true);
//        props.put("database-platform", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.h2.console.enable", "true");
        props.put("hibernate.h2.console.port", "8082");
        props.put("hibernate.h2.console.path", "/h2-console");
        props.put("hibernate.archive.autodetection", "class,hbm");
        props.put("jakarta.persistence.sql-load-script-source", "META-INF/import.sql");
        
        
        emf = new HibernatePersistenceProvider()
                .createEntityManagerFactory("pharmacom", props);
        
        Runtime.getRuntime().addShutdownHook(new Thread(JpaManager::shutdown));
    }
    
    public static EntityManager getEntityManager() {
        if (emf == null) initialize();
        return emf.createEntityManager();
    }
    
    public static void executeAsync(Runnable task) {
        dbExecutor.execute(() -> {
            try (EntityManager em = getEntityManager()) {
                em.getTransaction().begin();
                try {
                    task.run();
                    em.getTransaction().commit();
                } catch (Exception e) {
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    throw e;
                }
            }
        });
    }
    
    public static void shutdown() {
        if (emf != null) emf.close();
        if (dataSource != null) dataSource.close();
        dbExecutor.shutdown();
    }
}



