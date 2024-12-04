package shop_management.Controller;

import Dao.CommandeDao;
import Dao.FactureDao;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shop_management.Models.Commandes;
import shop_management.View.Modify.CommandeModify;

public class CommandeController {

    private CommandeDao commandesDao;
    private FactureDao factureDao;

    public CommandeController() {
        this.commandesDao = CommandeDao.getInstance();
        this.factureDao = FactureDao.getInstance();
    }

    public void ajouterCommande(Commandes commande, int idPersonne) {
        // Ajouter la commande dans la table des commandes
        commandesDao.ajouterCommande(commande);
        // Ajouter la relation entre le commande et le fournisseur dans la table 'Fournir'\
    }

    public void modifierCommande(Commandes commande) {
        if (commande.getDateCommande() == null) {
            throw new IllegalArgumentException("La date de commande ne peut pas être null.");
        }
        commandesDao.modifierCommande(commande);
    }

    public void supprimerCommandes(int idCommande) {
        commandesDao.supprimerCommandes(idCommande);
    }

    public List<Commandes> listeCommandes() {
        return commandesDao.listeCommandes();
    }

    public Commandes trouverCommandesParId(int idCommande) {
        return commandesDao.trouverCommandesParId(idCommande);
    }

public void listeCommandes(JTable table) {
    try {
        // Récupérer les commandes depuis la base de données
        List<Commandes> commandes = commandesDao.listeCommandes();

        // Définir les noms des colonnes
        String[] columnNames = {"Numero de Commande", "Date de la commande", "Quantité", "Nom du client", "Modifier", "Supprimer"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Remplir le tableau avec les données des commandes
        for (Commandes commande : commandes) {
            String formattedIdCommande = formatIdWithZeros(commande.getIdCommande()); // Formatage de l'ID

            // Ajouter chaque ligne avec les données
            tableModel.addRow(new Object[]{
                formattedIdCommande, // Affiche l'ID formaté
                commande.getDateCommande(),
                commande.getQuantiteCommande(),
                commande.getNomPersonne(), // Affiche le nom de la personne
                "Modifier",
                "Supprimer"
            });
        }

        // Appliquer le modèle de table
        table.setModel(tableModel);

        // Ajouter les boutons "Modifier" avec des actions
        table.getColumn("Modifier").setCellRenderer(new ButtonRenderer("Modifier"));
        table.getColumn("Modifier").setCellEditor(new ButtonEditor(table, "Modifier", e -> {
            int row = ((ButtonEditor) table.getCellEditor()).getCurrentRow();
            String idCommande = (String) table.getValueAt(row, 0); // Récupérer l'ID formaté
            openModificationForm(Integer.parseInt(idCommande)); // Ouvrir le formulaire de modification
        }));

        // Ajouter les boutons "Supprimer" avec des actions
        table.getColumn("Supprimer").setCellRenderer(new ButtonRenderer("Supprimer"));
        table.getColumn("Supprimer").setCellEditor(new ButtonEditor(table, "Supprimer", e -> {
            int row = ((ButtonEditor) table.getCellEditor()).getCurrentRow();
            String idCommande = (String) table.getValueAt(row, 0); // Récupérer l'ID formaté
            supprimerCommandes(Integer.parseInt(idCommande)); // Supprimer la commande
            listeCommandes(table); // Rafraîchir la liste des commandes
        }));

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des commandes : " + e.getMessage());
    }
}
  private String formatIdWithZeros(int id) {
        return String.format("%05d", id); // Formatage avec 4 chiffres
    }  

    private void openModificationForm(int idCommande) {
        // Créer une nouvelle fenêtre pour le formulaire de modification
        JFrame modificationFrame = new JFrame("Modifier le produit");

        // Créer une instance de ProductModify avec l'ID produit
        CommandeModify modificationForm = new CommandeModify(idCommande);

        // Configurer le contenu de la fenêtre
        JPanel panel = new JPanel();
        modificationForm.CommandeModifyContent(panel); // Charger le contenu du formulaire dans le panel
        modificationFrame.add(panel);

        // Configurer les dimensions de la fenêtre
        modificationFrame.setSize(400, 400);
        modificationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modificationFrame.setLocationRelativeTo(null); // Centrer la fenêtre
        modificationFrame.setVisible(true); // Afficher la fenêtre
    }

}
