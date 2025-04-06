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
        <link rel="stylesheet" type="text/css" href="./css/styles.css">
         <a href="index.html" style="position: absolute; left: 200px;">Voltar</a>
    </head>
    
        <%
       String listaHTML = (String) request.getAttribute("lista");
    %>    
    
    
    <body>
          <br>
        <br>
    <center>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
            
                    <th>Cliente</th>
              
                    <th>Animal</th>
                    
                       
                    
                </tr>
            </thead>
            <tbody>
                <%=listaHTML %>
            </tbody>
        </table>
    </center>
    </body>
</html>
