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
<script>$(document).ready(function(){if(${sessionScope.hiddenError}){$(".alert").alert('close');}});</script>
<script>$(document).ready(function(){$('.mypopover').popover();});</script>
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
	</ul>	
</div>
</nav>

	
<form method="post" action="<c:url value='/register'/>" accept-charset="UTF-8" enctype="multipart/form-data">
	<div class="form-group row">
		<label for="login"  class="col-sm-5 col-form-label my-2"><fmt:message key="login_name" /></label>
		<div class="col-sm-7 my-2">
     		<input type="text" class="form-control mypopover" data-toogle="popover" data-placement="right" title="<fmt:message key="example"/>" data-trigger="hover" data-content="login123" name="login"  placeholder="<fmt:message key="login_name" />">
    		</div>
    		<label for="password"  class="col-sm-5 col-form-label my-2"><fmt:message key="password" /></label>
		<div class="col-sm-7 my-2">
     		<input type="text" class="form-control mypopover" data-toogle="popover" data-placement="right" title="<fmt:message key="example"/>" data-trigger="hover" data-content="password123" name="password" id="password" placeholder="<fmt:message key="password" />">
    		</div>	
    		<label for="name_en"  class="col-sm-5 col-form-label my-2"><fmt:message key="name_en" /></label>
		<div class="col-sm-7 my-2">
     		<input type="text" class="form-control mypopover" data-toogle="popover" data-placement="right" title="<fmt:message key="example"/>" data-trigger="hover" data-content="Arthur" name="name_en" id="name_en" placeholder="<fmt:message key="name_en" />">
    		</div>	
    		<label for="surname_en"  class="col-sm-5 col-form-label my-2"><fmt:message key="surname_en" /></label>
		<div class="col-sm-7 my-2">
     		<input type="text" class="form-control mypopover" data-toogle="popover" data-placement="right" title="<fmt:message key="example"/>" data-trigger="hover" data-content="Connor" name="surname_en" id="surname_en" placeholder="<fmt:message key="surname_en" />">
    		</div> 
    		<label for="name_ua"  class="col-sm-5 col-form-label my-2"><fmt:message key="name_ua" /></label>
		<div class="col-sm-7 my-2">
     		<input type="text" class="form-control mypopover" data-toogle="popover" data-placement="right" title="<fmt:message key="example"/>" data-trigger="hover" data-content="Віктор" name="name_ua" id="name_ua" placeholder="<fmt:message key="name_ua" />">
    		</div>	
    		<label for="surname_ua"  class="col-sm-5 col-form-label my-2"><fmt:message key="surname_ua" /></label>
		<div class="col-sm-7 my-2">
     		<input type="text" class="form-control mypopover" data-toogle="popover" data-placement="right" title="<fmt:message key="example"/>" data-trigger="hover" data-content="Голун" name="surname_ua" id="surname_ua" placeholder="<fmt:message key="surname_ua" />">
    		</div> 	 
    		<label for="telephon"  class="col-sm-5 col-form-label my-2"><fmt:message key="telephon" /></label>
		<div class="col-sm-7 my-2">
     		<input type="text" class="form-control mypopover" data-toogle="popover" data-placement="right" title="<fmt:message key="example"/>" data-trigger="hover" data-content="000-000-00-00" name="telephon" id="telephon" placeholder="<fmt:message key="telephon" />">
    		</div> 
    		<label for="cash"  class="col-sm-5 col-form-label my-2"><fmt:message key="cash" /></label>
		<div class="col-sm-7 my-2">
     		<input type="number" class="form-control mypopover" data-toogle="popover" data-placement="right" title="<fmt:message key="example"/>" data-trigger="hover" data-content="100" name="cash" id="cash" placeholder="<fmt:message key="cash" />">
    		</div>	   
    		<label for="email"  class="col-sm-5 col-form-label my-2"><fmt:message key="email" /></label>
		<div class="col-sm-7 my-2">
     		<input type="text" class="form-control mypopover" data-toogle="popover" data-placement="right" title="<fmt:message key="example"/>" data-trigger="hover" data-content="example@example.com" name="email" id="email" placeholder="<fmt:message key="email" />">
    		</div>	
    		<label for="photo"  class="col-sm-5 col-form-label my-2"><fmt:message key="photo" /></label>
		<div class="col-sm-7 my-2 ">
     		<input type="file" class="form-control-file border border-info rounded mypopover py-2 pl-2 " data-toogle="popover" data-placement="right" title="<fmt:message key="example"/>" data-trigger="hover" data-content="Photo file" name="photo" id="photo" placeholder="<fmt:message key="photo" />">
    		</div>		
	</div>	   
	<button type="submit" class="btn btn-default bg-primary"><fmt:message key="create" /></button> 	
</form>
<div class="col-xs-4">
<div class="alert alert-danger" role="alert">      
   <fmt:message key="${sessionScope.error}"/>
   <p>${sessionScope.errorM}</p>
</div></div>

</div>
</body>
</html>