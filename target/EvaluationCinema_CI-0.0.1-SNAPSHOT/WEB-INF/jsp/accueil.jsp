<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cinema | accueil</title>
</head>
<body>
	<h1>Bienvenue sur le site du Cinema Riletthes</h1>
	<c:forEach var="current" items="${films}">
		<h2>${ current.nom }</h2>
		<p>Durée : ${ current.duree }</p>
		<p>${ current.description }</p>
		<c:forEach var="seance" items="${current.seances}">
			<p>Seance à ${seance.heure } en salle ${seance.salle }</p>
		</c:forEach>
		<form method="get" action="${ pageContext.request.contextPath }/modifierFilm">
			<input type="hidden" value="${ current.id }" name="idFilm">
			<input type="submit" value="modifier">
		</form>
		<form method="post" action="${ pageContext.request.contextPath }/supprimerFilm">
			<input type="hidden" value="${ current.id }" name="idFilm">
			<input type="submit" value="supprimer">
		</form>
		<hr/>
	</c:forEach>
	
	
	<form method="post" action="${ pageContext.request.contextPath }/accueil">
		<input type="text" name="nouveauNom">
		<input type="number" name="nouvelleDuree">
		<input type="text" name="nouvelleDescription">
		
		<input type="submit" value="ajouter">
	</form>
	
	<form method="get" action="${ pageContext.request.contextPath }/genererPDF">
		<input type="submit" value="export pdf">
	</form>
</body>
</html>