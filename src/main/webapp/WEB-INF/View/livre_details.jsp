<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Fiche livre</h1>
      </div>
      <div class="row">
      <div class="container">
      <h5>Détails du livre n°${livreId}</h5>
        <div class="row">
	      <form action="/TP3Ensta/livre_details?id=${livreId}" method="post" class="col s12">
	        <div class="row">
	          <div class="input-field col s12">
	            <input id="titre" type="text" value="${livreTitre}" name="titre">
	            <label for="titre">Titre</label>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s6">
	            <input id="auteur" type="text" value="${livreAuteur}" name="auteur">
	            <label for="auteur">Auteur</label>
	          </div>
	          <div class="input-field col s6">
	            <input id="isbn" type="text" value="${livreIsbn}" name="isbn">
	            <label for="isbn">ISBN 13</label>
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	      
	      <form action="/TP3Ensta/livre_delete" method="get" class="col s12">
	        <input type="hidden" value="${livreId}" name="id">
	        <div class="row center">
	          <button class="btn waves-effect waves-light red" type="submit">Supprimer le livre
	            <i class="material-icons right">delete</i>
	          </button>
	        </div>
	      </form>
	    </div>
	    <c:if test="${livreEmprunt != null}">
        <div class="row">
          <div class="col s12">
	        <table class="striped">
              <thead>
              	<tr>
                  <th>Emprunteur</th>
                  <th>Date d'emprunt</th>
                  <th>Retour</th>
                </tr>
              </thead>
              <tbody id="results">
				<%@ page import="com.ensta.librarymanager.service.MembreServiceImpl,com.ensta.librarymanager.service.LivreServiceImpl,com.ensta.librarymanager.service.EmpruntServiceImpl,com.ensta.librarymanager.modele.Livre,com.ensta.librarymanager.modele.Emprunt,com.ensta.librarymanager.modele.Membre" %>
				<c:forEach items="${livreEmprunts}" var="emprunt">
				<% Emprunt emprunt = (Emprunt) pageContext.getAttribute("emprunt"); %>
				<% Membre membre = MembreServiceImpl.getInstance().getById(emprunt.getIdMembre()); %>
                
                <tr>
                  <td><% out.print(membre.getPrenom() + " " + membre.getNom()); %></td>
                  <td><% out.print(emprunt.getDateEmprunt()); %></td>
                  <td>
                    <a href="emprunt_return?id=${livreEmprunt.id}"><ion-icon class="table-item" name="log-in"></a>
                  </td>
                </tr>
				</c:forEach>
				</tbody>
            </table>
          </div>
        </div>
        </c:if>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
