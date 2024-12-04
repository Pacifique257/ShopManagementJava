package shop_management.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import shop_management.Controller.DetteController;
import shop_management.Controller.FactureController;
import shop_management.Models.Dettes;
import shop_management.Models.Factures;

public class AddDettes {

    public JTextField montantDetteField;
    public JTextField dateReceptionTextField;
    public JTextField datePayementTextField;
    public JComboBox<Factures> factureComboBox;

    public void addDettesContent(JPanel panel) {
        // Effacer le contenu précédent du panel
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        // Créer les nouveaux composants
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10)); // 2 colonnes, 4 lignes
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Titre du formulaire
        JLabel titleLabel = new JLabel("Ajouter une dette");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Centrer le titre

        // Champ de montant à payer de la dette
        JLabel montantDetteLabel = new JLabel("Montant à payer:");
        montantDetteLabel.setForeground(Color.BLUE);
        montantDetteField = new JTextField(); // Utilisez la variable d'instance
        montantDetteField.setPreferredSize(new Dimension(200, 30));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
        montantDetteField.setBorder(border);
        contentPanel.add(montantDetteLabel);
        contentPanel.add(montantDetteField);

        // Champ de date de la réception de la dette
        JLabel dateReceptionLabel = new JLabel("Date de la réception:");
        dateReceptionLabel.setForeground(Color.BLUE);
        dateReceptionTextField = new JTextField(); // Utilisez la variable d'instance
        dateReceptionTextField.setPreferredSize(new Dimension(200, 30));
        dateReceptionTextField.setBorder(border);
        contentPanel.add(dateReceptionLabel);
        contentPanel.add(dateReceptionTextField);

        // Champ de date de paiement de la dette
        JLabel datePayementLabel = new JLabel("Date de paiement:");
        datePayementLabel.setForeground(Color.BLUE);
        datePayementTextField = new JTextField(); // Utilisez la variable d'instance
        datePayementTextField.setPreferredSize(new Dimension(200, 30));
        datePayementTextField.setBorder(border);
        contentPanel.add(datePayementLabel);
        contentPanel.add(datePayementTextField);

        // Champ de l'id de la facture
        JLabel idFactureLabel = new JLabel("Facture numéro:");
        idFactureLabel.setForeground(Color.BLUE);
        factureComboBox = new JComboBox<>();
        factureComboBox.setPreferredSize(new Dimension(200, 30));
        factureComboBox.setBorder(border);
        FactureController factureController = new FactureController();
        List<Factures> factures = factureController.listeFactures();
        for (Factures facture : factures) {
            factureComboBox.addItem(facture);
        }

        contentPanel.add(idFactureLabel);
        contentPanel.add(factureComboBox);

        // Bouton de soumission
        JButton submitButton = new JButton("Ajouter");
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.GREEN);
        contentPanel.add(new JPanel()); // Espace vide pour centrer le bouton
        contentPanel.add(submitButton);

        // Créer un panel principal pour le titre et le formulaire
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        // Ajouter le panel principal au panel de l'application
        panel.add(mainPanel, BorderLayout.CENTER);
    }

    private void saveData() {
        try {
            // Récupérer les valeurs des champs
            Double montantAPayer = Double.parseDouble(montantDetteField.getText());
            Date datePrendsDette = parseDateWithMultipleFormats(dateReceptionTextField.getText());

            // Utiliser la méthode parseDateWithMultipleFormats pour gérer différents formats
            Date dateDePayerdette = parseDateWithMultipleFormats(datePayementTextField.getText());

            if (datePrendsDette == null || dateDePayerdette == null || montantAPayer == null) {
                JOptionPane.showMessageDialog(null, "Le format de date n'est pas valide. Veuillez réessayer.");
                return;
            }

            Factures selectedFacture = (Factures) factureComboBox.getSelectedItem();
            if (selectedFacture != null) {
                // Récupérer l'ID de la facture
                int idFacture = selectedFacture.getIdFacture(); // Correction ici

                System.out.println("id Facture: " + idFacture);
                // Créer une instance de Dettes avec l'ID de la facture
                Dettes dette = new Dettes(montantAPayer, datePrendsDette, dateDePayerdette, idFacture);

                // Utiliser le contrôleur pour ajouter la dette
                DetteController detteController = new DetteController();
                detteController.ajouterDette(dette);
            } else {
                System.out.println("Erreur: Aucune facture sélectionnée.");
            }

            JOptionPane.showMessageDialog(null, "Dette enregistrée avec succès.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement des données: " + ex.getMessage());
        }
    }

    public Date parseDateWithMultipleFormats(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }

        // Liste des formats de date à essayer
        String[] dateFormats = {
            "dd/MM/yyyy", // Format jour/mois/année
            "yyyy-MM-dd", // Format année-mois-jour
            "MM-dd-yyyy" // Format mois-jour-année
        };

        for (String format : dateFormats) {
            try {
                SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
                dateFormatter.setLenient(false); // Désactive la tolérance pour les dates incorrectes
                return dateFormatter.parse(text); // Tente de convertir la chaîne en Date
            } catch (ParseException e) {
                // Ignorer et continuer avec le prochain format
            }
        }

        // Retourner null si aucun format n'est valide
        return null;
    }

}
