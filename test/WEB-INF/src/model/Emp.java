package etu001982.framework.modele;
import etu001982.framework.myAnnotations.Url;
import etu001982.framework.Modelview.*;
public class Emp {
    String nom;
    int age;
    Date dtn;
    double salaire;
/// GETTER & SETTER
    @Url(name = "getNom_Emp")
    public String getNom() {return nom;}
    @Url(name = "setNom_Emp")
    public void setNom(String nom) {this.nom = nom;}
    @Url(name = "getAge_Emp")
    public int getAge() {return age;}
    @Url(name = "setAge_Emp")
    public void setAge(int age) {this.age = age;}
    @Url(name = "getDtn_Emp")
    public Date getdtn() {return dtn;}
    @Url(name = "setDtn_Emp")
    public void setdtn(Date dtn) {this.dtn = dtn;}
    @Url(name = "getSalaire_Emp")
    public double getsalaire() {return salaire;}
    @Url(name = "setSalaire_Emp")
    public void setsalaire(double salaire) {this.salaire = salaire;}
    public Emp(String nom, int age,Date dtn,double salaire) {
        this.setnom(nom);
        this.setage(age);
        this.setdtn(dtn);
        this.setsalaire(salaire);
    }
    @Url(name="finEmp")
    public Emp findAll(){
        Emp emp = new Emp(getNom(),getAge());
        return emp;
    }
    @Url(name="view")
    public ModelView getModelView(){
        String view="test.jsp";
        ModelView Modelview=new ModelView(view);
    return Modelview;  
    }
    @Url(name="sprint6")
    public ModelView findAlls() {
        ModelView modelView = new ModelView("view.jsp");
        
        // Ajouter des éléments à l'attribut "data"
        modelView.addItem("2nd prenom", "Yvette");
        modelView.addItem("prenom", "Linah");

        
        // Autres opérations de la fonction findAll
        
        return modelView;
    }
    // Méthode pour sauvegarder les données du formulaire
    @Url(name = "sprint7")
    public ModelView save() {
        ModelView modelView = new ModelView("save_Emp.jsp");
        //Emp e=new Emp(nom, age, dtn, salaire)
        // Ajoute des données sauvegardées à l'attribut "data" de ModelView
        modelView.addItem("nom", this.getnom());
        modelView.addItem("age", this.getage());
        modelView.addItem("dtn", this.getdtn());
        modelView.addItem("salaire", this.getsalaire());
        return modelView;
    }

}


