<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>支付失败</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<hr />
	<h1>支付失败，<span id="time" class="text-danger">5</span>秒后返回...</h1>
</div>
<script type="text/javascript">
	$(function(){
		var t = 4;
		var timer = setInterval(function(){
			$("#time").text(t);
			t--;
			if(t<0){
				clearInterval(timer);
				window.location = "${APP_PATH }/userServlet?method=myShopCart";
			}
		},1000);
	});
	
	document.addEventListener('plusready', function() {
	    var webview = plus.webview.currentWebview();
	    plus.key.addEventListener('backbutton', function() {
	        webview.canBack(function(e) {
	            if(e.canBack) {
	                webview.back();
	            } else {
	                webview.close(); //hide,quit
	                //plus.runtime.quit();
	            }
	        })
	    });
	});
</script>
</body>
</html>