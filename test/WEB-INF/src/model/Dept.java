package etu001982.framework.modele;
import etu001982.framework.myAnnotations.Url;
import etu001982.framework.myAnnotations.UrlParam;
import etu001982.framework.myAnnotations.Authentification;
import etu001982.framework.Modelview.*;
import java.util.Date;
@scope(singleton=true)
public class Dept {
    public Dept() {}
    @Url(name = "sprint8")
    public ModelView save(@UrlParam(name = "anarana") String anarana,
                          @UrlParam(name = "taona") int taona,
                          @UrlParam(name = "daty") Date daty,
                          @UrlParam(name = "karama") double karama) {
        // Créer une instance de ModelView pour retourner les données sauvegardées
        ModelView modelView = new ModelView("save.jsp");
        // Ajouter les données sauvegardées à l'attribut "data" de ModelView
        modelView.addItem("anarana", anarana);
        modelView.addItem("taona", taona);
        modelView.addItem("daty", daty);
        modelView.addItem("karama", karama);
        return modelView;
    }
        @Url(name = "sprint9")
    public ModelView saveFile(@UrlParam(name = "name") String name,
                          @UrlParam(name = "file") UploadFile file 
                        ){
        ArrayList<Dept> liste=new ArrayList<Dept>(null);
        Dept dept =new Dept(name,file);
        liste.add(dept);
        // Créer une instance de ModelView pour retourner les données sauvegardées
        ModelView modelView = new ModelView("saveFile.jsp");
        modelView.addItem("name",liste);
        return modelView;
    }
    @Url("sprint10")
    public ModelView myMethodId(@Argument("nom") String nom, @Argument("prenom") String prenom,@Argument("age") int age,@Argument("date") Date date,@Argument("lieu") String lieu,@Argument("poids") double poids,@Argument("time") Time time) {
        String String="SaveSingleton.jsp";
       ModelView m=new ModelView(String);
       ArrayList<Dept> olona=new ArrayList<Dept>();
       Dept user=new Dept(this.getnom(),this.getprenom(),this.getage(),this.getdate(),this.getlieu(),this.getpoids(),this.gettime());
       olona.add(user);
       m.add("val",olona);
        return m;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public UploadFile getFile() {
        return file;
    }
    public void setFile(UploadFile file) {
        this.file = file;
    }
}
