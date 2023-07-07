<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap/dist/css/bootstrap.min.css">
    <title>Document</title>
</head>
<body>
    
    <div class="container">
        <h1 class="mt-4">FORMULAIRE</h1>
        <% 
        String id = (String)request.getAttribute("id"); 
        String nom = (String)request.getAttribute("nom");
        String prenom = (String)request.getAttribute("prenom"); 
        String date_de_naissance = (String)request.getAttribute("date_de_naissance");
        %>
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <p class="form-control" id="id"><%= id %></p>
        </div>
        <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <p class="form-control" id="nom"><%= nom %></p>
        </div>
        <div class="mb-3">
            <label for="prenom" class="form-label">Prénom</label>
            <p class="form-control" id="prenom"><%= prenom %></p>
        </div>
        <div class="mb-3">
            <label for="date_de_naissance" class="form-label">Date de naissance</label>
            <p class="form-control" id="date_de_naissance"><%= date_de_naissance %></p>
        </div>
    </div>
</body>
</html>