<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/aboutSectionStyle.css></link>
	<link rel="stylesheet" type="text/css" href="css/allPages.css">
	<link rel="stylesheet" type="text/css" href="css/UserPage.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="js/upload.js"></script>
	
	
	
<title>upload</title>
</head>

<body>
	<jsp:include page="Nav.jsp" />
	<div class="ui-widget-default">
		<div id="progressbar" style="height: 20px;"></div>
	</div>
	<div class = "inputs" >
			Name: &nbsp &nbsp &nbsp &nbsp &nbsp<input id= "name" type="text" placeholder="name"  required >    <br>
			Description: &nbsp <input id = "description" type="text" placeholder="description"   required>    <br>
			Album: &nbsp &nbsp &nbsp &nbsp &nbsp<select id = "albums" ></select>  <div>or create new album </div><br>
			Tags: /please enter your image tags separated with a comma/
		<input id = "tags" type="text" placeholder="Red, roses " >    <br>
	</div>
	<div id="main">
		<h1>Upload Your Images</h1>
		<form method="post" enctype="multipart/form-data"  action="upload.php" >
			<input type="file" name="images" id="images" multiple  required/>
			<button type="submit" id="btn">Upload Files!</button>
		</form>
	</div>
		
	<div class = "panel" > 
		<div class = "inputAlb" >
				create new album:<br>
				Please choose:<br>
				Name: &nbsp &nbsp &nbsp &nbsp <input id= "nameAlb" type="text" placeholder="name"  required>    <br>
				description: <input id = "descriptionAlb" type="text" placeholder="description"  required> <br>
			<input id = "createAlb" type= "submit" value="create">
		</div>
	</div>



<ul id = "image-list">
</ul>

</body>
</html>