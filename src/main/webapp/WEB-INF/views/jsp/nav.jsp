<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/bootstrap.min.css></link>
	<link rel="stylesheet" type="text/css" href="css/API.css">
<title>Insert title here</title>
</head>
<body>
<nav role="navigation" class="navbar navbar-inverse">
	    <div class="navbar-header">
	        <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	        </button>
	        <f:form commandName="getApi" action="/ourwif/home" method = "POST">
				<input class="navbar-brand" type="submit" value="Ourwif">
			</f:form>
	    </div>
	
	    <div id="navbarCollapse" class="collapse navbar-collapse">
	        <ul class="nav navbar-nav">
	            <li class="active">
	            	<f:form commandName="getApi" action="/ourwif/api" method = "POST">
						<input class= "noMods" type="submit" value="API">
					</f:form>
				</li>
	            <li>
	            	<f:form commandName="getApi" action="/ourwif/index" method = "POST">
						<input class= "noMods" type="submit" value="Home">
					</f:form>
	            </li>
	        </ul>
	        <form role="search" class="navbar-form navbar-left">
	            <div class="form-group">
	                <input type="text" placeholder="Search" class="form-control">
	            </div>
	        </form>
	        <ul class="nav navbar-nav navbar-right">
	            <li>
	            	<f:form commandName="getApi" action="/ourwif/loginPage" method = "POST">
						<input class= "noMods" type="submit" value="login">
					</f:form>
	            </li>
	        </ul>
	    </div>
	</nav>
</body>
</html>