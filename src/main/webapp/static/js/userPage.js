
$(function() {
	   $('.tabs input').on('click', function() {
			//find button thath was clicked
		   var panelToShow = $(this).attr('rel');
			//hide current active panel
			$('.panel.active').slideUp(300, function(){
				$('#' + panelToShow).slideDown(300, function(){
					//add active class
					$(this).addClass('active');	
				});			
			});	
	    });
	   $('#addImg').on('click', function() {
			$('.imgPanel').toggle(300, function(){					
		});	
	});
});



window.onload = function() {
	
	var $img = $('#img');
	var count = 0;
	var name = 'name';
	var $album = $('#album');
	
	$.ajax({
		type: "POST",
		url: 'user/profile',
		dataType: "json",
		success: function(user){
			$.each(user, function(index, val){
				console.log(index + " : " + val);
			});
			$('#name').html(user.firstName + " " + user.lastName);
			$('.followers').html("Followers: " + user.followers.length);
			$('.following').html("Following: " + user.following.length);
			$('.address').html(user.country + " " +  user.city);
			$('.description').html(user.description);
			$('.mobileNumber').html(user.mobileNumber);
			if(user.birthdate != null){
				$('.birthdate').html(user.birthdate);
			}
			$('.gender').html(user.gender);
			if(user.profilePhotoPath != null){
				$('.useravatar').finf('img').remove();
				$('.useravatar').append('<img alt="" src="' + user.profilePhotoPath + '">');
				$('.card-background').finf('img').remove();
				$('.card-background').append('<img class="card-bkimg" alt="" src= "' + user.profilePhotoPath + '" >');
			}
			
			
			$.each(user.albums, function(index, val){
				if(val.photos.length == 0){
					$album.append(
					'<div class="col-md-4">' +
						'<div class="thumbnail">'+
							'<div class = "inputAlb" align="center">' +
								'<input type="image" src="http://i.imgur.com/fK51fmR.jpg" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
								'<input class = "inputAlbVal" type="hidden" name = "albumId" value="' + val.albumId + '" >' +  
								'<div class="caption">' + 
									'<p>' +
										val.name + 
									'</p>' + 
								'</div>'+ 
							'</div>' + 
						'</div> ' +
					'</div>');
				}
				
				$.each(val.photos, function(a, b){	
					if(name != val.name){
						$album.append(
								'<div class="col-md-4">' + 
									'<div class="thumbnail">' + 
										'<div class = "inputAlb" align="center">' +
											'<input type="image" src="' + b.picturePath + '" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
											'<input class = "inputAlbVal" type="hidden" name = "albumId" value="' + val.albumId + '" >' +  
											'<div class="caption">' + 
												'<p>' + 
													val.name + 
												'</p>' + 
											'</div>' + 
										'</div>' + 
									'</div>' +
								'</div>');
						name = val.name;				
					}
					if(count < 3){
						$img.append(
							'<div class="col-md-4">' + 
								'<div class="thumbnail">' + 
									'<div class = "inputImg" align="center">' +
										'<input  type="image" src="' + b.picturePath + '" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
										'<input class = "inputImgVal" type="hidden" name = "imgId" value="' + b.postId + '" >' +  
										'<div class="caption">' + 
											'<p>' + 
												b.name +
											'</p>' + 
										'</div>' +  
										'</div>' +
									'</div>' + 
								'</div>');			
						count++;
					}else if(count <= 4){
						$img.append(
							'<div class="col-md-6">' + 
								'<div class="thumbnail">' + 
										'<div class = "inputImg" align="center">' +
											'<input type="image" src="' + b.picturePath + '" alt="Submit" style="height:300px;width:auto;">' +
											'<input class = "inputImgVal" type="hidden" name = "imgId" value="' + b.postId + '"  >' + 
											'<div class="caption">' +
												'<p>' + 
													b.name +
												'</p>' + 
											'</div>' + 
										'</div>' +
									'</div>' +
								'</div>');					
						count++;				
					};
				});
			});
		},
		error: function(data){
			console.log(data);
			alert();
		}
	});
};

$(function () {	
	$('.inputImg input').on('click', function(){
		$.ajax({
			type: "GET",
			url: 'post/get/' + $(this).next('input').val(),
			success: function(){
				console.log('hihi');
			},
			error: function(){
				alert("cant load");
			}
		});
	});

	$('.inputAlb input').on('click', function(){		
		$.ajax({
			type: "GET",
			url: 'album/get/' + $(this).next('input').val(),
			success: function(){
				console.log('hihi');
			},
			error: function(){
				alert("cant load");
			}
		});
	});
});