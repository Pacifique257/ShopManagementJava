//package shop_management.View;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.Border;
//import javax.swing.border.EmptyBorder;
//
//public class AddCommandes {
//    
//      public static void addCommadesContent(JPanel panel) {
//        // Effacer le contenu précédent du panel
//        panel.removeAll();
//        panel.revalidate();
//        panel.repaint();
//
//        // Créer les nouveaux composants
//        JPanel contentPanel = new JPanel();
//        contentPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 2 colonnes, 4 lignes
//        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//
//        // Titre du formulaire
//        JLabel titleLabel = new JLabel("Passe une commande");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
//        titleLabel.setForeground(Color.BLUE);
//        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Centrer le titre
//
//        // Champ de date de la commade
//        JLabel dateCommandetLabel = new JLabel("Date de la commande:");
//        dateCommandetLabel.setForeground(Color.BLUE);
//        JTextField dateCommandetLabelField = new JTextField();
//        dateCommandetLabelField.setPreferredSize(new Dimension(200, 30));
//        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
//        dateCommandetLabelField.setBorder(border);
//        contentPanel.add(dateCommandetLabel);
//        contentPanel.add(dateCommandetLabelField);
//
//        // Champ de la quantite commande
//        JLabel quantiteCommandeLabel = new JLabel("Quantite commande:");
//        quantiteCommandeLabel.setForeground(Color.BLUE);
//        JTextField quantiteCommandTextField = new JTextField();
//        quantiteCommandTextField.setPreferredSize(new Dimension(200, 30));
//        quantiteCommandTextField.setBorder(border);
//        contentPanel.add(quantiteCommandeLabel);
//        contentPanel.add(quantiteCommandTextField);
//
//     
//        // Bouton de soumission
//        JButton submitButton = new JButton("Ajouter");
//        submitButton.setBackground(Color.BLUE);
//        submitButton.setForeground(Color.GREEN);
//        contentPanel.add(new JPanel()); // Espace vide pour centrer le bouton
//        contentPanel.add(submitButton);
//
//        // Créer un panel principal pour le titre et le formulaire
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.add(titleLabel, BorderLayout.NORTH);
//        mainPanel.add(contentPanel, BorderLayout.CENTER);
//        submitButton.addActionListener((ActionEvent e) -> {
//           // try{
//            // Récupérer les valeurs des champs
////           String prodName = nameProductField.getText();
////            float prixUnit = Float.parseFloat (priceTextField.getText());
////            float quantiteProd =  Float.parseFloat (quantityTextField.getText());
////            Produits produit = new Produits(nomProduit, prixUnitaire, quantiteProduit);
////            boolean success = ProductController.addProduct(produit);
//            
////              if (success) {
////                    JOptionPane.showMessageDialog(this, "Exemplaire ajouté avec succès");
////                    dispose();
////                } else {
////                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de l'exemplaire", "Erreur", JOptionPane.ERROR_MESSAGE);
////                }
////           } catch (NumberFormatException ex) {
////                JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs numériques valides pour les champs ID du livre et Numéro du livre.", "Erreur de format", JOptionPane.ERROR_MESSAGE);
////            } catch (Exception ex) {
////                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
////            }
//
////            // Afficher les valeurs dans la console
////            System.out.println("No du produit: " + prodName);
////            System.out.println("prix du produt: " + prixUnit);
////            System.out.println("Quantité du produit: " + quantiteProd);
////
////            // Optionnel : Effacer les champs après l'envoi
////            nameProductField.setText("");
////            priceTextField.setText("");
////            quantityTextField.setText("");
////            }
////        catch (NumberFormatException ex) {
////                JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs numériques valides pour les champs ID du livre et Numéro du livre.", "Erreur de format", JOptionPane.ERROR_MESSAGE);
////            } catch (Exception ex) {
////                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
////            }
//        });
//
//        // Ajouter le panel principal au panel de l'application
//        panel.add(mainPanel, BorderLayout.CENTER);
//    }
//}
