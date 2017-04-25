
$(function () {
	
	
	$('#login').on('click', function(){
		var $username = $("#form-username");
		var $password = $("#form-password");
		
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
				$("span").html("");
				$("span").html(" &nbsp; *Wrong username or password! Please try again! <br>");
				document.getElementById('form-username').value = "";
				document.getElementById('form-password').value = "";
			}
		});
	});
	
	
	$("#register-password, #register-confirm-password").on('keyup', function () {
		 $("#divCheckPasswordMatch").html("");
		var $password = $('#register-password').val();
		var $confirmPassword = $('#register-confirm-password').val();
		if($password != "" && $confirmPassword != ""){
			if ($password == $confirmPassword) {
			   $("#divCheckPasswordMatch").html(" &nbsp; * Passwords match! <br>").css("color", "green");
			} else{
			   $("#divCheckPasswordMatch").html(" &nbsp; * Passwords do not match! <br>").css("color", "red");
			}
		}
	});
	
	
	
	
});





