<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="etu001982.framework.Modelview.ModelView" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire</title>
</head>
<body>
    <h5>Load File</h5>
    <form action="sprint9">
        <label for="name">Title :</label>
        <input type="text" name="name" id="name"><br><br>

        <label for="file">File :</label>
        <input type="file" name="file" id="file"><br><br>

        <input type="submit" value="check">
</body>
</html>
