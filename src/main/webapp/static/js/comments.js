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
//get all comments
	setTimeout(function(){	
		$comments = $('#comments');	
		$.ajax({
			type: 'GET',
			url: 'comment/'+ $postId,
			success: function(data){
				$.each(data, function(index, val){
					$comments.append(
							'<li class="media">' + 
								'<a class="pull-left"  id = "'+ val.commentId +'">' 
					);    
	               	if(val.creatorUrl != null){
	    				$comments.append(
									'<img class="media-object img-circle" src="'+ val.creatorUrl +'" alt="profile" style ="height:100px;width:auto;max-width:100px;">'
	    				); 
	               	}else{
	    				$comments.append(
									'<img class="media-object img-circle" src="http://i.imgur.com/d7jOt4k.jpg" alt="profile" style = "height:100px;width:auto;max-width:100px;">'
	    				); 
	               	}
					$comments.append(
								'</a>' +
								'<div class="media-body">' +
									'<div class="well well-lg" style = "width: 600px" >' +
										'<h4 id = class="media-heading text-uppercase reviews">'+ val.creator +'</h4>' + 
										'<ul class="media-date text-uppercase reviews list-inline">' +
											'<li> Posted on: </li>' +
											'<li class="dd">' + val.dateCreated.dayOfMonth + '</li>' +
											'<li class="mm">' + val.dateCreated.month + '</li>' + 
											'<li class="aaaa">' + val.dateCreated.year + '</li>' + 
										'</ul>' +
										'<p></p>' +
										'<p class="media-comment"' +
											'<div class = "sizeComment">' + val.content + '</div>' +
										'</p>' +
									'</div>' +              
								'</div> ' +
							'</li> '  
					); 
				});
			},
			error: function(e){
				console.log(e);
			}
		});	
		}, 300);
	});	
	
	//create new comment 
	$(function () {	
		var url = window.location.href;
		var n = url.indexOf("imgId=");
		var $postId = url.substring(n+6);

		setTimeout(function(){				
			$('#submitComment').on('click', function(){
				var $comment = document.getElementById('addComment').value;
				var commentche = {
						comment : $comment,
						postId: $postId
					};
				$.ajax({
					type: 'POST',
					url: 'comment/add',
					data: commentche,
					success: function(val){ 
						$.each(val.errors, function(a, b){
							console.log(a + " : "+ b);
							var x = document.getElementById("error").innerHTML = '<h4 style = "color:red">' + b + '</h4>';
						});
						if(val.creatorUrl == null && val.commentId != null){
							var x = document.getElementById("error").innerHTML = "";
							 $('#comments').append(
								'<li class="media">' + 
									'<a class="pull-left" id = "'+ val.commentId +'">' +
										'<img class="media-object img-circle" src="'+ val.creatorURL +'" alt="profile" style ="height:100px;width:auto;max-width:100px;">' + 
									'</a>' +
									'<div class="media-body">' +
										'<div class="well well-lg" style = "width: 600px">' +
											'<h4 id = class="media-heading text-uppercase reviews">'+ val.creator +'</h4>' + 
											'<ul class="media-date text-uppercase reviews list-inline">' +
												'<li> Posted on: </li>' +
												'<li class="dd">' + val.dateCreated.dayOfMonth + '</li>' +
												'<li class="mm">' + val.dateCreated.dayOfMonth + '</li>' + 
												'<li class="aaaa">' + val.dateCreated.year + '</li>' + 
											'</ul>' +
											'<p></p>' +
											'<p class="media-comment">' +
												'<div class = "sizeComment">' + val.content + '</div>' +
											'</p>' +
										'</div>' +              
									'</div> ' +
								'</li> '  
								); 
						};
						if(val.creatorUrl != null){
							var x = document.getElementById("error").innerHTML = "";
							$('#comments').append(
								'<li class="media">' + 
									'<a class="pull-left" id = "'+ val.commentId +'">' +
										'<img class="media-object img-circle" src="http://i.imgur.com/d7jOt4k.jpg" alt="profile" style = "height:100px;width:auto;max-width:100px;">' +
									'</a>' +
									'<div class="media-body">' +
										'<div class="well well-lg" style = "width: 600px">' +
											'<h4 id = class="media-heading text-uppercase reviews">'+ val.creator +'</h4>' + 
											'<ul class="media-date text-uppercase reviews list-inline">' +
												'<li> Posted on: </li>' +
												'<li class="dd">' + val.dateCreated.dayOfMonth + '</li>' +
												'<li class="mm">' + val.dateCreated.dayOfMonth + '</li>' + 
												'<li class="aaaa">' + val.dateCreated.year + '</li>' + 
											'</ul>' +
											'<p></p>' +
											'<p class="media-comment">' +
												'<div class = "sizeComment">' + val.content + '</div>' +
											'</p>' +
										'</div>' +              
									'</div> ' +
								'</li> ' 			
							); 
		               	};
					},
					error: function(e){
						alert(e);
					}
				});
			});
		}, 1100);
	});