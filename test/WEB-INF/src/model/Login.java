/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu001982.framework.Auth;
import etu001982.framework.myAnnotations.Url;
import etu001982.framework.myAnnotations.UrlParam;
import etu001982.framework.myAnnotations.myAnnotations.Authentification;
import etu001982.framework.Modelview.*;

 
 import java.sql.Date;
 import java.sql.Time;
 import java.util.ArrayList;
 
 public class Login{
     String nom;
     String mdp;
 
    public String getNom() {
        return nom;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public String getMdp() {
        return mdp;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
     
    @Url("connexion")
    public ModelView Connected() {
        String view="acceuil.jsp";
        ModelView v=new ModelView(view);
        boolean  value=true;
        String name=this.nom;
        v.addAuthenf("isconnected",value);
        v.addAuthenf("profil",name);
       return v;
    }
    @Url("session")
    public ModelView Json() {
        String view="json.jsp";
        ModelView v=new ModelView(view);
        boolean  value=true;
        String name=this.nom;
        v.addAuthenf("session",value);
       return v;
    }
    
 }
 //economie memoire positif singleton
 //sprint8 bis
 //css / fotsin na /* .do na .action   ex @getall.do am fonction findAll
 //filter
 //sprint9
 
 