package etu1831.framework;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import etu1831.framework.FileUpload;
import etu1831.framework.Mapping;
import etu1831.framework.ModelView;
import etu1831.framework.Url;



public class Utils {

    public static Object cast(String toCast, Class typeOfCast) throws Exception {
        if (typeOfCast == int.class || typeOfCast == Integer.class) {
            return Integer.parseInt(toCast);
        } else if (typeOfCast == double.class || typeOfCast == Double.class) {
            return Double.parseDouble(toCast);
        } else if (typeOfCast == Date.class) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = format.parse(toCast);
            return new java.sql.Date(utilDate.getTime());
        } else if (typeOfCast == Boolean.class) {
            return Boolean.parseBoolean(toCast);
        }
    
        return toCast;
    }
    
    
    //Obtenir toutes les classes dans chaque dossier
    private static List<Class<?>> getClassesDansDossiers(File dossier, String nomDePackage)throws Exception{
        //System.out.println(dossier.getAbsolutePath() + " PATH");
        List<Class<?>> classes = new ArrayList<>();
        if(dossier.getAbsolutePath().toString().contains("%20")){
            dossier = new File(dossier.getAbsolutePath().toString().replace("%20", " "));
        }
        //System.out.println(dossier.getAbsolutePath() + " PATH 2");
        try {
            if(!dossier.exists()){
                return classes;
            }else{
                    File[] fichiersDansDossier = dossier.listFiles();
                    for (File fichier : fichiersDansDossier) {
                        if(fichier.isDirectory()){
                            assert !fichier.getName().contains(".");
                            classes.addAll(getClassesDansDossiers(fichier, nomDePackage + "." + fichier.getName()));
                        }else if(fichier.getName().endsWith(".class") == true){
                            String nomDeClasse = nomDePackage + "." + fichier.getName().substring(0, fichier.getName().length()-6);
                            classes.add(Class.forName(nomDeClasse));
                        }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur: Utils getClassesDansDossiers(File dossier, String nomDePackage)");
            // TODO: handle exception
        }
        return classes;
    }
    
    //Avoir toutes les classes dans un package spécifié
    public static List<Class<?>> getLesClasses(String packageScannes) throws Exception{
        //System.out.println(" packageScannes : " + packageScannes);
        List<Class<?>> classes = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String chemin = packageScannes.replace('.', '/');
            Enumeration<URL> ressources = classLoader.getResources(chemin);
            //System.out.println(ressources.nextElement().getFile());
            List<File> dossiers = new ArrayList<>();
            while(ressources.hasMoreElements()){
                URL ressource = ressources.nextElement();
                dossiers.add(new File(ressource.getFile()));
            }
            // System.out.println(dossiers.size());
            for (File dossier : dossiers) {
                classes.addAll(getClassesDansDossiers(dossier, packageScannes));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur: Utils avoirLesClasses(String packageScannés)");
            // TODO: handle exception
        }
        return classes;
    }

    public static HashMap<String, Mapping> getMethodesAnnotees(String nomDePackage, Class<? extends Annotation> annotationDeClasse) throws Exception{
        HashMap<String, Mapping> methodesAnnotees = new HashMap<String, Mapping>();
        try {
            List<Class<?>> classes = getLesClasses(nomDePackage);
            for (Class<?> class1 : classes) {
                Method[] listesMethodes = class1.getDeclaredMethods();
                for (Method methode : listesMethodes) {
                    Annotation annotation = methode.getAnnotation(annotationDeClasse);
                    if(annotation != null){
                        methodesAnnotees.put(((Url) annotation).method(), new Mapping( class1.getName(), methode.getName(), methode.getParameterTypes()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur: Utils avoirLesMethodesAnnotees(String nomDePackage, Class<? extends Annotation> annotationDeClasse)");
            // TODO: handle exception
        }
        return methodesAnnotees;
    }

    public static void uploadFile(FileUpload file, String pathName, String value, HttpServletRequest request) throws Exception {
    // Get the filename from the file part
    String fileName = file.getFileName();
    
    // Specify the directory to save the uploaded file
    String savePath = pathName + "ETU1767-Framework-" + fileName;
    
    // Save the file to the specified location
    OutputStream out = null;
    InputStream fileContent = null;
    try {
        Part filePart=request.getPart(value);
        out = new FileOutputStream(new File(savePath));
        fileContent = filePart.getInputStream();
        int read;
        byte[] buffer = new byte[1024];
        while ((read = fileContent.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (out != null) {
            out.close();
        }
        if (fileContent != null) {
            fileContent.close();
        }
    }
    }
}
