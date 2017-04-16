<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>USER</h1>
<div class="col-md-6">
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Format</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>id</td>
	    <td>Long</td> 
	    <td>user id</td> 
	  </tr>
	  <tr>
	    <td>username</td>
	    <td>String</td> 
	    <td>username</td> 
	  </tr>
	  <tr>
	    <td>firstName</td>
	    <td>String</td> 
	    <td>user first name</td> 
	  </tr>
	  <tr>
	    <td>lastName</td>
	    <td>String</td> 
	    <td>user last name</td> 
	  </tr>
	  <tr>
	    <td>country</td>
	    <td>String</td> 
	    <td>user country</td> 
	  </tr>
	  <tr>
	    <td>city</td>
	    <td>String</td> 
	    <td>user city</td> 
	  </tr>
	  <tr>
	    <td>descriprion</td>
	    <td>String</td> 
	    <td>user descriprion</td> 
	  </tr>
	  <tr>
	    <td>mobileNumber</td>
	    <td>String</td> 
	    <td>user mobileNumber</td> 
	  </tr>
	  	  <tr>
	    <td>descriprion</td>
	    <td>String</td> 
	    <td>user descriprion</td> 
	  </tr>
	  <tr>
	    <td>mobileNumber</td>
	    <td>String</td> 
	    <td>user mobileNumber</td> 
	  </tr>
	  <tr>
	    <td>birthdate</td>
	    <td>String</td> 
	    <td>user birthdate</td> 
	  </tr>
	  <tr>
	    <td>gender</td>
	    <td>String</td> 
	    <td>user gender</td> 
	  </tr>
	  <tr>
	    <td>profilePhotoPath</td>
	    <td>String</td> 
	    <td>user profile photo path</td> 
	  </tr>
	  <tr>
	    <td>albums</td>
	    <td>Array</td> 
	    <td>user albums</td> 
	  </tr>
	  <tr>
	    <td>followers</td>
	    <td>Array</td> 
	    <td>all followers</td> 
	  </tr>
	  <tr>
	    <td>following</td>
	    <td>Array</td> 
	    <td>all following user</td> 
	  </tr>
	</table>
</div>
<br class="stop">
<h1>COMPACT USER</h1>
<div class="col-md-6">
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Format</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>id</td>
	    <td>Long</td> 
	    <td>user id</td> 
	  </tr>
	  <tr>
	    <td>username</td>
	    <td>String</td> 
	    <td>username</td> 
	  </tr>
	</table>
</div>
<br class="stop">
<h1>IMAGE</h1>
<div class="col-md-6">
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Format</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>postId</td>
	    <td>String</td> 
	    <td>post id</td> 
	  </tr>
	  <tr>
	    <td>user</td>
	    <td>User /Array</td> 
	    <td>post creator</td> 
	  </tr>
	  <tr>
	    <td>name</td>
	    <td>String</td> 
	    <td>post name</td> 
	  </tr>
	  <tr>
	    <td>description</td>
	    <td>String</td> 
	    <td>post description</td> 
	  </tr>
	  <tr>
	    <td>dateCreated</td>
	    <td>String</td> 
	    <td>post dateCreated</td> 
	  </tr>
	  <tr>
	    <td>picturePath</td>
	    <td>String</td> 
	    <td>post picture path</td> 
	  </tr>
	  <tr>
	    <td>comments</td>
	    <td>Array</td> 
	    <td>all post comments</td> 
	  </tr>
	  <tr>
	    <td>likes</td>
	    <td>Array of compact users</td> 
	    <td>all post user that have liked this picture </td> 
	  </tr>
	  <tr>
	    <td>tags</td>
	    <td>Array</td> 
	    <td>array of Strings with kay: tag and some value: * </td> 
	  </tr>
	</table>
</div>
<br class="stop">
<h1>COMMENT</h1>
<div class="col-md-6">
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Format</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>commentId</td>
	    <td>Long</td> 
	    <td>comment id</td> 
	  </tr>
	  <tr>
	    <td>user</td>
	    <td>Compact user</td> 
	    <td>the user that made this comment</td> 
	  </tr>
	  <tr>
	    <td>content</td>
	    <td>String</td> 
	    <td>content</td> 
	  </tr>
	  <tr>
	    <td>dateCreated</td>
	    <td>String</td> 
	    <td>dateCreated</td> 
	  </tr>
	  <tr>
	    <td>pictureId</td>
	    <td>String</td> 
	    <td>picture Id</td> 
	  </tr>
	  <tr>
	    <td>supComment</td>
	    <td>Comment</td> 
	    <td>This is the parent comment</td> 
	  </tr>
	</table>
</div>
<br class="stop">
<h1>ALBUM</h1>
<div class="col-md-6">
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Format</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>albumId</td>
	    <td>Long</td> 
	    <td>album id</td> 
	  </tr>
	  <tr>
	    <td>name</td>
	    <td>String</td> 
	    <td>Album name</td> 
	  </tr>
	  <tr>
	    <td>description</td>
	    <td>String</td> 
	    <td>Album description</td> 
	  </tr>
	  <tr>
	    <td>description</td>
	    <td>String</td> 
	    <td>Album description</td> 
	  </tr>
	  <tr>
	    <td>dateCreated</td>
	    <td>String</td> 
	    <td>Album dateCreated</td> 
	  </tr>
	  <tr>
	    <td>user</td>
	    <td>User compact user-type</td> 
	    <td>creator</td> 
	  </tr>
	  <tr>
	    <td>photos</td>
	    <td>Array of photos</td> 
	    <td>All photos from this album</td> 
	  </tr>
	</table>
</div>
<br class="stop">
<h1>BASIC</h1>
<div class="col-md-6">
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Format</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>data</td>
	    <td>mixed</td> 
	    <td>This can be null or integer. This can be the user, post, album comment id. </td> 
	  </tr>
	  <tr>
	    <td>success</td>
	    <td>boolean</td> 
	    <td>Was the request successful</td> 
	  </tr>
	</table>
</div>

<center><p class = "stop"><a href = "API.jsp" >Back to API</a></p></center>
<br>
<br>
<br>

</body>
</html>