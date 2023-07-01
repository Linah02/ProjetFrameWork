package etu001982.framework.servlet;
import java.io.*;
import java.util.HashMap;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map.Entry;
import etu001982.framework.myAnnotations.Url;
import etu001982.framework.Mapping;
import etu001982.framework.Modelview.ModelView;
public class FrontServlet extends HttpServlet{
     static HashMap<String,etu001982.framework.Mapping> MappingUrls;
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req,res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req,res);
    }
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        PrintWriter out=res.getWriter();
        out.println("null");
        try {
///HashMap
            for (Map.Entry<String, etu001982.framework.Mapping> entry : MappingUrls.entrySet()) {
                out.println("\tAnnotation : \"" + entry.getKey() + "\"");
                out.println("\tClass:" + entry.getValue().getClassName());
                out.println("\tMethod:" + entry.getValue().getMethod());
                out.println("\n\n\n");
            }
/// Display URL
                String requestUrl = req.getRequestURL().toString();
                String host = req.getHeader("Host");
                requestUrl = requestUrl.split("//")[1].replace(host, "");
                out.print(requestUrl);
                
                requestUrl = requestUrl.split("//")[1].replace(host, "");
                //out.print(requestUrl);         
// //HashMap
           // out.println(MappingUrls.entrySet().isEmpty());

            for (Map.Entry<String, Mapping> entry : MappingUrls.entrySet()) {
                String key = entry.getKey();
                Mapping mapping = entry.getValue();
                // out.println(key);
                // out.println(mapping);
                
                if (key.compareTo(page) == 0) {
                    

                       // out.print(mapping.getClassName());
                        Class<?> class1 = Class.forName(packages+"."+mapping.getClassName());
               // out.print(mapping.getMethod());
               out.print("n");
                        
                        //out.print(packages+"."+mapping.getClassName());
                  
                        out.print(key);
                        out.print("\n");
                        out.print(page);
                        out.print("\n");

                        //Object object = class1.newInstance();
                        Object object = class1.getDeclaredConstructor().newInstance();

               

                        //Object object = class1.newInstance();
                        Method method = object.getClass().getMethod(mapping.getMethod());
                         // Itérer sur chaque paire clé-valeur de l'HashMap
                        // ModelView modelViews = (ModelView) method.invoke(object);
                         // // Récupérer les valeurs de l'attribut "data" de ModelView
                        ModelView modelView = (ModelView) method.invoke(object);
                       // String modelString = "save.jsp"; // Ajouter le préfixe "/" pour indiquer le chemin absolu
                       HashMap<String, Object> data = modelView.getData();
             
                       for (Map.Entry<String, Object> entr : data.entrySet()) {
                           String ke = entr.getKey();
                           Object value = entr.getValue();

                           // Ajouter chaque paire clé-valeur à la requête
                           req.setAttribute(ke, value);
                       }
    
                       String modelString =modelView.getView();
                        out.print(modelView.getView());
                        out.print("\n");
                        out.print(modelString);
                        out.print("\n");
        
                        try {
                            RequestDispatcher dispatch = req.getRequestDispatcher(modelString);
                            dispatch.forward(req, res);
                        } catch (Exception e) {
                            out.print(e.getMessage());
                        }

                }
                
            }
            } catch (Exception e) {
                out.print(e.getMessage());
                e.printStackTrace();
            out.println("null");

        }
    }
    /// INIT
    @Override
    public void init() throws ServletException {
        super.init(config);
        this.packages=getServletConfig().getInitParameter("test");
         //String path = "WEB-INF/classes/etu001982/framework/modele/";
         try {
             this.displayAnnot(packages);
           
        } catch (URISyntaxException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//GET CLASS
    public  String [] getEachClass(String path){
       // path = "Emp";
        ArrayList<String> c=new ArrayList<>();
        File modele = new File(path);
        String[] clazz = modele.list();
        for(int i=0; i<clazz.length; i++){
            //String fl=FilenameUtils.getExtension(clazz[i]);
            String [] java = clazz[i].split("[.]");
            if(java[1].equalsIgnoreCase("class")){
                c.add(java[0]);
                System.out.println(c.get(i));
            }
        }
        return c.toArray(new String[c.size()]);
    }
//GET ANNOTATION
public void displayAnnot(HashMap<String, etu001982.framework.Mapping> mapping, String path, HttpServletRequest request, HttpServletResponse response) {
    try {
        String[] classe = this.getEachClass(path);
        for (int i = 0; i < classe.length; i++) {
            String className = "classes.etu001982.framework.modele." + classe[i];
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Annotation[] annotations = method.getAnnotations();
                if (annotations.length > 0) {
                    etu001982.framework.myAnnotations.Url url = method.getAnnotation(etu001982.framework.myAnnotations.Url.class);
                    System.out.println(url.toString());
                    mapping = new HashMap<String, etu001982.framework.Mapping>();
                    mapping.put(url.name(), new etu001982.framework.Mapping(classe[i], method.getName()));
                    ModelView modelView = (ModelView) method.invoke(clazz.newInstance());
                    String viewName = modelView.getView();
                    String jspPath = "/src/view/" + viewName;
                    ServletContext context = request.getServletContext();
                    if (context.getResource(jspPath) != null) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
                        dispatcher.forward(request, response);
                        return;
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "La vue " + viewName + " n'existe pas.");
                        return;
                    }
                }
            }
        }
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {
        ex.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue lors de la recherche de la vue.");
    } catch (IOException ex) {
        ex.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue lors de la redirection vers la vue.");
    }
}

    public static void main(String[] args) {
     new FrontServlet().getEachClass("classes/etu001982/framework/modele/");
     new FrontServlet().displayAnnot(MappingUrls,"classes/etu001982/framework/modele/");
    }
}
