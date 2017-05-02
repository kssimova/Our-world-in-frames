<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/aboutSectionStyle.css></link>
	<link rel="stylesheet" type="text/css" href="css/allPages.css">
	<link rel="stylesheet" type="text/css" href="css/UserPage.css">
	<link rel="stylesheet" type="text/css" href="css/upload.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="js/upload.js"></script>
	
	
	
<title>upload</title>
</head>

	<body>
		<jsp:include page="Nav.jsp" />
		<div class = "row">
			<div class = "col-sm-4" style= "background-color: #eeeeee;margin: 4% 0 0 8%;">
				<div class="form-top-left" >
					<h3 style = "font-family: Alegreya Sans; font-size: 34px; margin: 5% 5% 5% 5%;">Image upload <span class="glyphicon glyphicon-pencil"></span></h3>
				</div>
				
				<div class = "inputs" style = "margin: 5% 0 0 0;" >
					
					Name: <br>
					<input id= "name" type="text" placeholder="name..." class="form-username form-control">
					<ul id = "nameError"></ul>
					Description: <br>
					<input id = "description" type="text" placeholder="description..."  class="form-username form-control">
					<ul id = "descError"></ul>
					Album: <br>
					<select id = "albums"  class="form-username form-control"></select>  
					or create new album
					<ul id = "albumError"></ul>
					Tags:  <br>
					<input id = "tags" type="text" placeholder="Red, roses... " class="form-username form-control" >
					/please enter your image tags separated with a comma/
					<ul id = "tagError"></ul>
				</div>
				<div id="main">
					<div class="form-top-left" >
						<h3 style = "font-family: Alegreya Sans; font-size: 30px; margin: 5% 5% 5% 5%;">Select your image</h3>
					</div>
					<form method="post" enctype="multipart/form-data"  action="upload.php" >
						<input type="file" name="images" id="images" multiple  required/>
						<ul id = "typeError"></ul>
						<button type="submit" id="btn" class="form-button form-control buttons"><span class="glyphicon glyphicon-upload"></span>Upload Files!</button>
					</form>
				</div>
			</div>
				                        
	        <div class="col-sm-1 middle-border"></div>
	        <div class="col-sm-1"></div>       
			<div class = "col-sm-4" style= "background-color: #eeeeee;margin: 4% 0 0 0;">
				<div class = "inputAlb" >
					<h3 style = "font-family: Alegreya Sans; font-size: 34px; margin: 5% 5% 5% 5%;">Create new album <span class="glyphicon glyphicon-picture"></span></h3>
					Name: <br>
					<input id= "nameAlb" type="text" placeholder="name..." class="form-username form-control">
					<ul id = "albumNameError"></ul>
					Description: <br>
					<input id = "descriptionAlb" type="text" placeholder="description..." class="form-username form-control">
					<ul id = "albumDescError"></ul>
					<button type="submit" id="createAlb" class="form-button form-control buttons"><span class="glyphicon glyphicon-upload"></span>Create album!</button>
				</div>
			</div>
		</div>
		<div align="center" class = "position col-sm-12">
			<ul id = "image-list">
				
			</ul>
			<div class="progress progress-striped active">
				<div class="progress-bar" style="width: 0%;">
					<span class="sr-only">0% Complete</span>
				</div>
			</div>
		</div>

	</body>
</html>