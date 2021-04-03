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
			<a class="dropdown-item" href="?sessionLocale=en&action=list"><fmt:message key="en" /></a>		
			<a class="dropdown-item" href="?sessionLocale=ua&action=list"><fmt:message key="ua" /></a>
			</div>
		</li>		
		<li class="nav-item"><a class="nav-link" href="adminShip?action=list&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="ships" /></a></li>
 		<li class="nav-item"><a class="nav-link" href="adminExcursion?action=list&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="excursions" /></a></li>
 		<li class="nav-item"><a class="nav-link" href="adminExcursion?action=createShipExc&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="createSE" /></a></li>
	</ul>	
	<form class="navbar-nav ml-auto" method="post" action="<c:url value='/logout'/>" >
	<button class="form-control mr-sm-2" type="submit" class="btn btn-default"><fmt:message key="logout" /></button> 
	</form>		
</div>
</nav>

<table class="table table-condensed">
	<thead>
		<tr>
			<th><fmt:message key="ship_id" /></th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy"/>
        				<input type="text" hidden name="orderBy" value="capacity"/>
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="capacity" /></button>  
        			</form>
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="route">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="route" /></button> 
        			</form> 
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="countPort">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="countPorts" /></button>
        			</form>  
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="duration">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="duration" /></button> 
        			</form> 
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="staff">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="staff" /></button>  
        			</form>
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="type">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="type" /></button> 
        			</form> 
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="price">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="price" /></button>  
        			</form>
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="services">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="services" /></button>  
        			</form>
			</th>		
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="departure">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="departure" /></button> 
        			</form> 
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="ship" items="${ships}"> 					
   			<tr>
    				<th><c:out value="${ship.shipID}"/></th>   
        				<th><c:out value="${ship.capacity}"/></th>             
       				<th><c:out value="${ship.route}"/></th>         
       				<th><c:out value="${ship.countPort}"/></th>          
        				<th><c:out value="${ship.duration}"/></th>           
        				<th><c:out value="${ship.staff}"/></th>         
        				<th><c:out value="${ship.type}"/></th> 
        				<th><c:out value="${ship.price}"/></th> 
        				<th><c:out value="${ship.services}"/></th>  
        				<th><c:out value="${ship.departure}"/></th>       
        				<th>
        				<form method="get" action="<c:url value='/admin/adminShip'/>" accept-charset="UTF-8">
        				<input type="text" hidden name="action" value="edit">
        				<input type="text" hidden name="shipID" value="${ship.shipID}">
        				<button type="submit" class="btn btn-default"><fmt:message key="edit" /></button>  
        				</form>
        				</th>   
        				<th>
        				<form method="get" action="<c:url value='/admin/adminShip'/>" accept-charset="UTF-8">
        				<input type="text" hidden name="action" value="delete">
        				<input type="text" hidden name="shipID" value="${ship.shipID}">
        				<button type="submit" class="btn btn-default"><fmt:message key="delete" /></button>  
        				</form>
        				</th>         				  				     
       			</tr>			 
		</c:forEach>		
	</tbody>
</table>
<form method="get" action="<c:url value='/admin/adminShip'/>" accept-charset="UTF-8"> 
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