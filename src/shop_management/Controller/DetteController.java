package shop_management.Controller;

import Dao.DetteDao;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import shop_management.Models.Dettes;
import shop_management.Models.Personnes;

public class DetteController {

    private DetteDao detteDao;

    public DetteController() {
        this.detteDao = DetteDao.getInstance();

    }

    public void ajouterDette(Dettes dette) {
        // Ajouter la commande dans la table des commandes
        detteDao.ajouterDette(dette);
        // Ajouter la relation entre le produit et le fournisseur dans la table 'Fournir'\
    }

    public List<Dettes> listeDettes() {
        return detteDao.listeDettes();
    }

    public void modifierCommande(Dettes dette) {
        detteDao.modifierDette(dette);
    }

    public void supprimerDette(int idDette) {
        detteDao.supprimerDette(idDette);
    }

    public DefaultTableModel getDebtTableModel() {

        List<Dettes> dettes = detteDao.listeDettes();

        // Colonnes du tableau
        String[] columnNames = {"Montant à Payer", "Date de Dette", "Date de Paiement", "ID Facture"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Ajouter les lignes au modèle
        for (Dettes dette : dettes) {
            Object[] row = {
                dette.getMontantAPayer(),
                dette.getDatePrendsDette(),
                dette.getDateDePayerdette(),
                dette.getIdFacture()
            };
            tableModel.addRow(row);
        }

        return tableModel;

    }

}
