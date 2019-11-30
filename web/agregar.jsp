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
        <nav class="navbar navbar-dark bg-dark">
            <a class="navbar-brand font-weight-bold" href="ServletProducto?op=listar">CRUD JAVA</a>
        </nav>
        <div class="container mt-5">
            <form action="ServletProducto" method="POST" class="col-5">
                <input type="hidden" name="op" value="insertar">
                <h3>Nuevo Producto</h3>
                <hr>
                <div class="form-group">
                    <input name="nombre" class="form-control" placeholder="Nombre" value="${producto.nom_prod}" required>
                </div>
                <div class="form-group">
                    <input name="precio" class="form-control" placeholder="Precio" value="${producto.precio_prod}" required>
                </div>
                <input type="submit" class="btn btn-success btn-block" value="AGREGAR">
            </form>
        </div>
    </body>
</html>
