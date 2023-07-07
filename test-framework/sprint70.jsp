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
        String nomDeDepartement = (String)request.getAttribute("nomDeDepartement"); 
        String nombreEmploye = (String)request.getAttribute("nombreEmploye");
        %>
        <p class="mb-2">nomDeDepartement: <% out.println(nomDeDepartement); %></p>
        <p>nombreEmploye: <% out.println(nombreEmploye); %></p>
    </div>

</body>
</html>