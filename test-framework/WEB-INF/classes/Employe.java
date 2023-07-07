package modele;
import etu1831.framework.ModelView;
import etu1831.framework.Url;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Employe {
    int id;
    String nom;
    String prenom;
    Date date_de_naissance;
    public Employe(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    public Employe() {
    }
    public Employe(int id, String nom, String prenom, Date date_de_naissance) {
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);;
        this.setDate_de_naissance(date_de_naissance);;
    }
    @Url(method = "emp-jsp")
    public ModelView methodeAAnnoter(){
        ModelView modelView = new ModelView("employe.jsp");
        return modelView;
    }
    @Url(method = "get-emp")
    public ModelView getAllEmploye(){
        ModelView modelView = new ModelView("listeEmp.jsp");
        List<Employe> employes = new ArrayList<>();
        Employe employe1 = new Employe(1,"Jeon", "JK", new Date(1997, 7, 1));
        Employe employe2 = new Employe(2,"Wang", "Jackson", new Date(1994, 3, 26));
        Employe employe3 = new Employe(3,"Tuan", "Mark", new Date(1995, 12, 30));  
        employes.add(employe1);
        employes.add(employe2);
        employes.add(employe3);
        for (int i = 0; i < employes.size(); i++) {
            System.out.println(employes.get(i).getNom());
        }
        modelView.addItem("allEmploye", employes); 
        return modelView;
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
