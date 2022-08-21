<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="css/styles.css" rel="stylesheet">
    <meta charset="ISO-8859-1">
    <title>Peliculas</title>
</head>

<body>
<c:if test="${msgError!=null }">
    <c:out value="${msgError }"></c:out>
</c:if>

<div class="container">

    <!--Formulario de busqueda-->
    <form action="/movies/movies" method="post">
        <%--@declare id="movie"--%>
        <label for="movie">Buscar</label>
        <input type="text" id="movie" name="movie" placeholder="Filtra por pelicula">
        <input type="submit" value="Filtrar">
    </form>

    <!--Tabla de peliculas-->
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Título</th>
            <th scope="col">Imagen</th>
            <th scope="col">Fecha de estreno</th>
            <th scope="col">Calificacion</th>
            <th scope="col">Género</th>
            <th scope="col">Personajes asociados</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="movie" items="${savedMovies}">
            <tr>
                <td>${movie.movieTitle}</td>
                <td><img src=""></td>
                <td>${movie.movieReleaseDate}</td>
                <td>${movie.movieScore}</td>
                <td>${movie.genre}</td>
                <td><a class="btn btn-warning" href="/movies/edit/${movie.movieId}" role="button">Editar</a>
                </td>
                <td><a class="btn btn-danger" href="/movies/delete/${movie.movieId}" role="button">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/movies/new">Agregar pelicula</a>
    <br>
    <a href="/characters/show">Volver a lista de personajes</a>
    <br>
    <a href="/genre">Volver a lista de géneros</a>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>

</body>

</html>