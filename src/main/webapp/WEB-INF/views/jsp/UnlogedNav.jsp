<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/bootstrap.min.css></link>
	<link rel="stylesheet" type="text/css" href="css/NavPage.css">
	<script src=js/login.js></script>
<title>Insert title here</title>
</head>
<body>
<nav role="navigation" class="navbar navbar-inverse">
	    <div class="navbar-header">
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
	            <li>
	            	<form role="search" class="navbar-form navbar-left">
	          		   <div class="form-group">
	             		   <input type="text" placeholder="Search" class="form-control">
	         		   </div>
	    		    </form>
	            </li>       
	        </ul>
	        <ul id = "navRightBar" class="nav navbar-nav navbar-right">
	        </ul>
	    </div>
	</nav>
	
	
	  <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Our world in frames</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
					 <li>
	            		<f:form commandName="logIn" action="/ourwif/loginPage" method = "POST">
							<input class= "noMods" type="submit" value="LOGIN">
						</f:form>
	         	   </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
	
	
</body>
</html>