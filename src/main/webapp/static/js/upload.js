$(function() {
 	var base64;
 	var input = document.getElementById("images");
 	var	formdata = false;
 	var magic = true;
 	var imgOk = false;
	var progres = 0;
	
	//progress bar 
	function makeProgress(){
		if(progres < 100){
			progres++;
			$(".progress-bar").css("width", progres + "%").text(progres + " %");
			}else{
				return;
			}
		if(progres < 100){
	 		setTimeout(
	 			function (){
	 				makeProgress();
	 		}, 100);
		}
		if(progres == 100){
			alert("Image uploaded successfully!");
		}
	}
 	
 	//show image at the bottom of the screen
 	function showUploadedItem (source) {
 		var list = document.getElementById("image-list");
 		var li   = document.createElement("li");
 		var img  = document.createElement("img");
 		
 		img.src = source;
 		li.appendChild(img);
 		list.appendChild(li);
 	};
 	
 	if (window.FormData) {
  		formdata = new FormData();
  	}
 	
 	//on click event for getting all data for this image
  	$("#btn").click(function (evt) {
  		evt.preventDefault();
  		var i = 0, len = input.files.length, img, reader, file;
  	
 		for ( ; i < len; i++ ) {
 			file = input.files[i];
 	
 			if (!!file.type.match(/image.*/)) {
 				imgOk = true;
 				if ( window.FileReader ) {
 					reader = new FileReader();
 					reader.onloadend = function (e) { 
 						showUploadedItem(e.target.result, file.fileName);
 						base64 = e.target.result;
 					};
 					reader.readAsDataURL(file);
 				}
 				if (formdata) {
 					formdata.append("images[]", file);
 				}
 			}else{
 				imgOk = false;
 			}
			var $description = document.getElementById('description').value;
 			var $tags = document.getElementById('tags').value;
 			var $album = document.getElementById('albums').value;
 			var $name = document.getElementById('name').value;
 			var data = {
 					name: $name,
 					description: $description,
 					album: $album,
 					tags: $tags,
 				};
 			//validate this data
 			$.ajax({
 				url: "post/valid",
 				type: "POST",
 				data: data,
 				dataType: 'JSON',
 				success: function (res) {
 					if(!res.status){
 						magic = false;
 					}else if(res.status){
 						magic = true;	
 					}
 					if(res.errors.NameError){
 		  				$('#nameError li').remove();
 		  				$('#nameError').append('<li class = "erro"><h5 style = "color:red">'+ res.errors.NameError + '</h5></li>');
 					}
 					if(res.errors.NameLength){
 		  				$('#nameError li').remove();
 		  				$('#nameError').append('<li class = "erro"><h5 style = "color:red">'+ res.errors.NameError + '</h5></li>');
 					}
 					if(res.errors.AlbumLength){
 		  				$('#albumError li').remove();
 		  				$('#albumError').append('<li class = "erro"><h5 style = "color:red">'+ res.errors.NameError + '</h5></li>');
 					}
 					if(res.errors.DescriptionLength){
 		  				$('#descError li').remove();
 		  				$('#descError').append('<li class = "erro"><h5 style = "color:red">'+ res.errors.NameError + '</h5></li>');
 					}
 					if(res.errors.TagLength){
 		  				$('#tagError li').remove();
 		  				$('#tagError').append('<li class = "erro"><h5 style = "color:red">'+ res.errors.NameError + '</h5></li>');
 					}
 				},
 				error: function(res){
 					console.log(res);
 				} 	
 			});
 			if (!imgOk) {
  				$('#typeError li').remove();
  				$('#nameError li').remove();
	  			$('#tagError li').remove();
	  			$('#descError li').remove();
	  			$('#albumError li').remove();
  				$('#typeError').append('<li class = "erro"><h4 style = "color:red">This file is not an image.</h4></li>');
  			}else{
  				$('#typeError li').remove();
  				$('#nameError li').remove();
	  			$('#tagError li').remove();
	  			$('#descError li').remove();
	  			$('#albumError li').remove();
  				if(!magic){
  	  				makeProgress();
  				}
  			}
 		}
 		
 		//make post request to create this image
 		setTimeout(function(){
 			if (formdata && base64 != null) {
 				var user = {
 					name: $name,
 					description: $description,
 					album: $album,
 					tags: $tags,
 					file: base64
 				};
 				//only if the input is valid
 		        if(magic){	    
 		        	makeProgress();
	 				$.ajax({
	 					url: "post/add",
	 					type: "POST",
	 					data: user,
	 					dataType: 'JSON',
	 					success: function (res) {
	 						alert("Image done");
	 						document.getElementById("response").innerHTML = res; 
	 					},
	 					error: function(res){
	 						console.log(res);
	  					} 	
	  				});
 		        };
 		        if(!magic){
 		        	
 		        }
  			};
 		}, 10000);
  	});  	
 }); 


 //show all albums of this user
  $(function (){	
  	$albums = $('#albums');
  	$.ajax({
 		type: "POST",
 		url: 'user/profile',
 		dataType: "json",
 		success: function(user){
 			$.each(user.albums, function(index, val){
 				$albums.append('<option value="'+ val.name +'">'+ val.name +'</option>');
 			});
 		},
 		error: function(data){
 			console.log(data);
 		}
  	});
  });
  		
 
 //add album
$(function(){
  	var $name = $("#nameAlb");
  	var $description = $('#descriptionAlb');
  	var valid = true;
 	$('#createAlb').on('click', function(){		
 		var albums = {
 			name : $name.val(),
 			description : $description.val()
 		};
	 		$.ajax({
	 			url: "album/valid",
	 			type: "POST",
	 			data: albums,
	 			success: function (res) {
	 				$('#albumNameError li').remove();
		  			$('#albumDescError li').remove();
		  			console.log(res.status)
 					if(!res.status){
 						valid = false;
 					}else if(res.status){
 						valid = true;	
 					}
 					if(res.errors.NameError){
 		  				$('#albumNameError').append('<li class = "erro"><h5 style = "color:red">'+ res.errors.NameError + '</h5></li>');
 					}
 					if(res.errors.NameLength){
 		  				$('#albumNameError').append('<li class = "erro"><h5 style = "color:red">'+ res.errors.NameError + '</h5></li>');
 					}
 					if(res.errors.DescriptionLength){
 		  				$('#albumDescError').append('<li class = "erro"><h5 style = "color:red">'+ res.errors.NameError + '</h5></li>');
 					}
	 			},
	 			error: function(res){
	 				console.log(res);
	 			} 	
	 		});
	 	setTimeout(function(){
	 		if(valid){
		 		$.ajax({
		 			url: "album/add",
		 			type: "POST",
		 			data: albums,
		 			success: function (res) {
		 				$albums.append('<option value="'+ $name.val() +'">'+ $name.val() +'</option>');
		 			},
		 			error: function(res){
		 				console.log(res);
		 			} 	
		 		});
	 		}
		}, 100);
 	});
 });
