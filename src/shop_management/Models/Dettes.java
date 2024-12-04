package shop_management.Models;

import java.util.Date;


public class Dettes {
   private int idDette;
   private double montantAPayer;
   private Date datePrendsDette;
   private Date dateDePayerdette;
   private int idFacture;

    public Dettes(int idDette, double montantAPayer, Date datePrendsDette, Date dateDePayerdette, int idFacture) {
        this.idDette = idDette;
        this.montantAPayer = montantAPayer;
        this.datePrendsDette = datePrendsDette;
        this.dateDePayerdette = dateDePayerdette;
        this.idFacture = idFacture;
    }

    public Dettes(double montantAPayer, Date datePrendsDette, Date dateDePayerdette, int idFacture) {
        this.montantAPayer = montantAPayer;
        this.datePrendsDette = datePrendsDette;
        this.dateDePayerdette = dateDePayerdette;
        this.idFacture = idFacture;
    }

    
    public Dettes() {
    }

    public int getIdDette() {
        return idDette;
    }

    public void setIdDette(int idDette) {
        this.idDette = idDette;
    }

    public double getMontantAPayer() {
        return montantAPayer;
    }

    public void setMontantAPayer(double montantAPayer) {
        this.montantAPayer = montantAPayer;
    }

    public Date getDatePrendsDette() {
        return datePrendsDette;
    }

    public void setDatePrendsDette(Date datePrendsDette) {
        this.datePrendsDette = datePrendsDette;
    }

    public Date getDateDePayerdette() {
        return dateDePayerdette;
    }

    public void setDateDePayerdette(Date dateDePayerdette) {
        this.dateDePayerdette = dateDePayerdette;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public int setIdFacture() {
       
       return 0;
       
    }
   
}
