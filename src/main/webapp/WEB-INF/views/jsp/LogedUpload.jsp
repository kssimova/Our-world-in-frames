<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/bootstrap.min.css></link>
	<link rel="stylesheet" type="text/css" href="css/API.css">
<title>upload</title>
</head>

<body>
	<jsp:include page="LogedNav.jsp" />


<script type="text/javascript">
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

</script>

<div>
     Name: <input id= "name" type="text" placeholder="name" >    <br>
     description: <input id = "description" type="text" placeholder="description" >    <br>
     album: <select id = "albums"></select>   <br>
     Tags: /please enter your image tags separated with a comma/
     <input id = "tags" type="text" placeholder="Red, roses " >    <br>
</div>
<div id="main">
	<h1>Upload Your Images</h1>
	<form method="post" enctype="multipart/form-data"  action="upload.php">
		<input type="file" name="images" id="images" multiple />
		<button type="submit" id="btn">Upload Files!</button>
	</form>
</div>

<ul id = "image-list">
</ul>

<script type="text/javascript">
(function () {
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
}());
</script>



</body>
</html>