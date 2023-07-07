<%@page import="etu1767.framework.*, modele.Employe, java.util.*"%>
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
    <% List<Employe> emp = (List<Employe>) request.getAttribute("allEmploye"); %>
    <table>
        <thead>
            <td>Nom</td>
            <td>Prenom</td>
        </thead>
        <% for(int i = 0; i < emp.size(); i++){ %>
            <tbody>
                <td> <%= emp.get(i).getId() %> </td>
                <td> <%= emp.get(i).getNom() %> </td>
                <td> <%= emp.get(i).getPrenom() %> </td>
                <td> <%= emp.get(i).getDate_de_naissance() %> </td>
            </tbody>
        <% } %>
    </table>
</body>
</html>