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
        <title>Listagem de Clientes</title>
        <link rel="stylesheet" type="text/css" href="./css/styles.css">
    </head>

    <%
        String listaHTML = request.getParameter("lista");
    %>    

    <body>
    <header>
        <a href="index.html" style="position: absolute; left: 200px;">Voltar</a>
        <div class="logo">
            <svg width="28" height="22" viewBox="0 0 28 22" fill="none" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M14 10h1.652c1.708 0 2.63 2.004 1.518 3.302l-1.618 1.887A4.501 4.501 0 0024.5 14.5a1.5 1.5 0 013 0A7.5 7.5 0 0114 19 7.5 7.5 0 01.5 14.5a1.5 1.5 0 013 0 4.5 4.5 0 008.948.689l-1.618-1.887C9.718 12.004 10.64 10 12.35 10H14z" fill="#333"></path><circle cx="21" cy="3" r="3" fill="#333"></circle><circle cx="7" cy="3" r="3" fill="#333"></circle></svg>
        </div>
    </header>
        <br>
        <br>
    <center>
        <table border="0">
            <thead>
                <tr>
                     <th>ID</th>
                      <th></th>
                    <th>Nome</th>
                    <th></th>
                    <th>Telefone</th>
                    <th></th>
                    <th>Email</th>
                     <th></th>
                       <th>Endereco</th>
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
