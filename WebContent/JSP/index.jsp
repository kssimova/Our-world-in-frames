<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

<form action="../getImg" method="get">
<input type="submit" value="getImg">
</form>
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