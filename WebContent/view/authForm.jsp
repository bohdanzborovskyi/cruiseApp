<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.sessionLocale}"/>
<fmt:setBundle basename="resources.loc"/>
<html lang="${sessionScope.sessionLocale}">
<head>
<meta charset="UTF-8">
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

<div class="container ">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
<a class="navbar-brand" href="${pageContext.request.contextPath}/view/startPage.jsp?sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="main"></fmt:message></a>
<div class="collapse navbar-collapse">
	<ul class="navbar-nav mr-auto">
		<li class="nav-item dropdown">
		<a class="nav-link dropdown-toggle"  id="languages" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="language"></fmt:message></a>
			<div class="dropdown-menu" aria-labelledby="languages">
			<a class="dropdown-item" href="?sessionLocale=en"><fmt:message key="en" /></a>		
			<a class="dropdown-item"  href="?sessionLocale=ua"><fmt:message key="ua" /></a>
			</div>
		</li>
	</ul>		
</div>
</nav>

<form method="post" action="<c:url value='/auth'/>">
	<div class="form-group row">
		<label for="login"  class="col-sm-12 col-form-label"><fmt:message key="login_name" />:</label>
		<div class="col-sm-3">
     		<input type="text" class="form-control" name="login">
    		</div>    		
    		<label for="password"  class="col-sm-12 col-form-label"><fmt:message key="password" />:</label>
		<div class="col-sm-3">
     		<input type="password" class="form-control" name="password">
    		</div>	    			    			
	</div>	 
	<button type="submit" class="btn btn-default"><fmt:message key="login"/></button> 	
</form>

<form method="get" action="<c:url value='/register'/>"> 	    
	<button type="submit" class="btn btn-default"><fmt:message key="registration"/></button>      
</form>

<form method="get" action="<c:url value='/admin/adminShip'/>"> 
	<input type="text" hidden name="action" value="create">    
	<button type="submit" class="btn btn-default"><fmt:message key="create"/></button>      
</form>


<div class="col-xs-4">
<div class="alert alert-danger" role="alert">
   <fmt:message key="${sessionScope.errorMessage}" />  
</div></div>

<div class="col-xs-4">
<div class="alert alert-success" role="alert">
   <fmt:message key="${sessionScope.message}" />  
</div></div>

</div>

</body>
</html>