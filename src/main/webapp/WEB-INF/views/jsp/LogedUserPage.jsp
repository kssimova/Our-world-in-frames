<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/bootstrap.min.css></link>
	<link rel="stylesheet" type="text/css" href="css/API.css">
	<link rel="stylesheet" type="text/css" href="css/UserPage.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="js/profile.js"></script>
<title>user</title>
</head>
<body>
	<jsp:include page="LogedNav.jsp" />
	
	<div class="col-lg-12 col-md-12 col-sm-12">
	    <div class="card hovercard">
	        <div class="card-background">
	            <img class="card-bkimg" alt="" src= "http://i.imgur.com/fK51fmR.jpg" >
	            <!-- http://lorempixel.com/850/280/people/9/ -->
	        </div>
	        <div class="useravatar">
	            <img alt="" src="http://i.imgur.com/fK51fmR.jpg">
	        </div>
	    	<div class="card-info"> 
	    	    <span id = "name" class="card-title">Pamela Anderson</span>
	     	</div>
	    </div>
    </div>

<!--  first row     -->
  <div class="container">
        <div class="row visible-lg-block">
            <div class="col-lg-2 first">
                <div class="content" id ="followers">Followers</div>
            </div>
            <div class="col-lg-2">
                <div class="content" id ="following">Following</div>
            </div>
            <div class="col-lg-2">
            	<div class="content" id ="Address">Address</div>
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-md-block">
            <div class="col-md-2 first">
                <div class="content" id ="followers">Followers</div>
            </div>
            <div class="col-md-2">
                <div class="content" id ="following">Following</div>
            </div>
            <div class="col-md-2">
            	<div class="content" id ="Address">Address</div>
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-sm-block">
            <div class="col-md-3 second">
                <div class="content" id ="followers">Followers</div>
            </div>
            <div class="col-md-3 second">
                <div class="content" id ="following">Following</div>
            </div>
            <div class="col-md-3 second">
            	<div class="content" id ="Address">Address</div>
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-xs-block">
            <div class="col-md-2 second">
                <div class="content" id ="followers">Followers</div>
            </div>
            <div class="col-md-2 second">
                <div class="content" id ="following">Following</div>
            </div>
            <div class="col-md-2 second">
            	<div class="content" id ="Address">Address</div>
       		</div>
     	 </div>
	</div>
	
<!--  second row     -->
  <div class="container">
        <div class="row visible-lg-block">
            <div class="col-lg-2 first">
                <f:form action="/ourwif/api" method = "POST">
					<input class = "button" type="submit" value="Photos">
				</f:form>
            </div>
            <div class="col-lg-2">
                 <f:form action="/ourwif/index" method = "POST">
					<input class = "button" type="submit" value="Galleries">
				</f:form>
            </div>
            <div class="col-lg-2">
            	 <f:form action="/ourwif/index" method = "POST">
					<input class = "button" type="submit" value="About">
				</f:form>
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-md-block">
            <div class="col-md-2 first">
               	<f:form action="/ourwif/api" method = "POST">
					<input class = "button" type="submit" value="Photos">
				</f:form>
            </div>
            <div class="col-md-2">
                 <f:form action="/ourwif/index" method = "POST">
					<input class = "button" type="submit" value="Galleries">
				</f:form>
            </div>
            <div class="col-md-2">
            	 <f:form action="/ourwif/index" method = "POST">
					<input class = "button" type="submit" value="About">
				</f:form>
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-sm-block">
            <div class="col-md-3 second">
                <f:form action="/ourwif/api" method = "POST">
					<input class = "button" type="submit" value="Photos">
				</f:form>
            </div>
            <div class="col-md-3 second">
                 <f:form action="/ourwif/index" method = "POST">
					<input class = "button" type="submit" value="Galleries">
				</f:form>
            </div>
            <div class="col-md-3 second">
            	 <f:form action="/ourwif/index" method = "POST">
					<input class = "button" type="submit" value="About">
				</f:form>
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-xs-block">
            <div class="col-md-2 second">
                <f:form action="/ourwif/api" method = "POST">
					<input class = "button" type="submit" value="Photos">
				</f:form>
            </div>
            <div class="col-md-2 second">
                 <f:form action="/ourwif/index" method = "POST">
					<input class = "button" type="submit" value="Galleries">
				</f:form>
            </div>
            <div class="col-md-2 second">
            	 <f:form action="/ourwif/index" method = "POST">
					<input class = "button" type="submit" value="About">
				</f:form>
       		</div>
     	 </div>
	</div>	



	
</body>
</html>