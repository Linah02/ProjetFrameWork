<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="etu001982.framework.modele.Emp" %>
<%@ page import="etu001982.framework.Modelview.ModelView" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View</title>
</head>
<body>
    <h1>View Page</h1>
    <table  border="1">
        <tr>
            <th>Nom</th>
            <th>Age</th>
             <th>Date de naissance</th>
            <th>Salaire</th>
        </tr>
        <%
        String s=(String)request.getAttribute("nom");    
        int i=(Integer)request.getAttribute("age");    
        Date d=(Date)request.getAttribute("dtn");    
        double salaire=(Double)request.getAttribute("salaire");    
        %>
        <tr>
            <td><%= s %></td>
            <td><%= i %></td>
             <td><%= d %></td>
            <td><%= salaire %></td>
        </tr>
    </table>
</body>
</html>