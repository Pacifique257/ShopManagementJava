package shop_management.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.border.Border;
import shop_management.Controller.CommandeController;
import shop_management.Controller.CommanderController;
import shop_management.Controller.FactureController;
import shop_management.Controller.PersonneController;
import shop_management.Controller.ProductController;
import shop_management.Models.Commander;
import shop_management.Models.Commandes;
import shop_management.Models.Factures;
import shop_management.Models.Personnes;
import shop_management.Models.Produits;

public class AddFactures {

    public JTextField dateFacturationField;
    public JTextField montantField;
    public JTextField fullNameField;
    public JTextField adresseField;
    public JTextField adresseEmailField;
    public JTextField dateCommandeField;
    public JTextField quantiteCommandeField;
    public JTextField phoneNumberField;
    public JComboBox<Personnes> clientComboBox;
    public JComboBox<Produits> produitComboBox;

    public void addFacturesContent(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(12, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Créer une facture");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Champs pour la date de facturation et commande
        JLabel dateFacturationLabel = new JLabel("Date de facturation:");
        dateFacturationLabel.setForeground(Color.BLUE);
        dateFacturationField = new JTextField();
        dateFacturationField.setPreferredSize(new Dimension(200, 30));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
        dateFacturationField.setBorder(border);
        contentPanel.add(dateFacturationLabel);
        contentPanel.add(dateFacturationField);

        // Champs pour le montant de la facture
        JLabel montantLabel = new JLabel("Montant à payer:");
        montantLabel.setForeground(Color.BLUE);
        montantField = new JTextField();
        montantField.setPreferredSize(new Dimension(200, 30));
        montantField.setBorder(border);
        contentPanel.add(montantLabel);
        contentPanel.add(montantField);

        // ComboBox pour le choix du client
        JLabel clientLabel = new JLabel("Sélectionner un client:");
        clientLabel.setForeground(Color.BLUE);
        clientComboBox = new JComboBox<>();
        clientComboBox.setPreferredSize(new Dimension(200, 30));
        clientComboBox.setBorder(border);
        PersonneController personneController = new PersonneController();
        List<Personnes> clients = personneController.getAllClient();
        for (Personnes client : clients) {
            clientComboBox.addItem(client);
        }

        contentPanel.add(clientLabel);
        contentPanel.add(clientComboBox);

        JButton newClientButton = new JButton("Nouveau client");
        contentPanel.add(new JLabel()); // espace vide
        contentPanel.add(newClientButton);

        // Champs pour saisir un nouveau client si nécessaire
        JLabel fullNameLabel = new JLabel("Nom complet:");
        fullNameLabel.setForeground(Color.BLUE);
        fullNameLabel.setVisible(false);
        fullNameField = new JTextField();
        fullNameField.setVisible(false); // Masqué par défaut
        fullNameField.setPreferredSize(new Dimension(200, 30));
        fullNameField.setBorder(border);
        contentPanel.add(fullNameLabel);
        contentPanel.add(fullNameField);

        JLabel adresseLabel = new JLabel("Adresse:");
        adresseLabel.setForeground(Color.BLUE);
        adresseLabel.setVisible(false);
        adresseField = new JTextField();
        adresseField.setVisible(false);
        adresseField.setPreferredSize(new Dimension(200, 30));
        adresseField.setBorder(border);
        contentPanel.add(adresseLabel);
        contentPanel.add(adresseField);

        JLabel phoneNumberLabel = new JLabel("Numéro de téléphone:");
        phoneNumberLabel.setForeground(Color.BLUE);
        phoneNumberLabel.setVisible(false);
        phoneNumberField = new JTextField();
        phoneNumberField.setVisible(false);
        phoneNumberField.setPreferredSize(new Dimension(200, 30));
        phoneNumberField.setBorder(border);
        contentPanel.add(phoneNumberLabel);
        contentPanel.add(phoneNumberField);

        JLabel adresseEmailLabel = new JLabel("Adresse Email:");
        adresseEmailLabel.setForeground(Color.BLUE);
        adresseEmailLabel.setVisible(false);
        adresseEmailField = new JTextField();
        adresseEmailField.setVisible(false);
        adresseEmailField.setPreferredSize(new Dimension(200, 30));
        adresseEmailField.setBorder(border);
        contentPanel.add(adresseEmailLabel);
        contentPanel.add(adresseEmailField);

        // Gestion du bouton "Nouveau client"
        newClientButton.addActionListener(e -> {
            fullNameLabel.setVisible(true);
            adresseLabel.setVisible(true);
            adresseEmailLabel.setVisible(true);
            phoneNumberLabel.setVisible(true);
            fullNameField.setVisible(true);
            adresseField.setVisible(true);
            adresseEmailField.setVisible(true);
            phoneNumberField.setVisible(true);
            newClientButton.setPreferredSize(new Dimension(200, 30));
            newClientButton.setBackground(Color.CYAN);
            newClientButton.setForeground(Color.BLACK);
            panel.revalidate();
            panel.repaint();
        });

        // ComboBox pour le choix du produit
        JLabel produitLabel = new JLabel("Sélectionner un produit:");
        produitLabel.setForeground(Color.BLUE);
        produitComboBox = new JComboBox<>();
        produitComboBox.setPreferredSize(new Dimension(200, 30));
        produitComboBox.setBorder(border);
        contentPanel.add(produitLabel);
        contentPanel.add(produitComboBox);
        ProductController productController = new ProductController();
        List<Produits> produits = productController.listeProduits();
        for (Produits produit : produits) {
            produitComboBox.addItem(produit);
        }

        JLabel dateCommandeLabel = new JLabel("Date de la commande:");
        dateCommandeLabel.setForeground(Color.BLUE);
        dateCommandeField = new JTextField();
        dateCommandeField.setPreferredSize(new Dimension(200, 30));
        dateCommandeField.setBorder(border);
        contentPanel.add(dateCommandeLabel);
        contentPanel.add(dateCommandeField);

        JLabel quantiteCommandeLabel = new JLabel("Quantité commandée:");
        quantiteCommandeLabel.setForeground(Color.BLUE);
        quantiteCommandeField = new JTextField();
        quantiteCommandeField.setPreferredSize(new Dimension(200, 30));
        quantiteCommandeField.setBorder(border);
        contentPanel.add(quantiteCommandeLabel);
        contentPanel.add(quantiteCommandeField);

        // Bouton de soumission
        JButton submitButton = new JButton("Ajouter");
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.GRAY);
        contentPanel.add(new JPanel());
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
            // Récupérer les valeurs des champs
            Date dateFacture = parseDateWithMultipleFormats(dateFacturationField.getText());
            Double montantTotal = Double.parseDouble(montantField.getText());

            // Utiliser la méthode parseDateWithMultipleFormats pour gérer différents formats
            Date dateCommande = parseDateWithMultipleFormats(dateCommandeField.getText());

            if (dateCommande == null || dateFacture == null) {
                JOptionPane.showMessageDialog(null, "Le format de date n'est pas valide. Veuillez réessayer.");
                return;
            }

            Double quantiteCommande = Double.parseDouble(quantiteCommandeField.getText());

            Personnes selectedPerson = (Personnes) clientComboBox.getSelectedItem();
            if (selectedPerson == null) {
                String fullName = fullNameField.getText().trim();
                String personAdress = adresseField.getText().trim();
                String email = adresseEmailField.getText().trim();
                String phoneNumber = phoneNumberField.getText().trim();

                if (fullName.isEmpty() || personAdress.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir les informations du nouveau client.");
                    return;
                }

                Personnes newPerson = new Personnes(fullName, personAdress, email, phoneNumber);
                PersonneController personneController = new PersonneController();
                personneController.ajouterClient(newPerson);
                selectedPerson = newPerson;

                JOptionPane.showMessageDialog(null, "Nouveau client ajouté avec succès.");
            }

            int idPersonne = selectedPerson.getIdPersonne();
            Commandes commande = new Commandes(dateCommande, quantiteCommande, idPersonne);
            CommandeController commandeController = new CommandeController();
            commandeController.ajouterCommande(commande, idPersonne);

            int idCommande = commande.getIdCommande();
            Factures facture = new Factures(dateFacture, montantTotal, idCommande);
            FactureController factureController = new FactureController();
            facture.setMontantTotal(montantTotal);
            factureController.ajouterFactures(facture, idPersonne);

            Produits selectedProduct = (Produits) produitComboBox.getSelectedItem();
            if (selectedProduct != null) {
                
                // Récupérer l'ID du produit
                int idProduit = selectedProduct.getIdProduct();
              

                // Récupérer l'ID de la commande (en supposant que 'commande' est déjà initialisé)
                int idCommand = commande.getIdCommande();
                
                
                System.out.println("id produit"+idProduit +"idcommende"+idCommand);
                // Créer une instance de Commander avec l'ID de la commande et du produit
                Commander commender = new Commander(idProduit,idCommand);

                // Utiliser le contrôleur pour ajouter la commande
                CommanderController commanderController = new CommanderController();
                commanderController.ajouterCommender(commender);
            } else {
                System.out.println("Erreur: Aucun produit sélectionné.");
            }

            JOptionPane.showMessageDialog(null, "client et commande enregistrés avec succès.");
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
