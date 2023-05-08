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
            } catch (Exception e) {
                out.print(e.getMessage());
                e.printStackTrace();
            out.println("null");

        }
    }
    /// INIT
    @Override
    public void init() throws ServletException {
        MappingUrls = new  HashMap<String,etu001982.framework.Mapping>();
        String path = "classes/etu001982/framework/modele/";
        displayAnnot(MappingUrls,path);
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
