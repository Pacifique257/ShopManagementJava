package shop_management.View.Modify;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import shop_management.Models.Produits;
import shop_management.Controller.ProductController;

public class ProductModify {

    // Champs déclarés
    private JTextField nameProductField;
    private JTextField priceTextField;
    private JTextField quantityTextField;
    private JButton modifyButton;
    private JButton deleteButton;
    private JTable productTable;
    private ProductController controller;
    private JPanel panel;

    // Constructeur par défaut
    public ProductModify() {
        controller = new ProductController();
        initializeFields(); // Initialisation des champs
    }

    // Constructeur avec ID produit
    public ProductModify(int idProduit) {
        controller = new ProductController();
        initializeFields(); // Initialisation des champs
        trouverProduitParId(idProduit); // Charger le produit
    }

    // Méthode d'initialisation des champs
    private void initializeFields() {
        nameProductField = new JTextField();
        priceTextField = new JTextField();
        quantityTextField = new JTextField();
    }

    public void ProductModifyContent(JPanel panel, Runnable onSaveCallback) {
         ProductModifyContent(panel);
    }

    public void ProductModifyContent(JPanel panel) {
        panel.setLayout(new GridLayout(4, 2));
        this.panel = panel;
        // Effacer le contenu précédent du panel
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        // Créer le panneau principal
        JPanel contentPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        // Ajouter les champs au formulaire
        contentPanel.add(createLabel("Nom du produit:"));
        nameProductField = createTextField();
        contentPanel.add(nameProductField);
        contentPanel.add(createLabel("Prix du produit:"));
        priceTextField = createTextField();
        contentPanel.add(priceTextField);
        contentPanel.add(createLabel("Quantité:"));
        quantityTextField = createTextField();
        contentPanel.add(quantityTextField);
        // Ajouter le formulaire au panel principal
        panel.add(contentPanel, BorderLayout.NORTH);
        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel();
        modifyButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // Actions des boutons
        modifyButton.addActionListener(e -> modifyProductAction());
        deleteButton.addActionListener(e -> deleteProductAction());
        // Charger et afficher la table des produits
        updateTable(controller.listeProduits());
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

    private void modifyProductAction() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int idProduct = (int) productTable.getValueAt(selectedRow, 0);
                String prodName = nameProductField.getText().trim();
                double prixUnit = Double.parseDouble(priceTextField.getText().trim());
                double quantiteProd = Double.parseDouble(quantityTextField.getText().trim());

                if (prodName.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Le nom du produit ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Produits modifiedProduct = new Produits(idProduct, prodName, prixUnit, quantiteProd);
                controller.modifierProduit(modifiedProduct);
                updateTable(controller.listeProduits());
                JOptionPane.showMessageDialog(panel, "Produit modifié avec succès !");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Veuillez saisir des valeurs valides pour le prix et la quantité.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Veuillez sélectionner un produit à modifier.", "Erreur", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteProductAction() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            int idProduct = (int) productTable.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(panel, "Êtes-vous sûr de vouloir supprimer ce produit ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                controller.supprimerProduit(idProduct);
                updateTable(controller.listeProduits());
                JOptionPane.showMessageDialog(panel, "Produit supprimé avec succès !");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Veuillez sélectionner un produit à supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateTable(List<Produits> produits) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Produit", "Nom", "Prix Unitaire", "Quantité"}, 0);

        for (Produits produit : produits) {
            model.addRow(new Object[]{produit.getIdProduct(), produit.getProdName(), produit.getPrixUnit(), produit.getQuantiteProd()});
        }

        if (productTable == null) {
            productTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(productTable);
            panel.add(scrollPane, BorderLayout.CENTER);
        } else {
            productTable.setModel(model);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void trouverProduitParId(int idProduit) {
        Produits produit = controller.trouverProduitParId(idProduit);
        if (produit != null) {
            nameProductField.setText(produit.getProdName());
            priceTextField.setText(String.valueOf(produit.getPrixUnit()));
            quantityTextField.setText(String.valueOf(produit.getQuantiteProd()));
        } else {
            JOptionPane.showMessageDialog(panel, "Produit non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

   
}
