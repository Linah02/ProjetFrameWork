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
        String path = "etu001982/framework/modele/";
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
    public void displayAnnot(HashMap<String,etu001982.framework.Mapping> mapping , String path){
        try {
            String [] classe = this.getEachClass(path);
            for(int i =0 ;i< classe.length; i++){
                String className  = "etu001982.framework.modele." +classe[i];
                Class<?> clazz = Class.forName(className);
                Method [] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    Annotation[] url = method.getAnnotations();
                    if(url.length > 0 ){
                        etu001982.framework.myAnnotations.Url  u = methods[i].getAnnotation(etu001982.framework.myAnnotations.Url.class);
                        System.out.println(u.toString());
                        mapping = new HashMap<String,etu001982.framework.Mapping>();            
                        mapping.put(u.name(),new etu001982.framework.Mapping(classe[i],method.getName()));
                        System.out.println(u.name()+" / "+classe[i]+" / "+methods[i].getName());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
     new FrontServlet().getEachClass("etu001982/framework/modele/");
     new FrontServlet().displayAnnot(MappingUrls,"etu001982/framework/modele/");
    }


}
