<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="css/styles.css" rel="stylesheet">
    <meta charset="ISO-8859-1">
    <title>Personajes</title>
    
</head>

<body>
<c:if test="${msgError!=null }">
    <c:out value="${msgError }"></c:out>
</c:if>

<div class="container">

    <!--Formulario de busqueda-->
    <form action="/characters/characters" method="get">
        <%--@declare id="name"--%>
        <label for="name">Nombre del personaje</label>
        <input type="text" id="name" name="name" placeholder="Filtra por nombre">
        <input type="submit" value="Filtrar">
    </form>

    <!--Tabla de personajes-->
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Nombre</th>
            <th scope="col">Foto</th>
            <th scope="col">Edad</th>
            <th scope="col">Peso</th>
            <th scope="col">Historia</th>
            <th scope="col">Aparece en</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="character" items="${savedCharacters}">
            <tr>
                <td>${character.characterName}</td>
                <td><img src="${character.characterImg}" height="50px" width="50px"></td>
                <td>${character.characterAge}</td>
                <td>${character.characterWeight}</td>
                <td>${character.characterStory}</td>
                <td></td>
                <td><a class="btn btn-warning" href="/characters/edit/${character.characterId}" role="button">Editar</a>
                </td>
                <td><a class="btn btn-danger" href="/characters/delete/${character.characterId}" role="button">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/characters/new">Agregar personaje</a>
    <br>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>

</body>

</html>