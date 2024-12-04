package shop_management.View.Select;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shop_management.Controller.DetteController;
import shop_management.Models.Dettes;

public class DettesSelecte {
 private JTable debtTable;
    private final DetteController detteController;

    public DettesSelecte() {
        this.detteController = new DetteController();
    }

    public void addDebtTableContent(JPanel contentPanel) {
        // Effacer le contenu précédent du contentPanel
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();

        // Récupérer les données des dettes
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Montant à payer", "Date Dette", "Date Payement", "Numero de Facture"}, 0);

        for (Dettes dette : detteController.listeDettes()) {
            // Formatter l'ID de facture avec des zéros
            String formattedIdFacture = formatFactureId(dette.getIdFacture());
            tableModel.addRow(new Object[]{
                dette.getMontantAPayer(),
                dette.getDatePrendsDette(),
                dette.getDateDePayerdette(),
                formattedIdFacture
            });
        }

        // Créer le tableau
        debtTable = new JTable(tableModel);

        // Ajouter le tableau dans un JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(debtTable);

        // Ajouter le JScrollPane au panel
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Réactualiser l'affichage
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Méthode pour formater l'ID de facture avec des zéros
    private String formatFactureId(int idFacture) {
        return String.format("%05d", idFacture); // Ajoute des zéros pour un total de 4 chiffres
    }
}
