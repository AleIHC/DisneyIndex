<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Guardar Pelicula</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>

<body>
<div class="container">
    <div class="row justify-center">
        <div class="col-6">

            <!--<c:if test="${msgError!=null }">
                <c:out value="${msgError }"></c:out>
            </c:if>-->

            <form:form action="/movies/save" method="post" modelAttribute="movie" autocomplete="off">
                <br>
                <form:label path="movieTitle" class="form-label">Titulo</form:label>
                <form:input path="movieTitle" class="form-control"/>
                <br>
                <form:label path="movieReleaseDate" class="form-label">Fecha de estreno</form:label>
                <form:input path="movieReleaseDate" type="date" class="form-control"/>
                <br>
                <form:label path="movieScore" class="form-label">Calificacion</form:label>
                <form:input path="movieScore" class="form-control"/>
                <br>
                <form:label path="genre" class="form-label">Género</form:label>
                <form:select path="genre" class="form-select">
                    <c:forEach items="${savedGenres}" var="genre">
                        <form:option value="${genre.genreId}">${genre.genreName}</form:option>
                    </c:forEach>
                </form:select>
                <br>

                <!--Películas disponibles-->
                <form:select path="movieCharacters" class="form-select">
                    <form:option value="0">Personajes</form:option>
                    <c:forEach items="${savedCharacters}" var="character">
                        <form:option value="${character.id}">${character.characterName}</form:option>
                    </c:forEach>
                </form:select>
                <br>
                <input type="submit" value="Guardar Película">
            </form:form>
            <a href="/movies">Ir a lista de películas</a>
        </div>
    </div>
</html>