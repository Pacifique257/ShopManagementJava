package Dao;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shop_management.Database.DatabaseConnection;
import shop_management.Models.Commandes;

public class CommandeDao {

    private static CommandeDao instance;
    private Connection conn;

    private CommandeDao() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    public static CommandeDao getInstance() {
        if (instance == null) {
            instance = new CommandeDao();
        }
        return instance;
    }

    public void ajouterCommande(Commandes commande) {
        // Correction ici : utilisation des guillemets autour de "fullName"
        Date sqlDate = new Date(commande.getDateCommande().getTime());
        String sql = "INSERT INTO commandes (\"dateCommande\", \"quantiteCommande\", \"idPersonne\") VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //stmt.setDate(1, (Date) commande.getDateCommande());
            stmt.setDate(1, sqlDate);
            stmt.setDouble(2, commande.getQuantiteCommande());
            stmt.setInt(3, commande.getIdPersonne());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        commande.setIdCommande(generatedKeys.getInt(1)); // Récupérer l'ID généré
                        System.out.println("Commande ajoutée avec succès, ID: " + commande.setIdPersonne());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierCommande(Commandes commande) {
        String sql = "UPDATE commandes SET \"dateCommande\"=?, \"quantiteCommande\"=? WHERE \"idCommande\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Vérification si la date est null
            if (commande.getDateCommande() == null) {
                throw new IllegalArgumentException("La date de commande ne peut pas être null.");
            }

            // Conversion de la date
            java.sql.Date sqlDate = new java.sql.Date(commande.getDateCommande().getTime());

            stmt.setDate(1, sqlDate);
            stmt.setDouble(2, commande.getQuantiteCommande());
            stmt.setInt(3, commande.getIdCommande());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Commande modifiée avec succès.");
            } else {
                System.out.println("Aucune commande trouvée avec cet ID.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerCommandes(int idCommande) {
        String sql = "DELETE FROM commandes WHERE \"idCommande\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCommande);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public List<Commandes> listeCommandes() {
    List<Commandes> commandes = new ArrayList<>();
    String sql = """
        SELECT c."idCommande", c."dateCommande", c."quantiteCommande", p."fullName"
        FROM "commandes" c
        JOIN "personnes" p ON c."idPersonne" = p."idPersonne"
    """;

    try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
        while (resultSet.next()) {
            Commandes commande = new Commandes();
            commande.setIdCommande(resultSet.getInt("idCommande"));
            commande.setDateCommande(resultSet.getDate("dateCommande"));
            commande.setQuantiteCommande(resultSet.getDouble("quantiteCommande"));

            // Récupérer le nom de la personne
            String nomPersonne = resultSet.getString("fullName");

            // Vérifiez si le nom de la personne est null ou vide
            if (nomPersonne == null || nomPersonne.trim().isEmpty()) {
                System.out.println("Nom de la personne est vide ou null pour la commande ID: " + commande.getIdCommande());
                nomPersonne = "Nom non disponible";  // Valeur par défaut si le nom est vide ou null
            }

            commande.setNomPersonne(nomPersonne); // Assurez-vous de bien affecter le nom à l'objet Commandes

            // Ajoutez la commande à la liste
            commandes.add(commande);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return commandes;
}


    public Commandes trouverCommandesParId(int idCommande) {
        Commandes commande = null;
        String sql = "SELECT * FROM commandes WHERE \"idCommande\"=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idCommande);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                commande = new Commandes();
                commande.setIdCommande(resultSet.getInt("idCommande"));
                commande.setDateCommande(resultSet.getDate("dateCommande"));
                commande.setQuantiteCommande(resultSet.getDouble("quantiteCommande"));
                commande.setIdPersonne(resultSet.getInt("idPersonne"));
            } else {
                System.out.println("Aucune commande trouvée pour cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }

    public List<Commandes> getAllCommandes() {
        List<Commandes> commandes = new ArrayList<>();
        try {
            String query = "SELECT * FROM commandes WHERE idCommande = ?";  // Exemple de requête
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Créer un objet Personnes pour chaque ligne récupérée
                Commandes commande = new Commandes(
                        resultSet.getInt("idCommande"),
                        resultSet.getDate("dateCommande"),
                        resultSet.getDouble("quantiteCommande"),
                        resultSet.getInt("idPersonne")
                );
                commandes.add(commande); // Ajouter le fournisseur à la liste
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commandes;
    }

    public Commandes trouverCommandetParId(int idCommande) {
        Commandes commande = null;
        String sql = "SELECT * FROM commandes WHERE \"idCommande\"=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCommande);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                commande = new Commandes();
                commande.setIdCommande(resultSet.getInt("idCommande"));
                commande.setDateCommande(resultSet.getDate("dateCommande"));
                commande.setQuantiteCommande(resultSet.getDouble("quantiteCommande"));
                commande.setIdCommande(resultSet.getInt("idPersonne"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }

}
