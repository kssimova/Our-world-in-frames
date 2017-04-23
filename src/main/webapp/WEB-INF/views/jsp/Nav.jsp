<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/bootstrap.css></link>
	<link rel="stylesheet" type="text/css" href="css/API.css">
	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.0.min.js"></script>
	<script src="js/profile.js"></script>
<title>Insert title here</title>
</head>
<body>
	<nav role="navigation" class="navbar navbar-inverse">
	    <div id="navbarCollapse" class="collapse navbar-collapse">
	        <ul class="nav navbar-nav">
				<li>
	        	    <div class="navbar-header">
	       				 <f:form commandName="getApi" action="/ourwif/home" method = "POST">
							<input class="noMods" type="submit" value="OURWIF">
						</f:form>
	   				 </div>
				</li>
	            <li>
	            	<f:form commandName="getApi" action="/ourwif/api" method = "POST">
						<input class= "noMods" type="submit" value="API">
					</f:form>
				</li>
	            <li>
	            	<f:form commandName="getApi" action="/ourwif/index" method = "POST">
						<input class= "noMods" type="submit" value="HOME">
					</f:form>
	            </li>
	        </ul>
	        <f:form class="navbar-form navbar-left" action="search" method = "GET">
	            <input type="text" placeholder="Search" class="form-control" name = "tags">
			</f:form>
	        <ul id = "navRightBar" class="nav navbar-nav navbar-right">
	        	 <li>
	            	<f:form commandName="goToUpload" action="upload" method = "POST">
						<input class= "noMods" type="submit" value="UPLOAD">
					</f:form>
	            </li>
	          	<li>
	          		<f:form commandName="profile" action="/ourwif/profile" method = "POST">
						<input class= "noMods" type="submit" value="PROFILE">	
					</f:form>
	            </li>
	            <li>
	            	<f:form commandName="goToProfile" action="user/logout" method = "GET">
						<input class= "noMods" type="submit" value="LOGOUT">
					</f:form>
	            </li>
	        </ul>
	    </div>
	</nav>
	
</body>
</html>