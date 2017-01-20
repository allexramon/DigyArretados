
package birdpoint.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {
        System.out.println("Conectando ao Banco de Dados...");
        try {
            return DriverManager.getConnection("jdbc:mysql://192.168.3.38:1892/birdpoint", 
                    "root", "ads20161");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
