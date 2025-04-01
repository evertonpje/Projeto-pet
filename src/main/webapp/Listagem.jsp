<%-- 
    Document   : Listagem
    Created on : 1 de abr. de 2025, 17:19:58
    Author     : evert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
<%
    String listaHTML = request.getParameter("lista");
%>   

<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Clientes</title>
    </head>
    <body>
        <br>
        <br>
    <center>
        <table border="0">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Email</th>
                    <th>Endereço</th>
                </tr>
            </thead>
            <tbody>
                <%=listaHTML %>
            </tbody>
        </table>
    </center>
</body>
</html>
