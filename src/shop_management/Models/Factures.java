package shop_management.Models;

import java.util.Date;

public class Factures {

    private int idFacture;
    private Date dateFacture;
    private Double montantTotal;
    private int idCommande;
    public int getIdFacture;
    private double quantiteCommande;

    public Factures(int idFacture, Date dateFacture, Double montantTotal, int idCommande) {
        this.idFacture = idFacture;
        this.dateFacture = dateFacture;
        this.montantTotal = montantTotal;
        this.idCommande = idCommande;
    }

    public Factures() {

    }

    public Factures(int idCommande) {
        int idCommande1 = this.idCommande;
    }

    public Factures(Date dateFacture, Double montantTotal, int idCommande) {
        this.dateFacture = dateFacture;
        this.montantTotal = montantTotal;
        this.idCommande = idCommande;
    }

    public Factures(Double montantAPayer, Date datePrendsDette, Date dateDePayerdette, int idFacture) {

    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public Double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idPersonne) {
        this.idCommande = idPersonne;
    }

    public int setIdCommande() {

        return 0;

    }

    public int getIdProduit() {

        return 0;

    }

    @Override
    public String toString() {
        return Integer.toString(idFacture);
    }

    public double getQuantiteCommande() {
        return quantiteCommande;
    }

    public void setQuantiteCommande(double quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
    }

}
