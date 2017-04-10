$(function() {
	var $img = $('#ajaxGetImg');
	var img;
	$.ajax({
		type:'Get',
		url: '../getImg',
		success: function(img) {
			$img.append('<li>ID: '+ img.id+' </li> <br>');
			$img.append('<li>link ' + img.link + '</li> <br>');
			$img.append('<li>deletehash ' + img.deletehash + '</li> <br>');
		},
		error: function() {
		 alert('error loading image');
		}
	});
});