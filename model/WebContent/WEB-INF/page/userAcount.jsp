<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>个人账户</title>
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
	<div class="register-body2">
		<div class="register-area">
			<div class="area-head">
				<h6>我的个人账户</h6>
			</div>
		</div>
		<div class="input-regigter">

			<!-- 显示用户名 -->
			<div class="form-group">
				<label for="regigterUsername" class="col-sm-2 col-sm-2 control-label"> 用 户 名： </label>
				<div class="col-sm-10">
					<input class="form-control acountUsername" disabled="disabled" name="acountUsername" id="acountUsername" type="text" value="${userName}">
				</div>
				<div class="clearfix"></div>
			</div>

			<!-- 显示级别 -->
			<div class="form-group">
				<label for="regigterUsername" class="col-sm-2 col-sm-2 control-label">级&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别： </label>
				<div class="col-sm-10">
					<input class="form-control acountUsername" disabled="disabled" name="acountLevel" id="acountLevel" type="text" value="${level}">
				</div>
				<!-- 
				<div> 
				<button onclick="window.location.href='${APP_PATH }/userServlet?method=promotion'">
				晋级
				</button></div>-->
				<div class="clearfix"></div>
			</div>
			
			<!-- 身份证 -->
			
			<div class="form-group">
					<label for="regigterUsername" class="col-md-2 control-label">身份证： </label>
					<div class="col-md-10">
						<input disabled="disabled" class="form-control acountUsername" name="acountUserID" id="acountUserID" type="text" value="${userID}">
					</div>	
					<div class="clearfix"></div>
				</div>
			
			<!-- 等级关系显示 -->
			<!-- 上级 -->
			<div class="form-group">
			<label class="col-md-2">上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</label>
				<div class="col-md-4 ">
					<div class="input-group">
						<input disabled="disabled" type="text" class="form-control" value="${preUserID}" />
					</div>
				</div>
				<!-- 下级 -->
				<label class="col-md-2">下&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</label>
				<div class="col-md-4">
					<div class="input-group">
						<input type="text" disabled="disabled" class="form-control" value="${nextAmount}" />
						<div class="input-group-btn">
							<button class="btn btn-success" id="displayNextIds">查看等级</button>
						</div>
					</div>
				</div>
			</div>
			
			
			<!-- 显示余额 -->
			<div class="form-group">
				<label for="regigterUsername" class="col-md-2 control-label" style="margin-top: 10px;">账户余额：</label>
				<label class="col-md-5" style="margin-top: 10px;">
					<span>
						${(acount.s_total==0 || acount.s_total==null)?0:acount.s_total}
						<i>元</i>
					</span>
					<button class="btn btn-success" id="displayCashbtn" style="margin-left: 15px;">查看明细</button>
				</label>
				
				<div class="clearfix"></div>
			</div>
			
			<!-- 显示注册时间 -->
			<div class="form-group">
				<label for="regigter-username" class="col-sm-2 col-sm-2 control-label">注册时间：</label>
				<div class="col-sm-10">
					<input class="form-control regigter-username" name="regigterDate" id="regigterDate" placeholder="注册时间"  value="${userTime}" disabled="">
				</div>
				<div class="clearfix"></div>
			</div>
			<!-- 用户操作 -->
			<div class="form-group">
				<label for="regigter-username" class="col-sm-2 col-sm-2 control-label">账户操作：</label>
				<div class="col-sm-10">
					<!--  
					<button class="btn btn-default btn-primary" data-toggle="modal" data-target="#myModal" id="modifyBtn">
						信息修改
					</button>
					-->
					<a style="color: white;" href="${APP_PATH }/userServlet?method=addCard" class="btn btn-primary" id="addCard">
						添加银行卡
					</a>
					<a style="color: white;" href="javaScript:void" class="btn btn-primary" id="updatePasswordBtn">
						修改密码
					</a>
					<a style="color: white;" href="javaScript:void" class="btn btn-primary" id="RechargeBtn">
						账户充值
					</a>
					<a style="color: white;" href="${APP_PATH }/userServlet?method=zhuceye" class="btn btn-primary">注册</a>
				</div>
				<div class="clearfix"></div>
			</div>

		</div>
	</div>
</div>

