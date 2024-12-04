//
//package shop_management.View;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.Border;
//import javax.swing.border.EmptyBorder;
//
//public class AddCustomers {
//    public static void addCustomersContent(JPanel panel) {
//        // Effacer le contenu précédent du panel
//        panel.removeAll();
//        panel.revalidate();
//        panel.repaint();
//
//        // Créer les nouveaux composants
// JPanel contentPanel = new JPanel();
//        contentPanel.setLayout(new GridLayout(7, 2, 10, 10)); // 2 colonnes, 4 lignes
//        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//
//        // Titre du formulaire
//        JLabel titleLabel = new JLabel("Ajouter un Client");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        titleLabel.setForeground(Color.BLUE);
//        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Centrer le titre
//
//        // Champ de nom du client
//        JLabel nomLabel = new JLabel("Nom du client:");
//        nomLabel.setForeground(Color.BLUE);
//        JTextField nomTextField1 = new JTextField();
//        nomTextField1.setPreferredSize(new Dimension(200, 30));
//        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
//        nomTextField1.setBorder(border);
//        contentPanel.add(nomLabel);
//        contentPanel.add(nomTextField1);
//
//        // Champ de prenom du client
//        JLabel prenomLabel = new JLabel("Prénom du client:");
//        prenomLabel.setForeground(Color.BLUE);
//        JTextField prenomtextField = new JTextField();
//        prenomtextField.setPreferredSize(new Dimension(200, 30));
//        prenomtextField.setBorder(border);
//        contentPanel.add(prenomLabel);
//        contentPanel.add(prenomtextField);
//
//        // Champ d'adresse du client
//        JLabel adresseLabel = new JLabel("Adresse du client:");
//        adresseLabel.setForeground(Color.BLUE);
//        JTextField adresseTextField = new JTextField();
//        adresseTextField.setPreferredSize(new Dimension(200, 30));
//        adresseTextField.setBorder(border);
//        contentPanel.add(adresseLabel);
//        contentPanel.add(adresseTextField);
//        
//        // Champ d'adresse email du client
//        JLabel emailLabel = new JLabel("Adresse email du client:");
//        emailLabel.setForeground(Color.BLUE);
//        JTextField emailTextField = new JTextField();
//        emailTextField.setPreferredSize(new Dimension(200, 30));
//        emailTextField.setBorder(border);
//        contentPanel.add(emailLabel);
//        contentPanel.add(emailTextField);
//        
//        // Champ de numero de telephone du client
//         JLabel numeroTelephoneLabel = new JLabel("Numéro de telephone du client:");
//        numeroTelephoneLabel.setForeground(Color.BLUE);
//        JTextField numeroTelephoneTextField = new JTextField();
//        numeroTelephoneTextField.setPreferredSize(new Dimension(200, 30));
//        numeroTelephoneTextField.setBorder(border);
//        contentPanel.add(numeroTelephoneLabel);
//        contentPanel.add(numeroTelephoneTextField);
//
//        // Bouton de soumission
//        JButton submitButton = new JButton("Ajouter");
//        submitButton.setBackground(Color.BLUE);
//        submitButton.setForeground(Color.WHITE);
//        contentPanel.add(new JPanel()); // Espace vide pour centrer le bouton
//        contentPanel.add(submitButton);
//
//        // Créer un panel principal pour le titre et le formulaire
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.add(titleLabel, BorderLayout.NORTH);
//        mainPanel.add(contentPanel, BorderLayout.CENTER);
//          submitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Récupérer les valeurs des champs
////                String reference = textField1.getText();
////                String designation = textField2.getText();
////                String quantite = textField3.getText();
////
////                // Afficher les valeurs dans la console
////                System.out.println("Référence: " + reference);
////                System.out.println("Désignation: " + designation);
////                System.out.println("Quantité: " + quantite);
////
////                // Optionnel : Effacer les champs après l'envoi
////                textField1.setText("");
////                textField2.setText("");
////                textField3.setText("");
//            }
//        });
//
//        // Ajouter le panel principal au panel de l'application
//        panel.add(mainPanel, BorderLayout.CENTER);
//    }
//}
