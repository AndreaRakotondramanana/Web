package etu1831.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import etu1831.framework.Mapping;
import etu1831.framework.Url;
import etu1831.framework.Utils;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;
    String nomDePackage;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("You are being redirected to FRONTSERVLET");
            for (Map.Entry<String, Mapping> entry : mappingUrls.entrySet()) {
                String clef = entry.getKey();// clef
                Mapping map = entry.getValue(); // valeur
                out.println("L' annotation: " + clef + " de valeur " + map.getClassName() + " de fonction appelée "
                        + map.getMethod());
            }
        } catch (Exception e) {
            out.println(e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
        this.setNomDePackage(this.getInitParameter("packageDeScan"));
        try {
            System.out.println(this.getNomDePackage() + " nom de package");
            setMappingUrls(Utils.getMethodesAnnotees(this.getNomDePackage(), Url.class));
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

}