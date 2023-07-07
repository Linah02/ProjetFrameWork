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
import java.sql.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import etu001982.framework.Modelview.ModelView;
public class FrontServlet extends HttpServlet{
     static HashMap<String,etu001982.framework.Mapping> MappingUrls;
     HashMap<Class<?>, Object> ClasseSingleton=new HashMap <Class<?>, Object>();
     String packages;
     String viewsDirectory;

     public String getViewsDirectory() {
         return viewsDirectory;
     }
 
     public void setViewsDirectory(String viewsDirectory) {
         this.viewsDirectory = viewsDirectory;
     }

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
            this.displayAnnot(packages);
            /// Display URL
            String requestUrl = req.getRequestURL().toString();
            String host = req.getHeader("Host");
            requestUrl = requestUrl.split("//")[1].replace(host, "");
            out.print(requestUrl);
            
            requestUrl = requestUrl.split("//")[1].replace(host, "");
            //out.print(requestUrl);  
///HashMap
            for (Map.Entry<String, etu001982.framework.Mapping> entry : MappingUrls.entrySet()) {
                out.println("\tAnnotation : \"" + entry.getKey() + "\"");
                out.println("\tClass:" + entry.getValue().getClassName());
                out.println("\tMethod:" + entry.getValue().getMethod());
                out.println("\n\n\n");

                String key = entry.getKey();
                out.print(key);
                Mapping mapping = entry.getValue();

                if (key.compareTo(page) == 0) {
                    Class<?> class1 = Class.forName(packages + "." + mapping.getClassName());
                    Object object = this.SingletonInstances(class1);
                //   Method method = object.getClass().getMethod(mapping.getMethod());
                    Map<String, String[]> params = req.getParameterMap(); 
                // maka an'ilay parametre avy any @ JSP                    
                    object = this.getParamForm(object, params, class1); 
                //recupere les attributs de la classe
                    Field[] field = object.getClass().getDeclaredFields();
                //les transformer en tableau de string pour la comparaison
                    String[] attributs = new String[field.length];
                    for(int j=0;j<field.length;j++)
                    {
                        attributs[j] = field[j].getName();
                    }
                // Récupérer le fichier téléchargé
////SPRINT7
//  Parcourir tous les paramètres et les valeurs du formulaire
                Enumeration<String> paramNames = req.getParameterNames();
                    while (paramNames.hasMoreElements()) {
                    String paramName = paramNames.nextElement();  
                        //Verifier si le parametre fait partie des attributs de la classe 
                        for(int j=0;j<attributs.length;j++){
                            if(attributs[j].compareTo(paramName)==0){
                                String[] paramValues = req.getParameterValues(paramName);
                                Method met= object.getClass().getMethod("set"+attributs[j], field[j].getType());
                                Object paramValue = convertParamValue(paramValues[0], field[j].getType());
                                met.invoke(object,paramValue);
                            }  
                        }
                    }
// ///SPRINT 8
                    Method[] methods = class1.getDeclaredMethods();
                    for (Method method1 : methods) {
                        if (method1.getName().equals(mapping.getMethod())) {
                            out.println(method1.getName());
                            // mameno parametre an'ilay fonction
                            Object[] arguments = this.setParamMethod(method1, params); 
                            ModelView modelView = null;
                                out.print("ouiiiiiii");
                        if (arguments != null) {
                            modelView = (ModelView) method1.invoke(object, arguments);
                        } else {
                            modelView = (ModelView) method1.invoke(object);
                        }
                
                        //ModelView modelView = (ModelView) method.invoke(object);
                        HashMap<String, Object> data = modelView.getData();
                        if (data != null) {
                        for (Map.Entry<String, Object> entr : data.entrySet()) {
                        String ke = entr.getKey();
                        Object value = entr.getValue();
                        req.setAttribute(ke, value);
                    }
                }
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
public HashMap<String, Mapping> getMappingUrls() {
    return mappingUrls;
}

/**
* Définit la map des urls mappées.
 * @param mappingUrls
*/
public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
    this.mappingUrls = mappingUrls;
}

private Object convertParamValue(String paramValue, Class<?> paramType) throws Exception {
    if (paramType == String.class) {
        return paramValue;
    } else if (paramType == int.class || paramType == Integer.class) {
        return Integer.parseInt(paramValue);
    } else if (paramType == boolean.class || paramType == Boolean.class) {
        return Boolean.parseBoolean(paramValue);
    }else if (paramType == double.class || paramType == Double.class) {
        return Double.parseDouble(paramValue);
   } else if (paramType == Long.class || paramType == long.class) {
        return Long.parseLong(paramValue);
    } else if (paramType == Date.class) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return new java.sql.Date(formatter.parse(paramValue).getTime());
    }else if (paramType == Timestamp.class) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new java.sql.Timestamp(formatter.parse(paramValue).getTime());
       }else if(paramType == Time.class) {
          SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return new java.sql.Time(formatter.parse(paramValue).getTime());
    }else {
        return null;
    }
}

public Object convertValue(Class<?> type, String[] value){
    if (type == String.class) {
        return value;
    }
    else if (type == Integer.class || type == int.class) {
        int[] liste = new int[value.length];
        for(int i=0; i<value.length; i++) {
            liste[i] = Integer.parseInt(value[i]);
        }
        return liste;
    }
    else if (type == Double.class || type == double.class) {
        double[] liste = new double[value.length];
        for(int i=0; i<value.length; i++) {
            liste[i] = Double.parseDouble(value[i]);
        }
        return liste;
    }
    else {
        return null;
    }
}

public Object[] setParamMethod(Method method, Map<String, String[]> params) throws Exception{
    Object[] arguments = null;
    if(params.isEmpty()==false){
        Parameter[] parameters = method.getParameters();
        if(parameters.length != 0) {
            arguments = new Object[parameters.length];
            int i = 0;
            for (Parameter parameter : parameters) {
                for (String paramName : params.keySet()) {
                    if(paramName.equals(parameter.getAnnotation(UrlParam.class).name())) {
                        String[] values = params.get(paramName);
                        Object reponse = null;
                        if(values!=null && values.length == 1){
                            arguments[i] = convertParamValue(values[0],parameter.getType());
                        } 
                        
                        else if(values!=null && values.length > 1) {
                            arguments[i] = convertValue(parameter.getType(), values);
                        }  
                    }   
                }
                i++;
            }     
        }
    }
    return arguments;
}

public Object getParamForm(Object object, Map<String, String[]> params, Class<?> class1) throws Exception {
    // Obtient tous les champs de la classe
    Field[] fields = class1.getDeclaredFields();
    
    for (Field field : fields) {
        // Vérifie si le champ correspond à un paramètre dans le formulaire
        String fieldName = field.getName();
        if (params.containsKey(fieldName)) {
            // Obtient la valeur du paramètre du formulaire
            String[] fieldValues = params.get(fieldName);
            String fieldValue = fieldValues[0]; // Suppose qu'il y a une seule valeur pour le paramètre
            
            // Convertit la valeur du paramètre au type approprié
            Object convertedValue = convertParamValue(fieldValue, field.getType());
            
            // Définit la valeur du champ dans l'objet
            field.setAccessible(true);
            field.set(object, convertedValue);
        }
    }
    
    return object;
}
public void checkFileUpload(Object ob, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException, Exception {
    // cet fonction vérifie si l'objet ob contient des attributs de type FileUpload et les initialise avec les fichiers téléchargés à partir de la requête HTTP.         
    Field[] attribut = ob.getClass().getDeclaredFields();
    Method[] fonction = ob.getClass().getDeclaredMethods();
    for (int i = 0; i < attribut.length; i++) {
        if (attribut[i].getType() == etu001982.framework.UploadFile.UploadFile.class) {
            for (int j = 0; j < fonction.length; j++) {
                if (fonction[j].getName().compareTo("set" + attribut[i].getName()) == 0) {
                    // System.out.println("hhhhhhhhhhhhhhhh");
                    etu001982.framework.UploadFile.UploadFile f = this.preparefile(attribut[i].getName(), request, response);
                    fonction[j].invoke(ob, f);
                }
            }
        }

    }
}

public UploadFile preparefile(String nom,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception{
    etu001982.framework.UploadFile.UploadFile fichier=new etu001982.framework.UploadFile.UploadFile();
    Part filePart = request.getPart(nom);
    if (filePart != null) {
          String submittedFileName = filePart.getSubmittedFileName();
          System.out.println(nom);
          fichier.setname(submittedFileName );
          InputStream fileInputStream = filePart.getInputStream();
          ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

          byte[] buffer = new byte[4096];
          int bytesRead;
           while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteOutputStream.write(buffer, 0, bytesRead);
            }

        byte[] fileBytes = byteOutputStream.toByteArray();

        Byte[] fileBytesWrapper = new Byte[fileBytes.length];
        for (int i = 0; i < fileBytes.length; i++) {
            fileBytesWrapper[i] = Byte.valueOf(fileBytes[i]);
        }
        fichier.setbytes(fileBytesWrapper);
        fileInputStream.close();
        byteOutputStream.close();
        return fichier;
    }else{
        throw new Exception("le fichier est vide");
    }
}
    public void getAllMappingSingleton() throws InstantiationException,IllegalAccessException,ClassNotFoundException, URISyntaxException {
        for(Map.Entry<String,Mapping> entry:this.mappingUrls.entrySet())
        {
            Mapping map=entry.getValue();
            Class<?> classes = Class.forName(packages+ "." + map.getClassName());
                if (classes.isAnnotationPresent(scope.class)) {
                    scope annotation = classes.getAnnotation(scope.class);
                    boolean singleton=annotation.singleton();
                    // Vérifier la valeur de l'annotation "name"
                    if (singleton==true) {
                        Object object=classes.newInstance();
                        // Ajouter la classe et l'instance au HashMap
                        this.ClasseSingleton.put(classes, object);
                    }
                }
        }        
        
        }

    public Object SingletonInstances(Class<?> class1) throws InstantiationException,IllegalAccessException,IllegalArgumentException,Exception{
        for (Map.Entry<Class<?>, Object> entry : this.ClasseSingleton.entrySet()) {
            Class<?> classe = entry.getKey();
            Object instance = entry.getValue();

                    if (class1.equals(classe)) {
                    System.out.println(classe.getSimpleName()+"ok singleton");
                    System.out.println(class1.getSimpleName());
                    this.resetvaluedefault(instance);
                    return instance;
                    //   System.out.println("ooooooooooooooooo");
                }
            }
            return class1.newInstance();
                
    }

    public void resetvaluedefault(Object object) throws IllegalAccessException,IllegalArgumentException,Exception{
    Field[] fields=object.getClass().getDeclaredFields();
    for(Field field:fields)
    {
        if (!Modifier.isStatic(field.getModifiers())) {
            field.setAccessible(true);
            Class<?> fieldtype=field.getType();
            Object valeurdefault=defaultvalue(fieldtype);
            field.set(object,valeurdefault);
            
        }
    }
    
    }

    private Object defaultvalue(Class<?> paramType) throws Exception {
        if (paramType == String.class) {
            return "null";
        } else if (paramType == int.class || paramType == Integer.class) {
            return 0;
        } else if (paramType == boolean.class || paramType == Boolean.class) {
            return false;
        }else if (paramType == double.class || paramType == Double.class) {
            return 0.0;
        }else {
            return null;
        }
    }



    public static void main(String[] args) {
     new FrontServlet().getEachClass("classes/etu001982/framework/modele/");
     new FrontServlet().displayAnnot(MappingUrls,"classes/etu001982/framework/modele/");
    }
}
