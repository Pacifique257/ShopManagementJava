package shop_management.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/shop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "paci";

    private DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Échec de la connexion à la base de données !");
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DatabaseConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                instance = new DatabaseConnection();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

