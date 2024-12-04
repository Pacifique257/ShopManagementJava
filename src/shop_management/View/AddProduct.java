package shop_management.View;

import java.sql.Connection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import shop_management.Controller.PersonneController;
import shop_management.Controller.ProductController;

import shop_management.Models.Personnes;
import shop_management.Models.Produits;

public class AddProduct extends JPanel {

    private JTable productTable;

    public JTextField nameProductField;
    public JTextField priceTextField;
    public JTextField quantityTextField;
    public JTextField fullNameField;
    public JTextField adresseField;
    public JTextField adresseEmailField;
    public JTextField telephoneField;
    public JComboBox<Personnes> clientComboBox;

    private Connection connection;

    public void addProductContent(JPanel panel) {
        // Effacer le contenu précédent du panel
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        // Créer les nouveaux composants
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(12, 2, 10, 10)); // 2 colonnes, 12 lignes
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Titre du formulaire
        JLabel titleLabel = new JLabel("Ajouter un Produit");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Centrer le titre

        JLabel nameProductLabel = new JLabel("Nom du produit:");
        nameProductLabel.setForeground(Color.BLUE);
        nameProductField = new JTextField();
        nameProductField.setPreferredSize(new Dimension(200, 30));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
        nameProductField.setBorder(border);
        contentPanel.add(nameProductLabel);
        contentPanel.add(nameProductField);

        JLabel priceLabel = new JLabel("Prix du produit:");
        priceLabel.setForeground(Color.BLUE);
        priceTextField = new JTextField();
        priceTextField.setPreferredSize(new Dimension(200, 30));
        priceTextField.setBorder(border);
        contentPanel.add(priceLabel);
        contentPanel.add(priceTextField);

        JLabel quantiteLabel = new JLabel("Quantité:");
        quantiteLabel.setForeground(Color.BLUE);
        quantityTextField = new JTextField();
        quantityTextField.setPreferredSize(new Dimension(200, 30));
        quantityTextField.setBorder(border);
        contentPanel.add(quantiteLabel);
        contentPanel.add(quantityTextField);

        // ComboBox pour le choix du fournisseur
        JLabel clientLabel = new JLabel("Sélectionner un fournisseur:");
        clientLabel.setForeground(Color.BLUE);
        clientComboBox = new JComboBox<>();
        clientComboBox.setPreferredSize(new Dimension(200, 30));
        clientComboBox.setBorder(border);

        // Récupérer la liste des fournisseurs depuis la base de données et les ajouter à la ComboBox
        PersonneController personneController = new PersonneController();
        List<Personnes> fournisseurs = personneController.getAllFournisseurs();
        for (Personnes fournisseur : fournisseurs) {
            clientComboBox.addItem(fournisseur);
        }

        contentPanel.add(clientLabel);
        contentPanel.add(clientComboBox);

        JButton newClientButton = new JButton("Nouveau fournisseur");
        contentPanel.add(new JLabel()); // espace vide
        contentPanel.add(newClientButton);

        // Champs pour saisir un nouveau fournisseur si nécessaire
        JLabel fullNameLabel = new JLabel("Nom complet:");
        fullNameLabel.setVisible(false);
        fullNameLabel.setForeground(Color.BLUE);
        fullNameField = new JTextField();
        fullNameField.setVisible(false); // Masqué par défaut
        fullNameField.setPreferredSize(new Dimension(200, 30));
        fullNameField.setBorder(border);
        contentPanel.add(fullNameLabel);
        contentPanel.add(fullNameField);

        JLabel adresseLabel = new JLabel("Adresse:");
        adresseLabel.setVisible(false);
        adresseLabel.setForeground(Color.BLUE);
        adresseField = new JTextField();
        adresseField.setVisible(false);
        adresseField.setPreferredSize(new Dimension(200, 30));
        adresseField.setBorder(border);
        contentPanel.add(adresseLabel);
        contentPanel.add(adresseField);

