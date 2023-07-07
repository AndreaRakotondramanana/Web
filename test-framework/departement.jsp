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
        <h1 class="mt-4">DEPARTEMENT</h1>
        <form action="add-dept.gg">
            <div class="mb-3">
                <label for="nomDeDepartement" class="form-label">Nom du département</label>
                <input type="text" name="nomDeDepartement" id="nomDeDepartement" class="form-control">
            </div>
            <div class="mb-3">
                <label for="nombreEmploye" class="form-label">Nombre d'employés</label>
                <input type="number" name="nombreEmploye" id="nombreEmploye" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Soumettre</button>
        </form>
    </div>

    <div class="container mt-5">
        <h1 class="mt-4">DEPARTEMENT WITH ARGUMENTS</h1>
        <form action="add-with-arguments.gg">
            <div class="mb-3">
                <label for="nombreEmploye" class="form-label">Nombre d'employés</label>
                <input type="number" name="nombreEmploye" id="nombreEmploye" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Soumettre en tant qu'argument</button>
        </form>
    </div>

</body>
</html>