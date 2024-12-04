package shop_management.View.Modify;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import shop_management.Models.Produits;
import shop_management.Controller.ProductController;

public class FactureModify {

    // Déclarations de champs
    private JTextField nameProductField;
    private JTextField priceTextField;
    private JTextField quantityTextField;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JTable productTable;
    private ProductController controller;

    public FactureModify() {
        controller = new ProductController(); // initialisation du contrôleur
    }

    public void FactureModifyContent(JPanel panel) {
        // Effacer le contenu précédent du panel
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        // Créer le panneau principal
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(12, 2, 10, 10)); // 2 colonnes, 12 lignes
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Titre du formulaire
        JLabel titleLabel = new JLabel("Ajouter un Produit");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Centrer le titre
        contentPanel.add(titleLabel); // Ajout du titre au panel

        // Nom du produit
        JLabel nameProductLabel = new JLabel("Nom du produit:");
        nameProductLabel.setForeground(Color.BLUE);
        nameProductField = new JTextField();
        nameProductField.setPreferredSize(new Dimension(200, 30));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
        nameProductField.setBorder(border);
        contentPanel.add(nameProductLabel);
        contentPanel.add(nameProductField);

        // Prix du produit
        JLabel priceLabel = new JLabel("Prix du produit:");
        priceLabel.setForeground(Color.BLUE);
        priceTextField = new JTextField();
        priceTextField.setPreferredSize(new Dimension(200, 30));
        priceTextField.setBorder(border);
        contentPanel.add(priceLabel);
        contentPanel.add(priceTextField);

        // Quantité du produit
        JLabel quantiteLabel = new JLabel("Quantité:");
        quantiteLabel.setForeground(Color.BLUE);
        quantityTextField = new JTextField();
        quantityTextField.setPreferredSize(new Dimension(200, 30));
        quantityTextField.setBorder(border);
        contentPanel.add(quantiteLabel);
        contentPanel.add(quantityTextField);

        // Ajout du formulaire au panel principal
        panel.add(contentPanel, BorderLayout.NORTH);

        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel();

        modifyButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Actions des boutons
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyProductAction();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProductAction();
            }
        });

        // Rafraîchir la table des produits
        updateTable(controller.listeProduits());
    }

    // Action pour ajouter un produit
    // Action pour modifier un produit
    private void modifyProductAction() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            int productId = (int) productTable.getValueAt(selectedRow, 0);
            String name = nameProductField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            double quantity = Double.parseDouble(quantityTextField.getText());

            // Modifier le produit sélectionné
            Produits modifiedProduct = new Produits(productId, name, price, quantity);
            controller.modifierProduit(modifiedProduct);

            // Rafraîchir la table des produits
            updateTable(controller.listeProduits());
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un produit à modifier.");
        }
    }

    // Action pour supprimer un produit
    private void deleteProductAction() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            int productId = (int) productTable.getValueAt(selectedRow, 0);

            // Demander une confirmation avant suppression
            int confirmation = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer ce produit ?");
            if (confirmation == JOptionPane.YES_OPTION) {
                controller.supprimerProduit(productId);

                // Rafraîchir la table des produits
                updateTable(controller.listeProduits());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un produit à supprimer.");
        }
    }

    // Méthode pour mettre à jour la table des produits
    private void updateTable(java.util.List<Produits> produits) {
        // Implémentation de l'affichage des produits dans la table
        // Vous pouvez remplacer cette partie par le code pour remplir la JTable avec la liste de produits
    }
}
