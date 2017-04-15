$(function () {
	
	var $username = $("#username");
	var $name = $("#name");
	var $country = $("#username");
	var $descriprion = $("#password");
	var $birthdate = $("#hello");
	var $gender= $("#hello");
	var $mobileNumber = $("#username");
	var $profilePhotoPath = $("#password");
	
	$('#profile').on('click', function(){		
		$.ajax({
			type: "POST",
			url: 'user/profile',
			success: function(user){
				window.location = user.url;
			    $name.remove();
				$name.append('<span id = "name" class="card-title"> ' + user.firstName + ' ' + user.lastName + '</span>');
			},
			error: function(user){
				console.log(user);
				alert(data);
			}
		});
	});
});