<!-- 更新密码的模态框 -->
<div class="modal fade" id="newPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">修改密码</h4>
			</div>
			<div class="modal-body">

				<form action="" method="post">
				
					<!-- 填写新密码 -->
					<div class="form-group">
						<label for="" class="col-sm-2 col-sm-2 control-label">新密码:</label>
						<div class="col-sm-10">
							<input class="form-control" name="newPassword" id="userPassword" placeholder="新密码" type="text">
						</div>
						<div class="clearfix"></div>
					</div>
					<input type="submit" id="savePassword" class="btn btn-default btn-success" value="确认">
				</form>
			</div>
		</div>
	</div>
</div>
			
			
<!-- 充值的模态框 -->
<div class="modal fade" id="RechargeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">充值页面</h4>
			</div>
			<div class="modal-body">

				<form action="" method="post">
				
					<!-- 填写金额 -->
					<div class="form-group">
						<label for="" class="col-sm-2 col-sm-2 control-label">充值金额:</label>
						<div class="col-sm-10">
							<input class="form-control" name="Recharge" id="Recharge" placeholder="金额" type="text">
						</div>
						<br/>
						<br/>
						<br/>
						<label for="" class="col-sm-2 col-sm-2 control-label">充值单号:</label>
						<div class="col-sm-10">
							<input class="form-control" name="Ordernumber" id="Ordernumber" placeholder="充值单号" type="text">
						</div>
						<div class="clearfix"></div>
					</div>
					<input type="submit" id="Rechargebtn" class="btn btn-default btn-success" value="确认">
				</form>
			</div>
		</div>
	</div>
</div>
			
			
			
			<!-- 修改信息的按钮控制 -->
<script>
<!-- 信息修改
	$(function(){
		var inp = $("#acountUserID").val().trim();
		if(inp != ""){
			$("#modifyBtn").attr("disabled",true);
		}else{
			$("#modifyBtn").attr("disabled",false);
		}
	});
-->
	$("#updatePasswordBtn").click(function(){
		$("#newPasswordModal").modal("show");
		return false;
	});
	
	$("#RechargeBtn").click(function(){
		$("#RechargeModal").modal("show");
	});
	
	$("#savePassword").click(function(){
		var userPassword = $("#userPassword").val().trim();
		$.ajax({
			url:"${APP_PATH }/userServlet?method=updatePassword",
			type:"POST",
			data:"newPassword=" + userPassword, 
			success:function(result){
				alert("密码修改成功！");
				$("#newPasswordModal").modal("hide");
			}
		});
		return false;
	});
	
	$("#Rechargebtn").click(function(){
		var Recharge = $("#Recharge").val().trim();
		var Ordernumber = $("#Ordernumber").val().trim();
		if((Recharge != null || Recharge !="")&&(Ordernumber != null || Ordernumber != "")){
		$.ajax({
			url:"${APP_PATH }/userServlet?method=Recharge",
			type:"POST",
			data:{"Recharge":Recharge,"Ordernumber":Ordernumber}, 
			success:function(result){
				if(result == false || result == "false"){
					alert("请检查填写是否完整!");
				}else{
					alert("请等待审核...");
				$("#RechargeModal").modal("hide");
				location.href ="${APP_PATH }/userServlet?method=userCount";
				}
			}
		});
		}else
			alert("请输入金额或订单号再点击！");
		return false;
	})
	
	//显示下级按钮
	$("#displayNextIds").click(function(){
		location.href ="${APP_PATH }/userServlet?method=showLevel";
		
		
	});
	
	//显示明细按钮
	$("#displayCashbtn").click(function(){
		location.href ="${APP_PATH }/userServlet?method=showAcount";
		
		
	});
	$("#btnCash").click(function(){
		var total = ${(acount.s_total==0 || acount.s_total==null)?0:acount.s_total};
		var userCash= $("#userCash").val();
		if(total > userCash){
		//ajax提交提现信息
			$.ajax({
				url:"${APP_PATH }/userServlet?method=myCash",
				data:$("#Cash").serialize(),
				type:"POST",
				success:function(result){
					if(result == "false"){
						alert("提现失败");
						location.href ="${APP_PATH }/userServlet?method=userCount";
						return false;
					}
					if(result == "true"){
						//success.jsp只能在外部目录，不然无法加密提交的数据
						alert("提现成功！！！");
						location.href ="${APP_PATH }/userServlet?method=userCount";
						return false;
					}
				}
			});
		}
		else{
			alert("余额不足！！");
			location.href ="${APP_PATH }/userServlet?method=userCount";
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