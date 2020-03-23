<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>用户信息管理</title>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
	
%>
</head>
<style type="text/css">
.current{
background:green;
}
</style>
<body class="content">
<jsp:include page="commonNav.jsp"></jsp:include>
<div class="container" style="margin-top: 0px;">
<h1 class="page-header" style="margin-top: 0;">信息管理</h1>
<form class="form-horizontal adminex-form" action="${APP_PATH }/adminServlet?method=userSelect" method="post">				
		<div class="form-group">
			<label for="regigter-username"
				class="col-sm-2 col-sm-2 control-label" style="font-size: 17px;">
				人员编号：
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control regigter-username"
					name="userId" id="userId"
					placeholder="人员编号" value="${param.userId }">
			</div>
		</div>
		
		<div class="form-group">
			<label for="regigter-username"
				class="col-sm-2 col-sm-2 control-label" style="font-size: 17px;">
				手机号：
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control regigter-username"
					name="regigterPhone" id="regigterPhone"
					placeholder="手机号" value="${param.regigterPhone }">
			</div>
		</div>
		
		<div class="form-group">
			<label for="regigterUsername"
				class="col-sm-2 col-sm-2 control-label" style="font-size: 17px;">用户名：
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control regigterUsername"
					name="regigterUsername" id="regigterUsername"
					placeholder="用户名" value="${param.regigterUsername }">
			</div>
		</div>
		
		<div class="form-group">
			<label for="regigter-userID"
				class="col-sm-2 col-sm-2 control-label" style="font-size: 17px;">
				身份证号：
			</label>
			<div class="col-sm-9" >
				<input type="text" class="form-control regigter-username"
					name="regigteruserID" id="regigteruserID"
					placeholder="身份证号" value="${param.regigteruserID }">
			</div>
		</div>
		
		<div class="form-group">
			<label for="regigter-userID"
				class="col-sm-2 control-label" style="font-size: 17px;">
				日期：
			</label>
			<div class="col-sm-4">
				<input type="text" class="form-control regigter-username"
					name="date" id="date"
					placeholder="2001-09-21" value="${param.date }">
			</div>
		</div>
	
		<button type="submit"
			class="btn btn-default btn-success btn-lg btn-block" id="selectBtn">查询</button>
	</form>
	<hr />
	<c:if test="${empty requestScope.users }">
	<p class="lead text-danger">暂无查询信息</p>
	</c:if>
	<c:if test="${!empty requestScope.users }">
		<p class="lead text-success">查询信息如下：</p>
		<div class="table-responsive">
			<table cellpadding="2" class="table table-striped table-bordered table-hover">
				<tr align="center">
					<th style="text-align: center;">手机号</th>
					<th style="text-align: center;">用户名</th>
					<th style="text-align: center;">性别</th>
					<th style="text-align: center;">身份证号</th>
					<th style="text-align: center;">注册时间</th>
					<th style="text-align: center;">级别</th>
					<th style="text-align: center;">地址</th>
					<th style="text-align: center;">密码</th>
					<th style="text-align: center;">状态</th>
					<th></th>
				</tr>
				<c:forEach items="${users }" var="user">
					<tr align="center">
						<td>${user.uPhone }</td>
						<td>${user.uName }</td>
						<td>${user.uSex }</td>
						<td>${user.uID }</td>
						<td>${user.uRegistTime }</td>
						<td>${user.uLevel }</td>
						<td>${user.uAdress }</td>
						<td>${user.uPassword }</td>
						<c:if test="${user.uStatus==1 }">
							<td><font color="green">已审核</font></td>
						</c:if>
						<c:if test="${user.uStatus!=1 }">
							<td><font color="red">未审核</font></td>
						</c:if>
						<td>
							<button class="btn btn-info updateStatus">用户审核</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	<font color="red" id="ptext">${iCurrPage }</font>
<nav aria-label="Page navigation">
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous" onclick='nextPage(${page.lpage })'>
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>

    <c:forEach begin="${page.lpage }" end="${page.rpage }" var="pageNum">
    	<li><a href="#" onclick='nextPage(${pageNum })'>${pageNum }</a></li>
    </c:forEach>
    <li>
      <a href="#" aria-label="Next" onclick='nextPage(${page.rpage })'>
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</div>

<script>
function nextPage(pageNum){
	window.location = "${APP_PATH }/adminServlet?method=userMassage&&currPage="+pageNum;
}
$(document).on("click",".updateStatus",function(){
	var userID = $(this).parent().parent().find('td:eq(3)').text().trim();
	var status = $(this).parent().parent().find("td:eq(8)").find("font").text().trim();
	var $btn = $(this);
	if(status == "未审核"){
		$.ajax({
			url:"${APP_PATH }/adminServlet?method=updateStatu",
			data:{"uID":userID,"uStatus":"0"},
			type:"POST",
			success:function(result){
				alert("审核成功");
				$btn.parent().parent().find("td:eq(8)").find("font").attr("color","green");
				$btn.parent().parent().find("td:eq(8)").find("font").text("已审核");
			}
		});
	}else if(status == "已审核"){
		var mymessage=confirm("是否将此账号拉黑？"); 
		if(mymessage==true) { 
			$.ajax({
				url:"${APP_PATH }/adminServlet?method=updateStatu",
				data:{"uID":userID,"uStatus":"1"},
				type:"POST",
				success:function(result){
					alert("拉黑成功");
					$btn.parent().parent().find("td:eq(8)").find("font").attr("color","red");
					$btn.parent().parent().find("td:eq(8)").find("font").text("未审核");
				}
			});
		} else if(mymessage==false) { 
			false;
		}
	}
});

/* function shenhe(id,status) {
	location.href="${APP_PATH }/adminServlet?method=updateStatu&uID="+id+"&uStatus="+status;
} */
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
