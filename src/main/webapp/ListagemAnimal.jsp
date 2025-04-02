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
                    <th>Nome</th>
                    <th></th>
                    <th>Especie</th>
                    <th></th>
                    <th>Raca</th>
                     <th></th>
                       <th></th>
                       <th></th>
                       
                    
                </tr>
            </thead>
            <tbody>
                <%=listaHTML %>
            </tbody>
        </table>
    </center>
</body>
</html>
