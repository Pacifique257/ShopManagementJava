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
import shop_management.Models.Factures;

public class FactureDao {

    private static FactureDao instance;
    private Connection conn;

    private FactureDao() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    public static FactureDao getInstance() {
        if (instance == null) {
            instance = new FactureDao();
        }
        return instance;
    }

    public void ajouterFactures(Factures facture) {
        Date sqlDate = new Date(facture.getDateFacture().getTime());
        // Correction ici : utilisation des guillemets autour de "fullName"
        String sql = "INSERT INTO factures (\"dateFacture\", \"montantFacture\", \"idCommande\") VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, sqlDate);
            stmt.setDouble(2, facture.getMontantTotal());
            stmt.setInt(3, facture.getIdCommande());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        facture.setIdCommande(generatedKeys.getInt(1)); // Récupérer l'ID généré
                        System.out.println("Factures ajoutée avec succès, ID: " + facture.setIdCommande());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierFactures(Factures facture) {
        String sql = "UPDATE factures SET \"dateFacture\"=?, \"montantFacture\"=?, \"idCommande\"=? WHERE \"idFacture\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, (Date) facture.getDateFacture());
            stmt.setDouble(2, facture.getMontantTotal());
            stmt.setInt(3, facture.getIdCommande());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerFacture(int idFacture) {
        String sql = "DELETE FROM factures WHERE \"idFacture\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFacture);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public List<Factures> listeFactures() {
    List<Factures> factures = new ArrayList<>();
    String sql = """
        SELECT f."idFacture", f."dateFacture", f."montantFacture", c."quantiteCommande"
        FROM "factures" f
                     JOIN "commandes" c ON f."idCommande" = c."idCommande"
    """;

    try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
        while (resultSet.next()) {
            Factures facture = new Factures();
            facture.setIdFacture(resultSet.getInt("idFacture"));
            facture.setDateFacture(resultSet.getDate("dateFacture"));
            facture.setMontantTotal(resultSet.getDouble("montantFacture"));
            facture.setQuantiteCommande(resultSet.getDouble("quantiteCommande")); // Récupérer la quantité de la commande

            factures.add(facture);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return factures;
}


    public Factures trouverFactureParId(int idFacture) {
        Factures facture = null;
        String sql = "SELECT * FROM factures WHERE idFacture=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idFacture);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                facture = new Factures();
                facture.setIdFacture(resultSet.getInt("idFacture"));
                facture.setDateFacture(resultSet.getDate("dateFacture"));
                facture.setMontantTotal(resultSet.getDouble("montantFacture"));
                facture.setIdCommande(resultSet.getInt("idCommande"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facture;
    }

    public List<Factures> getAllFactures() {
        List<Factures> factures = new ArrayList<>();
        try {
            String query = "SELECT * FROM factures WHERE idFacture = ?";  // Exemple de requête
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Créer un objet Personnes pour chaque ligne récupérée
                Factures facture = new Factures(
                        resultSet.getInt("idFacture"),
                        resultSet.getDate("dateFacture"),
                        resultSet.getDouble("montantFacture"),
                        resultSet.getInt("idCommande")
                );
                factures.add(facture); // Ajouter le fournisseur à la liste
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factures;
    }

  
}
