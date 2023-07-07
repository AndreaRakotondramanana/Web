package etu1831.framework.servlet;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.http.HttpRequest;
import java.nio.file.Path;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.text.Utilities;
import javax.servlet.annotation.WebServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import etu1831.framework.Mapping;
import etu1831.framework.FileUpload;
import etu1831.framework.ModelView;
import etu1831.framework.PathUpload;
import etu1831.framework.Scope;
import etu1831.framework.Url;
import etu1831.framework.Arguments;
import etu1831.framework.Utils;

@MultipartConfig
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;
    String nomDePackage;
    HashMap<Class, Object> singletons = new HashMap<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // entrySet -> ampiasaina ao am boucle angalana an le clef sy valeur
            out.println("You are being redirected to FRONTSERVLET");

            
            Mapping map = this.getMapping(request);
            Class classe = Class.forName(map.getClassName());

            System.out.println(classe + " classe " + getSingletons().size());
            Object obj = null;
            if(this.getSingletons().containsKey(classe)){
                obj = this.getSingletons().get(classe);
                revenirANull(obj);
                System.out.println("PAS BESOIN DE NOUVELLE INSTANCE");
            }else{
                obj = classe.getDeclaredConstructor().newInstance();
                System.out.println("VRAIMENT BESOIN DE NOUVELLE INSTANCE");
            }
            System.out.println(obj + " ADRESSEE");
            sendData(request, obj);

            Method method = getMethod(map, obj);
            ModelView modelView = (ModelView) getModelView(request, map, obj);

                addData(request, modelView);
                RequestDispatcher dispat = request.getRequestDispatcher(modelView.getVueRedirection());
    
                System.out.println(modelView.getVueRedirection() + " VUE DE REDIRECTION");
    
                dispat.forward(request, response);

        } catch (Exception e) {
            out.println(e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    public void addData(HttpServletRequest request, ModelView modelView) {
        Map<String, String[]> donneesJSP;
        if (request.getParameterMap() != null && !request.getParameterMap().isEmpty()) {
            donneesJSP = request.getParameterMap();

            // out.println(donneesJSP.toString() + " donneesJSP");
            for (String parameterName : donneesJSP.keySet()) {
                String[] values = donneesJSP.get(parameterName);
                // out.println(parameterName + " : " + String.join(", ", values));
                modelView.addItem(parameterName, donneesJSP.get(parameterName)[0]);
            }
        } else {
        }
        for (Map.Entry<String, Object> obj : modelView.getData().entrySet()) {
            request.setAttribute(obj.getKey(), obj.getValue());
        }
    }

    public void sendData(HttpServletRequest request, Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String value = field.getName();
            if(multiPartFormDataContentType(request)){
            if (value != null) {
                    if(field.getType().getSimpleName().equalsIgnoreCase("FileUpload") == true){
                        try {
                            System.out.println("THERE IS A FILE TO UPLOAD TAFIDITRAAAAAAAAAAAA");
                            Part filePart=request.getPart(value);
                            FileUpload upload=new FileUpload();
                            upload.setFileName(filePart.getSubmittedFileName()); 
                            InputStream inputStream = filePart.getInputStream();
                            upload.setData(inputStream.readAllBytes());
                            System.out.println(upload.getData());
                            Class<?> clazz = obj.getClass();
                            // Retrieve the PathUploadClass annotation from the class
                            PathUpload annotation = clazz.getAnnotation(PathUpload.class);
                            if (annotation != null) {
                                String filePath = annotation.filePath();
                                Utils.uploadFile(upload, filePath, value, request);
                            }else{
                                System.out.println("Aucune annotation donnée à PathUpload");
                            }
                        } catch (Exception e) {
                            throw new Exception("Verifier si vous avez bien télécharger quelque chose");
                        }
                    }else{
                        String valeur = request.getParameter(field.getName());
                        Class castValue = (Class<?>) field.getType();
                        field.set(obj, Utils.cast(valeur, castValue));
                    }
                }
            }
        }
    }

    public void revenirANull(Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            Object objectNull = new Object();
            
            for (Field field : fields) {
                // Obtenir le nom du champ
                String fieldName = field.getName();
                
                // Convertir le nom du champ en nom de méthode de setter
                String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                System.out.println(setterName + " SETTERNAME");
                // Obtenir le type du champ
                Class<?> fieldType = field.getType();
                List<Class<?>> nonNullableTypes = typesNonNullables();
                for (int i = 0; i < nonNullableTypes.size(); i++) {
                    if(fieldType.toString().equalsIgnoreCase(nonNullableTypes.get(i).toString()))
                    System.out.println("NON NULLABLE" + fieldType);
                }
                objectNull = null;
                // Obtenir la méthode de setter correspondante
                Method setterMethod = object.getClass().getMethod(setterName, fieldType);
                System.out.println(setterMethod + " SETTERMETHOD");
                // Appeler le setter avec la valeur null
                
                setterMethod.invoke(object, objectNull);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Class<?>> typesNonNullables(){
        List<Class<?>> nonNullableTypes = new ArrayList<>();
        nonNullableTypes.add(boolean.class);
        nonNullableTypes.add(byte.class);
        nonNullableTypes.add(short.class);
        nonNullableTypes.add(int.class);
        nonNullableTypes.add(long.class);
        nonNullableTypes.add(float.class);
        nonNullableTypes.add(double.class);
        nonNullableTypes.add(char.class);
        return nonNullableTypes;
    }
public static boolean multiPartFormDataContentType(HttpServletRequest request){
    String contentType = request.getContentType();
    if(contentType != null && contentType.startsWith("multipart/form-data")){
        return true;
    }
    return false;
}

    public String[] avoirLaListeArguments(Method methodCalled) {
        String[] thoseAre = null;
        Arguments argumentsAnnotation = methodCalled.getAnnotation(Arguments.class);

        // Vérifiez si l'annotation existe
        if (argumentsAnnotation != null) {
            // Obtenez les valeurs de arguments()
            thoseAre = argumentsAnnotation.arguments();
        }
        return thoseAre;
    }

    // Prendre Mapping : Class,Methode,Argument correspondant de L'URL
    public Mapping getMapping(HttpServletRequest request) throws Exception {
        String servletPath = request.getServletPath();
        String[] path = servletPath.split("/");
        System.out.println(path[1]);
        for (Map.Entry<String, Mapping> entry : mappingUrls.entrySet()) {
            String clef = entry.getKey();
            String method = entry.getValue().getMethod();
            if (path[1].compareToIgnoreCase(clef) == 0) {
                return entry.getValue();
            }
        }
        throw new Exception("URL INTROUVABLE");
    }

    public Object[] getArgumentsDeMethode(HttpServletRequest request, Method methodCalled) throws Exception {
        // Prendre les arguments de la méthode
        System.out.println(methodCalled + " methodCalled");
        Parameter[] parameters = methodCalled.getParameters();
        Object[] argumentsDeMethode = new Object[parameters.length];
        String[] listeArguments = avoirLaListeArguments(methodCalled);
        for (int i = 0; i < argumentsDeMethode.length; i++) {
            // prendre la type de la classe de chaque argument
            Class typeArguments = parameters[i].getType();
            // Avadika string le valeur an le paramètre tany am le méthode
            String value = request.getParameter(listeArguments[i]);
            // Avadika ho le type originaleny amzay le izy aveo
            argumentsDeMethode[i] = Utils.cast(value, typeArguments);
        }
        return argumentsDeMethode;
    }

    public Method getMethod(Mapping map, Object obj) throws Exception {
        return obj.getClass().getDeclaredMethod(map.getMethod(), map.getArguments());
    }

    public ModelView getModelView(HttpServletRequest request, Mapping map, Object obj) throws Exception {

        Method method = getMethod(map, obj);
        method.setAccessible(true);
        Object[] argument = getArgumentsDeMethode(request, method);
        ModelView mv = (ModelView) method.invoke(obj, argument);
        return mv;

    }

    public void init() throws ServletException {
        this.setNomDePackage(this.getInitParameter("packageDeScan"));
        HashMap hash = new HashMap<Class,Object>();
        try {
            System.out.println(this.getNomDePackage() + " nom de package");
            setMappingUrls(Utils.getMethodesAnnotees(this.getNomDePackage(), Url.class));
            List<Class<?>> classes = Utils.getLesClasses(this.getNomDePackage());
            for (Class<?> class1 : classes) {
                Annotation annotation = class1.getAnnotation(Scope.class);
                    if(annotation != null){
                        System.out.println(class1 + " ANNOTERRR SCOPPEE");
                        Object obj = class1.getDeclaredConstructor().newInstance();
                        singletons.put(class1, obj);
                    }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public HashMap<String, Mapping> getMappingUrls() {
        return mappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }

    public String getNomDePackage() {
        return nomDePackage;
    }

    public void setNomDePackage(String nomDePackage) {
        this.nomDePackage = nomDePackage;
    }

    public HashMap<Class, Object> getSingletons() {
        return singletons;
    }

    public void setSingletons(HashMap<Class, Object> singletons) {
        this.singletons = singletons;
    }

}