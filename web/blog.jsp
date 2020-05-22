<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Blog"%>
<%@page  import="java.util.List"%>
<%
    if (session.getAttribute("logeado") != "OK") {
            response.sendRedirect("login.jsp");
        }
%>
<%
    List<Blog> lista = (List<Blog>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/icomoon.css">
        <script languaje="JavaScript" type="text/javascript" src="js/bootstrap.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <nav style="float:right;padding: 5px;"  >
                
                <h6 ><span class="icon-user">  </span>ADMINISTRADOR : ${sessionScope.usuario}   <a   href="LoginControlador?action=logout"> <span class="icon-sign-out"></span>  Salir</a></h6>
            </nav>
            <br>
            
            <div >
                   <h1 style="text-align: center">Blog de Salud</h1>
                 <p><a href="MainControlador?op=nuevo&autor=${sessionScope.usuario}">Nueva Entrada</a></p>
                <table border="0">

                    <c:forEach var="item" items="${lista}">           
                    <tr>

                    </tr>
                    <tr>
                        <td><i>${item.fecha}</i></td>
                    </tr>
                    <tr>
                        <td><h1><b>${item.titulo}</b></h1></td>
                    </tr>
                    <tr>
                          <td>${item.contenido}</td>
                    </tr>
                    <tr>
                        <td><b>Autor : ${item.autor}</b>

                          <a style="float: right"href="MainControlador?op=eliminar&id=${item.id}" onclick=" return confirm('Esta Seguro')">Eliminar</a> 
                           <a style="float: right"href="MainControlador?op=editar&id=${item.id}">Editar</a>

                        <br><hr style="border-color:red;">
                        </td>

                    </tr>

                    </c:forEach>
                </table>
            </div>
         
        </div>
        
        
    </body>
</html>
