<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href=css/bootstrap.min.css></link>
	<link rel="stylesheet" type="text/css" href="css/API.css">
	<script src="js/album.js"></script>
<title>Album</title>
</head>

<body style="padding:0px; margin:0px; background-color:#fff;font-family:'Open Sans',sans-serif,arial,helvetica,verdana">
	<jsp:include page="LogedNav.jsp" />

    <div id="jssor_1" style="position:relative;margin:0 auto;top:0px;left:0px;width:600px;height:500px;overflow:hidden;visibility:hidden;">
        <!-- Loading Screen -->
        <div data-u="loading" style="position:absolute;top:0px;left:0px;background-color:rgba(0,0,0,0.7);">
            <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
            <div style="position:absolute;display:block;background:url('img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
        </div>
        <div data-u="slides" style="cursor:default;position:relative;top:0px;left:0px;width:600px;height:500px;overflow:hidden;">
            <div>
                <img data-u="image" src="img/01.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/02.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/03.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/04.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/05.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/06.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/07.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/08.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/09.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/10.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/11.jpg" />
            </div>
        </div>
        <!-- Bullet Navigator -->
        <div data-u="navigator" class="jssorb13" style="bottom:16px;right:16px;" data-autocenter="1">
            <!-- bullet navigator item prototype -->
            <div data-u="prototype" style="width:21px;height:21px;"></div>
        </div>
    </div>
</body>
</html>