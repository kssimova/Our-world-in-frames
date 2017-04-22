
$(function() {
	   $('.tabs input').on('click', function() {
			//find button thath was clicked
		   var panelToShow = $(this).attr('rel');
			//hide current active panel
			$('.panel.active').slideUp(300, function(){
				$('#' + panelToShow).slideDown(300, function(){
					//add active class
					$(this).addClass('active');	
				});			
			});	
	    });
	   $('#addImg').on('click', function() {
			$('.imgPanel').toggle(300, function(){					
		});	
	});
});