<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Our world in frames</title>
    
      <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="css/font-awesome.css">
		<link rel="stylesheet" href="css/form-elements.css">
        <link rel="stylesheet" href="css/loginStyle.css">

    <!-- Bootstrap Core CSS -->
   	    <link href="css/aboutSectionStyle.css" rel="stylesheet">

    <!-- Custom Fonts -->
	    <link href="css/mainFont.css" rel="stylesheet" type="text/css">
	    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
	    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
	    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
	    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- Theme CSS -->
   	 	<link href="css/page.css" rel="stylesheet">
   	 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script> <!-- or use local jquery -->

</head>

<body id="page-top" class="index">

    <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Our world in frames</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#pointer">Login/Register</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#team-pointer">Team</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
  
  
    

    <!-- Header -->
    <header>
        <div class="container">
            <div class="intro-text">
                <div class="intro-lead-in"  style = "font-family: Kaushan Script, Helvetica Neue, Helvetica, Arial, cursive; font-size: 50px">This is our world in frames!</div>
                <div class="intro-heading" style = "font-family: Kaushan Script, Helvetica Neue, Helvetica, Arial, cursive; font-size: 80px">Find your inspiration</div>
                <a href="#pointer" class="page-scroll btn btn-xl" style = "font-family: Kaushan Script, Helvetica Neue, Helvetica, Arial, cursive; font-size: 25px; background-color: #A386BF">Get Started!</a>
               <br><br><br><br>
            </div>
        </div>
    </header>

    

    <!-- Login/Register Grid Section -->
    <section id="portfolio" class="bg-light-gray">      
         <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container" id = "pointer">
                    
                    <div class="row">
                        <div class="col-sm-5" >
                        	
                        	<div class="form-box">
	                        	<div class="form-top">
	                        		<div class="form-top-left">
	                        			<h3 style = "font-family: Alegreya Sans; font-size: 28px">Login to our site!</h3>
	                            		<p style = "font-family: Alegreya Sans; font-size: 18px">Enter username and password to log in:</p>
	                        		</div>
	                        		<div class="form-top-right">
	                        			<i class="fa fa-key"></i>
	                        		</div>
	                            </div>
	                            <div class="form-bottom login-form">
				                    <div class="form-group">
				                    	<label class="sr-only" for="form-username">Username</label>
				                    	<input id = "form-username" name="form-username" type="text" placeholder="Username..."  class="form-username form-control" >
									</div>
									<div class="form-group">
				                        <label class="sr-only" for="form-password">Password</label>
				                       	<input id = "form-password" name="form-password" type="password" placeholder="Password..." class="form-password form-control" >
									</div>
									<span id = "login_message" style = "color: red; font: 15px arial, sans-serif;" ;></span><br>
										<button id ="login" type="submit" class="form-button form-control" style = "background-color:#dab3ff; height: 50px; font-size:18px; color: white; border: 3px solid #ddd;"> Sign in! </button>
			                    </div>
			                    
			        
			                    
		                    </div>
		                
		                	<div class="social-login">
	                        	<h3>...or login with:</h3>
	                        	<div class="social-login-buttons">
		                        	<a class="btn btn-link-1 btn-link-1-facebook" href="#">
		                        		<i class="fa fa-facebook"> </i> Facebook
		                        	</a>
		                        	<a class="btn btn-link-1 btn-link-1-google-plus" href="#">
		                        		<i class="fa fa-google-plus"></i> Google Plus
		                        	</a>
	                        	</div>
	                        </div>
	                        
                        </div>
                        
                        <div class="col-sm-1 middle-border"></div>
                        <div class="col-sm-1"></div>
                        	
                        <div class="col-sm-5">
                        	
                        	<div class="form-box">
                        		<div class="form-top">
	                        		<div class="form-top-left">
	                        			<h3 style = "font-family: Alegreya Sans; font-size: 28px">Register here!</h3>
	                            		<p style = "font-family: Alegreya Sans; font-size: 18px">If you don't have an account create one:</p>
	                        		</div>
	                        		<div class="form-top-right">
	                        			<i class="fa fa-pencil"></i>
	                        		</div>
	                            </div>
	                            <div class="form-bottom">
				                    <form role="form" action="" method="post" class="registration-form">
				                        <div class="form-group">
				                    		<label class="sr-only" for="form-username">Username</label>
				                        	<input type="text" id = "register-username" name="form-username" required="required" placeholder="Username..." class="form-username form-control">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-email">Email</label>
				                        	<input type="text" id = "register-email" name="form-email" required="required" placeholder="Email..." class="form-email form-control">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-password">Password</label>
				                        	<input type="password" id = "register-password" name="form-password" required="required" placeholder="Password..." class="form-password form-control">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-confirm-password">Confirm Password</label>
				                        	<input type="password" id = "register-confirm-password" name="form-confirm-password" required="required" placeholder="Confirm password..." onChange="checkPasswordMatch()" class="form-confirm-password form-control">
				                        </div>
				                        
				                        <span class="registrationFormAlert" id="divCheckPasswordMatch" style = "font: 15px arial, sans-serif;">   </span><br>
				                        
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-select-gender">Select gender</label>
				                        		<select required name="form-select-gender"  id="form-select-gender" class="form-select-gender form-control"style = "height: 50px; font-size:16px; outline: 0; background: #F5F5F5; border: 3px solid #ddd;">
				                       				 <option value="" selected>Select gender...</option> 
                   									 <option value="male">Male</option>
                       								 <option value="female">Female</option>
												</select>
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-select-city-country">Select country and city</label>
				                        		<select required name="form-select-city-country" class="form-select-city-country form-control" id="form-select-city-country" style = "height: 50px; font-size:16px; outline: 0; background: #F5F5F5; border: 3px solid #ddd;">
				                       				 <option value="" selected>Select city and country...</option> 
                   									 <option value="male">Male</option>
                       								 <option value="female">Female</option>
												</select>
				                        </div>
				                        <button id = "register" type="submit" class="form-button form-control" style = "background-color:#dab3ff; height: 50px; font-size:18px; color: white; border: 3px solid #ddd;">Register!</button>
				                    </form>
			                    </div>
			                 
                        	</div>
                        	
                        </div>
                    </div>
                    
                </div>
            </div>
            
        </div>
        
    </section>
    


    <!-- Team Section -->
    
    <section id="team" class="bg-light-gray"> <br><br>
             <span id = "team-pointer"> </span>
        <div class="container">
            <div class="row" >
                <div class="col-lg-12 text-center" >
               
                    <h2 class="section-heading"><font color=#dab3ff> Our Amazing Team </font></h2>
                    <h3 class="section-subheading text-muted">This is our amazing team!</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="team-member">
                        <img src="img/krisi.jpg" class="img-responsive img-circle" alt="">
                        <h4> <font color=#dab3ff> Kristina Simova </font></h4>
                        <p class="text-muted">Java Developer</p>
                        <ul class="list-inline social-buttons">
                            <li><a href="https://twitter.com/KristinaSimovaa"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="https://www.facebook.com/Sunshin3e"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="team-member">
                        <img src="img/kitty.jpg" class="img-responsive img-circle" alt="">
                        <h4> <font color=#dab3ff> Lead Kitten </font></h4>
                        <p class="text-muted">Lead Kitten</p>
                        <ul class="list-inline social-buttons">
                            <li><a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="team-member">
                        <img src="img/mariq.jpg" class="img-responsive img-circle" alt="" style = "height: 250px">
                       <h4> <font color=#dab3ff> Mariya Yordanova </font></h4>
                        <p class="text-muted">Java Developer</p>
                        <ul class="list-inline social-buttons">
                            <li><a href="https://twitter.com/josette_darling"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="https://www.facebook.com/werewolf0girl"><i class="fa fa-facebook" color = "blue"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <p class="large text-muted">"Talent wins games, but teamwork and intelligence win championships."<br>
                    	"Individual commitment to a group effort--that is what makes a team work, a company work, a society work, a civilization work."<br>
                    </p>
                </div>
            </div>
        </div>
    </section>

  

    <!-- Contact Section -->
    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading"> <font color = "#dab3ff">  Contact Us </font> </h2> 
                    <h3 class="section-subheading text-muted"> <font color = "#dab3ff"> Tell us what you think about our website! </font> </h3>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <form name="sentMessage" id="contactForm" novalidate>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Please enter your name *"  id="name" required data-validation-required-message="Please enter your name.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control" placeholder="Please enter your email address *" id="email" required data-validation-required-message="Please enter your email address.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="tel" class="form-control" placeholder="Please enter your mobile number *" id="phone" required data-validation-required-message="Please enter your phone number.">
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <textarea class="form-control" placeholder="Message to us *" id="message" required data-validation-required-message="Please enter a message."></textarea>
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="col-lg-12 text-center">
                                <div id="success"></div>
                                <button type="submit" class="btn btn-xl" style = "background-color:#dab3ff"> Send Message </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    
     <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <span class="copyright" style = "color: black">Copyright &copy; Our world in frames 2017. Designed by Mariya & Kristina</span>
                </div>
            </div>
        </div>
    </footer>


    <!-- jQuery -->
    <script src="js/jqueryBackground.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrapMenu.js"></script>

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js" integrity="sha384-mE6eXfrb8jxl0rzJDBRanYqgBxtJ6Unn4/1F7q4xRRyIw7Vdg9jP4ycT7x1iVsgb" crossorigin="anonymous"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    

    <!-- Theme JavaScript -->
    <script src="js/pageScroll.js"></script>
    
    <script src=js/login.js></script>
    
    
            <!-- Javascript -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.backstretch.js"></script>
        <script src="js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" />

</body>

</html>