<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新用户管理</title>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
	
%>
</head>
<body class="content">
<jsp:include page="commonNav.jsp"></jsp:include>
<div class="container" style="margin-top: 0px;">
<h1 class="page-header" style="margin-top: 0;">新用户管理</h1>
<form class="form-horizontal adminex-form" action="${APP_PATH }/adminServlet?method=bonusMassage" method="post">				
		<div class="form-group">
			<label for="regigter-username"
				class="col-sm-1 control-label" style="font-size: 17px;">
				日期：
			</label>
			<div class="col-sm-3">
				<input type="text" class="form-control regigter-username"
					name="startTime" id="Date"
					placeholder="2019-01-01" value="${param.startTime }">
			</div>
			<div class="col-sm-3">
				<input type="text" class="form-control regigter-username"
					name="endTime" id="Date"
					placeholder="2019-12-01" value="${param.endTime }">
			</div>
		</div>
		<button type="submit"
			class="btn btn-default btn-success btn-lg btn-block" id="selectBtn">查询</button>
	</form>
	<hr />
	<c:if test="${empty requestScope.bonusRecords }">
	<p class="lead text-danger">暂无查询信息</p>
	</c:if>
	<c:if test="${!empty requestScope.bonusRecords }">
		<p class="lead text-success">查询信息如下：</p>
		<div class="table-responsive">
			<table cellpadding="2" class="table table-striped table-bordered table-hover">
				<tr align="center">
					<th style="text-align: center;">序号</th>
					<th style="text-align: center;">姓名</th>
					<th style="text-align: center;">身份证</th>
					<th style="text-align: center;">父级身份证</th>
					<th style="text-align: center;">添加时间</th>
					<th style="text-align: center;">注册基金</th>
				</tr>
				<c:forEach items="${bonusRecords }" var="bonusRecord">
					<tr align="center">
						<td>${bonusRecord.id }</td>
						<td>${bonusRecord.r_name }</td>
						<td>${bonusRecord.r_ID }</td>
						<td>${bonusRecord.r_FID }</td>
						<td>${bonusRecord.r_time }</td>
						<td>${bonusRecord.r_money }</td>
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
    	<li class="active"><a href="#" onclick='nextPage(${pageNum })'>${pageNum }</a></li>
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
	window.location = "${APP_PATH }/adminServlet?method=bonusMassage&&currPage="+pageNum;
};

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
