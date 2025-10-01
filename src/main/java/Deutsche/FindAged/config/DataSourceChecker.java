package Deutsche.FindAged.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Sarav on 01 Oct 2025
 * @project govtech
 * @package Deutsche.FindAged.config
 * @class DataSourceChecker
 */


@Component
public class DataSourceChecker implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            String url = conn.getMetaData().getURL();
            String dbName = conn.getCatalog();
            System.out.println("=== DATASOURCE INFO ===");
            System.out.println("URL: " + url);
            System.out.println("Database: " + dbName);
            System.out.println("=== END DATASOURCE INFO ===");
        }

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW TABLES")) {

            System.out.println("=== Database Tables ===");
            while (rs.next()) {
                System.out.println("Table: " + rs.getString(1));
            }
        }
    }
}