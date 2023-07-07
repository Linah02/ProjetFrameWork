import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
boolean isJson=false;
Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(v.getData());
        PrintWriter out = response.getWriter();
        out.println(json);
<%
    // Récupérer la HashMap de la session
    HashMap<String, Object> sessions = (HashMap<String, Object>) request.getAttribute("session");
    
    // Vérifier si la HashMap est vide
    if (session != null && !sessions.isEmpty()) {
        // Parcourir les entrées de la HashMap
        for (String key : sessions.keySet()) {
            Object value = sessions.get(key);
 %>
    <p>Clé : <%= key %>, Valeur : <%= value %></p>
<%
}
} else {%>
<p>La session est vide.</p><%
}
%>