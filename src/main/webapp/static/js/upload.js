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
						alert();
						document.getElementById("response").innerHTML = res; 
					},
					error: function(res){
						console.log(res);
						console.log($name);
						console.log($description);
						console.log($album);
						console.log(base64);
						alert();
					} 	
				});
			}
		}, 10000);
	});
}); 
	
$(function (){	
	$albums = $('#albums');
	$.ajax({
		type: "POST",
		url: 'user/profile',
		dataType: "json",
		success: function(user){
			$.each(user.albums, function(index, val){
				console.log(val.albumId);
				$albums.append('<option value="'+ val.name +'">'+ val.name +'</option>');
			});
		},
		error: function(data){
			console.log(data);
			alert();
		}
	});
});
	
$(function() {
	$('.inputs div').on('click', function() {
		$('.panel').toggle(300);
	});
});
		
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
				console.log($name.val());
				console.log($description.val());
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