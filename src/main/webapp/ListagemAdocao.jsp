<%-- 
    Document   : ListagemAdocao
    Created on : 3 de abr. de 2025, 14:49:28
    Author     : evert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    
  <%
   String listaHTML = (String) request.getAttribute("lista");
   if (listaHTML == null) {
       listaHTML = "";
   }
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
         <form action="AdocaoSRV?acao=pesquisar" method="GET">
    <input type="text" name="cliente_id" placeholder="ID do Cliente">
    <input type="text" name="animal_id" placeholder="ID do Animal">
    <br>
    <input type="submit" value="Pesquisar">
</form>
        <table border="0">
            <thead>
                <tr>
                    <th>ID</th>
            
                    <th>Cliente</th>
              
                    <th>Animal</th>
                    
                    <th>ações</th>
                    
                       
                    
                </tr>
            </thead>
            <tbody>
                <%=listaHTML %>
            </tbody>
        </table>
    </center>
    </body>
</html>
