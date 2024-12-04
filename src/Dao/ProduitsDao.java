package dao;

import shop_management.Models.Produits;
import shop_management.Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitsDao {

    private static ProduitsDao instance;
    private Connection conn;

    public ProduitsDao() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    public static ProduitsDao getInstance() {
        if (instance == null) {
            instance = new ProduitsDao();
        }
        return instance;
    }

    public void ajouterProduit(Produits produit) {
        String query = "INSERT INTO produits (\"nomProduit\", \"prixUnitaire\", \"quantiteProduit\") VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, produit.getProdName());
            stmt.setDouble(2, produit.getPrixUnit());
            stmt.setDouble(3, produit.getQuantiteProd());
   
           int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produit.setIdProduct(generatedKeys.getInt(1)); // Récupérer l'ID généré
                    System.out.println("Personne ajoutée avec succès, ID: " + produit.getIdProduct());
                }
            }
        }
            System.out.println("Produit ajouté : " + produit);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void modifierProduit(Produits produit) {
        String sql = "UPDATE produits SET \"nomProduit\"=?, \"prixUnitaire\"=?, \"quantiteProduit\"=? WHERE \"idProduit\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produit.getProdName());
            stmt.setDouble(2, produit.getPrixUnit());
            stmt.setDouble(3, produit.getQuantiteProd());
            stmt.setInt(4, produit.getIdProduct());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerProduit(int idProduct) {
        String sql = "DELETE FROM produits WHERE \"idProduit\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduct);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produits> listeProduits() {
        List<Produits> produits = new ArrayList<>();
        String sql = "SELECT * FROM produits";
        try (Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                Produits produit = new Produits(resultSet.getInt("idProduit"),resultSet.getString("nomProduit"),resultSet.getDouble("prixUnitaire") ,resultSet.getDouble("quantiteProduit"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    public Produits trouverProduitParId(int idProduct) {
        Produits produit = null;
        String sql = "SELECT * FROM produits WHERE \"idProduit\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduct);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                produit = new Produits();
                produit.setIdProduct(resultSet.getInt("idProduit"));
                produit.setProdName(resultSet.getString("nomProduit"));
                produit.setPrixUnit(resultSet.getDouble("prixUnitaire"));
                produit.setQuantiteProd(resultSet.getDouble("quantiteProduit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produit;
    }

    public List<Produits> getAllProduits() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
