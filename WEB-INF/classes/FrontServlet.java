package etu1831.framework.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class FrontServlet extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
try{
    out.print("Vous avez ete redirige vers FRONTSERVLET");
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

}