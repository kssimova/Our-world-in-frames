<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/allPages.css">
		<link rel="stylesheet" type="text/css" href="css/UserPage.css">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	    	
		<title>post</title>
	</head>
	<script type="text/javascript">
	$(function () {	
		var url = window.location.href;
		var n = url.indexOf("imgId=");
		var $albumId = url.substring(n+6);
		var count = 0;
		var album = {
			albumId: $albumId
		};
		var $tags = $('#tags');
		
		$.ajax({
			type: "POST",
			url: 'album/get',
			data: album,
			success: function(albums){
	  			$('#name').html(albums.name);
	  			$('#desc').html('<h3 class="title" style = "float:left;">Description: '+albums.description+'</h3><h3 class="title" style = "float:right;">Created on: ' + albums.dateCreated.dayOfMonth +  '/' + albums.dateCreated.month + '/'+ albums.dateCreated.year + '</h3>');
	  			$('#date').html('<h3 class="title" style = "clear: both;">Photos: ' + albums.photos.length + '</h3>');
				if(albums.photos.length == 0){
					$('#photos').append("<h3 class = 'noFollowers' style='margin:0 0 45px 45px;'>Ops! You don't have photos in this album!</h3>");	
				}
				$.each(albums.photos, function(index, val){
	  	  			if(count == 0){
	  	    	  		$('#photos').append(
	  	    	  			'<div class="col-sm-4 col-md-4">' +
								'<div class="thumbnail"  style = "background-color: #f5f5f5;padding: 20px 0 0 0 ;">' + 
									'<f:form commandName="goToPostPage" action="postView" method = "GET" align="center" >' + 
										'<input type="image" src="' + val.picturePath + '" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
										'<input type="hidden" name = "imgId" value="' + val.postId + '" >' +  
										'<div class="caption">' + 
											'<p>' + 
												val.name +
											'</p>' + 
										'</div>' + 
									'</f:form>' + 
								'</div>' + 
							'</div>' 
	  	    	  		);
	  	    	  	count ++;
					}else if(count == 1){
		  	    	  	$('#photos').append(
	  	  				    '<div class="col-sm-4 col-md-4">' +
								'<div class="thumbnail"  style = "background-color: #f5f5f5;padding: 20px 0 0 0 ;">' + 
									'<f:form commandName="goToPostPage" action="postView" method = "GET" align="center" >' + 
										'<input type="image" src="' + val.picturePath + '" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
										'<input type="hidden" name = "imgId" value="' + val.postId + '" >' +  
										'<div class="caption">' + 
											'<p>' + 
												val.name +
											'</p>' + 
										'</div>' + 
									'</f:form>' + 
								'</div>' + 
	  	  			       	'</div>'
						);
					count ++;
					}else if(count == 2){
		  	    	  	$('#photos').append(
	  	  			        '<div class="col-sm-4 col-md-4">' +
								'<div class="thumbnail"  style = "background-color: #f5f5f5;padding: 20px 0 0 0 ;">' + 
									'<f:form commandName="goToPostPage" action="postView" method = "GET" align="center" >' + 
										'<input type="image" src="' + val.picturePath + '" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
										'<input type="hidden" name = "imgId" value="' + val.postId + '" >' +  
										'<div class="caption">' + 
											'<p>' + 
												val.name +
											'</p>' + 
										'</div>' + 
									'</f:form>' + 
								'</div>' + 
	   	  			        '</div>'
						);
					count = 0;
					}				
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
		<div style= "background-color:white;margin: 0 70px 0 70px;">
			<h1 id= "name" class="title" ></h1>
			<div class="col-md-11" style = "background-color: #f5f5f5;margin: 0 0 0 45px;">
				<p id = "desc"></p>
				<p id = "date"></p>
				<p id = "photoNum"></p>
			</div>
			    <br>    <br>
			    <div align="center" >
				    <f:form commandName="goToUpload" action="upload" method = "POST">
				    <button class="btn btn btn-circle text-uppercase uploadButton " type="submit"><span class="glyphicon glyphicon-upload"></span> Upload new photo</button>
					</f:form>
				</div>
			    <br>    <br>
			
			<div class="container">
				<div class="row">
					<span id = "photos"></span>
			  	</div>
			</div>
		</div>
	
	 	<p style = "padding: 0 0 1px 0">.</p>
	</body>
</html>