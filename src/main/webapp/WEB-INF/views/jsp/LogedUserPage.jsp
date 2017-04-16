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
	<script src="js/profile.js"></script>
<title>user</title>
</head>
<body>
<script type="text/javascript">
window.onload = function() {
	var $username = $("#username");
	var $country = $("#username");
	var $descriprion = $("#password");
	var $birthdate = $("#hello");
	var $gender= $("#hello");
	var $mobileNumber = $("#username");
	var $profilePhotoPath = $("#password");	

	
	
	$.ajax({
		type: "POST",
		url: 'user/profile',
		dataType: "json",
		success: function(user){
			$('#name').html(user.firstName + " " + user.lastName);
			$('.followers').html("Followers: " + user.followers.length);
			$('.following').html("Following: " + user.following.length);
			$('.address').html(user.country + " " +  user.city);
			
		},
		error: function(data){
			console.log(data);
			alert();
		}
	});
};
</script>
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
	
	
	
<div id = "images" class = "panel active">
	<div class="row">
	  <div class="col-md-4">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Lights" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div>
	  <div class="col-md-4">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Nature" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div>
	  <div class="col-md-4">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Fjords" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div>
	</div>
	
	<div class="row">
	  <div class="col-md-6">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Lights" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div>
	  <div class="col-md-6">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Nature" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div> 
	</div>
</div>



<div id = "albums" class = "panel">
		<div class="row">
	  <div class="col-md-4">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Lights" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div>
	  <div class="col-md-4">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Nature" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div>
	  <div class="col-md-4">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Fjords" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div>
	</div>
	
		<div class="row">
	  <div class="col-md-4">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Lights" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div>
	  <div class="col-md-4">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Nature" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div>
	  <div class="col-md-4">
	    <div class="thumbnail">
	      <a href="http://i.imgur.com/IFMSjda.jpg">
	        <img src="http://i.imgur.com/IFMSjda.jpg" alt="Fjords" style="width:100%">
	        <div class="caption">
	          <p>Lorem ipsum...</p>
	        </div>
	      </a>
	    </div>
	  </div>
	</div>
</div>


<div id = "about" class = "panel">

some text


</div>


<script type="text/javascript">
	
	$(function() {
	   $('.tabs input').on('click', function() {
			//find button thath was clicked
		   var panelToShow = $(this).attr('rel');
			//hide current active panel
			$('.panel.active').slideUp(300, function(){
				$('#' + panelToShow).slideDown(300, function(){
					//add active class
					$(this).addClass('active');	
				});			
			});	
	    });
	});
	
	//TODO make img dynamic
</script>
	
</body>
</html>