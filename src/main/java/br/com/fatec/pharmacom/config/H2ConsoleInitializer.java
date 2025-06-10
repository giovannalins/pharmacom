package br.com.fatec.pharmacom.config;


import java.sql.SQLException;
import org.h2.tools.Server;

public class H2ConsoleInitializer {


    public static void startH2Console() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
              .start();
        System.out.println("H2 Console available at http://localhost:8082");
    }
}