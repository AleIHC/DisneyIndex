<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Editar Pelicula</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-3">
            <!--Mensaje de error-->
            <c:if test="${msgError!=null }">
                <c:out value="${msgError }"></c:out>
            </c:if>


            <%--@elvariable id="movie" type="movie"--%>
            <form:form action="/movies/update/${movie.id}" method="post" modelAttribute="movie">
                <form:label path="movieTitle" class="form-label">Título</form:label>
                <form:input path="movieTitle" class="form-control"/>
                <br>
                <form:label path="movieReleaseDate" class="form-label">Fecha de estreno</form:label>
                <form:input path="movieReleaseDate" type="date" class="form-control"/>
                <br>
                <form:label path="score" class="form-label">Calificacion</form:label>
                <form:input path="score" class="form-control"/>
                <br>
                <button type="submit" class="btn btn-primary">Editar Película</button>

            </form:form>
        </div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
</body>

</html>