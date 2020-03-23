<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>龙酒注册页</title>

<jsp:include page="/common/cssAndjs.jsp"></jsp:include>

</head>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
	request.setCharacterEncoding("UTF-8");
%>
<body class="register-body content">
		<c:if test="${sessionScope.userID =='371522199904018483' }">
			<jsp:include page="../admin/commonNav.jsp"></jsp:include>	
		</c:if>
		<c:if test="${sessionScope.userID !='371522199904018483' }">
			<jsp:include page="commonNav.jsp"></jsp:include>
		</c:if>
	<div class="container">
		<div class="register-body2">
			<div class="register-area">
				<div class="area-head">
					<h6>龙酒新用户注册</h6>
				</div>
			</div>
			<div class="input-regigter">
				<form class="form-horizontal adminex-form" action="${APP_PATH}/userServlet?method=zhuce" method="post">
					
					<div class="form-group">
						<label class="col-sm-2 col-sm-2 control-label"> <span>*</span>推荐人：
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control regigterUsername"
								name="regigterFID" id="regigterFID" disabled="disabled" 
								placeholder="请输入推荐人身份证号" value="${userID }">
								<span class="help-block"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="regigter-username"
							class="col-sm-2 col-sm-2 control-label">级别：</label>
						<div class="col-sm-10">
							<label class="radio-inline"> 
							<input type="radio" name="regigterLevel" checked="checked" id="inlineRadio1" value="业务员">业务员（贰千）
							</label> <label class="radio-inline"> 
							<input type="radio" name="regigterLevel" id="inlineRadio2" value="代理商">代理商（壹万）
							</label>
							<label class="radio-inline"> 
							<input type="radio" name="regigterLevel" id="inlineRadio3" value="销售经理">销售经理（叁万）
							</label>
							<label class="radio-inline"> 
							<input type="radio" name="regigterLevel" id="inlineRadio3" value="销售总监">销售总监（五万）
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="regigterUsername"
							class="col-sm-2 col-sm-2 control-label"> <span>*</span>用户名：
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control regigterUsername"
								name="regigterUsername" id="regigterUsername"
								placeholder="请输入您的用户名">
						</div>
					</div>

					<div class="form-group">
						<label for="regigter-username"
							class="col-sm-2 col-sm-2 control-label">性 别：</label>
						<div class="col-sm-10">
							<label class="radio-inline"> 
								<input type="radio" name="regigterSex" checked="checked" id="inlineRadio1" value="男">男
							</label> <label class="radio-inline"> 
								<input type="radio" name="regigterSex" id="inlineRadio2" value="女">女
							</label>
						</div>
					</div>

					<div class="form-group">
						<label for="regigter-username"
							class="col-sm-2 col-sm-2 control-label"> <span>*</span>
							手机号：
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control regigter-username"
								name="regigterPhone" id="regigterPhone"
								placeholder="请输入您的手机号">
							<span class="help-block"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="regigter-userID"
							class="col-sm-2 col-sm-2 control-label"> <span>*</span>
							身份证：
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control regigter-username"
								name="regigteruserID" id="regigteruserID"
								placeholder="请输入您的身份证">
							<span class="help-block"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="regigterUsername"
							class="col-sm-2 col-sm-2 control-label"> <span>*</span>密码：
						</label>
						<div class="col-sm-10">
							<input disabled="disabled" type="password" class="form-control regigter-username"
								name="regigterPassword" id="regigterPassword"
								placeholder="默认为：123456">
							<span class="help-block"></span>
						</div>
					</div>
					<%--注册时间 --%>
					<div class="form-group">
						<label for="regigter-username"
							class="col-sm-2 col-sm-2 control-label">注册时间：</label>
						<div class="col-sm-10">
							<input type="password" class="form-control regigter-username"
								name="regigterDate" id="regigterDate"
								placeholder="注册时间" disabled>
						</div>
					</div>
					<%--收货地址 --%>
					<div class="form-group">
						<label for="regigterUsername"
							class="col-sm-2 col-sm-2 control-label"> <span>*</span>收货地址：
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control regigterUsername"
								name="regigterAdress" id="regigterAdress"
								placeholder="请输入您的收货地址">
						</div>
					</div>

					<button type="submit"
						class="btn btn-default btn-success btn-lg btn-block" id="registerBtn">注册</button>
										<!-- show error message -->
					<div class="errorMessage" id="errorMessage"><span>${error}</span></div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var tag1 = false;
	var tag3 = false;
	var tag5 = false;
	
	function inputCss(ele,Msg,status){
		$(ele).parent().removeClass("has-success has-error");
		$(ele).parent().find(".help-block").text("");
		if(status == "success"){
			$(ele).parent().addClass("has-success");
			tag1 = true;
		}
		if(status == "error"){
			tag1 = false;
			$(ele).parent().addClass("has-error");
			$(ele).parent().find(".help-block").text(Msg);
		}
	}
	
		//验证身份证号是否已注册
		$("#regigteruserID").change(function(){
			var ID = $(this).val().trim();
			var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
			if(!reg.test(ID)){
				tag5 = false;
				inputCss("#regigteruserID","身份证格式不正确","error");
				return;
			}else{
				if(ID != ""){
					$.ajax({
						url:"${APP_PATH}/userServlet?method=checkID",
						data:"ID=" + ID,
						type:"POST",
						success:function(result){
							if(result == "false"){
								tag5 = false;
								inputCss("#regigteruserID","身份证已存在！！！","error");
							}else{
								tag5 = true;
								inputCss("#regigteruserID","","success");
							}
						}
					});
				}
			}
			
		});
		//验证推荐人身份证号
		<!--
		$("#regigterFID").change(function(){
			var FID = $(this).val().trim();
			var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
			if(!reg.test(FID)){
				tag2 = false;
				inputCss("#regigterFID","身份证格式不正确","error");
				return;
			}else{
				if(FID != ""){
					$.ajax({
						url:"${APP_PATH}/userServlet?method=checkFID",
						data:"FID=" + FID,
						type:"POST",
						success:function(result){
							if(result == "false"){
								tag2 = false;
								inputCss("#regigterFID","不存在此推荐人","error");
							}else{
								tag2 = true;
								inputCss("#regigterFID","","success");
							}
						}
					});
				}
			}
			
		});
		 -->
		//验证手机号
		$("#regigterPhone").change(function(){
			var phone = $(this).val().trim();
			var reg = /^1[34578]\d{9}$/;
			if(!reg.test(phone)){
				tag3 = false;
				inputCss("#regigterPhone","手机号格式不正确","error");
				return;
			}else{
				$.ajax({
					url:"${APP_PATH}/userServlet?method=checkPhone",
					data:"phone=" + phone,
					type:"POST",
					success:function(result){
						if(result == "false"){
							tag3 = false;
							inputCss("#regigterPhone","手机号重复注册","error");
						}
						if(result == "true"){
							tag3 = true;
							inputCss("#regigterPhone","","success");
						}
					}
				});
			}
		});
		<!--
		//验证密码
		$("#regigterPassword").change(function(){
			var password = $(this).val().trim();
			var reg = /^[a-zA-Z]\w{5,17}$/;
			if(!reg.test(password)){
				tag4 = false;
				inputCss("#regigterPassword","密码要以字母开头，长度在6-18之间，只能包含字符、数字和下划线","error");
				return;
			}else{
				tag4 = true;
				inputCss("#regigterPassword","","success");
			}
		});
		-->
		$("#registerBtn").click(function(){
			if($("#regigterUsername").val().trim() == "" || $("#regigterFID").val().trim() == "" || $("#regigteruserID").val().trim() == "" || $("#regigterPhone").val().trim() == "" ||$("#regigterAdress").val().trim() == ""){
				alert("请填写完整信息");
				return false;
			}else{
				if(tag1 && tag3 && tag5){
					alert("注册成功")
					return true;
				}else{
					return false;
				}
			}
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