
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
					dataType: "json",
					success: function(user){

					},
					error: function(data){
						console.log(data);
						alert(data);
			}
		});
	});
});
