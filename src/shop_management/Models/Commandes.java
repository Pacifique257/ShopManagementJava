package shop_management.Models;

import java.util.Date;

public class Commandes {

    private int idCommande;
    private Date dateCommande;
    private double quantiteCommande;
    private int idPersonne;
    private String nomPersonne;

    public Commandes(int idCommande, Date dateCommande, double quantiteCommande, int idPersonne) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.quantiteCommande = quantiteCommande;
    }

    public Commandes() {

    }

    public Commandes(Date dateCommande, double quantiteCommande, int idPersonne) {
        this.dateCommande = dateCommande;
        this.quantiteCommande = quantiteCommande;
        this.idPersonne = idPersonne;
    }

    public Commandes(int idCommande, Date dateCommande, double quantiteCommande) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.quantiteCommande = quantiteCommande;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public double getQuantiteCommande() {
        return quantiteCommande;
    }

    public void setQuantiteCommande(double quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
    }

    public Commandes(Date dateCommande, double quantiteCommande) {
        this.dateCommande = dateCommande;
        this.quantiteCommande = quantiteCommande;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public int setIdPersonne() {
        // this.idPersonne=idPersonne;
        return idPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;  // Affectation de la valeur à l'attribut de la classe
    }

    public String getNomPersonne() {

        return nomPersonne;
    }

}
