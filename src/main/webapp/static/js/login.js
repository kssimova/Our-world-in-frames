
$(function () {
	
	var $username = $("#form-username");
	var $password = $("#form-password");
	
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
				$("span").append(" &nbsp; *Wrong username or password! Please try again! <br>");
				document.getElementById('form-username').value = "";
				document.getElementById('form-password').value = "";
			}
		});
	});
});