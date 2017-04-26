
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
				$("#login_message").html("");
				$("#login_message").html(" &nbsp; *Wrong username or password! Please try again! <br>");
				document.getElementById('form-username').value = "";
				document.getElementById('form-password').value = "";
			}
		});
	});
	
	
});



$(function () {
	
	
	$('#registerButton').on('click', function(){
		var $username = $('#register-username').val();
		var $email = $('#register-email').val();
		var $password = $('#register-password').val();
		var $gender = $('#form-select-gender').val();
		
		var user = {
			username: $username.val(),
			password: $password.val(),
			email: $email.val(),
			gender: $gender.val()
		};
		
		$.ajax({
			type: "POST",
			url: 'user/register',
			data: user,
			
			success: function(response){
				var responseData = response;
				var map = responseData.errors;
				for (var place in map) {
				    var value = map[place];
				    // now you can use key as the key, value as the... you guessed right, value
				    ($(place).val()).html($(value).val());
				}
			},
		
			error: function(response){
				var responseData = response;
				var map = responseData.errors;
				for (var place in map) {
				    var value = map[place];
				    // now you can use key as the key, value as the... you guessed right, value
				    ($(place).val()).html($(value).val());
				}
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






