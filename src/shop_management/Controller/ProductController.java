package shop_management.Controller;

import dao.ProduitsDao;
import dao.FournirDao;
import shop_management.Models.Produits;
import shop_management.Models.Fournir;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shop_management.View.Modify.ProductModify;

public class ProductController {

    private ProduitsDao produitsDao;
    private FournirDao fournirDao;

    public ProductController() {
        this.produitsDao = ProduitsDao.getInstance();
        this.fournirDao = FournirDao.getInstance();
    }

    public void ajouterProduit(Produits produit, int idPersonne) {
        // Ajouter le produit dans la table des produits
        produitsDao.ajouterProduit(produit);
        
        // Ajouter la relation entre le produit et le fournisseur dans la table 'Fournir'
        Fournir fournir = new Fournir(produit.getIdProduct(), idPersonne);
        fournirDao.ajouterFournir(fournir);
    }

    public void modifierProduit(Produits produit) {
        produitsDao.modifierProduit(produit);
    }

    public void supprimerProduit(int idProduct) {
        produitsDao.supprimerProduit(idProduct);
    }

    public List<Produits> listeProduits() {
        return produitsDao.listeProduits();
    }

    public Produits trouverProduitParId(int idProduct) {
        return produitsDao.trouverProduitParId(idProduct);
    }

  public void listeProduits(JTable table) {
    try {
        List<Produits> produits = produitsDao.listeProduits();

        String[] columnNames = {"ID Produit", "Nom", "Prix Unitaire", "Quantité", "Modifier", "Supprimer"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Produits produit : produits) {
            String formattedIdProduct = formatIdWithZeros(produit.getIdProduct());

            tableModel.addRow(new Object[]{
                formattedIdProduct, // Affiche l'ID formaté
                produit.getProdName(),
                produit.getPrixUnit(),
                produit.getQuantiteProd(),
                "Modifier",
                "Supprimer"
            });
        }

        table.setModel(tableModel);

        // Ajouter les boutons avec des actions
        table.getColumn("Modifier").setCellRenderer(new ButtonRenderer("Modifier"));
        table.getColumn("Modifier").setCellEditor(new ButtonEditor(table, "Modifier", e -> {
            int row = ((ButtonEditor) table.getCellEditor()).getCurrentRow();
            String idProduit = (String) table.getValueAt(row, 0); // Récupère l'ID formaté
            openModificationForm(Integer.parseInt(idProduit)); // Convertit en entier pour la modification
        }));

        table.getColumn("Supprimer").setCellRenderer(new ButtonRenderer("Supprimer"));
        table.getColumn("Supprimer").setCellEditor(new ButtonEditor(table, "Supprimer", e -> {
            int row = ((ButtonEditor) table.getCellEditor()).getCurrentRow();
            String idProduit = (String) table.getValueAt(row, 0); // Récupère l'ID formaté
            supprimerProduit(Integer.parseInt(idProduit)); // Convertit en entier pour la suppression
            listeProduits(table);
        }));

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des produits : " + e.getMessage());
    }
}

private String formatIdWithZeros(int id) {
    return String.format("%05d", id); // Formatage avec 4 chiffres
}



private void openModificationForm(int idProduit) {
    // Créer une nouvelle fenêtre pour le formulaire de modification
    JFrame modificationFrame = new JFrame("Modifier le produit");

    // Créer une instance de ProductModify avec l'ID produit
    ProductModify modificationForm = new ProductModify(idProduit);

    // Configurer le contenu de la fenêtre
    JPanel panel = new JPanel();
    modificationForm.ProductModifyContent(panel); // Charger le contenu du formulaire dans le panel
    modificationFrame.add(panel);

    // Configurer les dimensions de la fenêtre
    modificationFrame.setSize(400, 400);
    modificationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    modificationFrame.setLocationRelativeTo(null); // Centrer la fenêtre
    modificationFrame.setVisible(true); // Afficher la fenêtre
}


}
