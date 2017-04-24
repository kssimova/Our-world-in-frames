<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href=css/aboutSectionStyle.css></link>
	<link rel="stylesheet" type="text/css" href="css/HomePage.css">
	<link rel="stylesheet" type="text/css" href="css/Discover.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="js/userPage.js"></script>
	<script src=js/tether.min.js></script>
<title>Hello</title>
</head>
<body>
	<jsp:include page="Nav.jsp" />
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '792399230917035',
      xfbml      : true,
      version    : 'v2.8'
    });
    FB.AppEvents.logPageView();
  };
  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
<div
  class="fb-like"
  data-share="true"
  data-width="450"
  data-show-faces="true">
</div>	
	
	 <!--  second row     -->
   <div class="container">
         <div class="row visible-lg-block tabs">
             <div class="col-lg-2 firstLarge">
 				<input rel = "popular" class = "button btn1" type="submit" value="Popular">
             </div>
             <div class="col-lg-2">
 				<input rel = "fresh" class = "button btn2" type="submit" value="Fresh">
             </div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-md-block tabs">
             <div class="col-md-2 first">
 				<input rel = "popular" class = "button btn1" type="submit" value="Popular">
             </div>
             <div class="col-md-2">
 				<input rel = "fresh" class = "button btn2" type="submit" value="Fresh">
             </div>

      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-sm-block tabs">
             <div class="col-md-3 second">
 				<input rel = "popular" class = "button btn1" type="submit" value="Popular">
             </div>
             <div class="col-md-3 second">
 				<input rel = "fresh" class = "button btn2" type="submit" value="Fresh">
             </div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-xs-block tabs">
             <div class="col-md-3 second">
 				<input rel = "popular" class = "button btn1" type="submit" value="Popular">
             </div>
             <div class="col-md-3 second">
 				<input rel = "fresh" class = "button btn2" type="submit" value="Fresh">
             </div>
       	 </div>	
 	</div>	
	
	
	  	<!--  image panel     -->
  <div id = "popular" class = "panel active">
  
  <!--   template 1 -->
 	<div id = "img" class="row">
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>		
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/ZrF9do6.jpg" />
		</div>	 	
 	</div>
 	
 	  <!--   template 2 -->
 	 <div id = "img" class="row">
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/ZrF9do6.jpg" />
		</div>
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
	</div>
	
	 	  <!--   template 3 -->
  	<div id = "img" class="row">
		<div class = "col-md-3 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/kMUgnfZ.jpg" />
		</div>
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/xDKN6kn.jpg" />
		</div>
	</div>
	
		 	  <!--   template 4 -->
  	<div id = "img" class="row">
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-3 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/xDKN6kn.jpg" />
		</div>
	</div>
	
			 	  <!--   template 5 -->
  	<div id = "img" class="row">
		<div class = "col-md-7 size">
			<img src="http://i.imgur.com/ZrF9do6.jpg" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
	</div>
	
		 	  <!--   template 6 -->
  	<div id = "img" class="row">
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/ZrF9do6.jpg" />
		</div>
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
	</div>
	
	
	
	
 </div>
 
 	<!--  album panel     -->
 <div id = "fresh" class = "panel">
 	<div id = "album" class="row"></div>

			 	  <!--   template 5 -->
  	<div id = "img" class="row">
		<div class = "col-md-7 size">
			<img src="http://i.imgur.com/ZrF9do6.jpg" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
	</div>
	
		 	  <!--   template 6 -->
  	<div id = "img" class="row">
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/ZrF9do6.jpg" />
		</div>
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
	</div>
	 	  <!--   template 1 -->
 	<div id = "img" class="row">
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>		
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/ZrF9do6.jpg" />
		</div>	 	
 	</div>
 	
 	  <!--   template 2 -->
 	 <div id = "img" class="row">
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/ZrF9do6.jpg" />
		</div>
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
	</div>
	
	 	  <!--   template 3 -->
  	<div id = "img" class="row">
		<div class = "col-md-3 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/kMUgnfZ.jpg" />
		</div>
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/xDKN6kn.jpg" />
		</div>
	</div>
	
		 	  <!--   template 4 -->
  	<div id = "img" class="row">
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-3 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/xDKN6kn.jpg" />
		</div>
	</div>
	
	
 </div>


<br>
<br>

</body>
</html>