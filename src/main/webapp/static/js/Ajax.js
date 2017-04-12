$(function() {
	
	//use this function when you open jsp for the image... 
	//populate this page with all the thing in the json
		var $img = $('#ajaxGetImg');
		var $comment = $('#comment-content');
		var $user = $();
	$('#getImgButton').on('click', function(){
		$.ajax({
			type:'Get',
			url: '../getImg',
			success: function(img) {
			    $img.find('li').remove();
				$img.append('<li>ID: '+ img.id+'</li> ');
				$img.append('<li>Image <img src = "' + img.link + ' "> </li>');
				$img.append('<li>deletehash ' + img.deletehash + ' </li>');
			},
			error: function() {
			 alert('error loading image');
			}
		});
	});

	//use this function when you add comment in the jsp...
	//this will send request after a button "send" is clicked
	$('#addCommentButton').on('click', function(){
		var com = {
			comment: $comment.val(),
			//add some other stuff in this java script object
			//like user
			//post id ...i mean photo id			
		};
		
		$.ajax({
			type:'Post',
			url: '../addComments',
			date: com,
			success: function(newComment) {
				$img.append('<li>comment: ' + newComment.comment + '</li> <br>');
			},
			error: function() {
				 alert('error uploadin comment');
			}
		});	
	});
});