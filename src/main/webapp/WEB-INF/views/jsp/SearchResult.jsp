<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/aboutSectionStyle.css></link>
	<link rel="stylesheet" type="text/css" href="css/API.css">
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
	  				$('#photos').append(
	  			 	  	'<div class="col-sm-4 col-md-4">' +
	  						'<div class="thumbnail">' + 
	  							'<f:form commandName="goToPostPage" action="postView" method = "GET" align="center" >' + 
	  								'<input type="image" src="' + value.picturePath + '" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
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
	<div class="row">
		<span id = "photos"></span>
  	</div>
</div>

</body>
</html>