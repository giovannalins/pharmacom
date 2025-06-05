package br.com.fatec.pharmacom.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JpaManager {
    private static EntityManagerFactory emf;
    private static HikariDataSource dataSource;
    private static final ExecutorService dbExecutor = 
        Executors.newFixedThreadPool(5); // Adjust pool size
    
    public static synchronized void initialize() {
        if (emf != null) return;
        
        // Configure HikariCP
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(ConfigManager.getString("db.url"));
        hikariConfig.setUsername(ConfigManager.getString("db.user"));
        hikariConfig.setPassword(ConfigManager.getString("db.password"));
        hikariConfig.setMaximumPoolSize(ConfigManager.getInt("db.pool.size", 10));
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setIdleTimeout(600000);
        hikariConfig.setMaxLifetime(1800000);
        
        dataSource = new HikariDataSource(hikariConfig);
        
        // Hibernate properties
        Map<String, Object> properties = Map.of(
            "jakarta.persistence.nonJtaDataSource", dataSource,
            "hibernate.hikari.dataSource", dataSource,
            "hibernate.dialect", ConfigManager.getString("hibernate.dialect"),
            "hibernate.hbm2ddl.auto", ConfigManager.getString("hibernate.ddl", "validate"),
            "hibernate.show_sql", ConfigManager.getBoolean("hibernate.show_sql", false),
            "hibernate.format_sql", true
        );
        
        emf = Persistence.createEntityManagerFactory("yourPersistenceUnit", properties);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            dbExecutor.shutdown();
            if (emf != null) emf.close();
            if (dataSource != null) dataSource.close();
        }));
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