        JLabel telephoneLabel = new JLabel("Numéro de téléphone:");
        telephoneLabel.setVisible(false);
        telephoneLabel.setForeground(Color.BLUE);
        telephoneField = new JTextField();
        telephoneField.setVisible(false);
        telephoneField.setPreferredSize(new Dimension(200, 30));
        telephoneField.setBorder(border);
        contentPanel.add(telephoneLabel);
        contentPanel.add(telephoneField);

        JLabel adresseEmailLabel = new JLabel("Adresse Email:");
        adresseEmailLabel.setVisible(false);
        adresseEmailLabel.setForeground(Color.BLUE);
        adresseEmailField = new JTextField();
        adresseEmailField.setVisible(false);
        adresseEmailField.setPreferredSize(new Dimension(200, 30));
        adresseEmailField.setBorder(border);
        contentPanel.add(adresseEmailLabel);
        contentPanel.add(adresseEmailField);

        // Gestion du bouton "Nouveau fournisseur"
        newClientButton.addActionListener(e -> {
            fullNameLabel.setVisible(true);
            adresseLabel.setVisible(true);
            telephoneLabel.setVisible(true);
            adresseEmailLabel.setVisible(true);
            fullNameField.setVisible(true);
            adresseField.setVisible(true);
            adresseEmailField.setVisible(true);
            telephoneField.setVisible(true);
            panel.revalidate();
            panel.repaint();
        });

        // Bouton de soumission
        JButton submitButton = new JButton("Ajouter");
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.WHITE);
        contentPanel.add(new JPanel()); // espace vide
        contentPanel.add(submitButton);

        // Ajout du listener au bouton de soumission
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        panel.add(mainPanel, BorderLayout.CENTER);
    }

    private void saveData() {
        try {
            // Récupérer les valeurs des champs pour le produit
            String prodName = nameProductField.getText();
            Double prixUnit = Double.parseDouble(priceTextField.getText());
            Double quantiteProd = Double.parseDouble(quantityTextField.getText());

            // Vérifier si un fournisseur est sélectionné dans la ComboBox
            Personnes selectedPerson = (Personnes) clientComboBox.getSelectedItem();

            // Si aucun fournisseur n'est sélectionné, vérifier les champs pour un nouveau fournisseur
            if (selectedPerson == null) {
                String fullName = fullNameField.getText().trim();
                String personAdress = adresseField.getText().trim();
                String email = adresseEmailField.getText().trim();
                String phoneNumber = telephoneField.getText().trim();

                // Vérifier que les informations pour un nouveau fournisseur sont fournies
                if (fullName.isEmpty() || personAdress.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Veuillez saisir les informations du nouveau fournisseur.");
                    return;
                }

                // Créer et sauvegarder le nouveau fournisseur dans la base de données
                Personnes newPerson = new Personnes(fullName, personAdress, email, phoneNumber);
                PersonneController personneController = new PersonneController();
                personneController.ajouterPersonne(newPerson); // Ajouter la personne dans la base de données

                // Attendre que l'ID de la personne soit mis à jour dans l'objet
                selectedPerson = newPerson;

                JOptionPane.showMessageDialog(this, "Nouveau fournisseur ajouté avec succès.");
            }

            int idPersonne = selectedPerson.getIdPersonne();

            // Créer l'objet produit avec les informations récupérées
            Produits produit = new Produits(prodName, prixUnit, quantiteProd);
            ProductController productController = new ProductController();
            productController.ajouterProduit(produit, idPersonne);

            JOptionPane.showMessageDialog(this, "Produit et Fournisseur enregistrés avec succès.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement des données: " + ex.getMessage());
        }
    }

    public void modifierProduit(List<Produits> produits) {
        String[] columnNames = {"ID Produit", "Nom", "Prix", "Quantité", "Modifier", "Supprimer"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Produits produit : produits) {
            Object[] row = {
                produit.getIdProduct(),
                produit.getProdName(),
                produit.getPrixUnit(),
                produit.getQuantiteProd(),
                "Modifier",
                "Supprimer"
            };
            model.addRow(row);
        }
        productTable.setModel(model);
    }

}
