package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shop_management.Database.DatabaseConnection;
import shop_management.Models.Dettes;

public class DetteDao {

    private static DetteDao instance;
    private Connection conn;

    private DetteDao() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    public static DetteDao getInstance() {
        if (instance == null) {
            instance = new DetteDao();
        }
        return instance;
    }

    public void ajouterDette(Dettes dette) {
        // Correction ici : utilisation des guillemets autour de "fullName"
        Date sqlDate = new Date(dette.getDatePrendsDette().getTime());
        Date sqDate = new Date(dette.getDateDePayerdette().getTime());
        String sql = "INSERT INTO dettes (\"montantPayer\", \"dateDette\", \"datePayement\", \"idFacture\") VALUES (?,?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //stmt.setDate(1, (Date) commande.getDateCommande());
            stmt.setDouble(1, dette.getMontantAPayer());
            stmt.setDate(2, sqlDate);
            stmt.setDate(3, sqDate);
            stmt.setInt(4, dette.getIdFacture());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        dette.setIdDette(generatedKeys.getInt(1)); // Récupérer l'ID généré
                        System.out.println("Dette ajoutée avec succès, ID: " + dette.setIdFacture());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierDette(Dettes dette) {
        String sql = "UPDATE dettes SET \"montantPayer\"=?, \"dateDette\"=?, \"datePayement\"=?, \"idFacture\"=? WHERE \"idDette\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDouble(1, dette.getMontantAPayer());
            stmt.setDate(2, (Date) dette.getDatePrendsDette());
            stmt.setDate(3, (Date) dette.getDateDePayerdette());
            stmt.setInt(4, dette.getIdFacture());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerDette(int idDette) {
        String sql = "DELETE FROM dettes WHERE \"idDette\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDette);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Dettes> listeDettes() {
        List<Dettes> dettes = new ArrayList<>();
        String sql = "SELECT * FROM dettes";
        try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Dettes dette = new Dettes();
                dette.setMontantAPayer(resultSet.getInt("montantPayer"));
                dette.setDatePrendsDette(resultSet.getDate("dateDette"));
                dette.setDateDePayerdette(resultSet.getDate("datePayement"));
                dette.setIdFacture(resultSet.getInt("idFacture"));

                dettes.add(dette);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dettes;
    }

    public Dettes trouverDettesParId(int idDette) {
        Dettes dette = null;
        String sql = "SELECT * FROM dettes WHERE idDette=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idDette);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                dette = new Dettes();
                dette.setMontantAPayer(resultSet.getInt("montantPayer"));
                dette.setDatePrendsDette(resultSet.getDate("dateDette"));
                dette.setDateDePayerdette(resultSet.getDate("datePayement"));
                dette.setIdFacture(resultSet.getInt("idFacture"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dette;
    }

    public List<Dettes> getAllDettes() {
        List<Dettes> dettes = new ArrayList<>();
        try {
            String query = "SELECT * FROM commandes WHERE idCommande = ?";  // Exemple de requête
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Créer un objet Personnes pour chaque ligne récupérée
                Dettes dette;
                dette = new Dettes(
                        resultSet.getInt("idDette"),
                        resultSet.getDouble("montantPayer"),
                        resultSet.getDate("dateDette"),
                        resultSet.getDate("datePayement"),
                        resultSet.getInt("idFacture")
                );
                dettes.add(dette); // Ajouter le fournisseur à la liste
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dettes;
    }

}
