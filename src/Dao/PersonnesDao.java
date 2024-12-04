package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import shop_management.Database.DatabaseConnection;
import shop_management.Models.Personnes;

public class PersonnesDao {

    private static PersonnesDao instance;
    private Connection conn;

    private PersonnesDao() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    public static PersonnesDao getInstance() {
        if (instance == null) {
            instance = new PersonnesDao();
        }
        return instance;
    }

    public void ajouterPersonne(Personnes personne) {
        // Correction ici : utilisation des guillemets autour de "fullName"
        String sql = "INSERT INTO personnes (\"fullName\", \"adressePersonne\", \"email\", \"telephone\", type) VALUES (?, ?, ?, ?, 'fournisseur')";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, personne.getFullName());
            stmt.setString(2, personne.getPersonAdress());
            stmt.setString(3, personne.getEmail());
            stmt.setString(4, personne.getPhoneNumber());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        personne.setIdPersonne(generatedKeys.getInt(1)); // Récupérer l'ID généré
                        System.out.println("Personne ajoutée avec succès, ID: " + personne.getIdPersonne());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterClient(Personnes personne) {
        // Correction ici : utilisation des guillemets autour de "fullName"
        String sql = "INSERT INTO personnes (\"fullName\", \"adressePersonne\", \"email\", \"telephone\",type) VALUES (?, ?, ?, ?, 'client')";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, personne.getFullName());
            stmt.setString(2, personne.getPersonAdress());
            stmt.setString(3, personne.getEmail());
            stmt.setString(4, personne.getPhoneNumber());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        personne.setIdPersonne(generatedKeys.getInt(1)); // Récupérer l'ID généré
                        System.out.println("Personne ajoutée avec succès, ID: " + personne.getIdPersonne());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Personnes> listePersonnes() {
        List<Personnes> personnes = new ArrayList<>();
        String sql = "SELECT * FROM personnes";
        try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Personnes personne = new Personnes();
                personne.setIdPersonne(resultSet.getInt("idPersonne"));
                personne.setFullName(resultSet.getString("fullName"));
                personne.setPersonAdress(resultSet.getString("adressePersonne"));
                personne.setEmail(resultSet.getString("email"));
                personne.setPhoneNumber(resultSet.getString("telephone"));

                personnes.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }

    public Personnes trouverPersonneParId(int idPersonne) {
        Personnes personne = null;
        String sql = "SELECT * FROM personnes WHERE \"idPersonne\"=?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idPersonne);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                personne = new Personnes();
                personne.setIdPersonne(resultSet.getInt("idPersonne"));
                personne.setFullName(resultSet.getString("fullName"));
                personne.setPersonAdress(resultSet.getString("adressePersonne"));
                personne.setEmail(resultSet.getString("email"));
                personne.setPhoneNumber(resultSet.getString("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personne;
    }

    public List<Personnes> getAllFournisseurs() {
        List<Personnes> fournisseurs = new ArrayList<>();
        try {
            String query = "SELECT * FROM personnes WHERE type = 'fournisseur'";  // Exemple de requête
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Créer un objet Personnes pour chaque ligne récupérée
                Personnes fournisseur = new Personnes(
                        resultSet.getInt("idPersonne"),
                        resultSet.getString("fullName"),
                        resultSet.getString("adressePersonne"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone")
                );
                fournisseurs.add(fournisseur); // Ajouter le fournisseur à la liste
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fournisseurs;
    }

    public List<Personnes> getAllClient() {
        List<Personnes> clients = new ArrayList<>();
        try {
            String query = "SELECT * FROM personnes WHERE type = 'client'";  // Exemple de requête
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Créer un objet Personnes pour chaque ligne récupérée
                Personnes client = new Personnes(
                        resultSet.getInt("idPersonne"),
                        resultSet.getString("fullName"),
                        resultSet.getString("adressePersonne"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone")
                );
                clients.add(client); // Ajouter le fournisseur à la liste
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }

   public void modifierFournisseur(Personnes fournisseur) {
    String sql = "UPDATE personnes SET \"fullName\" = ?, \"adressePersonne\" = ?, \"email\" = ?, \"telephone\" = ? WHERE \"idPersonne\" = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, fournisseur.getFullName());
        stmt.setString(2, fournisseur.getPersonAdress());
        stmt.setString(3, fournisseur.getEmail());
        stmt.setString(4, fournisseur.getPhoneNumber());
        stmt.setInt(5, fournisseur.getIdPersonne()); // Ajout de l'ID de la personne
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void modifierClient(Personnes client) {
           String sql = "UPDATE personnes SET \"fullName\" = ?, \"adressePersonne\" = ?, \"email\" = ?, \"telephone\" = ? WHERE \"idPersonne\" = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, client.getFullName());
        stmt.setString(2, client.getPersonAdress());
        stmt.setString(3, client.getEmail());
        stmt.setString(4, client.getPhoneNumber());
        stmt.setInt(5, client.getIdPersonne()); // Ajout de l'ID de la personne
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void supprimerPersonnes(int idPersonne) {
        String sql = "DELETE FROM personnes WHERE \"idPersonne\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPersonne);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
