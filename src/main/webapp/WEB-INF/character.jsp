<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Guardar personaje</title>
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
            

            <form:form action="/characters/save" method="post" modelAttribute="character" autocomplete="off">
                <br>
                <form:label path="characterImg" class="form-label">URL de imagen</form:label>
                <form:input path="characterImg" class="form-control"/>    
                <br>
                <form:label path="characterName" class="form-label">Nombre del personaje</form:label>
                <form:input path="characterName" class="form-control"/>
                <br>
                <form:label path="characterAge" class="form-label">Edad del personaje</form:label>
                <form:input path="characterAge" type="text" class="form-control"/>
                <br>
                <form:label path="characterWeight" class="form-label">Peso del personaje</form:label>
                <form:input path="characterWeight" class="form-control"/>
                <br>
                <br>
                <form:label path="characterStory" class="form-label">Historia</form:label>
                <form:input path="characterStory" class="form-control"/>
                <br>

                <!--Películas disponibles-->
                <form:select path="movies" class="form-select">
                    <form:option value="0">Seleccione una pelicula</form:option>
                    <c:forEach items="${savedMovies}" var="movie">
                        <form:option value="${movie.id}">${movie.movieTitle}</form:option>
                    </c:forEach>
                </form:select>
                <br>
                <input type="submit" value="Guardar Personaje">
            </form:form>

            <a href="/movies">Ir a lista de películas</a>
        </div>
    </div>
</html>