package dao;

import shop_management.Models.Fournir;
import java.sql.*;
import shop_management.Database.DatabaseConnection;

public class FournirDao {

    private static FournirDao instance;
    private Connection conn;

    public FournirDao() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    public static FournirDao getInstance() {
        if (instance == null) {
            instance = new FournirDao();
        }
        return instance;
    }

    public void ajouterFournir(Fournir fournir) {
        String sql = "INSERT INTO fournir (\"idProduit\", \"idPersonne\") VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fournir.getIdProduit());
            stmt.setInt(2, fournir.getIdPersonne());
            stmt.executeUpdate();
            System.out.println("Fournir ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
