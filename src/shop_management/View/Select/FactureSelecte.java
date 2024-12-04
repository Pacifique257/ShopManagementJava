
package shop_management.View.Select;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shop_management.Controller.FactureController;


public class FactureSelecte {
   private JTable facturesTable;
    private final FactureController facturesController;

    public FactureSelecte() {
        this.facturesController = new FactureController();
    }

    public void addFacturesContent(JPanel contentPanel) {
        // Effacer le contenu précédent du panel
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();

        // Récupérer le modèle de table
        DefaultTableModel tableModel = facturesController.getFacturesTableModel();

        // Créer la table
        facturesTable = new JTable(tableModel);

        // Ajouter la table dans un JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(facturesTable);

        // Ajouter le JScrollPane au panel
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Réactualiser l'affichage
        contentPanel.revalidate();
        contentPanel.repaint();
    }   
}
