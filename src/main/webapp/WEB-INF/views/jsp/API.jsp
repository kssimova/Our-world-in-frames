<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/API.css">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>

<body>
<h1>USER</h1>
<div class="col-md-6">
	<h3>To get one user //this is for viewing profiles </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>GET</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/user</td> 
	  </tr>
	  <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >User</a></td> 
	  </tr>
	</table>
</div>
<div class="col-md-6">
<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>user_id</td>
	    <td>Required</td> 
	    <td>User ID</td>
	  </tr>
	</table>
</div>
<br class = "stop">
<div class="col-md-6">
	<h3>To change user info </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>PUT</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/user</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th >Description</th>
	  </tr>
	  <tr>
	    <td>email</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>new_password</td>
	    <td>required</td> 
	    <td>To change the password you need the old one and the new one</td>
	  </tr>
	  <tr>
	    <td>old_password</td>
	    <td>required</td> 
	    <td>To change the password you need the old one and the new one</td>
	  </tr>
	  <tr>
	    <td>firstName</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>lastName</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>country</td>
	    <td>optional</td> 
	    <td>To change the country you need the city too</td>
	  </tr>
	  <tr>
	    <td>city</td>
	    <td>optional</td> 
	    <td>To change the city you need the country too</td>
	  </tr>
	  <tr>
	    <td>descriprion</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>mobileNumber</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>birthdate</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>gender</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>profilePhotoPath</td>
	    <td>optional</td> 
	    <td>Image</td>
	  </tr>  
	</table>
</div>
<br class= "stop">
<div class="col-md-6">
	<h3>To add new user</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>POST</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/register</td> 
	  </tr>
	    <tr>
	   	 <th>Response Model</th>
	   	 <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th >Description</th>
	  </tr>
	  <tr>
	    <td>username</td>
	    <td>required</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>email</td>
	    <td>required</td> 
	    <td>String</td>
	  </tr>
	   <tr>
	    <td>password</td>
	    <td>required</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>counfirm_password</td>
	    <td>required</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>firstName</td>
	    <td>required</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>lastName</td>
	    <td>required</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>country</td>
	    <td>required</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>city</td>
	    <td>required</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>descriprion</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>mobileNumber</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>birthdate</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>gender</td>
	    <td>required</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>profilePhotoPath</td>
	    <td>optional</td> 
	    <td>Image</td>
	  </tr> 
	</table>
</div>
<br class= "stop">
<div class="col-md-6">
	<h3>To login</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>POST</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/login</td> 
	  </tr>
	    <tr>
	   	 <th>Response Model</th>
	   	 <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>
<div class="col-md-6">
<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>username</td>
	    <td>Required</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>password</td>
	    <td>Required</td> 
	    <td>String</td>
	  </tr>
	</table>
</div>
<br class= "stop">
<div class="col-md-6">
	<h3>To logout</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>Get</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/logout</td> 
	  </tr>
	    <tr>
	   	 <th>Response Model</th>
	   	 <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>
<div class="col-md-6">
<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>user_id</td>
	    <td>Required</td> 
	    <td>Long</td>
	  </tr>
	</table>
</div>
<br class = "stop">
<div class="col-md-6">
	<h3>To add user in following </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>POST</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/follow</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th >Description</th>
	  </tr>
	  <tr>
	    <td>user_id</td>
	    <td>required</td> 
	    <td>The user that is making the request</td>
	  </tr>
	   <tr>
	    <td>user_id</td>
	    <td>required</td> 
	    <td>The user that is getting added</td>
	  </tr>
	</table>
</div>
<br class = "stop">
<div class="col-md-6">
	<h3>To remove user from following </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>DELETE</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/follow</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th >Description</th>
	  </tr>
	  <tr>
	    <td>user_id</td>
	    <td>required</td> 
	    <td>The user that is making the request</td>
	  </tr>
	   <tr>
	    <td>user_id</td>
	    <td>required</td> 
	    <td>The user that is getting removed</td>
	  </tr>
	</table>
</div>
<br class = "stop">
<div class="col-md-6">
	<h3>To like post</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>POST</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/likes</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th >Description</th>
	  </tr>
	  <tr>
	    <td>user_id</td>
	    <td>required</td> 
	    <td>The user that is making the request</td>
	  </tr>
	   <tr>
	    <td>post_id</td>
	    <td>required</td> 
	    <td>Liked post</td>
	  </tr>
	</table>
</div>
<br class = "stop">
<div class="col-md-6">
	<h3>To remove like </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>DELETE</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/likes</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th >Description</th>
	  </tr>
	  <tr>
	    <td>user_id</td>
	    <td>required</td> 
	    <td>The user that is making the request</td>
	  </tr>
	   <tr>
	    <td>post_id</td>
	    <td>required</td> 
	    <td>The post from with you are removing like</td>
	  </tr>
	</table>
</div>


<h1>IMAGE</h1>
<div class="col-md-6">
	<h3>To get one image </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>GET</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/image</td> 
	  </tr>
	  <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Image</a></td> 
	  </tr>
	</table>
