package modele;
import java.sql.Date;

public class Employe {
    int id;
    String nom;
    String prenom;
    Date date_de_naissance;
    public Employe(int id, String nom, String prenom, Date date_de_naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_de_naissance = date_de_naissance;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Date getDate_de_naissance() {
        return date_de_naissance;
    }
    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }
}
