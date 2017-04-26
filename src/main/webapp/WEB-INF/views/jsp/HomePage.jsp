<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/aboutSectionStyle.css></link>
	<link rel="stylesheet" type="text/css" href="css/allPages.css">
	<link rel="stylesheet" type="text/css" href="css/HomePage.css">
	<link rel="stylesheet" type="text/css" href="css/Discover.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="js/userPage.js"></script>
	
<title>Home</title>
</head>
<script type="text/javascript">
	//get all posts from all followed users
	
	

</script>

<body>
	<jsp:include page="Nav.jsp" />
	
	
	 <!--  second row     -->
   <div class="container">
         <div class="row visible-lg-block tabs">
             <div class="col-lg-2 firstLarge">
 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
             </div>
             <div class="col-lg-2">
 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
             </div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-md-block tabs">
             <div class="col-md-2 first">
 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
             </div>
             <div class="col-md-2">
 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
             </div>

      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-sm-block tabs">
             <div class="col-md-3 second">
 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
             </div>
             <div class="col-md-3 second">
 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
             </div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-xs-block tabs">
             <div class="col-md-3 second">
 				<input rel = "follows" class = "button btn1" type="submit" value="Following">
             </div>
             <div class="col-md-3 second">
 				<input rel = "activities" class = "button btn2" type="submit" value="Activity">
             </div>
       	 </div>	
 	</div>	
	
	
	  	<!--  follow panel     -->
	  	<!--  followed user last uploaded images -->
  <div id = "follows" class = "panel active">
 	<div id = "follow" class="row"></div>
<div class="container">
    <ul class="timeline">
        <li><!---Time Line Element--->
          <div class="timeline-badge up"><i class="fa fa-thumbs-up"></i></div>
          <div class="timeline-panel">
            <div class="timeline-heading">
             	 <h4 class="timeline-title">User123 uploaded (imageName)  </h4>
             	 <h4 class="timeline-title">Like Button </h4>
            </div>
            <div class="timeline-body"><!---Time Line Body&Content--->
             	<img src="http://i.imgur.com/xDKN6kn.jpg" style = "max-width: 100%;" />
            </div>
          </div>
        </li>
        <li><!---Time Line Element--->
          <div class="timeline-badge down"><i class="fa fa-thumbs-down"></i></div>
          <div class="timeline-panel">
            <div class="timeline-heading">
              <h4 class="timeline-title">User123 uploaded (imageName)  </h4>
              <h4 class="timeline-title">Like Button </h4>
            </div>
            <div class="timeline-body"><!---Time Line Body&Content--->
				<img src="http://i.imgur.com/DLCkST0.png" style = "max-width: 100%;" />
            </div>
          </div>
        </li>
        <li><!---Time Line Element--->
          <div class="timeline-badge neutral"><i class="fa fa-navicon"></i></div>
          <div class="timeline-panel">
            <div class="timeline-heading">
              <h4 class="timeline-title">User123 uploaded (imageName)  </h4>
              <h4 class="timeline-title">Like Button </h4>
            </div>
            <div class="timeline-body"><!---Time Line Body&Content--->
              <img src="http://i.imgur.com/kMUgnfZ.jpg" style = "max-width: 100%;"/>
            </div>
          </div>
        </li>
        <li><!---Time Line Element--->
          <div class="timeline-badge down"><i class="fa fa-thumbs-down"></i></div>
          <div class="timeline-panel">
            <div class="timeline-heading">
              <h4 class="timeline-title">User123 uploaded (imageName)  </h4>
              <h4 class="timeline-title">Like Button </h4>
            </div>
            <div class="timeline-body"><!---Time Line Body&Content--->
              <img src="http://i.imgur.com/WtT9f6J.jpg" style = "max-width: 100%;"/>
            </div>
          </div>
        </li>
    </ul>
</div>

 </div>
 
 	<!--  album panel     -->
 	<!--  images with the most likes from followed users  -->
 <div id = "activities" class = "panel">
 	<div id = "activity" class="row"></div>
 	  <!--   template 1 -->
 	<div id = "img" class="row">
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>		
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/ZrF9do6.jpg" />
		</div>	 	
 	</div>
 	
 	  <!--   template 2 -->
 	 <div id = "img" class="row">
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/ZrF9do6.jpg" />
		</div>
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
	</div>
	
	 	  <!--   template 3 -->
  	<div id = "img" class="row">
		<div class = "col-md-3 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/kMUgnfZ.jpg" />
		</div>
		<div class = "col-md-2 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/xDKN6kn.jpg" />
		</div>
	</div>
	
		 	  <!--   template 4 -->
  	<div id = "img" class="row">
		<div class = "col-md-4 size">
			<img src="http://i.imgur.com/DLCkST0.png" />
		</div>
		<div class = "col-md-3 size">
			<img src="http://i.imgur.com/WtT9f6J.jpg" />
		</div>
		<div class = "col-md-5 size">
			<img src="http://i.imgur.com/xDKN6kn.jpg" />
		</div>
	</div> 	



 </div>
 
</body>
</html>