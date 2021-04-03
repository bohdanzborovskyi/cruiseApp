<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.sessionLocale}"/>
<fmt:setBundle basename="resources.loc"/>
<html lang="${sessionScope.sessionLocale}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>$(document).ready(function() {setTimeout(function() {$(".alert").alert('close');}, 2000);});</script>
<script>$(document).ready(function(){if(${sessionScope.hiddenError}){$(".alert-danger").alert('close');}});</script>
<script>$(document).ready(function(){if(${sessionScope.hiddenMessage}){$(".alert-success").alert('close');}});</script>
<title><fmt:message key="cruise_firm" /></title>
</head>
<body>
<div class="container">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
<a class="navbar-brand" href="${pageContext.request.contextPath}/view/startPage.jsp?sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="main"></fmt:message></a>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
</button>
<div class="collapse navbar-collapse"  id="collapsibleNavbar">
	<ul class="navbar-nav mr-auto">
		<li class="nav-item dropdown">
		<a class="nav-link dropdown-toggle"  id="languages" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="language"></fmt:message></a>
			<div class="dropdown-menu" aria-labelledby="languages">
			<a class="dropdown-item" href="/CruiseApp/customUser?sessionLocale=en&action=editUser"><fmt:message key="en" /></a>		
			<a class="dropdown-item" href="/CruiseApp/customUser?sessionLocale=ua&action=editUser"><fmt:message key="ua" /></a>
			</div>
		</li>		
		<li class="nav-item"><a class="nav-link" href="?action=userListShips&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="myShips" /></a></li>
 		<li class="nav-item"><a class="nav-link" href="?action=userListExcursions&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="myExcursions" /></a></li>
 		<li class="nav-item"><a class="nav-link" href="?action=listShips&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="ships" /></a></li>
 		<li class="nav-item"><a class="nav-link" href="?action=listExcursions&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="excursions" /></a></li>
 		<li class="nav-item"><a class="nav-link" href="?action=availableListExcursions&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="available_excursions" /></a></li>		
	 	<li class="nav-item"><a class="nav-link" href="?action=download&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="download" /></a></li>	
	 	
	</ul>
	<a href="/CruiseApp/customUser?action=editUser&sessionLocale=${sessionScope.sessionLocale}">	
	<img src="${sessionScope.photo}" alt=""  width="50" height="50" class="rounded-circle mr-1"></a>
	<li class="nav-item"><span class="badge badge-pill badge-primary"><fmt:message key="currentCash" />${sessionScope.cash}</span></li>
	<form class="navbar-nav ml-auto" method="post" action="<c:url value='/logout'/>" >
	<button class="form-control mr-sm-2" type="submit" class="btn btn-default"><fmt:message key="logout" /></button> 
	</form>		
</div>
</nav>
</div>
<br/>
<div class="container">
<div class="row">
	<div class="col-sm-4">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3><span class="badge badge-pill">${user.name} ${user.surname}</span></h3>	
			</div>
			<div class="panel-body">
				<img src="${sessionScope.photo}" alt=""  width="200" height="200" class="rounded-circle mr-1"><br/>
			</div>
			<div class="panel-footer">
				<h3><span class="badge badge-pill badge-primary"><fmt:message key="currentCash" />${sessionScope.cash}</span></h3>
			</div>		
		</div>		
	</div>	
	<div class="col-sm-8">
		<form  class="form-horizontal" method="post" action="<c:url value='/register'/>" accept-charset="UTF-8" enctype="multipart/form-data">			
		<div class=" form-group row">	
			<label for="login"  class="control-label col-sm-6 "><fmt:message key="login_name" /></label>
			<div class="col-sm-6">
     				<input type="text" class="form-control" name="login" id="login"  value="${user.login}">
    			</div> 
    		</div>   		
    		<div class="form-group row ">	
    			<label for="password"  class="col-sm-6 control-label"><fmt:message key="password" /></label>
			<div class="col-sm-6">
     				<input type="text" class="form-control" name="password" id="password" value="${user.password}">
    			</div>	
    		</div>
    		<div class="form-group row">	    		
    			<label for="name_ua"  class="col-sm-6 control-label"><fmt:message key="name_ua" /></label>
			<div class="col-sm-6">
     				<input type="text" class="form-control" name="name_ua" id="name_ua" value="${requestScope.name}">
    			</div>	
    		</div>
    		<div class="form-group row">	    		
    			<label for="surname_ua"  class="col-sm-6 control-label"><fmt:message key="surname_ua" /></label>
			<div class="col-sm-6">
     				<input type="text" class="form-control" name="surname_ua" id="surname_ua" value="${requestScope.surname}">
    			</div>	
    		</div>
    		<div class="form-group row">	    		
    			<label for="name_en"  class="col-sm-6 control-label"><fmt:message key="name_en" /></label>
			<div class="col-sm-6">
     				<input type="text" class="form-control" name="name_en" id="name_en" value="${user.name}">
    			</div>	
    		</div>
    		<div class="form-group row">	
    			<label for="surname_en"  class="col-sm-6 control-label"><fmt:message key="surname_en" /></label>
			<div class="col-sm-6">
     				<input type="text" class="form-control" name="surname_en" id="surname_en" value="${user.surname}">
    			</div>     			 
    		</div>
    		<div class="form-group row">	
    			<label for="telephon"  class="col-sm-6 control-label"><fmt:message key="telephon" /></label>
			<div class="col-sm-6">
     				<input type="text" class="form-control" name="telephon" id="telephon" value="${user.telefon}">
    			</div>    			   
    		</div>
    		<div class="form-group row">	
    			<label for="email"  class="col-sm-6 control-label"><fmt:message key="email" /></label>
			<div class="col-sm-6">
     				<input type="text" class="form-control" name="email" id="email" value="${user.email}">
    			</div>	
    		</div>
    		<div class="form-group row">	
    			<label for="photo"  class="col-sm-6 control-label"><fmt:message key="photo" /></label>
			<div class="col-sm-6">
     				<input type="file" class="form-control" name="photo" id="photo" value="${sessionScope.photoName}">
    			</div>		
		</div>		
		<div class="form-group row">
		<div class="col-sm-offset-3 col-sm-8">	
		<input type="text" hidden name="action" value="editUser">	
		<input type="text" hidden name="cash" value="${user.cash}">		
		<button type="submit" class="btn btn-success"><fmt:message key="edit" /></button>
		</div></div>
		<div class="col-xs-4">
			<div class="alert alert-danger" role="alert">      
			<fmt:message key="${sessionScope.error}"/>
			<p>${sessionScope.errorM}</p>
		</div></div>
		<div class="col-xs-4">
			<div class="alert alert-success" role="alert">
 			<fmt:message key="${sessionScope.message}" />  
		</div></div>
		</form>			
	</div>		
</div>
</div>
</body>
</html>