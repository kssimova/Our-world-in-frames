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
	//get loged user
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
  			console.log(data);
  			alert();
  		}
  	});
	
		
	var following = false;	
	var numOfFollowers = 0;
	setTimeout(function(){			
		//dispay post creator
		var numOfPhotos = 0;
		$.ajax({
			type: 'GET',
			url: 'user/' + $postId,
			success: function(user){
				$.each(user.albums, function (i, v){
					numOfPhotos += v.photos.length;
				});
				$('#username').html("Creator: " + user.username);
			 	$('#followers').html("Followers: " + user.followers.length);
			 	numOfFollowers = user.followers.length;
			  	$('#photos').html("Photos: " + numOfPhotos);
			  	if(following){
			  		console.log(following);
			  		$('#follow').html('<span class="glyphicon glyphicon-send"></span> Unfollow');
			  	}else{
			  		console.log(following);
			  		$('#follow').html('<span class="glyphicon glyphicon-send"></span> Follow');
			 	}
			},			
			error: function(e){
				alert(e);
			}
		});
	}, 100);
	
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
			  		console.log(url);
				  	if(url == 'user/follow'){
				  		$('#follow').html('<span class="glyphicon glyphicon-send"></span> Unfollow');
					 	$('#followers').html("Followers: " + ++numOfFollowers);
					 	following = true;
				  	}else{
				  		$('#follow').html('<span class="glyphicon glyphicon-send"></span> Follow');
					 	$('#followers').html("Followers: " + --numOfFollowers);
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