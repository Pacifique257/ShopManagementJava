package shop_management.Controller;

import Dao.CommanderDao;
import Dao.FactureDao;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import shop_management.Models.Factures;

public class FactureController {

    private FactureDao facturesDao;
    private CommanderDao commanderDao;

    public FactureController() {
        this.facturesDao = FactureDao.getInstance();
        this.commanderDao = CommanderDao.getInstance();
    }

    public void ajouterFactures(Factures facture, int idCommande) {
        // Ajouter le produit dans la table des produits
        facturesDao.ajouterFactures(facture);

        // Ajouter la relation entre le produit et le fournisseur dans la table 'Fournir'
//        Commander commander = new Commander(facture.getIdProduit(), idCommande);
//        commanderDao.ajouterCommender(commander);
    }

    public void modifierFactures(Factures facture) {
        facturesDao.modifierFactures(facture);
    }

    public List<Factures> getAllFactures() {
        return facturesDao.getAllFactures();
    }

    public List<Factures> listeFactures() {
       return facturesDao.listeFactures();
    }

   
public DefaultTableModel getFacturesTableModel() {
    String[] columnNames = {"Numero de Facture", "Date Facture", "Montant Total", "Quantité Commande"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    List<Factures> factures = facturesDao.listeFactures();

    for (Factures facture : factures) {
        String formattedIdFacture = formatIdWithZeros(facture.getIdFacture());
        // String formattedIdCommande = formatIdWithZeros(facture.getIdCommande()); // Plus besoin de l'ID de la commande

        tableModel.addRow(new Object[]{
            formattedIdFacture,
            facture.getDateFacture(),
            facture.getMontantTotal(),
            facture.getQuantiteCommande() // Afficher la quantité de la commande
        });
    }

    return tableModel;
}


private String formatIdWithZeros(int id) {
    return String.format("%05d", id); // Formatage avec 4 chiffres
}

       
    
}
