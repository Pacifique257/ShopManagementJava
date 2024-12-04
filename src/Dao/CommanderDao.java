package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import shop_management.Database.DatabaseConnection;
import shop_management.Models.Commander;

public class CommanderDao {

    private static CommanderDao instance;
    private Connection conn;

    public CommanderDao() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    public static CommanderDao getInstance() {
        if (instance == null) {
            instance = new CommanderDao();
        }
        return instance;
    }

//    public void ajouterCommender(Commander commander) {
//    String selectQuantiteSql = "SELECT \"quantiteProduit\" FROM produits WHERE \"idProduit\" = ?";
//    String insertSql = "INSERT INTO commander (\"idProduit\", \"idCommande\") VALUES (?, ?)";
//    String updateSql = "UPDATE produits SET \"quantiteProduit\" = \"quantiteProduit\" - ? WHERE \"idProduit\" = ?";
//
//    try (
//        PreparedStatement checkStmt = conn.prepareStatement(selectQuantiteSql);
//        PreparedStatement insertStmt = conn.prepareStatement(insertSql);
//        PreparedStatement updateStmt = conn.prepareStatement(updateSql)
//    ) {
//        // Vérifier la quantité disponible dans les produits
//        checkStmt.setInt(1, commander.getIdProduit());
//        try (ResultSet resultSet = checkStmt.executeQuery()) {
//            if (resultSet.next()) {
//                int quantiteDisponible = resultSet.getInt("quantiteProduit");
//                if (commander.getQuantiteCommande() > quantiteDisponible) {
//                    JOptionPane.showMessageDialog(null, 
//                        "Quantité insuffisante pour le produit ID: " + commander.getIdProduit(),
//                        "Erreur de quantité",
//                        JOptionPane.WARNING_MESSAGE);
//                    return; // Sortir si la quantité est insuffisante
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, 
//                    "Produit non trouvé pour l'ID: " + commander.getIdProduit(),
//                    "Produit introuvable",
//                    JOptionPane.ERROR_MESSAGE);
//                return; // Sortir si le produit n'existe pas
//            }
//        }
//
//        // Insertion dans la table "commander"
//        insertStmt.setInt(1, commander.getIdProduit());
//        insertStmt.setInt(2, commander.getIdCommande());
//        insertStmt.executeUpdate();
//
//        // Mise à jour de la quantité dans la table "produits"
//        updateStmt.setInt(1, commander.getQuantiteCommande());
//        updateStmt.setInt(2, commander.getIdProduit());
//        int rowsAffected = updateStmt.executeUpdate();
//
//        if (rowsAffected > 0) {
//            System.out.println("Quantité du produit mise à jour avec succès.");
//        } else {
//            System.out.println("Aucun produit trouvé pour la mise à jour.");
//        }
//
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}
    public void ajouterCommender(Commander commander) {
        String selectQuantiteSql = "SELECT \"quantiteProduit\" FROM produits WHERE \"idProduit\" = ?";
        String insertSql = "INSERT INTO commander (\"idProduit\", \"idCommande\") VALUES (?, ?)";
        String updateSql = "UPDATE produits SET \"quantiteProduit\" = \"quantiteProduit\" - ? WHERE \"idProduit\" = ?";

        try {
            conn.setAutoCommit(false); // Désactiver l'auto-commit

            // Vérifier la quantité disponible dans les produits
            try (PreparedStatement checkStmt = conn.prepareStatement(selectQuantiteSql)) {
                checkStmt.setInt(1, commander.getIdProduit());
                try (ResultSet resultSet = checkStmt.executeQuery()) {
                    if (resultSet.next()) {
                        int quantiteDisponible = resultSet.getInt("quantiteProduit");
                        if (commander.getQuantiteCommande() > quantiteDisponible) {
                            JOptionPane.showMessageDialog(null,
                                    "Quantité insuffisante pour le produit ID: " + commander.getIdProduit(),
                                    "Erreur de quantité",
                                    JOptionPane.WARNING_MESSAGE);
                            return; // Sortir si la quantité est insuffisante
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Produit non trouvé pour l'ID: " + commander.getIdProduit(),
                                "Produit introuvable",
                                JOptionPane.ERROR_MESSAGE);
                        return; // Sortir si le produit n'existe pas
                    }
                }
            }

            // Insertion dans la table "commander"
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setInt(1, commander.getIdProduit());
                insertStmt.setInt(2, commander.getIdCommande());
                insertStmt.executeUpdate();
            }

            // Mise à jour de la quantité dans la table "produits"
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, commander.getQuantiteCommande());
                updateStmt.setInt(2, commander.getIdProduit());
                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit(); // Valider la transaction
                    System.out.println("Quantité du produit mise à jour avec succès.");
                } else {
                    System.out.println("Aucun produit trouvé pour la mise à jour.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback(); // Annuler la transaction en cas d'erreur
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }

}
