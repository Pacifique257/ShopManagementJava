package shop_management.Controller;

import shop_management.Models.Personnes;
import dao.PersonnesDao;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shop_management.View.Modify.ClientModify;
import shop_management.View.Modify.FournisseurModify;

public class PersonneController {

    private final PersonnesDao personnesDao;

    public PersonneController() {
        this.personnesDao = PersonnesDao.getInstance();
    }

    public void ajouterPersonne(Personnes personne) {
        personnesDao.ajouterPersonne(personne);
    }

    public void ajouterClient(Personnes personne) {
        personnesDao.ajouterClient(personne);
    }

    public void supprimerPersonnes(int idPersonne) {
        personnesDao.supprimerPersonnes(idPersonne);
    }

    public List<Personnes> obtenirListePersonnes() {
        return personnesDao.listePersonnes();
    }

    public Personnes trouverPersonneParId(int idPersonne) {
        return personnesDao.trouverPersonneParId(idPersonne);
    }

    public List<Personnes> getAllFournisseurs() {
        return personnesDao.getAllFournisseurs();
    }

    public void modifierFournisseur(Personnes fournisseur) {
        personnesDao.modifierFournisseur(fournisseur);
    }

    public void modifierClient(Personnes client) {
        personnesDao.modifierFournisseur(client);
    }

    public List<Personnes> getAllClient() {
        return personnesDao.getAllClient();
    }

    public void getAllFournisseurs(JTable table) {
        try {
            List<Personnes> fournisseurs = personnesDao.getAllFournisseurs();

            String[] columnNames = {"ID Personne", "Nom complet", "Adresse", "Email", "Téléphone", "Modifier", "Supprimer"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (Personnes fournisseur : fournisseurs) {
                String formattedIdPersonne = formatIdWithZeros(fournisseur.getIdPersonne());

                tableModel.addRow(new Object[]{
                    formattedIdPersonne, // ID formaté
                    fournisseur.getFullName(),
                    fournisseur.getPersonAdress(),
                    fournisseur.getEmail(),
                    fournisseur.getPhoneNumber(),
                    "Modifier",
                    "Supprimer"
                });
            }

            table.setModel(tableModel);

            // Ajouter les boutons avec des actions
            table.getColumn("Modifier").setCellRenderer(new ButtonRenderer("Modifier"));
            table.getColumn("Modifier").setCellEditor(new ButtonEditor(table, "Modifier", e -> {
                int row = ((ButtonEditor) table.getCellEditor()).getCurrentRow();
                String idPersonne = (String) table.getValueAt(row, 0); // Récupère l'ID formaté
                openModificationFournisseur(Integer.parseInt(idPersonne)); // Convertit en entier
            }));

            table.getColumn("Supprimer").setCellRenderer(new ButtonRenderer("Supprimer"));
            table.getColumn("Supprimer").setCellEditor(new ButtonEditor(table, "Supprimer", e -> {
                int row = ((ButtonEditor) table.getCellEditor()).getCurrentRow();
                String idPersonne = (String) table.getValueAt(row, 0); // Récupère l'ID formaté
                supprimerPersonnes(Integer.parseInt(idPersonne)); // Convertit en entier
                getAllFournisseurs(table);
            }));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des fournisseurs : " + e.getMessage());
        }
    }

    private String formatIdWithZeros(int id) {
        return String.format("%05d", id); // Formatage avec 4 chiffres
    }

    private void openModificationFournisseur(int idPersonne) {
        // Créer une nouvelle fenêtre pour le formulaire de modification
        JFrame modificationFrame = new JFrame("Modifier le produit");

        // Créer une instance de ProductModify avec l'ID produit
        FournisseurModify modificationForm = new FournisseurModify(idPersonne);
        // Configurer le contenu de la fenêtre
        JPanel panel = new JPanel();
        modificationForm.FournisseurModifyContent(panel); // Charger le contenu du formulaire dans le panel
        modificationFrame.add(panel);

        // Configurer les dimensions de la fenêtre
        modificationFrame.setSize(400, 400);
        modificationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modificationFrame.setLocationRelativeTo(null); // Centrer la fenêtre
        modificationFrame.setVisible(true); // Afficher la fenêtre
    }

    public void getAllClient(JTable table) {
        try {
            List<Personnes> clients = personnesDao.getAllClient();

            String[] columnNames = {"ID Client", "Nom du Client", "Adresse", "Email", "Téléphone", "Modifier", "Supprimer"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (Personnes client : clients) {
                String formattedIdClient = formatIdWithZeros(client.getIdPersonne());

                tableModel.addRow(new Object[]{
                    formattedIdClient, // ID formaté
                    client.getFullName(),
                    client.getPersonAdress(),
                    client.getEmail(),
                    client.getPhoneNumber(),
                    "Modifier",
                    "Supprimer"
                });
            }

            table.setModel(tableModel);

            // Ajouter les boutons avec des actions
            table.getColumn("Modifier").setCellRenderer(new ButtonRenderer("Modifier"));
            table.getColumn("Modifier").setCellEditor(new ButtonEditor(table, "Modifier", e -> {
                int row = ((ButtonEditor) table.getCellEditor()).getCurrentRow();
                String idClient = (String) table.getValueAt(row, 0); // Récupère l'ID formaté
                openModificationClient(Integer.parseInt(idClient)); // Convertit en entier
            }));

            table.getColumn("Supprimer").setCellRenderer(new ButtonRenderer("Supprimer"));
            table.getColumn("Supprimer").setCellEditor(new ButtonEditor(table, "Supprimer", e -> {
                int row = ((ButtonEditor) table.getCellEditor()).getCurrentRow();
                String idClient = (String) table.getValueAt(row, 0); // Récupère l'ID formaté
                supprimerPersonnes(Integer.parseInt(idClient)); // Convertit en entier
                getAllClient(table);
            }));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des clients : " + e.getMessage());
        }
    }

    private void openModificationClient(int idPersonne) {
        // Créer une nouvelle fenêtre pour le formulaire de modification
        JFrame modificationFrame = new JFrame("Modifier le produit");

        // Créer une instance de ProductModify avec l'ID produit
        ClientModify modificationForm = new ClientModify(idPersonne);
        // Configurer le contenu de la fenêtre
        JPanel panel = new JPanel();
        modificationForm.ClientModifyContent(panel); // Charger le contenu du formulaire dans le panel
        modificationFrame.add(panel);

        // Configurer les dimensions de la fenêtre
        modificationFrame.setSize(400, 400);
        modificationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modificationFrame.setLocationRelativeTo(null); // Centrer la fenêtre
        modificationFrame.setVisible(true); // Afficher la fenêtre
    }
}
