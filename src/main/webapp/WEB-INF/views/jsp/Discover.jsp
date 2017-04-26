<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href=css/aboutSectionStyle.css></link>
	<link rel="stylesheet" type="text/css" href="css/allPages.css">
	<link rel="stylesheet" type="text/css" href="css/HomePage.css">
	<link rel="stylesheet" type="text/css" href="css/searchPage.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="js/userPage.js"></script>
	<script src=js/tether.min.js></script>
<title>Hello</title>
</head>
<script type="text/javascript">
	//get all posts from all followed users
$(function () {	
	
	

	var count2 = 1;
	var count = 0;
	var	request = {
			followers: "true",
			orderBy: "time"
		};
	var	$phot = $('#phot');
	var	request1 = {
			followers: "true",
			orderBy: "notTime"
		};
	var	$phot1 = $('#pop');
		
		$.ajax({
		 	type: "POST",
		  	url: 'post/getPhotos',
		  	data: request1,
		  	dataType: "json",
		  	success: function(user){
				$.each(user, function(index, value){
				  	if(count2 == 1){
				  		$phot1.append('<div id = "img" class="row">');
						}	
					if(count2 == 4 || count2 == 7 || count2 == 11 || count2 == 14 || count2 == 16|| count2 == 18){
						$phot1.append('</div><div id = "img" class="row">');
					}
					if(count2 == 1 || count2 == 2 || count2 == 10 || count2 ==13 || count2 == 15 || count2 == 17 || count2 == 18 || count2 == 20){
						$phot1.append(
							'<div class = "col-md-5 size">' + 
								'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
									'<input type="image" src="' + value.picturePath + '" alt="Submit">' + 
										'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
											'<div class="caption">' + 
												'<p>' + 
													value.name +
												'</p>' + 
											'</div>' + 
										'</f:form>' + 
									'</div>'
						);
						count2++;
						}else if(count2 == 3 || count2 == 8 || count2 == 9 || count2 == 19){	
							$phot1.append(
								'<div class = "col-md-2 size">' +
									'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
										'<input type="image" src="' + value.picturePath + '" alt="Submit">' + 
										'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
										'<div class="caption">' + 
											'<p>' + 
												value.name +
											'</p>' + 
										'</div>' + 
									'</f:form>' + 
								'</div>' +	 	
							'</div>' 				
							);
							count2++;
						}else if(count2 == 4 || count2 == 5 || count2 == 6 || count2 == 11){	
							$phot1.append(
								'<div class = "col-md-4 size">' +
									'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
										'<input type="image" src="' + value.picturePath + '" alt="Submit">' + 
										'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
										'<div class="caption">' + 
											'<p>' + 
												value.name +
											'</p>' + 
										'</div>' + 
									'</f:form>' + 
								'</div>' +	 	
							'</div>' 				
							);
						count2++;
					}else if(count2 == 7 || count2 == 12){	
						$phot1.append(
							'<div class = "col-md-3 size">' +
								'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
									'<input type="image" src="' + value.picturePath + '" alt="Submit">' + 
									'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
									'<div class="caption">' + 
										'<p>' + 
											value.name +
										'</p>' + 
									'</div>' + 
								'</f:form>' + 
							'</div>' +	 	
						'</div>' 				
						);
						count2++;
					}else if(count2 == 14 || count2 ==16){	
						$phot1.append(
							'<div class = "col-md-7 size">' +
								'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
									'<input type="image" src="' + value.picturePath + '" alt="Submit">' + 
									'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
									'<div class="caption">' + 
										'<p>' + 
											value.name +
										'</p>' + 
									'</div>' + 
								'</f:form>' + 
							'</div>' +	 	
						'</div>' 				
						);
						count2++;
					} else if(count2 == 21){
						count2 = 1;
					};
				});	
			count++;
		  	},
		  	error: function(e){
			alert(e);
		}
	});	
		
		

		$.ajax({
		 	type: "POST",
		  	url: 'post/getPhotos',
		  	data: request,
		  	dataType: "json",
		  	success: function(user){
				$.each(user, function(index, value){
					console.log(request.followers + request.orderBy);
				  	if(count2 == 1){
				  		$phot.append('<div id = "img" class="row">');
						}	
					if(count2 == 4 || count2 == 7 || count2 == 11 || count2 == 14 || count2 == 16|| count2 == 18){
						$phot.append('</div><div id = "img" class="row">');
					}
					if(count2 == 1 || count2 == 2 || count2 == 10 || count2 ==13 || count2 == 15 || count2 == 17 || count2 == 18 || count2 == 20){
						$phot.append(
							'<div class = "col-md-5 size">' + 
								'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
									'<input type="image" src="' + value.picturePath + '" alt="Submit">' + 
										'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
											'<div class="caption">' + 
												'<p>' + 
													value.name +
												'</p>' + 
											'</div>' + 
										'</f:form>' + 
									'</div>'
						);
						count2++;
						}else if(count2 == 3 || count2 == 8 || count2 == 9 || count2 == 19){	
							$phot.append(
								'<div class = "col-md-2 size">' +
									'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
										'<input type="image" src="' + value.picturePath + '" alt="Submit">' + 
										'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
										'<div class="caption">' + 
											'<p>' + 
												value.name +
											'</p>' + 
										'</div>' + 
									'</f:form>' + 
								'</div>' +	 	
							'</div>' 				
							);
							count2++;
						}else if(count2 == 4 || count2 == 5 || count2 == 6 || count2 == 11){	
							$phot.append(
								'<div class = "col-md-4 size">' +
									'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
										'<input type="image" src="' + value.picturePath + '" alt="Submit">' + 
										'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
										'<div class="caption">' + 
											'<p>' + 
												value.name +
											'</p>' + 
										'</div>' + 
									'</f:form>' + 
								'</div>' +	 	
							'</div>' 				
							);
						count2++;
					}else if(count2 == 7 || count2 == 12){	
						$phot.append(
							'<div class = "col-md-3 size">' +
								'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
									'<input type="image" src="' + value.picturePath + '" alt="Submit">' + 
									'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
									'<div class="caption">' + 
										'<p>' + 
											value.name +
										'</p>' + 
									'</div>' + 
								'</f:form>' + 
							'</div>' +	 	
						'</div>' 				
						);
						count2++;
					}else if(count2 == 14 || count2 ==16){	
						$phot.append(
							'<div class = "col-md-7 size">' +
								'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
									'<input type="image" src="' + value.picturePath + '" alt="Submit">' + 
									'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
									'<div class="caption">' + 
										'<p>' + 
											value.name +
										'</p>' + 
									'</div>' + 
								'</f:form>' + 
							'</div>' +	 	
						'</div>' 				
						);
						count2++;
					} else if(count2 == 21){
						count2 = 1;
					};
				});	
			count++;
		  	},
		  	error: function(e){
			alert(e);
		}
	});	
});	

</script>

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
  	<div class="container">
		<span id = "pop"></span>
	</div>
</div>	
	
 	<!--  album panel     -->
<div id = "fresh" class = "panel">
	<div class="container">
		<span id = "phot"></span>
	</div>
</div>
 

<br>
<br>

</body>
</html>