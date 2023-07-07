<%@page import="java.util.ArrayList"%>
<%@ page import="etu001982.framework.modele.Dept" %>
<%@ page import="etu001982.framework.Modelview.ModelView" %>
<%@ page import="etu001982.framework.UploadFile.UploadFile" %>
<%
    ArrayList<Dept> listes=(ArrayList<Dept>)request.getAttribute("name");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenue</title>
</head>
<body>
<% for(int i=0; i<listes.size(); i++) { %>
     <%= listes.get(i).getName() %>
    <% if (listes.get(i).getFile() != null) { %>
        Nom du fichier : <%= listes.get(i).getfile().getName() %>
        Nombre de bytes : <%=listes.get(i).getfile().getByte().length %>
    <% } else { %>
        Nom du fichier : Null
        Nombre de bytes : Null
    <% } %>
<% } %>
</body>
</html>