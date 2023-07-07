<%@page import="etu1767.framework.*, modele.Employe"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>LISTE EMPLOYES</h1>
    <% Employe emp = (Employe) request.getAttribute("employer"); %>
    <table>
        <thead>
            <td>Id</td>
            <td>Nom</td>
            <td>Prenom</td>
            <td>Date</td>
        </thead>
            <tbody>
                <td> <%= emp.getId() %> </td>
                <td> <%= emp.getNom() %> </td>
                <td> <%= emp.getPrenom() %> </td>
                <td> <%= emp.getDate_de_naissance() %> </td>
            </tbody>
    </table>
</body>
</html>