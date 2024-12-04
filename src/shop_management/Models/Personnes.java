package shop_management.Models;



public class Personnes {

    private int idPersonne;
    private String fullName;  // Correction du nom
    private String personAdress;
    private String email;
    private String phoneNumber;

    public Personnes(int idPersonne, String fullName, String personAdress, String email, String phoneNumber) {
        this.idPersonne = idPersonne;
        this.fullName = fullName;
        this.personAdress = personAdress;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Personnes(String fullName, String personAdress, String email, String phoneNumber) {
        this.fullName = fullName;
        this.personAdress = personAdress;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Personnes(int idPersonne, String fullName) {
        this.idPersonne = idPersonne;
        this.fullName = fullName;
    }

    public Personnes() {
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPersonAdress() {
        return personAdress;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPersonAdress(String personAdress) {
        this.personAdress = personAdress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return fullName;
    }

    
}
