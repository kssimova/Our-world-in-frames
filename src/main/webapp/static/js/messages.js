$(function () {
	
	$('#contactUs').on('click', function(){
		var $name = $("#contact-name");
		var $email = $("#contact-email");
		var $mobile = $("#contact-number");
		var $message = $("#contact-message");
		
		var user = {
			name: $name.val(),
			email: $email.val(),
			mobile: $mobile.val(),
			message: $message.val()
		};
		
		$.ajax({
			type: "POST",
			url: 'user/contactUs',
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
	
	
});