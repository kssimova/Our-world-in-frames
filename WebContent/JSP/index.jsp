<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="../js/Ajax.js"></script>
<body>
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


<input id = "getImgButton" type="submit" value="getImg">
Response from ajax : <ul id="ajaxGetImg"></ul>

<br>
<br>



<FORM enctype="multipart/form-data" action="../uploadImg" method="post">
	<b><h1> Choose your image:</h1></b>
		  <INPUT NAME="file" TYPE="file">
		 </br>
		 <input type="submit" value="Submit">
	</div>
</FORM>
<br>
<br>


<form action="../deleteImg" method="get">
<input type="submit" value="deleteImg">
</form>


</body>
</html>