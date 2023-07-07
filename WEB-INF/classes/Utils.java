package etu1831.framework;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import etu1831.framework.Mapping;
import etu1831.framework.Url;

public class Utils {
    //Obtenir toutes les classes dans chaque dossier
    private static List<Class<?>> getClassesDansDossiers(File dossier, String nomDePackage)throws Exception{
        System.out.println(dossier.getAbsolutePath() + " PATH");
        List<Class<?>> classes = new ArrayList<>();
        if(dossier.getAbsolutePath().toString().contains("%20")){
            dossier = new File(dossier.getAbsolutePath().toString().replace("%20", " "));
        }
        System.out.println(dossier.getAbsolutePath() + " PATH 2");
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
        }/* finally{
            return classes;
        } */
        return classes;
    }
    
    //Avoir toutes les classes dans un package spécifié
    private static List<Class<?>> getLesClasses(String packageScannes) throws Exception{
        System.out.println(" packageScannes : " + packageScannes);
        List<Class<?>> classes = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String chemin = packageScannes.replace('.', '/');
            System.out.println("Chemin " + chemin);
            Enumeration<URL> ressources = classLoader.getResources(chemin);
            //System.out.println(ressources.nextElement().getFile());
            List<File> dossiers = new ArrayList<>();
            while(ressources.hasMoreElements()){
                URL ressource = ressources.nextElement();
                dossiers.add(new File(ressource.getFile()));
            }
            System.out.println(dossiers.size());
            for (File dossier : dossiers) {
                System.out.println("HIHIHIIHII");
                classes.addAll(getClassesDansDossiers(dossier, packageScannes));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur: Utils avoirLesClasses(String packageScannés)");
            // TODO: handle exception
        }/* finally{
            return classes;
        } */
        for (Class<?> class1 : classes) {
            System.out.println("classe1 "  + class1.getSimpleName());
        }
        return classes;
    }

    public static HashMap<String, Mapping> getMethodesAnnotees(String nomDePackage, Class<? extends Annotation> annotationDeClasse) throws Exception{
        System.out.println("We' re in getMethodesAnnotees: " + nomDePackage);
        HashMap<String, Mapping> methodesAnnotees = new HashMap<String, Mapping>();
        try {
            List<Class<?>> classes = getLesClasses(nomDePackage);
            for (Class<?> class1 : classes) {
                Method[] listesMethodes = class1.getDeclaredMethods();
                for (Method methode : listesMethodes) {
                    Annotation annotation = methode.getAnnotation(annotationDeClasse);
                    if(annotation != null){
                        System.out.println("methode " + ((Url) annotation).method());
                        System.out.println("nomdeclasse " + class1.getName());
                        System.out.println("nomdemethode " + methode.getName());
                        methodesAnnotees.put(((Url) annotation).method(), new Mapping( class1.getName(), methode.getName()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur: Utils avoirLesMethodesAnnotees(String nomDePackage, Class<? extends Annotation> annotationDeClasse)");
            // TODO: handle exception
        }/* finally{
            return methodesAnnotees;
        } */
        return methodesAnnotees;
    }
}
