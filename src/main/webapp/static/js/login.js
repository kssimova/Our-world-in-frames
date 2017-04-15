
$(function () {
	
	var $username = $("#username");
	var $password = $("#password");
	var $sayHello = $("#hello");
	
	$('#login').on('click', function(){
		
		var user = {
				username: $username.val(),
				password: $password.val()
		};
		
		$.ajax({
			type: "POST",
			url: 'user/login',
			data: user,
			success: function(ourUser){
				window.location = ourUser.url;
			},
			error: function(){
				alert("cant load");
			}
		});
	});
});