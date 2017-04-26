
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
				$("#login_message").html(" &nbsp; * Wrong username or password! Please try again! <br>");
				document.getElementById('form-username').value = "";
				document.getElementById('form-password').value = "";
			}
		});
		
	});
	
	
});



$(function () {
	
	
	$('#signup').on('click', function(){
		var $username = $('#register-username');
		var $email = $('#register-email');
		var $password = $('#register-password');
		var $confirmPassword = $('#register-confirm-password');
		var $gender = $("select[name='form-select-gender'] option:selected");
		
		document.getElementById('usernameError').innerHTML = "";
		document.getElementById('emailError').innerHTML = "";
		document.getElementById('passwordError').innerHTML = "";
		document.getElementById('registration').innerHTML = "";
		
		var user = {
			username: $username.val(),
			password: $password.val(),
			confirmPassword: $confirmPassword.val(),
			email: $email.val(),
			gender: $gender.val()
		};
		
		$.ajax({
			type: "POST",
			url: 'user/register',
			data: user,
			
			success: function(response){
				var responseData = response;
				
				$.each(response.errors, function (index, val) {
					console.log(index + " : " + val);
					$(index).text(val);
				});
			},
		
		});
		
	});
	
	
	$("#register-password, #register-confirm-password").on('keyup', function () {
		var $password = $('#register-password').val();
		var $confirmPassword = $('#register-confirm-password').val();
		if($password != "" && $confirmPassword != ""){
			if ($password == $confirmPassword) {
			   $("#checkPasswordMatch").html(" &nbsp; * Passwords match! <br>").css("color", "green");
			} else{
			   $("#checkPasswordMatch").html(" &nbsp; * Passwords do not match! <br>").css("color", "red");
			}
		}
	});
	
	
});





