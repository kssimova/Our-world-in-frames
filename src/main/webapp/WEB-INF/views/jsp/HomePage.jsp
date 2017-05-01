<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href=css/aboutSectionStyle.css></link>
		<link rel="stylesheet" type="text/css" href="css/allPages.css">
		<link rel="stylesheet" type="text/css" href="css/UserPage.css">
		<link rel="stylesheet" type="text/css" href="css/HomePage.css">
		<link rel="stylesheet" type="text/css" href="css/searchPage.css">
		<link rel="stylesheet" type="text/css" href="css/PostView.css">
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="js/userPage.js"></script>
		<title>Home</title>
	</head>
	<script type="text/javascript">

	//get all posts from all followed users
	$(function () {	
		var liked = {
				
		};
		
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
		 		console.log(user.length);
		 		if(user.length == 0){
					$('#photos1').append("<h3 class = 'noFollowers'>Ops! You don't follow anyone.</h3>");
					$follo.append("<h3 class = 'noFollowers'>Ops! You don't follow anyone.</h3>");
		 		};
					//check if this image is already liked
					$.ajax({
				  		type: "GET",
				  		url: 'post/getLike',
				  		dataType: "json",
				  		success: function(post){	
							$.each(post, function(inde, va){	
								liked[va.postId] = false;
							});
				  		},
				  		error: function(data){
				  			alert(data);
				  		}
				  	}); 
				$.each(user, function(index, val){				
			  		$.ajax({
			  	 		type: "GET",
			 	  		url: 'user/' + val.postId,
			  			dataType: "json",
			  			success: function(post){
							$.each(liked, function(v,b){
								if(v == val.postId){
									liked[v] = true;
								}
							});
			  				if(!liked[val.postId]){
				  				$follo.append(
							 		'<li>' + 
										'<div class="timeline-badge up"><i class="fa fa-thumbs-up"></i></div>' +
										'<div class="timeline-panel" style = "background-color: #f5f5f5;">' +
											'<div class="timeline-heading">' +
												'<img class="media-object img-circle imageFloat" src="http://i.imgur.com/ADi2E7S.jpg" alt="profile" style = "height:50px;width:auto;max-width:50px;">' + 
												'<h2 class="timeline-title">'+ post.username + ' </h2>'+
											'</div>' +
											'<div class="timeline-body" align="center">' +
												'<h2>'+ val.name +' </h2>'+
												'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
													'<input type="image" src="' + val.picturePath + '" alt="Submit" class = "timeline-img">' + 
													'<input type="hidden" name = "imgId" value="' + val.postId + '" >' +
												'</f:form>' + 
												'<br>' + 
												'<h2> '+
					  								'<span class = "like">' +
														'<ul style = "list-style: none outside none; margin:0; padding: 0; text-align: cente; ">' + 
															'<li id ="panel1" rel = "panel2-'+val.postId+'" class = "heart">' + 
																'<span class="glyphicon glyphicon-heart"></span>' +
															'</li>' +
															'<li id = "panel2"  rel = "panel1-'+val.postId+'" class = "heart active">' + 
																'<span class="glyphicon glyphicon-heart-empty display:inline"></span>' +
															'</li>' +
														'</ul>' +
													'</span>' + 
												'</h2>'+
											'</div>' +
										'</div>' +
									'</li>'
									);		
				  			}else{
					  			$follo.append(
									'<li>' + 
										'<div class="timeline-badge up"><i class="fa fa-thumbs-up"></i></div>' +
										'<div class="timeline-panel" style = "background-color: #f5f5f5;">' +
											'<div class="timeline-heading">' +
												'<img class="media-object img-circle imageFloat" src="http://i.imgur.com/ADi2E7S.jpg" alt="profile" style = "height:50px;width:auto;max-width:50px;">' + 
												'<h2 class="timeline-title">'+ post.username + ' </h2>'+
											'</div>' +
											'<div class="timeline-body" align="center">' +
												'<h2>'+ val.name +' </h2>'+			
												'<f:form commandName="goToPostPage" action="postView" method = "GET">' + 
													'<input type="image" src="' + val.picturePath + '" alt="Submit" class = "timeline-img">' + 
													'<input type="hidden" name = "imgId" value="' + val.postId + '" >' +  
												'</f:form>' + 	
												'<br>'+
												'<h2> '+
													'<span class = "like">' +
														'<ul style = "list-style: none outside none; margin:0; padding: 0; text-align: center;">' + 
															'<li id ="panel1" rel = "panel2-'+val.postId+'" class = "heart active">' + 
																'<span class="glyphicon glyphicon-heart"></span>' +
															'</li>' +
															'<li id = "panel2"  rel = "panel1-'+val.postId+'" class = "heart">' + 
																'<span class="glyphicon glyphicon-heart-empty display:inline"></span>' +
															'</li>' +
														'</ul>' +
													'</span>'+
												'</h2>'+
											'</div>' +
										'</div>' +
									'</li>'
								);
				  			};
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
						if(count2 == 1 || count2 == 4 || count2 == 7 || count2 == 11 || count2 == 14 || count2 == 16|| count2 == 18){
							$('#photos1').append('<div id = "img" class="row">');
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
							count2++;
						} else if(count2 == 21){
							count2 = 1;
						};
					});		
					$('#photos1').append('<div id = "img" class="row">');
			  	},
				error: function(e){
		 			alert(e);
				 }
		});
	});	
	</script>
	<body>
		<jsp:include page="Nav.jsp" />
		<div style= "background-color:white;margin: 0 70px 0 70px;">	
		   <div class="container">
				<div class="row visible-lg-block tabs">
		             <div class="col-lg-4 firstLarge1">
		 				<input rel = "follows" class = "button btn1" type="submit" value="Following"  Style = "font-size:13pt;">
		             </div>
		             <div class="col-lg-4">
		 				<input rel = "activities" class = "button btn2" type="submit" value="Activity"  Style = "font-size:13pt;">
		             </div>
		      	 </div>
		 	</div>
		 	<div class="container">
		         <div class="row visible-md-block tabs">
		             <div class="col-md-2 first1">
		 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
		             </div>
		             <div class="col-md-2">
		 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
		             </div>	
		      	 </div>
		 	</div>
		 	<div class="container">
		         <div class="row visible-sm-block tabs">
		             <div class="col-md-3 second1">
		 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
		             </div>
		             <div class="col-md-3 second1">
		 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
		             </div>
		      	 </div>
		 	</div>
		 	<div class="container">
		         <div class="row visible-xs-block tabs">
		             <div class="col-md-3 second1">
		 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
		             </div>
		             <div class="col-md-3 second1">
		 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
		             </div>
		       	 </div>	
		 	</div>	
					
			  	<!--  follow panel     -->
			  	<!--  followed user last uploaded images -->
			<div id = "follows" class = "panel active">
				<div id = "follow" class="row"></div>
				<div class="container">
					<ul class="timeline" id ="foll">		
				    </ul>
				</div>	
			</div>
		 
		 	<!--  album panel     -->
			<div id = "activities" class = "panel">
				<span id = "photos1"></span>
	
			</div> 
		</div>
	
	</body>
</html>