<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href=css/bootstrap.css></link>
	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.0.min.js"></script>
	<script src=js/tether.js></script>
	<script src=js/tether.min.js></script>
<title>Hello</title>
</head>
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


<br>
<br>


<input id ="getImgButton" type="submit" value="getImg">
Response from ajax : <ul id="ajaxGetImg"></ul>

<br>
<br>


<br>
<br>
<p><b>Your comment: </b></p>
<p>comment: <input type="text" id="comment-content"></p>
<input id = "addCommentButton" type="submit" value="submit">
this is just one demo button for comments : 
<br>
<ul id="comments"></ul>

<br>
<br>


<FORM enctype="multipart/form-data" action="/post/add" method="post">
	<b><h1> Choose your image:</h1></b>
		  <INPUT NAME="file" TYPE="file">
		 </br>
		 <input type="submit" value="Submit">
	</div>
</FORM>
<br>
<br>

<f:form commandName="getApi" action="/ourwif/api" method = "POST">
	<button class= "removeStyle" type="submit" value="Go to API"></button>
</f:form>
</body>
</html>