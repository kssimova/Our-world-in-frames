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
        Name: <input type="text" id= "fileName">    <br>
        description: <input type="text" id= "fileName">    <br>
        album: <select id = "albums">
			   </select>
    </div>
    <br>
    
    <input type= "submit" value="Upload">


<script type="text/javascript">
var handleFileSelect = function(evt) {
    var files = evt.target.files;
    var file = files[0];
    var base64;

    if (files && file) {
        var reader = new FileReader();

        reader.onload = function(readerEvt) {
            var binaryString = readerEvt.target.result;
            console.log(base64);
        };
        
        reader.readAsBinaryString(file);
    }
};

var $albums = $('#albums');

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





</script>


</body>
</html>