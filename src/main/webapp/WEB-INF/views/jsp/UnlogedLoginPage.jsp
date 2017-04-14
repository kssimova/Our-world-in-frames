<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/API.css">
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<script src=js/login.js></script>
<title>login</title>
</head>
<body>
	<jsp:include page="UnlogedNav.jsp" />

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="pr-wrap">
                <div class="pass-reset">
                    <label>
                        Enter the email you signed up with</label>
                    <input type="email" placeholder="Email" />
                    <input type="submit" value="Submit" class="pass-reset-submit btn btn-success btn-sm" />
                </div>
            </div>
            <div class="wrap">
                <p class="form-title">Sign In</p>
                <input id = "username" type="text" placeholder="Username" >
                <input id = "password" type="password" placeholder="Password" >
                <input id = "login" type="submit" value="Sign In" class="btn btn-info btn-sm" >
                <div class="remember-forgot">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" />
                                    Remember Me
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <f:form class="login form1" commandName="register" action="/ourwif/registerPage" method = "POST">
              		 <input type="submit" value="Register" class="btn btn-info btn-sm" />
               </f:form>
            </div>
        </div>
    </div>
</div>

<p id = "hello">Hello

</p>

	
	
</body>
</html>