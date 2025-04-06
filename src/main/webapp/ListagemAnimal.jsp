<%-- 
    Document   : Listagem
    Created on : 10 de mar. de 2025, 18:44:05
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de animais</title>
        <link rel="stylesheet" type="text/css" href="./css/styles.css">
         <a href="index.html" style="position: absolute; left: 200px;">Voltar</a>
    </head>

    <%
        String listaHTML = request.getParameter("lista");
    %>    

    <body>
        <br>
        <br>
    <center>
        <table border="0">
            <thead>
                <tr>
                     <th>ID</th>
                      <th></th
                       <th></th>
                    <th>Nome</th>
                    <th></th>
                    <th>Especie</th>
                    <th></th>
                    <th>Raca</th>
                     <th></th>
                       <th>Adotado</th>
                 
                       
                    
                </tr>
            </thead>
            <tbody>
                <%=listaHTML %>
            </tbody>
        </table>
    </center>
</body>
</html>
