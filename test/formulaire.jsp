<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="etu001982.framework.modele.Dept" %>
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
    <h1>Formulaire</h1>
    <form action="sprint7">
        <label for="nom">Nom :</label>
        <input type="text" name="nom" id="nom"><br><br>

        <label for="age">Age :</label>
        <input type="text" name="age" id="age"><br><br>

        <label for="dtn">Date de début d'exercice :</label>
        <input type="date" name="dtn" id="dtn"><br><br>

        <label for="salaire">Salaire :</label>
        <input type="text" name="salaire" id="salaire"><br><br>

        <input type="submit" value="Enregistrer">
</body>
</html>
