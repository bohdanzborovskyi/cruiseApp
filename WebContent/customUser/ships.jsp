<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib   prefix="tag" uri="/WEB-INF/colortag.tld" %>
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
<script>$(document).ready(function call(x){if(x<100) {return "bg-warning";} else {return "bg-success";}});</script>
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
			<a class="dropdown-item" href="?sessionLocale=en&action=listShips"><fmt:message key="en" /></a>		
			<a class="dropdown-item" href="?sessionLocale=ua&action=listShips"><fmt:message key="ua" /></a>
			</div>
		</li>		
		<li class="nav-item"><a class="nav-link" href="?action=userListShips&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="myShips" /></a></li>
 		<li class="nav-item"><a class="nav-link" href="?action=userListExcursions&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="myExcursions" /></a></li>
 		<li class="nav-item"><a class="nav-link" href="?action=listShips&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="ships" /></a></li>
 		<li class="nav-item"><a class="nav-link" href="?action=listExcursions&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="excursions" /></a></li>
 		<li class="nav-item"><a class="nav-link" href="?action=availableListExcursions&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="available_excursions" /></a></li>		
	 	<li class="nav-item"><a class="nav-link" href="?action=download&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="download" /></a></li>		
	</ul>		
	<a href="?action=editUser&sessionLocale=${sessionScope.sessionLocale}">	
	<img src="${sessionScope.photo}" alt=""  width="50" height="50" class="rounded-circle mr-1"></a>
	<li class="nav-item"><span class="badge badge-pill badge-primary"><fmt:message key="currentCash" />${sessionScope.cash}</span></li>
	<form class="navbar-nav ml-auto" method="post" action="<c:url value='/logout'/>" >
	<button class="form-control mr-sm-2" type="submit" class="btn btn-default"><fmt:message key="logout" /></button> 
	</form>		
</div>
</nav>
<table class="table table-condensed table-responsive col">
	<thead>
		<tr>
			<th><fmt:message key="ship_id" /></th>
			<th>
			<form method="get" action="<c:url value='/customUser'/>" >
        				<input type="text" hidden name="action" value="listBy"/>
        				<input type="text" hidden name="orderBy" value="capacity"/>
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="capacity" /></button>  
        			</form>
			</th>
			<th>
			<form method="get" action="<c:url value='/customUser'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="route">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="route" /></button> 
        			</form> 
			</th>
			<th>
			<form method="get" action="<c:url value='/customUser'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="countPort">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="countPorts" /></button>
        			</form>  
			</th>
			<th>
			<form method="get" action="<c:url value='/customUser'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="duration">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="duration" /></button> 
        			</form> 
			</th>
			<th>
			<form method="get" action="<c:url value='/customUser'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="staff">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="staff" /></button>  
        			</form>
			</th>
			<th>
			<form method="get" action="<c:url value='/customUser'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="type">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        					<button type="submit" class="btn btn-default"><fmt:message key="type" /></button> 
        			</form> 
			</th>
			<th>
			<form method="get" action="<c:url value='/customUser'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="price">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="price" /></button>  
        			</form>
			</th>
			<th>
			<form method="get" action="<c:url value='/customUser'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="services">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="services" /></button>  
        			</form>
			</th>		
			<th>
			<form method="get" action="<c:url value='/customUser'/>" >
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
        				<form method="get" action="<c:url value='/customUser'/>" accept-charset="UTF-8">
        				<input type="text" hidden name="action" value="buyShip">
        				<input type="text" hidden name="shipID" value="${ship.shipID}">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}">
        				<button type="submit" class="btn btn-default"><fmt:message key="buy" /></button>  
        				</form>
        				</th>  
        				<th>        				
        				<div class="progress" >         	
        					<input type="text" hidden name="percent" id="percent" value="${ship.price}"/>        												
        					<div  class="progress-bar <tag:tag percent="${(sessionScope.cash/ship.price)*100}"/>" aria-valuenow="${(sessionScope.cash/ship.price)*100}" aria-valuemin="0" aria-valuemax="100" style="width:${(sessionScope.cash/ship.price)*100}%">${(sessionScope.cash/ship.price)*100}%</div>
        				</div>        				
        				</th>      				        				  				     
       			</tr>			 
		</c:forEach>		
	</tbody>
</table>
<form  method="post" action="<c:url value='/customUser'/>" accept-charset="UTF-8"> 
	<div class="col-sm-4">
	<input type="number" class="form-control" name="cash" placeholder="Add money to your cash account">
	<input type="text" hidden name="action" value="addCash">
	<input type="text" hidden name="page" value="ship">    
	<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}">   
	<button type="submit" class="btn btn-default"><fmt:message key="addCash" /></button> 
	</div>
</form> 

<div class="col-xs-4">
<div class="alert alert-danger" role="alert">
   <fmt:message key="${sessionScope.error}" />  
</div></div>

</div>
</body>
</html>