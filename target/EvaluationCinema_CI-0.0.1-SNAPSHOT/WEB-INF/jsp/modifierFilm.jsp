<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cinema | Modifier un film</title>
</head>
<body>
	<form method="post" action="${ pageContext.request.contextPath }/modifierFilm">
		<input type="hidden" value="${ film.id }" name="idFilm">
		<input type="text" value="${film.nom }" name="nouveauNom">
		<input type="number" value="${film.duree }" name="nouvelleDuree">
		<input type="text" value="${film.description }" name="nouvelleDescription">
		
		<input type="submit" value="appliquer">
	</form>
</body>
</html>