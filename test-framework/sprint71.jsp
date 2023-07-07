<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>FORMULAIRE</h1>
    <% 
    String id = (String)request.getAttribute("id"); 
    String nom = (String)request.getAttribute("nom");
    String prenom = (String)request.getAttribute("prenom"); 
    String date_de_naissance = (String)request.getAttribute("date_de_naissance");
    %>
    <p>id: <% out.println(id); %></p>
    <p>nom: <% out.println(nom); %></p>
    <p>prenom: <% out.println(prenom); %></p>
    <p>date_de_naissance: <% out.println(date_de_naissance); %></p>
</body>
</html>