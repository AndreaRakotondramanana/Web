package modele;
import etu1831.framework.*;
public class Departement{
    String nomDeDepartement;
    int nombreEmploye;
    @Url(method = "dept-jsp")
    public ModelView myJsp(){
        ModelView modelView = new ModelView("departement.jsp");
        return modelView;
    }
    public String getNomDeDepartement() {
        return nomDeDepartement;
    }
    public void setNomDeDepartement(String nomDeDepartement) {
        this.nomDeDepartement = nomDeDepartement;
    }
    public int getNombreEmploye() {
        return nombreEmploye;
    }
    public void setNombreEmploye(int nombreEmploye) {
        this.nombreEmploye = nombreEmploye;
    }
}