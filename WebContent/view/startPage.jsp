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
			<a class="dropdown-item" href="?sessionLocale=en"><fmt:message key="en" /></a>		
			<a class="dropdown-item"  href="?sessionLocale=ua"><fmt:message key="ua" /></a>
			</div>
		</li>
		<li>
		<button type="button " class="btn-info py-2" data-toggle="modal" data-target="#modal"><fmt:message key="welcome"></fmt:message></button>
		<div class="modal" id="modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header"><h4 class="modal-title"><fmt:message key="greetings"></fmt:message></h4>
						<button type="button" class="close" data-dismiss="modal">&times</button>
					</div>					
					<div class="modal-body"><fmt:message key="welcome_form"></fmt:message></div>
					<div class="modal-footer">
						<button class="btn btn-info" data-dismiss="modal"><fmt:message key="close"></fmt:message></button>
					</div>
				</div>
			</div>
		</div>		
		</li>
	</ul>	
	<form class="form-inline my-2 my-lg-0" method="get" action="<c:url value='/auth'/>" >		
		<button class="form-control mr-sm-2" type="submit" class="btn btn-default"><fmt:message key="registration" /></button>  		
	</form>
</div>
</nav>

<h1 class=text-center><fmt:message key="cruise_firm" /></h1>
<h3 class=text-center><fmt:message key="cruises" /></h3>

<div class="bd-example">
  <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="images/nature1.jpg" class="d-block w-100" height="720" width="1080" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5><fmt:message key="mountain"/></h5>
          <p><fmt:message key="mountain_des"/></p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="images/nature2.jpg" class="d-block w-100" height="720" width="1080" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5><fmt:message key="lake"/></h5>
          <p><fmt:message key="lake_des"/></p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="images/nature3.jpg" class="d-block w-100" height="720" width="1080" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5><fmt:message key="exonature"/></h5>
          <p><fmt:message key="exonature_des"/></p>
        </div>
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

<table class="table table-condensed">
	<thead>
		<tr>			
			<th><fmt:message key="capacity" /></th>
			<th><fmt:message key="route" /></th>
			<th><fmt:message key="countPorts" /></th>
			<th><fmt:message key="duration" /></th>
			<th><fmt:message key="staff" /></th>
			<th><fmt:message key="type" /></th>
			<th><fmt:message key="price" /></th>
			<th><fmt:message key="services" /></th>
			<th><fmt:message key="departure" /></th>
						
		</tr>
	</thead>
	<tbody>
		<c:forEach var="ship" items="${ships}"> 					
   			<tr>    				
        				<th><c:out value="${ship.capacity}"/></th>             
       				<th><c:out value="${ship.route}"/></th>         
       				<th><c:out value="${ship.countPort}"/></th>          
        				<th><c:out value="${ship.duration}"/></th>           
        				<th><c:out value="${ship.staff}"/></th>         
        				<th><c:out value="${ship.type}"/></th> 
        				<th><c:out value="${ship.price}"/></th> 
        				<th><c:out value="${ship.services}"/></th>  
        				<th><c:out value="${ship.departure}"/></th>            				       				        				  				     
       			</tr>			 
		</c:forEach>		
	</tbody>
</table>

</div>
</body>
</html>