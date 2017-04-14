<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/bootstrap.min.css></link>
	<link rel="stylesheet" type="text/css" href="css/API.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
<title>register</title>
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
                <p class="form-title">
                    Register</p>
                <form class="login form1">
                <input type="text" placeholder="Username" required/>
                <input type="text" placeholder="Email" required/>
                <input type="password" placeholder="Password" required/>
                <input type="password" placeholder="ConfirmPassword" required/>
                <input type="text" placeholder="First name" required/>
                <input type="text" placeholder="Last name" required/>
                <select class="form-control" >
      				<option>Bulgaria Sofiq</option>
       				<option>England London</option>
      				<option>France Paris</option>
      				<option>USA New York</option>
    			</select>
    			<select class="form-control">
      				<option>male</option>
       				<option>female</option>
    			</select>
              
        
                
                
                
                <input type="submit" value="Register" class="btn btn-info btn-sm" />
                </form>
                <f:form class="login form1" commandName="login" action="/ourwif/loginPage" method = "POST">
              		 <input type="submit" value="Login" class="btn btn-info btn-sm" />
               </f:form>
            </div>
        </div>
    </div>
</div>
	
	
	
</body>
</html>