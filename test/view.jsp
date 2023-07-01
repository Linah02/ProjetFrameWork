<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="etu001982.framework.modele.Emp" %>
<%@ page import="etu001982.framework.Modelview.ModelView" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
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
            <th>Key</th>
            <th>Value</th>
        </tr>
        <% 
            Emp emp = new Emp();
            ModelView modelView = emp.findAlls();
            
            // Récupérer les données de l'attribut "data"
            HashMap<String, Object> data = modelView.getData();
            
            // Parcourir les entrées de l'HashMap
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
        %>
        <tr>
            <td><%= key %></td>
            <td><%= value %></td>
        </tr>
        <% 
            }
        %>
    </table>
</body>
</html>
