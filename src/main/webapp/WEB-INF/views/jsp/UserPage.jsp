<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
     pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
 <!DOCTYPE html>
 <html>
 <head>
 <meta charset="utf-8">
 	<link rel="stylesheet" type="text/css" href=css/aboutSectionStyle.css></link>
 	<link rel="stylesheet" type="text/css" href="css/UserPage.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
 	<script src="js/userPage.js"></script>
 <title>user</title>
 </head>
 <script type="text/javascript">

 $(function() {
  	
 	var $img = $('#img');
  	var count = 0;
  	var name = 'name';
  	var $album = $('#album');
  	var $like = $('#like');
  	
  	$.ajax({
  		type: "POST",
  		url: 'user/profile',
  		dataType: "json",
  		success: function(user){
  			$('#name').html(user.firstName + " " + user.lastName);
  			$('.followers').html("Followers: " + user.followers.length);
  			$('.following').html("Following: " + user.following.length);
  			$('.address').html(user.country + " " +  user.city);
  			$('.description').html(user.description);
  			$('.mobileNumber').html(user.mobileNumber);
  			if(user.birthdate != null){
  				$('.birthdate').html(user.birthdate);
  			}
  			$('.gender').html(user.gender);
  			if(user.profilePhotoPath != null){
  				$('.useravatar').finf('img').remove();
  				$('.useravatar').append('<img alt="" src="' + user.profilePhotoPath + '">');
  				$('.card-background').finf('img').remove();
  				$('.card-background').append('<img class="card-bkimg" alt="" src= "' + user.profilePhotoPath + '" >');
  			}
  						
   			$.each(user.albums, function(index, val){
	   			if(val.photos.length == 0){
	  				$album.append(
	  				'<div class="col-md-4">' +
	  					'<div class="thumbnail">'+
	  						'<f:form commandName="goToPostPage" action="album" method = "GET" align="center" >' + 
	 						'<input type="image" src="http://i.imgur.com/fK51fmR.jpg" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
	 						'<input class = "inputAlbVal" type="hidden" name = "albumId" value="' + val.albumId + '" >' +  
	  							'<div class="caption">' + 
	  								'<p>' +
	  									val.name + 
	  								'</p>' + 
	 							'</div>'+ 
	  						'</f:form>' + 
	  					'</div> ' +
	  				'</div>');
	   			}
	  			
	   			$.each(val.photos, function(a, b){
	   				if(count> 4){
	   					count = 0;
	   				};
	  				if(name != val.name){
	  					$album.append(
	  							'<div class="col-md-4">' + 
	  								'<div class="thumbnail">' + 
	  									'<f:form commandName="goToPostPage" action="album" method = "GET" align="center" >' + 
	  										'<input type="image" src="' + b.picturePath + '" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
	  										'<input type="hidden" name = "imgId" value="' + val.albumId + '" >' +  
	  										'<div class="caption">' + 
	  											'<p>' + 
	  												val.name + 
	  											'</p>' + 
	 										'</div>' + 
	  									'</f:form>' + 
	  								'</div>' +
	  							'</div>');
	  					name = val.name;				
	   				};
	   				if(count < 3){
	  					$img.append('<div class="col-md-4">' + 
	  							'<div class="thumbnail">' + 
	  								'<f:form commandName="goToPostPage" action="postView" method = "GET" align="center" >' + 
	  									'<input type="image" src="' + b.picturePath + '" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
	  									'<input type="hidden" name = "imgId" value="' + b.postId + '" >' +  
	  									'<div class="caption">' + 
	  										'<p>' + 
	  											b.name +
	  										'</p>' + 
	  									'</div>' + 
	  								'</f:form>' + 
	  								'</div>' + 
	  							'</div>');			
	  					count++;
	   				}else if(count == 4 || count == 3){
	  					$img.append('<div class="col-md-6">' + 
	  							'<div class="thumbnail">' + 
	  								'<f:form commandName="goToPostPage" action="postView" method = "GET" align="center" >' +
	  									'<input type="image" src="' + b.picturePath + '" alt="Submit" style="height:300px;width:auto;">' +
	  									'<input type="hidden" name = "imgId" value="' + b.postId + '" >' + 
	  									'<div class="caption">' +
	  										'<p>' + 
	  											b.name +
	  										'</p>' + 
	  									'</div>' + 
	  								'</f:form>' + 
	  							'</div>' +
	  						'</div>');					
	  					count++;				
	   				};
   				});
   			});
  		},
  		error: function(data){
  			console.log(data);
  			alert();
  		}
  	});
  	
  	var count1 = 0;
  	$.ajax({
  		type: "GET",
  		url: 'post/getLike',
  		dataType: "json",
  		success: function(post){	
			$.each(post, function(index, val){	
	   			if(count1 < 3){
	  				$like.append('<div class="col-md-4">' + 
	  						'<div class="thumbnail">' + 
	  							'<f:form commandName="goToPostPage" action="postView" method = "GET" align="center" >' + 
	  								'<input type="image" src="' + val.picturePath + '" alt="Submit" style="height:200px;width:auto;max-width:300px;">' + 
	  								'<input type="hidden" name = "imgId" value="' + val.postId + '" >' +  
	  								'<div class="caption">' + 
	  									'<p>' + 
	  										val.name +
	  									'</p>' + 
	  								'</div>' + 
	  							'</f:form>' + 
	  							'</div>' + 
	  						'</div>');			
	  				count1++;
	   			}else if(count1 == 3){
	   				count1 = 0;
	   			};
			});
  		},
  		error: function(data){
  			console.log(data);
  			alert(data);
  		}
  	});  	
});
 </script>
 <body style = "backgraund-color: #eeeeee;">
 	<jsp:include page="Nav.jsp" />
 	
 	<div class="col-lg-12 col-md-12 col-sm-12">
 	    <div class="card hovercard">
 	        <div class="card-background">
 	            <img class="card-bkimg" alt="" src= "http://i.imgur.com/fK51fmR.jpg" >
 	            <!-- http://lorempixel.com/850/280/people/9/ -->
 	        </div>
 	        <div class="useravatar">
 	            <img alt="" src="http://i.imgur.com/fK51fmR.jpg">
 	        </div>
 	    	<div class="card-info"> 
 	    	    <span id = "name" class="card-title"></span>
 	     	</div>
 	    </div>
     </div>
 
 <!--  first row     -->
   <div class="container">
         <div class="row visible-lg-block">
             <div class="col-lg-2 firstLarge1 content">
                 <span class="followers" >Followers</span>
             </div>
             <div class="col-lg-2 content">
                 <span class="following" >Following</span>
             </div>
             <div class="col-lg-2 content">
             	<span class="address" >Address</span>
        		</div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-md-block">
             <div class="col-md-2 first content">
                 <span class="followers" >Followers</span>
             </div>
             <div class="col-md-2 content">
                 <span class="following" >Following</span>
             </div>
             <div class="col-md-2 content">
             	<span class="address" >Address</span>
        		</div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-sm-block">
             <div class="col-md-3 second content">
                 <span class="followers" >Followers</span>
             </div>
             <div class="col-md-3 second content">
                 <span class="following" >Following</span>
             </div>
             <div class="col-md-3 second content">
             	<span class="address" >Address</span>
        		</div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-xs-block">
             <div class="col-md-2 second content">
                 <span class="followers" >Followers</span>
             </div>
             <div class="col-md-2 second content">
                 <span class="following" >Following</span>
             </div>
             <div class="col-md-2 second content">
             	<span class="address" >Address</span>
        		</div>
      	 </div>
 	</div>
 	
 <!--  second row     -->
   <div class="container">
         <div class="row visible-lg-block tabs">
             <div class="col-lg-2 firstLarge">
 				<input rel = "images" class = "button btn1" type="submit" value="Photos">
             </div>
             <div class="col-lg-2">
 				<input rel = "albums" class = "button btn2" type="submit" value="Galleries">
             </div>
             <div class="col-lg-2">
 				<input rel = "likes" class = "button btn2" type="submit" value="Liked photos">
             </div>
             <div class="col-lg-2">
 				<input rel = "about" class = "button btn3" type="submit" value="About">
        		</div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-md-block tabs">
             <div class="col-md-2 first">
 				<input rel = "images" class = "button btn1" type="submit" value="Photos">
             </div>
             <div class="col-md-2">
 				<input rel = "albums" class = "button btn2" type="submit" value="Galleries">
             </div>
             <div class="col-md-2">
 				<input rel = "likes" class = "button btn2" type="submit" value="Liked photos">
             </div>
             <div class="col-md-2">
 				<input rel = "about" class = "button btn3" type="submit" value="About">
        		</div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-sm-block tabs">
             <div class="col-md-3 second">
 				<input rel = "images" class = "button btn1" type="submit" value="Photos">
             </div>
             <div class="col-md-3 second">
 				<input rel = "albums" class = "button btn2" type="submit" value="Galleries">
             </div>
             <div class="col-md-3 second"">
 				<input rel = "likes" class = "button btn2" type="submit" value="Liked photos">
             </div>
             <div class="col-md-3 second">
 				<input rel = "about" class = "button btn3" type="submit" value="About">
        		</div>
      	 </div>
 	</div>
 	<div class="container">
         <div class="row visible-xs-block tabs">
             <div class="col-md-3 second">
 				<input rel = "images" class = "button btn1" type="submit" value="Photos">
             </div>
             <div class="col-md-3 second">
 				<input rel = "albums" class = "button btn2" type="submit" value="Galleries">
             </div>
             <div class="col-md-3 second"">
 				<input rel = "likes" class = "button btn2" type="submit" value="Liked photos">
             </div>
             <div class="col-md-3 second">
  				<input rel = "about" class = "button btn3" type="submit" value="About">
         		</div>
       	 </div>	
 	</div>		
  	
  	<!--  image panel     -->
  <div id = "images" class = "panel active">
 	<div id = "img" class="row"></div>
 	
 </div>
 
 	<!--  album panel     -->
 <div id = "albums" class = "panel">
 	<div id = "album" class="row"></div>
 </div>
 
  	<!--  like panel     -->
 <div id = "likes" class = "panel">
 	<div id = "like" class="row"></div>
 </div>
 
 
 <div id = "about" class = "panel">
     <div class="col-md-3 thumbnail">
   		 Description: 
     	<div class = "description thumbnail" ></div>
 		<input class = "button btn3" type="submit" value="Edit">
     </div>
     <div class="col-md-3 thumbnail">
     	Mobile Number:
     	<div class = "mobileNumber thumbnail" ></div>
 		<input class = "button btn3" type="submit" value="Edit">
     </div>
     <div class="col-md-3 thumbnail">
   		Birthday: 
     	<div class = "birthdate thumbnail" ></div>
 		<input class = "button btn3" type="submit" value="Edit">
     </div>
     <div class="col-md-3 thumbnail">
     	Gender: 
     	<div class = "gender thumbnail" ></div>
 		<input class = "button btn3" type="submit" value="Edit">
     </div>
 </div>
 
 </body>
 </html>