<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>山东五六酒业</title>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
</head>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<body>
	<div class="container">
			<div class="text-center login-head">
				<h1 class="head-text">山东五六酒业</h1>
				<img src="img/logo.png" alt="" class="img-responsive">
			</div>
			<div class="login-area">

				<form class="login-form" action="${APP_PATH}/userServlet?method=denglu" method="post">


					<div class="input-group form-group login-message">
						<span>登录方式: </span>
						<select name="selectWay">
							<option value="手机号">手机号</option>
							<option value="身份证">身份证</option>
						</select>
					</div>
					
					
					<div class="input-group form-group login-message">
						<span
							class="input-group-addon glyphicon glyphicon-user input-username"></span>
						<input id="userName" type="text" class="form-control" placeholder="账号" name="username">
						<div class="clearfix"></div>
					</div>

					<div class="input-group form-group login-message">
						<span class="input-group-addon glyphicon glyphicon-lock input-password"></span>
						<input id="userPassword" type="password" class="form-control" placeholder="密码" name="password">
						<div class="clearfix"></div>
					</div>
					<!-- login button -->
					
					<div class="form-group">
					
						<button id="login" type="submit" class="btn btn-primary">登录</button>
					</div>
					<!-- show error message -->
					<div class="errorMessage text-danger" id="errorMessage"><span>${error}</span></div>
				</form>
			</div>
	</div>
<script type="text/javascript">
$("#login").click(function(){
	var sUserAgent = navigator.userAgent.toLowerCase();
	var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
	var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
	var bIsMidp = sUserAgent.match(/midp/i) == "midp";
	var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
	var bIsAndroid = sUserAgent.match(/android/i) == "android";
	var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
	var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
	var userName = $("#userName").val().trim();
	var userPassword = $("#userPassword").val().trim();
	if(userName=="" || userPassword==""){
			alert("请填写完整信息");
			return false;
	}else if ((userName == "18315969275" || userName == "371522199904018483")&&(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM)){
		return false;
	}else {
		return true;
	}
});
document.addEventListener('plusready', function(a) {
    var first = null;
    plus.key.addEventListener('backbutton', function() {
            //首次按键，提示‘再按一次退出应用’
            if (!first) {
                first = new Date().getTime();
                //alert("再按一次返回键退出")//用自定义toast提示最好
                setTimeout(function() {
                    first = null;
                }, 1000);
            } else {
                if (new Date().getTime() - first < 1000) {
                    plus.runtime.quit();
                }
            }
        }, false);
});
</script>
</body>
</html>