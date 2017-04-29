$(function() {
 	var base64;
 	var input = document.getElementById("images");
 	var	formdata = false;
 	var magic = true;
 	var imgOk = false;
 	
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
 					}
 					$.each(res.errors, function(a, b){
 	 					console.log(a + " : "+ b);
 					});
 				},
 				error: function(res){
 					console.log(res);
 					alert();
 				} 	
 			});
 			if (!imgOk) {
  				$('#image-list li').remove();
  				$('#image-list').append('<li class = "erro"><h4 style = "color:red">This file is not an image.</h4></li>');
  			}else{
  				$('#image-list li').remove();
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
	 						alert();
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
 			alert();
 		}
  	});
  });
  	
 
 //show create album bar
  $(function() {
  	$('.inputs div').on('click', function() {
  		$('.panel').toggle(300);
  	});
  });
  		
 
 //add album
  $(function(){
  	var $name = $("#nameAlb");
  	var $description = $('#descriptionAlb');
 	$('#createAlb').on('click', function(){
 			
 		var albums = {
 			name : $name.val(),
 			description : $description.val()
 		};
 			
 		$.ajax({
 			url: "album/add",
 			type: "POST",
 			data: albums,
 			success: function (res) {
 				$albums.append('<option value="'+ $name.val() +'">'+ $name.val() +'</option>');
 				console.log(res);
 			},
 			error: function(res){
 				console.log(res);
 				alert();
 			} 	
 		});
 	});
 });
  
