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
     <script src="js/comments.js"></script>
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
	var count = 0;
	var isSameUser = false;
	
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
				$('#name').html(post.name);
	  			$('#likee').html( 
	  								'<span id = "like">' +
										'<ul style = "list-style: none outside none; margin:0; padding: 0;">' + 
											'<li id ="panel1" rel = "panel2" class = "heart">' + 
												'<h1><span class="glyphicon glyphicon-heart"></span></h1>' +
											'</li>' +
											'<li id = "panel2"  rel = "panel1" class = "heart active">' + 
												'<h1><span class="glyphicon glyphicon-heart-empty display:inline"></span></h1>' +
											'</li>' +
										'</ul>' +
									'</span>');			 							 
	  			$('#img').html(' <img class="img-fluid" src="'+ post.picturePath +'" alt="" style = "max-width: 100%;" >');
	  			$('#desc').html("<h3 style = 'overflow:hidden;'>Description: " + post.description + "<h3>");
				$.each(post.tags, function(index, val){
					tagcheta += val + ", ";
					$tags.append('<button>' + val + '</button>');
	  			});
			}else{
				$('#name').html(post.name);
	  			$('#likee').html( 
									'<span id = "like">' +
										'<ul style = "list-style: none outside none; margin:0; padding: 0;">' + 
											'<li id ="panel1" rel = "panel2" class = "heart active">' + 
												'<h1><span class="glyphicon glyphicon-heart"></span></h1>' +
											'</li>' +
											'<li id = "panel2"  rel = "panel1" class = "heart">' + 
												'<h1><span class="glyphicon glyphicon-heart-empty display:inline"></span></h1>' +
											'</li>' +
										'</ul>' +
									'</span>');			 							 
				$('#img').html(' <img class="img-fluid" src="'+ post.picturePath +'" alt="" style = "max-width: 100%;">');
				$('#desc').html("<h3>Description: " + post.description + "<h3>");
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
	  							'<div class="thumbnail" style = "background-color: #f5f5f5;padding: 20px 0 0 0 ;">' + 
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
	}, 500);
	
	
	//get logged user
	var thisId;
	$.ajax({
  		type: "POST",
  		url: 'user/profile',
  		dataType: "json",
  		success: function(user){
  			thisId = user.userId;
  		 	$.ajax({
  		 		type: 'GET',
  				url: 'user/get/' + thisId,
  				success: function(resp){
  					if(resp.status){
  						following = true;
  					}
  				},
  				error: function(e){
  					alert(e);
  				}
  		  	});
  		},
  		error: function(data){
  			alert();
  		}
  	});
	
		
	var following = false;	
	var thisUserId = "";
	
	$.ajax({
  		type: "POST",
  		url: 'user/profile',
  		dataType: "json",
  		success: function(user){
  			thisUserId = user.userId;
  		},
  		error: function(data){
  			alert(data);
  		}
  	});
	
	var numOfFollowers = 0;
	setTimeout(function(){			
		//display post creator
		var numOfPhotos = 0;
		$.ajax({
			type: 'GET',
			url: 'user/' + $postId,
			success: function(user){
				if(user.userId == thisUserId){
					isSameUser = true;
				}
				$.each(user.followers, function (a, b){
					if(b == thisUserId){
						following = true;
					}		
				});
				$.each(user.albums, function (i, v){
					numOfPhotos += v.photos.length;
				});
				
				
				$('#username').html('<h3><img class="media-object img-circle imageFloat" src="http://i.imgur.com/ADi2E7S.jpg" alt="profile" style = "height:150px;width:auto;max-width:150px;">' + user.username + "</h3>");
			 	$('#followers').html("<h3>Followers: " + user.followers.length + "</h3>");
			 	numOfFollowers = user.followers.length;
			  	$('#photos').html("<h3>Photos: " + numOfPhotos + "</h3>");
			  	if(following){
			  		$('#follow').html('<span class="glyphicon glyphicon-send"></span> Unfollow');
			  	}else{
			  		$('#follow').html('<span class="glyphicon glyphicon-send"></span> Follow');
			 	}
			  	
			  	if(isSameUser){
			  		$('#usertoshow button').remove();
			  		$('#usertoshow').append('<button class="btn btn btn-circle text-uppercase disabled uploadButton" type="submit"><span class="glyphicon glyphicon-send"></span> Follow</button>');
			  	}
			},			
			error: function(e){
				alert(e);
			}
		});
	}, 300);
	
	// follow user 
	setTimeout(function(){	
		$('#follow').on('click', function(){
			var url = 'user/follow';
	  		if(following){
	  			url = 'user/unfollow';
	  		};
			var postche = {
					postId : $postId
				};
			$.ajax({
				type: 'POST',
				url: url,
				data: postche,
				success: function(){	 
				  	if(url == 'user/follow'){
				  		$('#follow').html('<span class="glyphicon glyphicon-send"></span> Unfollow');
					 	$('#followers').html("<h3>Followers: " + ++numOfFollowers + "</h3>");
					 	following = true;
				  	}else{
				  		$('#follow').html('<span class="glyphicon glyphicon-send"></span> Follow');
					 	$('#followers').html("<h3>Followers: " + --numOfFollowers + "</h3>");
					 	following = false;
				 	}
				},
				error: function(e){
					alert(e);
				}
	
			});	
		});	
	}, 500);
});
</script>
<body>
	<jsp:include page="Nav.jsp" />
    <!-- Page Content -->
