<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Editar personaje</title>
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


            <%--@elvariable id="character" type="character"--%>
            <form:form action="/characters/update/${character.characterId}" method="post" modelAttribute="character">
                <form:label path="characterImg" class="form-label">URL de imagen</form:label>
                <form:input path="characterImg" class="form-control"/>
                <br>
                <form:label path="characterName" class="form-label">Nombre</form:label>
                <form:input path="characterName" class="form-control"/>
                <br>
                <form:label path="characterAge" class="form-label">Edad</form:label>
                <form:input path="characterAge" type="number" class="form-control"/>
                <br>
                <form:label path="characterWeight" class="form-label">Peso</form:label>
                <form:input path="characterWeight" class="form-control"/>
                <br>
                <form:label path="characterStory" class="form-label">Historia</form:label>
                <form:input path="characterStory" class="form-control"/>
                <br>
                <button type="submit" class="btn btn-primary">Editar Personaje</button>

            </form:form>
        </div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
</body>

</html>