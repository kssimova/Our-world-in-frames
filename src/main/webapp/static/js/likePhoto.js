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
	  			$('#img').html(' <img class="img-fluid" src="'+ post.picturePath +'" alt="" style = "max-width: 100%;" >');
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
});