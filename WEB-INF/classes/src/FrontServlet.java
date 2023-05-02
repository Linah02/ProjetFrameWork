package etu001982.framework.servlet;
import java.io.*;
import java.util.HashMap;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FrontServlet extends HttpServlet{
static HashMap<String,etu001982.framework.Mapping> MappingUrls;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req,res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req,res);
    }
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String requestUrl = req.getRequestURL().toString();
        String host = req.getHeader("Host");
        PrintWriter out = res.getWriter();
        requestUrl = requestUrl.split("//")[1].replace(host, "");
        out.print(requestUrl);
    }

}
