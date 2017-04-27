$(function() {
 	var base64;
 	var input = document.getElementById("images"), 
 		formdata = false;
 
 	function showUploadedItem (source) {
 		var list = document.getElementById("image-list"),
 		li   = document.createElement("li"),
 		img  = document.createElement("img");
 		img.src = source;
 		li.appendChild(img);
 		list.appendChild(li);
 	}   
 	if (window.FormData) {
  		formdata = new FormData();
  	}
  	$("#btn").click(function (evt) {
  		evt.preventDefault();
  		var i = 0, len = input.files.length, img, reader, file;
  	
 		for ( ; i < len; i++ ) {
 			file = input.files[i];
 	
 			if (!!file.type.match(/image.*/)) {
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
 			}   
 		}
 		setTimeout(function(){
 			if (formdata && base64 != null) {
 				var $name = document.getElementById('name').value;
 				var $description = document.getElementById('description').value;
 				var $tags = document.getElementById('tags').value;
 				var $album = document.getElementById('albums').value;
 				var user = {
 					name: $name,
 					description: $description,
 					album: $album,
 					tags: $tags,
 					file: base64
 				};
 		        	
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
 						console.log(base64);
 						alert();
  					} 	
  				});
  			}
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
  	
 
 //show album bar
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
 				console.log($name.val());
 				console.log($description.val());
 				console.log(res);
 				alert();
 			} 	
 		});
 	});
 });
  
  
$(function(){
	$('#btn').on('click', function(){
		var i = 0;
		function makeProgress(){
			if(i < 100){
				i = i + 1;
				$(".progress-bar").css("width", i + "%").text(i + " %");
			}
		// Wait for sometime before running this script again
			setTimeout("makeProgress()", 100);
		}
		if(i < 100){
			makeProgress();
		}
	});
});