<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<meta http-equiv="X-UA-Compatible" content="ie=edge">
			<title>添加银行卡</title>
			<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
			<%
			pageContext.setAttribute("APP_PATH", request.getContextPath());
			%>
</head>
<body class="content acount-body">
<c:if test="${sessionScope.userID =='371522199904018483' }">
	<jsp:include page="../admin/commonNav.jsp"></jsp:include>	
</c:if>
<c:if test="${sessionScope.userID !='371522199904018483' }">
	<jsp:include page="commonNav.jsp"></jsp:include>
</c:if>
	<div class="container">
<form>
	<div class="form-group">
		<label for="regigterUsername" class="col-sm-2 col-sm-2 control-label"> 银行卡号： </label>
		<div class="col-sm-10">
			<input class="form-control acountUsername" id="cardNumber" name="cardNumber" type="text" value="${cardNumber==null?"":cardNumber}">
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="form-group">
		<label for="regigterUsername" class="col-sm-2 col-sm-2 control-label"> 开户行地址： </label>
		<div class="col-sm-10">
			<input class="form-control acountUsername" id="cardAddress" name="cardAddress" type="text" value="${cardAddress==null?"":cardAddress}">
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="form-group">
		<button class="btn btn-primary col-md-offset-5 col-md-2" id="addCard">
			添加银行卡
		</button>
	</div>
</form>
</div>
<script type="text/javascript">
$(function() {
	var cardNumber = $("#cardNumber").val().trim();
	var cardAddress = $("#cardAddress").val().trim();
	if(cardNumber == null || cardAddress ==null ||cardNumber == ""||cardAddress == ""){
		alert("添加银行卡和姓名必须一致，便于打款!");
	}
});
$("#addCard").click(function(){
	var cardNumber = $("#cardNumber").val().trim();
	var cardAddress = $("#cardAddress").val().trim();
	if(cardNumber == null || cardAddress ==null ||cardNumber == ""||cardAddress == ""){
		alert("请填写完整!!!");
	}else{
	$.ajax({
		url:"${APP_PATH }/userServlet?method=updateCard",
		type:"POST",
		data:{"cardNumber":cardNumber,"cardAddress":cardAddress}, 
		success:function(result){
			alert("添加银行卡成功！");
			location.href ="${APP_PATH }/userServlet?method=userCount";
		}
	});
	}
	return false;
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