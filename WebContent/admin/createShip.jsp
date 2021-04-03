<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title><fmt:message key="cruise_firm" /></title>
</head>
<body>
<div class="container">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
<a class="navbar-brand" href="${pageContext.request.contextPath}/view/startPage.jsp?sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="main"></fmt:message></a>
<div class="collapse navbar-collapse">
	<ul class="navbar-nav mr-auto">
		<li class="nav-item dropdown">
		<a class="nav-link dropdown-toggle"  id="languages" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="language"></fmt:message></a>
			<div class="dropdown-menu" aria-labelledby="languages">
			<a class="dropdown-item" href="adminShip?sessionLocale=en&action=create"><fmt:message key="en" /></a>		
			<a class="dropdown-item" href="adminShip?sessionLocale=ua&action=create"><fmt:message key="ua" /></a>
			</div>
		</li>			
	</ul>	
	<form class="navbar-nav ml-auto" method="post" action="<c:url value='/logout'/>" >
	<button class="form-control mr-sm-2" type="submit" class="btn btn-default"><fmt:message key="logout" /></button> 
	</form>		
</div>
</nav>
	
<form method="post" action="<c:url value='/admin/adminShip'/>" accept-charset="UTF-8">
	<div class="form-group row">
		<label for="shipID"  class="col-sm-2 col-form-label"><fmt:message key="ship_id" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="shipID"  placeholder="Ship ID">
    		</div>
    		<label for="capacity"  class="col-sm-2 col-form-label"><fmt:message key="capacity" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="capacity" id="capacity" placeholder="Capacity">
    		</div>	
    		<label for="route"  class="col-sm-2 col-form-label"><fmt:message key="route" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="route" id="route" placeholder="Route">
    		</div>	
    		<label for="countPort"  class="col-sm-2 col-form-label"><fmt:message key="countPorts" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="countPort" id="countPort" placeholder="Count of ports">
    		</div>	
    		<label for="duration"  class="col-sm-2 col-form-label"><fmt:message key="duration" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="duration" id="duration" placeholder="Duration">
    		</div>	
    		<label for="staff"  class="col-sm-2 col-form-label"><fmt:message key="staff" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="staff" id="staff" placeholder="Staff">
    		</div>	
    		<label for="type"  class="col-sm-2 col-form-label"><fmt:message key="type" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="type" id="type" placeholder="Type of ticket">
    		</div>	
    		<label for="price"  class="col-sm-2 col-form-label"><fmt:message key="price" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="price" id="price" placeholder="Price">
    		</div>	
    		<label for="services"  class="col-sm-2 col-form-label"><fmt:message key="services" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="services" id="services" placeholder="Services">
    		</div>	
    		<label for="departure"  class="col-sm-2 col-form-label"><fmt:message key="departure" /></label>    					
 		<div class="col-sm-6">
   		<input class="form-control" type="date" value="Departure" name="departure" id="departure">
 		</div>					
	</div>
	<input type="text" hidden name="action" value="create">    
	<button type="submit" class="btn btn-default"><fmt:message key="create" /></button> 	
</form>
<div class="col-xs-4">
<div class="alert alert-danger" role="alert">
   <fmt:message key="${sessionScope.error}" />  
</div></div>
</div>
</body>
</html>