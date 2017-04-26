<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/allPages.css">
	<link rel="stylesheet" type="text/css" href="css/UserPage.css">
	<link rel="stylesheet" type="text/css" href="css/PostView.css">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
     <script src="js/jquery.min.js"></script>
     <script src="js/bootstrap.min.js"></script>
     <script src="js/likePhoto.js"></script>
     <script src="js/comments.js"></script>
     <script src="js/followUser.js"></script>
<title>post</title>
</head>
<body>
	<jsp:include page="Nav.jsp" />
    <!-- Page Content -->
    <div class="container">

        <!-- Portfolio Item Heading -->
        <h1 id= "name" class="my-4"></h1>

        <!-- Portfolio Item Row -->
        <div class="row">

            <div id = "img" class="col-md-9">
                <img class="img-fluid" src="#" alt="">
            </div>

            <div class="col-md-3">
                <h3 class="my-3">Description:</h3>
                <p id = "desc"></p>
                <h3 id = "username" class="my-3"></h3>
                <h3 id = "followers" class="my-3"></h3>
                <h3 id = "photos" class="my-3"></h3>
                  <button class="btn btn-success btn-circle text-uppercase" type="submit" id="follow"></button>
            </div>

        </div>
        <!-- /.row -->
        <div id = "tags" class= "row">
        </div> 

        <!-- Related Projects Row -->
        <h3 class="my-4">Related Photos</h3>

        <div id = "red" class="row">
       </div>
    </div>
    
    <div class="container">
  <div class="row">
    <div class="col-sm-10 col-sm-offset-1" id="logout">
        <div class="comment-tabs">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#comments-logout" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Comments</h4></a></li>
                <li><a href="#add-comment" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Add comment</h4></a></li>
         
            </ul>   
                     
            <div class="tab-content">
                <div class="tab-pane active" id="comments-logout">                
                    <ul class="media-list">
                       <span id = "comments" ></span>
                   
                    </ul> 
                </div>
                <div class="tab-pane" id="add-comment">
                    <div class="form-horizontal" id="commentForm" > 
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Comment</label>
                            <div class="col-sm-10">
                            <input id = "addComment" type="text" placeholder="..." class="form-control" > 
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                    
                                <button class="btn btn-success btn-circle text-uppercase" type="submit" id="submitComment"><span class="glyphicon glyphicon-send"></span> Summit comment</button>
                            </div>
                        </div>            
                    </div>
                </div>
            </div>
        </div>
	</div>
  </div>
</div>


    <!-- Bootstrap core JavaScript -->
    <script src="js/tether.min.js"></script>
</body>
</html>