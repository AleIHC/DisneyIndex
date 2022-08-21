<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Nuevo Género</title>
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

            <form:form action="/genre/save" method="post" modelAttribute="genre" autocomplete="off">
                <br>
                <form:label path="genreName" class="form-label">Nombre</form:label>
                <form:input path="genreName" class="form-control"/>
                <br>
                

                <!--Películas disponibles-->
                <form:select path="movies" class="form-select">
                    <form:option value="0">Peliculas</form:option>
                    <c:forEach items="${savedMovies}" var="movie">
                        <form:option value="${movie.id}">${movie.movieTitle}</form:option>
                    </c:forEach>
                </form:select>
                <br>
                <input type="submit" value="Guardar Genero">
            </form:form>
        </div>
    </div>
</html>