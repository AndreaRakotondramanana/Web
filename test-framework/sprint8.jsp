<%@page import="etu1767.framework.*, modele.Employe"%>
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
        <h1 class="mt-4">EMPLOYE</h1>
        <% Employe emp = (Employe) request.getAttribute("employer"); %>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prenom</th>
                    <th scope="col">Date de naissance</th>
                    <th scope="col">Picture</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td> <%= emp.getId() %> </td>
                    <td> <%= emp.getNom() %> </td>
                    <td> <%= emp.getPrenom() %> </td>
                    <td> <%= emp.getDate_de_naissance() %> </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>