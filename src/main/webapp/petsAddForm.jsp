<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feira de Adoção</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./css/styles.css"></head>
<body>
    <header>
        <a href="index.html" style="position: absolute; left: 200px;">Voltar</a>
        <div class="logo">
            <svg width="28" height="22" viewBox="0 0 28 22" fill="none" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M14 10h1.652c1.708 0 2.63 2.004 1.518 3.302l-1.618 1.887A4.501 4.501 0 0024.5 14.5a1.5 1.5 0 013 0A7.5 7.5 0 0114 19 7.5 7.5 0 01.5 14.5a1.5 1.5 0 013 0 4.5 4.5 0 008.948.689l-1.618-1.887C9.718 12.004 10.64 10 12.35 10H14z" fill="#333"></path><circle cx="21" cy="3" r="3" fill="#333"></circle><circle cx="7" cy="3" r="3" fill="#333"></circle></svg>
        </div>
    </header>
    <section class="main">
        <form action="#">
            <h1 style="margin-bottom: 30px; text-align: center;">Criar Novo Pet</h1>
            <div class="form-input">
                <label for="name">Nome do Pet</label>
                <input type="text" name="name" id="name"/>
            </div>
            <div class="form-input">
                <label for="age">Idade</label>
                <input type="number" name="age" id="age"/>
            </div>
            <div class="form-input">
                <label for="race">Raça</label>
                <input type="text" name="name" id="name"/>
            </div>
            <div class="form-input">
                <label for="weight">Peso</label>
                <input type="number" name="weight" id="weight"/>
            </div>
            <input type="submit" value="Criar" class="submit">
        </form>
    </section>
</body>
</html>
