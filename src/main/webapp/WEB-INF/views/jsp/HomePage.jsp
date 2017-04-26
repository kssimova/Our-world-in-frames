<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/aboutSectionStyle.css></link>
	<link rel="stylesheet" type="text/css" href="css/allPages.css">
	<link rel="stylesheet" type="text/css" href="css/HomePage.css">
	<link rel="stylesheet" type="text/css" href="css/searchPage.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="js/userPage.js"></script>
	<script src="js/likePhoto.js"></script>
	
<title>Home</title>
</head>
<script type="text/javascript">
	//get all posts from all followed users
$(function () {	
	
	var request = {
		followers: "true",
		orderBy: "time"
	}
	var $follo = $('#foll'); 
	$.ajax({
	 	type: "POST",
	 	url: 'post/getPhotos',
	 	data: request,
	 	dataType: "json",
	 	success: function(user){
			console.log(user);
			$.each(user, function(index, val){
		  		$.ajax({
		  	 		type: "GET",
		 	  		url: 'user/' + val.postId,
		  			dataType: "json",
		  			success: function(post){	;
				  		$follo.append(
					 		'<li>' + 
								'<div class="timeline-badge up"><i class="fa fa-thumbs-up"></i></div>' +
								'<div class="timeline-panel">' +
									'<div class="timeline-heading">' +
										'<h4 class="timeline-title">'+ post.username +' uploaded: '+ val.name +' </h4>'+
									'</div>' +
									'<div class="timeline-body">' +
										'<img src="'+ val.picturePath +'" style = "max-width: 100%;" />' +
									'</div>' +
								'</div>' +
							'</li>'
						);
		  	  		},
		  	  		error: function(data){
		  	  			alert(data);
		  	  		}
		  	  	});  
			});
		},
		error: function(e){
			alert(e);
	  	}
 	});		
	
	var request1 = {
			followers: "true",
			orderBy: "notTime"
		}
	var count2 = 1;
		var $follo = $('#foll'); 
		$.ajax({
		 	type: "POST",
		  	url: 'post/getPhotos',
		  	data: request1,
		  	dataType: "json",
		  	success: function(user){
				$.each(user, function(i, value){
					console.log(i + ': '+ value);
				  	if(count2 == 1){
						$('#photos1').append('<div id = "img" class="row">');
					}
					if(count2 == 4 || count2 == 7 || count2 == 11 || count2 == 14 || count2 == 16|| count2 == 18){
						$('#photos1').append('</div><div id = "img" class="row">');
					}
					if(count2 == 1 || count2 == 2 || count2 == 10 || count2 ==13 || count2 == 15 || count2 == 17 || count2 == 18 || count2 == 20){
						$('#photos1').append(
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
						console.log(count2);
						count2++;
					}else if(count2 == 3 || count2 == 8 || count2 == 9 || count2 == 19){	
						$('#photos1').append(
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
						console.log(count2);
						count2++;
					}else if(count2 == 4 || count2 == 5 || count2 == 6 || count2 == 11){	
						$('#photos1').append(
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
						console.log(count2);
						count2++;
					}else if(count2 == 7 || count2 == 12){	
						$('#photos1').append(
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
						console.log(count2);
						count2++;
					}else if(count2 == 14 || count2 ==16){	
						$('#photos1').append(
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
						console.log(count2);
						count2++;
					} else if(count2 == 21){
						count2 = 1;
					};
				});			
		  	},
			error: function(e){
	 			alert(e);
			 }
	});
});	

</script>

<body>
	<jsp:include page="Nav.jsp" />
	
	
	 <!--  second row     -->
   <div class="container">

		<div class="row visible-lg-block tabs">
             <div class="col-lg-2 firstLarge">
 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
             </div>
             <div class="col-lg-2">
 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
             </div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-md-block tabs">
             <div class="col-md-2 first">
 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
             </div>
             <div class="col-md-2">
 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
             </div>

      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-sm-block tabs">
             <div class="col-md-3 second">
 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
             </div>
             <div class="col-md-3 second">
 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
             </div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-xs-block tabs">
             <div class="col-md-3 second">
 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
             </div>
             <div class="col-md-3 second">
 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
             </div>
       	 </div>	
 	</div>	
	
	
	  	<!--  follow panel     -->
	  	<!--  followed user last uploaded images -->
  <div id = "follows" class = "panel active">
 	<div id = "follow" class="row"></div>
<div class="container">
    <ul class="timeline">
		<span id ="foll"></span>
		
    </ul>
</div>

 </div>
 
 	<!--  album panel     -->
<div id = "activities" class = "panel">
		<span id = "photos1"></span>
</div>
 
</body>
</html>