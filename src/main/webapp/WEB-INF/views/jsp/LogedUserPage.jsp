<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href=css/bootstrap.min.css></link>
	<link rel="stylesheet" type="text/css" href="css/API.css">
	<link rel="stylesheet" type="text/css" href="css/UserPage.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
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
	    	    <span id = "name" class="card-title"></span>
	     	</div>
	    </div>
    </div>

<!--  first row     -->
  <div class="container">
        <div class="row visible-lg-block">
            <div class="col-lg-2 firstLarge content">
                <span class="followers" >Followers</span>
            </div>
            <div class="col-lg-2 content">
                <span class="following" >Following</span>
            </div>
            <div class="col-lg-2 content">
            	<span class="address" >Address</span>
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-md-block">
            <div class="col-md-2 first content">
                <span class="followers" >Followers</span>
            </div>
            <div class="col-md-2 content">
                <span class="following" >Following</span>
            </div>
            <div class="col-md-2 content">
            	<span class="address" >Address</span>
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-sm-block">
            <div class="col-md-3 second content">
                <span class="followers" >Followers</span>
            </div>
            <div class="col-md-3 second content">
                <span class="following" >Following</span>
            </div>
            <div class="col-md-3 second content">
            	<span class="address" >Address</span>
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-xs-block">
            <div class="col-md-2 second content">
                <span class="followers" >Followers</span>
            </div>
            <div class="col-md-2 second content">
                <span class="following" >Following</span>
            </div>
            <div class="col-md-2 second content">
            	<span class="address" >Address</span>
       		</div>
     	 </div>
	</div>
	
<!--  second row     -->
  <div class="container">
        <div class="row visible-lg-block tabs">
            <div class="col-lg-2 firstLarge">
				<input rel = "images" class = "button btn1" type="submit" value="Photos">
            </div>
            <div class="col-lg-2">
				<input rel = "albums" class = "button btn2" type="submit" value="Galleries">
            </div>
            <div class="col-lg-2">
				<input rel = "about" class = "button btn3" type="submit" value="About">
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-md-block tabs">
            <div class="col-md-2 first">
				<input rel = "images" class = "button btn1" type="submit" value="Photos">
            </div>
            <div class="col-md-2">
				<input rel = "albums" class = "button btn2" type="submit" value="Galleries">
            </div>
            <div class="col-md-2">
				<input rel = "about" class = "button btn3" type="submit" value="About">
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-sm-block tabs">
            <div class="col-md-3 second">
				<input rel = "images" class = "button btn1" type="submit" value="Photos">
            </div>
            <div class="col-md-3 second">
				<input rel = "albums" class = "button btn2" type="submit" value="Galleries">
            </div>
            <div class="col-md-3 second">
				<input rel = "about" class = "button btn3" type="submit" value="About">
       		</div>
     	 </div>
	</div>
	<div class="container">
        <div class="row visible-xs-block tabs">
            <div class="col-md-2 second">
				<input rel = "images" class = "button btn1" type="submit" value="Photos">
            </div>
            <div class="col-md-2 second">
				<input rel = "albums" class = "button btn2" type="submit" value="Galleries">
            </div>
            <div class="col-md-2 second">
				<input rel = "about" class = "button btn3" type="submit" value="About">
       		</div>
     	 </div>
	</div>		
	
	<!--  image panel     -->
<div id = "images" class = "panel active">
	<div id = "img" class="row"></div>
	
</div>

	<!--  album panel     -->
<div id = "albums" class = "panel">
	<div id = "album" class="row"></div>
</div>


<div id = "about" class = "panel">
    <div class="col-md-3 thumbnail">
  		 Description: 
    	<div class = "description thumbnail" ></div>
		<input class = "button btn3" type="submit" value="Edit">
    </div>
    <div class="col-md-3 thumbnail">
    	Mobile Number:
    	<div class = "mobileNumber thumbnail" ></div>
		<input class = "button btn3" type="submit" value="Edit">
    </div>
    <div class="col-md-3 thumbnail">
  		Birthday: 
    	<div class = "birthdate thumbnail" ></div>
		<input class = "button btn3" type="submit" value="Edit">
    </div>
    <div class="col-md-3 thumbnail">
    	Gender: 
    	<div class = "gender thumbnail" ></div>
		<input class = "button btn3" type="submit" value="Edit">
    </div>
</div>
	<script src="js/profile.js"></script>
	<script src="js/userPage.js"></script>
</body>
</html>