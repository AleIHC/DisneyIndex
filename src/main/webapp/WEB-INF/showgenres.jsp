<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="css/styles.css" rel="stylesheet">
    <meta charset="ISO-8859-1">
    <title>Géneros</title>
</head>

<body>
<c:if test="${msgError!=null }">
    <c:out value="${msgError }"></c:out>
</c:if>

<div class="container">

    <!--Tabla de Géneros-->
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Nombre</th>
            <th scope="col">Imagen</th>
            <th scope="col">Películas</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="genre" items="${savedGenre}">
            <tr>
                <td>${genre.genreName}</td>
                <td><img src=""></td>
                <td>${genre.movies}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/genre/new">Agregar género</a>
    <br>
    <a href="/characters/show">Volver a lista de personajes</a>
    <br>
    <a href="/movies">Volver a lista de películas</a>
    <br>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>

</body>

</html>