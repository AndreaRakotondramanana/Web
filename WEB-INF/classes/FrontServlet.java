package etu1831.framework.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import etu1831.framework.Mapping;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class FrontServlet extends HttpServlet{
    HashMap<String, Mapping> mappingUrls;  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
try{
    out.print("You are being redirected to FRONTSERVLET");
} catch(Exception e) {
    out.println(e.getMessage()+"\n");
    e.printStackTrace();
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

}