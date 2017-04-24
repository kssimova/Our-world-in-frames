<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/aboutSectionStyle.css></link>
	<link rel="stylesheet" type="text/css" href="css/API.css">
	<link rel="stylesheet" type="text/css" href="css/searchPage.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<title>post</title>
</head>

<script type="text/javascript">
$(function () {	
	var url = window.location.href;
	var n = url.indexOf("tags=");
	var ta = url.substring(n+5);
	var t = ta.replace("+", ",");
	var count = 0;
	var count2 = 1;
	function getRandomInt(min, max) {
	    return Math.floor(Math.random() * (max - min + 1)) + min;
	}
	
	setTimeout(function(){
		var tags = {
			tagche : t
		};

		$.ajax({
			type: "POST",
			url: 'post/tag',
			data: tags,
			success: function(tag){
				$.each(tag, function(index, value){
					$.each(value, function(i, v){
	  					console.log(i + " : " + v);
					});
					if(count2 == 1){
						$('#photos').append('<div id = "img" class="row">');
					}
					if(count2 == 4 || count2 == 7 || count2 == 11 || count2 == 14 || count2 == 16|| count2 == 18){
						$('#photos').append('</div><div id = "img" class="row">');
					}
					if(count2 == 1 || count2 == 2 || count2 == 10 || count2 ==13 || count2 == 15 || count2 == 17 || count2 == 18 || count2 == 20){
						$('#photos').append(
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
						$('#photos').append(
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
						$('#photos').append(
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
						$('#photos').append(
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
						$('#photos').append(
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
				console.log(e);
			}
		});
	}, 100);
});

</script>

<body>
	<jsp:include page="Nav.jsp" />
	

<div class="container">
	<span id = "photos"></span>
</div>

</body>
</html>