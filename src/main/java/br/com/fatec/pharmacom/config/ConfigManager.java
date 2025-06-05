package br.com.fatec.pharmacom.config;

import java.io.File;

import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.EnvironmentConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ConfigManager {
    private static CombinedConfiguration config;
    
    static {
        try {
            Parameters params = new Parameters();
            config = new CombinedConfiguration();
            
            // 1. Environment variables (highest priority)
            config.addConfiguration(new EnvironmentConfiguration());
            
            // 2. External config file (medium priority)
            File propertiesFile = new File("config.properties");
            if (propertiesFile.exists()) {
                config.addConfiguration(
                    new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                        .configure(params.fileBased().setFile(propertiesFile))
                        .getConfiguration()
                );
            }
            
            // 3. Default properties from classpath (lowest priority)
            config.addConfiguration(
                new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                    .configure(params.fileBased().setURL(
                        ConfigManager.class.getResource("/default.properties")))
                    .getConfiguration()
            );
            
        } catch (ConfigurationException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }
    
    // Basic getter without default value
    public static String getString(String key) {
        return config.getString(key);
    }
    
    // Getter with default value
    public static String getString(String key, String defaultValue) {
        return config.getString(key, defaultValue);
    }
    
    // Integer getter with default
    public static int getInt(String key, int defaultValue) {
        return config.getInt(key, defaultValue);
    }
    
    // Boolean getter without default
    public static Boolean getBoolean(String key) {
        return config.getBoolean(key, null);
    }
    
    // Boolean getter with default
    public static boolean getBoolean(String key, boolean defaultValue) {
        return config.getBoolean(key, defaultValue);
    }
    
    // Add more type-specific getters as needed...
}