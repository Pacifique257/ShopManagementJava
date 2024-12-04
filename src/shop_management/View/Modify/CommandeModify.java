package shop_management.View.Modify;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import shop_management.Controller.CommandeController;
import shop_management.Models.Commandes;

public class CommandeModify {

    private JTextField dateCommandeField;
    private JTextField quantiteCommandeField;
    private JButton modifyButton;
    private JButton deleteButton;
    private JTable commandeTable;
    private CommandeController controller;
    private JPanel panel;

    public CommandeModify() {
        controller = new CommandeController();
        initializeFields();
    }

    public CommandeModify(int idCommande) {
        controller = new CommandeController();
        initializeFields();
        trouverCommandesParId(idCommande);
    }

    private void initializeFields() {
        dateCommandeField = new JTextField();
        quantiteCommandeField = new JTextField();
    }

    public void CommandeModifyContent(JPanel panel) {
        this.panel = panel;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        contentPanel.add(createLabel("Date de la commande :"));
        dateCommandeField = createTextField();
        contentPanel.add(dateCommandeField);

        contentPanel.add(createLabel("Quantité :"));
        quantiteCommandeField = createTextField();
        contentPanel.add(quantiteCommandeField);

        panel.add(contentPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        modifyButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        modifyButton.addActionListener(e -> modifyCommandeAction());
        deleteButton.addActionListener(e -> deleteCommandeAction());

        updateTable(controller.listeCommandes());
        panel.revalidate();
        panel.repaint();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLUE);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
        textField.setBorder(border);
        return textField;
    }

    private void modifyCommandeAction() {
        int selectedRow = commandeTable.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                // Récupérer l'ID de la commande
                Object idValue = commandeTable.getValueAt(selectedRow, 0);
                int idCommande = idValue instanceof Integer ? (Integer) idValue : Integer.parseInt(idValue.toString());

                // Récupérer et valider la date de commande
                String dateCommandeText = dateCommandeField.getText().trim();
                if (dateCommandeText.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "La date de commande ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Convertir la date (supposons un format requis "yyyy-MM-dd")
                Date dateCommande;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateCommande = dateFormat.parse(dateCommandeText);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel, "Veuillez saisir une date valide au format AAAA-MM-JJ.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Récupérer et valider la quantité
                double quantiteCommande;
                try {
                    quantiteCommande = Double.parseDouble(quantiteCommandeField.getText().trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(panel, "Veuillez saisir une quantité valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Construire l'objet Commandes avec les nouvelles valeurs
                Commandes modifiedCommande = new Commandes(idCommande, dateCommande, quantiteCommande);

                // Appeler la méthode du contrôleur pour modifier la commande
                controller.modifierCommande(modifiedCommande);

                // Mettre à jour la table des commandes et afficher un message de succès
                updateTable(controller.listeCommandes());
                JOptionPane.showMessageDialog(panel, "Commande modifiée avec succès !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Une erreur est survenue lors de la modification : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Veuillez sélectionner une commande à modifier.", "Erreur", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteCommandeAction() {
        int selectedRow = commandeTable.getSelectedRow();
        if (selectedRow >= 0) {
            int idCommande = (int) commandeTable.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(panel, "Êtes-vous sûr de vouloir supprimer cette commande ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                controller.supprimerCommandes(idCommande);
                updateTable(controller.listeCommandes());
                JOptionPane.showMessageDialog(panel, "Commande supprimée avec succès !");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Veuillez sélectionner une commande à supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateTable(List<Commandes> commandes) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Commande", "Date de la commande", "Quantité"}, 0);

        for (Commandes commande : commandes) {
            model.addRow(new Object[]{commande.getIdCommande(), commande.getDateCommande(), commande.getQuantiteCommande()});
        }

        if (commandeTable == null) {
            commandeTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(commandeTable);
            panel.add(scrollPane, BorderLayout.CENTER);
        } else {
            commandeTable.setModel(model);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void trouverCommandesParId(int idCommande) {
        try {
            // Vérification de l'ID
            if (idCommande <= 0) {
                JOptionPane.showMessageDialog(panel, "L'ID de commande est invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Récupérer la commande via le contrôleur
            Commandes commande = controller.trouverCommandesParId(idCommande);

            if (commande != null) {
                // Formater et afficher la date de commande
                Date dateCommande = commande.getDateCommande();
                if (dateCommande != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateCommandeField.setText(dateFormat.format(dateCommande));
                } else {
                    dateCommandeField.setText("");
                    JOptionPane.showMessageDialog(panel, "Date de commande absente.", "Information", JOptionPane.WARNING_MESSAGE);
                }

                // Afficher la quantité de commande
                quantiteCommandeField.setText(String.valueOf(commande.getQuantiteCommande()));
            } else {
                JOptionPane.showMessageDialog(panel, "Commande non trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "L'ID de commande est invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Une erreur est survenue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
