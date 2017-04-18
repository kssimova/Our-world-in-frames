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
	
	
    <div>
       	Choose or drag a file:<br>
        <input type="file" id="filePicker">    <br>
        Name: <input id= "name" type="text">    <br>
        description: <input id = "description" type="text">    <br>
        album: <select id = "albums">
			   </select>
    </div>
    <input id = "upload" type= "submit" value="Upload">


<script type="text/javascript">
$(function (){	
	$.ajax({
		type: "POST",
		url: 'user/profile',
		dataType: "json",
		success: function(user){
			$.each(user.albums, function(index, val){
				console.log(val.albumId);
				$albums.append('<option value="'+val.albumId+'">'+ val.name +'</option>');
			});
		},
		error: function(data){
			console.log(data);
			alert();
		}
	});
	
	var base64;
	var $albums = $('#albums');
	
	var handleFileSelect = function(evt, colback) {
	    var files = evt.target.files;
	    var file = files[0];
	
	    if (files && file) {
	        var reader = new FileReader();
	        reader.onload = function(readerEvt, makeCall) {
	            var binaryString = readerEvt.target.result;
	            base64 = btoa(binaryString);
	            console.log(base64);
	            callback(postFunction);
	        };
	        reader.readAsBinaryString(file);
	    }
	    
	if (window.File && window.FileReader && window.FileList && window.Blob) {
	    document.getElementById('filePicker').addEventListener('change', handleFileSelect, false);
	} else {
	    alert('The File APIs are not fully supported in this browser.');
	}
};	
	$('#upload').on('click', function(){	
		var $name = $("#name");
		var $description = $("#description");
		var $albumId = $("#albums");
		
		var user = {
			name: $name.val(),
			description: $description.val(),
			albumId: $albumId.val(),
			file: base64
		};
		$.ajax({
			type: "POST",
			url: 'post/add',
			data: user,
			success: function(data, handleFileSelect){
				alert();
			},
			error: function(){
				alert("cant load");
			}
		});
	});
});


</script>


</body>
</html>