package etu001982.framework.modele;
import etu001982.framework.myAnnotations.Url;
import etu001982.framework.Modelview.*;
public class Emp {
    String nom;
    int age;
/// GETTER & SETTER
    @Url(name = "getNom_Emp")
    public String getNom() {return nom;}
    @Url(name = "setNom_Emp")
    public void setNom(String nom) {this.nom = nom;}
    @Url(name = "getAge_Emp")
    public int getAge() {return age;}
    @Url(name = "setAge_Emp")
    public void setAge(int age) {this.age = age;}

    public Emp(String nom, int age) {
        this.setNom(nom);
        this.setAge(age);
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
}


