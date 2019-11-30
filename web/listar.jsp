<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PRODUCTOS</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    </head>
    <body>
        <style>
            .autor{color: #fff }
            .autor:hover{color: #333 }
        </style>
        <nav class="navbar navbar-dark bg-dark">
            <a class="navbar-brand font-weight-bold" href="ServletProducto?op=listar">CRUD JAVA</a>
            
        </nav>

        <div class="container mt-5">
            <a href="agregar.jsp" class="btn btn-primary col-4 my-2">Nuevo Producto</a>
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr class="text-center">
                        <th>#</th>
                        <th>Producto</th>
                        <th>Precio</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaProductos}" var="lista">
                        <tr class="text-center">
                            <td>${lista.id_prod}</td>
                            <td>${lista.nom_prod}</td>
                            <td>${lista.precio_prod}</td>
                            <c:choose>
                                <c:when test="${lista.estado_prod}">
                                    <c:set var="estado" value=""/>
                                    <td>ACTIVO</td>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="estado" value="disabled"/>
                                    <td>INACTIVO</td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                <a href="ServletProducto?op=listarUno&id=${lista.id_prod}" class="btn btn-warning ${estado}">Editar</a>
                                <a href="ServletProducto?op=eliminar&id=${lista.id_prod}" class="btn btn-danger ${estado}" onclick="return confirm('¿Seguro que quieres eliminarlo?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>                    
                </tbody>
            </table>
        </div>
        <span class="autor mt-auto">@Autor: Aimar B.C</span>
    </body>
</html>