</div>
<div class="col-md-6">
<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th >Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>post_id</td>
	    <td>Required</td> 
	    <td>Post ID</td>
	  </tr>
	</table>
</div>
<br class= "stop">
<div class="col-md-6">
	<h3>To add new image</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>POST</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/image</td> 
	  </tr>
	    <tr>
	   	 <th>Response Model</th>
	   	 <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>image</td>
	    <td>required</td> 
	    <td>/multipart/form-data</td> 
	  </tr>
	  <tr>
	    <td>album</td>
	    <td>required</td> 
	    <td>album id</td> 
	  </tr>
	  <tr>
	    <td>name</td>
	    <td>required</td> 
	    <td>String</td> 
	  </tr>
	  <tr>
	    <td>description</td>
	    <td>required</td> 
	    <td>String</td> 
	  </tr>
	</table>
</div>
<br class= "stop">

<div class="col-md-6">
	<h3>To delete image </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>DELETE</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/image</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>
<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>post_id</td>
	    <td>Required</td> 
	    <td>Post ID</td>
	  </tr>
	</table>
</div>
<br class = "stop">
<div class="col-md-6">
	<h3>To change image title or description </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>PUT</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/image</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th >Description</th>
	  </tr>
	  <tr>
	    <td>title</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	   <tr>
	    <td>description</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	</table>
</div>

<h1>COMENTS</h1>

<div class="col-md-6">
	<h3>To get all comments </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>GET</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/comments</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Comments</a></td> 
	  </tr>
	</table>
	empty servlets with initialize methods
</div>
<div class="col-md-6">
<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>post_id</td>
	    <td>Required</td> 
	    <td>Post ID</td>
	  </tr>
	</table>
</div>
<br class= "stop">
<div class="col-md-6">
	<h3>To add new comment</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>POST</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/comments</td> 
	  </tr>
	    <tr>
	   	 <th>Response Model</th>
	   	 <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>post_id</td>
	    <td>required</td> 
	    <td>String this image id</td> 
	  </tr>
	  <tr>
	    <td>comment</td>
	    <td>required</td> 
	    <td>String</td> 
	  </tr>
	  <tr>
	    <td>parent_id</td>
	    <td>optional</td> 
	    <td>parent comment id if this is sub comment</td> 
	  </tr>
	</table>
</div>
<br class= "stop">
<div class="col-md-6">
	<h3>To delete comment</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>DELETE</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/comments</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>
<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>comment_id</td>
	    <td>Required</td> 
	    <td>Comment ID</td>
	  </tr>
	</table>
</div>
<br class = "stop">
<div class="col-md-6">
	<h3>To change comment content </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>PUT</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/comments</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th >Description</th>
	  </tr>
	  <tr>
	    <td>comment_id</td>
	    <td>Required</td> 
	    <td>Comment ID</td>
	  </tr>
	   <tr>
	    <td>content</td>
	    <td>required</td> 
	    <td>String</td>
	  </tr>
	</table>
</div>


<h1>ALBUMS</h1>

<div class="col-md-6">
	<h3>To get album/s </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>GET</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/albums</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Album</a></td> 
	  </tr>
	</table>
</div>
<div class="col-md-6">
<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>album_id</td>
	    <td>Required</td> 
	    <td>Album ID</td>
	  </tr>
	</table>
</div>
<br class= "stop">
<div class="col-md-6">
	<h3>To add new album</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>POST</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/albums</td> 
	  </tr>
	    <tr>
	   	 <th>Response Model</th>
	   	 <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>user_id</td>
	    <td>required</td> 
	    <td>user id</td> 
	  </tr>
	  <tr>
	    <td>title</td>
	    <td>required</td> 
	    <td>String</td> 
	  </tr>
	  <tr>
	    <td>description</td>
	    <td>optional</td> 
	    <td>String</td> 
	  </tr>
	</table>
</div>
<div class="col-md-6">
	<h3>To delete album</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>DELETE</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/albums</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>
<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th>Description</th>
	  </tr>
	  <tr>
	    <td>album_id</td>
	    <td>Required</td> 
	    <td>Album ID</td>
	  </tr>
	</table>
</div>
<br class = "stop">
<div class="col-md-6">
	<h3>To change album title or description </h3>
	<table class="table table-hover">
	  <tr>
	    <th>Method</th>
	    <td>PUT</td> 
	  </tr>
	  <tr>
	    <th>Route</th>
	    <td>/albums</td> 
	  </tr>
	    <tr>
	    <th>Response Model</th>
	    <td><a href = "APIResponse.jsp" >Basic</a></td> 
	  </tr>
	</table>
</div>

<div class="col-md-6">
	<h3>Parameters</h3>
	<table class="table table-hover">
	  <tr>
	    <th>Key</th>
	    <th>Required</th> 
	    <th >Description</th>
	  </tr>
	  <tr>
	    <td>album_id</td>
	    <td>Required</td> 
	    <td>Comment ID</td>
	  </tr>
	   <tr>
	    <td>title</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	  <tr>
	    <td>description</td>
	    <td>optional</td> 
	    <td>String</td>
	  </tr>
	</table>
</div>
<br class= "stop">
</body>
</html>
