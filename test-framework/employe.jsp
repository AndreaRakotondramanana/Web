<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap/dist/css/bootstrap.min.css">
    <title>Document</title>
</head>
<body><div class="container">
    <h1 class="mt-4">EMPLOYE</h1>
    <form action="add-emp.gg"  method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="number" name="id" id="id" class="form-control">
        </div>
        <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <input type="text" name="nom" id="nom" class="form-control">
        </div>
        <div class="mb-3">
            <label for="prenom" class="form-label">Prénom</label>
            <input type="text" name="prenom" id="prenom" class="form-control">
        </div>
        <div class="mb-3">
            <label for="date_de_naissance" class="form-label">Date de naissance</label>
            <input type="date" name="date_de_naissance" id="date_de_naissance" class="form-control">
        </div>
        <div class="mb-3">
            <input type="file" name="fileEmploye" id="fileEmploye"  class="form-control" />
        </div> 
        <input type="submit" class="btn btn-primary">
    </form>
</div>

<div class="container mt-5">
    <h1 class="mt-4">EMPLOYE AVEC ARGUMENTS DANS METHODE</h1>
    <form action="add-emp-with-arguments.gg" method="post" enctype="multipart/form-data"> 
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="number" name="id" id="id" class="form-control">
        </div>
        <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <input type="text" name="nom" id="nom" class="form-control">
        </div>
        <div class="mb-3">
            <label for="prenom" class="form-label">Prénom</label>
            <input type="text" name="prenom" id="prenom" class="form-control">
        </div>
        <div class="mb-3">
            <label for="date_de_naissance" class="form-label">Date de naissance</label>
            <input type="date" name="date_de_naissance" id="date_de_naissance" class="form-control">
        </div> 
        <div class="mb-3">
            <input type="file" name="fileEmploye" id="fileEmploye"  class="form-control" />
        </div> 
        <input type="submit" class="btn btn-primary" value="Soumettre en tant qu'arguments">
    </form>
</div>
</body>
</html>