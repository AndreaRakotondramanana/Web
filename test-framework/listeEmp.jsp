<%@page import="etu1767.framework.*, modele.Employe, java.util.*"%>
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
        <h1 class="mt-4">LISTE EMPLOYES</h1>
        <% List<Employe> emp = (List<Employe>) request.getAttribute("allEmploye"); %>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prenom</th>
                    <th scope="col">Date de naissance</th>
                </tr>
            </thead>
            <tbody>
                <% for(int i = 0; i < emp.size(); i++){ %>
                <tr>
                    <td><%= emp.get(i).getId() %></td>
                    <td><%= emp.get(i).getNom() %></td>
                    <td><%= emp.get(i).getPrenom() %></td>
                    <td><%= emp.get(i).getDate_de_naissance() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>