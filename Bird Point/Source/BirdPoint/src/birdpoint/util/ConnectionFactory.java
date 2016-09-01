
package birdpoint.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {
        System.out.println("Conectando ao Banco de Dados...");
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/techpesc", 
                    "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
