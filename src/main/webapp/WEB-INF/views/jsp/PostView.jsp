<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/API.css">
	<link rel="stylesheet" type="text/css" href="css/UserPage.css">
	<link rel="stylesheet" type="text/css" href="css/PostView.css">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
     <script src="js/jquery.min.js"></script>
     <script src="js/bootstrap.min.js"></script>
<title>post</title>
</head>

<script type="text/javascript">
$(function () {	
	var url = window.location.href;
	var n = url.indexOf("imgId=");
	var $postId = url.substring(n+6);
	var post = {
		postId: $postId
	};
	var $tags = $('#tags');
	var tagcheta = "";
	var liked = false;
	
	//check if this image is already liked
	$.ajax({
  		type: "GET",
  		url: 'post/getLike',
  		dataType: "json",
  		success: function(post){	
			$.each(post, function(index, val){	
				if(val.postId == $postId ){
					liked = true;
					return;
				};
			});
  		},
  		error: function(data){
  			alert(data);
  		}
  	});  
	
	//display image with proper heart and values
	setTimeout(function(){
	$.ajax({
		type: "GET",
		url: 'post/get',
		data: post,
		success: function(post){
			if(!liked){
	  			$('#name').html(post.name + 
	  								'<span id = "like">' +
										'<ul style = "list-style: none outside none; margin:0; padding: 0; text-align: cente;">' + 
											'<li id ="panel1" rel = "panel2" class = "heart">' + 
												'<span class="glyphicon glyphicon-heart"></span>' +
											'</li>' +
											'<li id = "panel2"  rel = "panel1" class = "heart active">' + 
												'<span class="glyphicon glyphicon-heart-empty display:inline"></span>' +
											'</li>' +
										'</ul>' +
									'</span>');			 							 
	  			$('#img').html(' <img class="img-fluid" src="'+ post.picturePath +'" alt="">');
	  			$('#desc').html(post.description);
				$.each(post.tags, function(index, val){
					tagcheta += val + ", ";
					$tags.append('<button>' + val + '</button>');
	  			});
			}else{
	  			$('#name').html(post.name + 
									'<span id = "like">' +
										'<ul style = "list-style: none outside none; margin:0; padding: 0; text-align: cente;">' + 
											'<li id ="panel1" rel = "panel2" class = "heart active">' + 
												'<span class="glyphicon glyphicon-heart"></span>' +
											'</li>' +
											'<li id = "panel2"  rel = "panel1" class = "heart">' + 
												'<span class="glyphicon glyphicon-heart-empty display:inline"></span>' +
											'</li>' +
										'</ul>' +
									'</span>');			 							 
				$('#img').html(' <img class="img-fluid" src="'+ post.picturePath +'" alt="">');
				$('#desc').html(post.description);
				$.each(post.tags, function(index, val){
					tagcheta += val + ", ";
					$tags.append('<button>' + val + '</button>');
				});
			};
		},
		error: function(e){
			alert(e);
		}
	});
	}, 100);
	
	//display related images ...with same tags
	setTimeout(function(){
		var taggs = {
			tagche: tagcheta
		}
		$.ajax({
			type: "POST",
			url: 'post/tag',
			data: taggs,
			success: function(tags){
				$.each(tags, function(index, value){
					if(tagcheta != ""){
	  					$('#red').append(
	  			 		  	'<div class="col-sm-4 col-md-4">' +
	  							'<div class="thumbnail">' + 
	  								'<f:form commandName="goToPostPage" action="postView" method = "GET" align="center" >' + 
	  									'<input type="image" src="' + value.picturePath + '" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
	  									'<input type="hidden" name = "imgId" value="' + value.postId + '" >' +  
	  									'<div class="caption">' + 
	  										'<p>' + 
	  											value.name +
	  										'</p>' + 
	  									'</div>' + 
	  								'</f:form>' + 
	  							'</div>' + 
	  						'</div>' 				
						);
					};
				});			
			},
			error: function(e){
				console.log(e);
			}
		});
	}, 300);

	//on click event for liking this image
	var urlLike = "post/like";
	var typeMethod = "POST";	
	setTimeout(function(){			
		$('#like .heart').on('click', function(){
			var panelToShow = $(this).attr('rel');
			var $likeHeart = $(this);
			if(panelToShow == "panel2"){
				urlLike = "post/unlike";
				typeMethod = "POST";
			};
			$.ajax({
				type: typeMethod,
				url: urlLike,
				data: post,
				success: function(){
					$likeHeart.slideUp(300, function(){
						$(this).removeClass('active');
						$('#' + panelToShow).slideDown(300, function(){
							$(this).addClass('active');
						});
					});
				},
				error: function(e){
					console.log(e);
				}
			});
		});
	}, 1000);
});

</script>



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
                <h3 class="my-3">Description</h3>
                <p id = "desc"></p>
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
                      <li class="media">
                        <a class="pull-left" href="#">
                          <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/dancounsell/128.jpg" alt="profile">
                        </a>
                        <div class="media-body">
                          <div class="well well-lg">
                              <h4 class="media-heading text-uppercase reviews">Marco </h4>
                              <ul class="media-date text-uppercase reviews list-inline">
                                <li class="dd">22</li>
                                <li class="mm">09</li>
                                <li class="aaaa">2014</li>
                              </ul>
                              <p class="media-comment">
                                Great snippet! Thanks for sharing.
                              </p>
                            </div>              
                        </div>
                      </li>      
                      <li class="media">
                        <a class="pull-left" href="#">
                          <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/lady_katherine/128.jpg" alt="profile">
                        </a>
                        <div class="media-body">
                          <div class="well well-lg">
                              <h4 class="media-heading text-uppercase reviews">Kriztine</h4>
                              <ul class="media-date text-uppercase reviews list-inline">
                                <li class="dd">22</li>
                                <li class="mm">09</li>
                                <li class="aaaa">2014</li>
                              </ul>
                              <p class="media-comment">
                                Yehhhh... Thanks for sharing.
                              </p>
                          </div>              
                        </div>
                      </li>
                    </ul> 
                </div>
                <div class="tab-pane" id="add-comment">
                    <form action="#" method="post" class="form-horizontal" id="commentForm" role="form"> 
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Comment</label>
                            <div class="col-sm-10">
                              <textarea class="form-control" name="addComment" id="addComment" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                    
                                <button class="btn btn-success btn-circle text-uppercase" type="submit" id="submitComment"><span class="glyphicon glyphicon-send"></span> Summit comment</button>
                            </div>
                        </div>            
                    </form>
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