<div style= "background-color:white;margin: 0 70px 0 70px;">
    <div class="container">
		<div class="row">
        	<h1 id= "name" class="my-4"></h1>
            <div id = "img" class="col-md-8"  align="center" style = "margin: 0 75px 0 0; padding: 20px;">
				<img class="img-fluid" src="#" alt="">
            </div>
			<div class = "col-sm-3 userr" align="center" >
	            <div id = "usertoshow">
	                <div id = "username" ></div>
	                <div id = "followers" ></div>
	                <div id = "photos"></div>
					<button class="btn btn btn-circle text-uppercase uploadButton" type="submit" id="follow"></button>
	            </div>
	            <div id = "desc"></div>
			</div>
        </div>
        <!-- /.row -->
		<div class= " col-md-9 row tagg" >
        	<div id = "tags"></div>
        	<div id = "likee" style = "float: right; padding: 0 20px 20px 20px;"></div>
        	</div> 
        	<br style = "float: left;"><br><br><br><br><br><br><br>
        	<h3 class="my-4" >Related Photos</h3>
			<div id = "red" class="row">
			</div>
		</div>
	    <div class="container">
	  		<div class="row">
	   			 <div class="col-sm-10 col-sm-offset-1" id="logout">
	      			  <div class="comment-tabs">
	        			<ul class="nav nav-tabs">
	          		      <li class="active">
							<a href="#comments-logout" role="tab" data-toggle="tab">
		           		   		<h4 class="reviews text-capitalize">Comments</h4>
							</a>
	                	</li>
	                	<li>
		                	<a href="#add-comment" role="tab" data-toggle="tab">
		            	    	<h4 class="reviews text-capitalize">Add comment</h4>
		                	</a>
	                	</li>
	            	</ul>                      
	            	<div class="tab-content">
	                	<div class="tab-pane active" id="comments-logout">                
	                    	<ul class="media-list">
	                       		<li id = "comments" ></li>
	                   
	                    	</ul> 
	                	</div>
	                	<div class="tab-pane" id="add-comment">
	                    	<div class="form-horizontal" id="commentForm" > 
	                        	<div class="form-group">
	                            	<label for="email" class="col-sm-2 control-label">Comment</label>
	                            	<div class="col-sm-10">
	                            		<textarea  id = "addComment" type="text" class="form-control" maxlength="500" style="font-size:15pt;width: 500px;rows: 15; overflow:scroll;max-width:500px;" required> </textarea>
	                            	</div>
	                        	</div>
	                        	<div class="form-group">
	                            	<div class="col-sm-offset-2 col-sm-10">                    
	                                	<button class="btn btn btn-circle text-uppercase uploadButton" type="submit" id="submitComment"><span class="glyphicon glyphicon-send"></span> Summit comment</button>
	                           			<br>
	                           			<span id = "error"></span>
	                            	</div>
	                        	</div>            
	                    	</div>
	                	</div>
	            	</div>
	        	</div>
			</div>
		</div>
	</div>
</div>

 	<p style = "padding: 0 0 100px 0">.</p>
    <!-- Bootstrap core JavaScript -->
    <script src="js/tether.min.js"></script>
</body>
</html>