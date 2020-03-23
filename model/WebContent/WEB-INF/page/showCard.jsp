<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>用户提现信息管理</title>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
	
%>
</head>
<body class="content">
<jsp:include page="commonNav.jsp"></jsp:include>
<div class="container" style="margin-top: 0px;">
<h1 class="page-header" style="margin-top: 0;">提现管理</h1>
<hr />
<form action="${APP_PATH }/adminServlet?method=exportCashExcel" method="post">
	<input type="hidden" name="userNo" value="${param.userId }">
	<input type="hidden" name="phone" value="${param.regigterPhone }">
	<input type="hidden" name="userID" value="${param.regigteruserID }">
	<input type="hidden" name="date" value="${param.date }">
	<input id="btnExport1" type="submit" value="导出Excel">
</form>
<c:if test="${empty requestScope.cashs }">
<p class="lead text-danger">暂无查询信息</p>
</c:if>
<c:if test="${!empty requestScope.cashs }">
	<p class="lead text-success">查询信息如下：</p>
	<div class="table-responsive">
		<table cellpadding="2" class="table table-striped table-bordered table-hover">
			<tr align="center">
				<th style="text-align: center;">提现编号</th>
				<th style="text-align: center;">姓名</th>
				<th style="text-align: center;">身份证号</th>
				<th style="text-align: center;">银行卡号</th>
				<th style="text-align: center;">开户行地址</th>
				<th style="text-align: center;">实际提现金额</th>
				<th style="text-align: center;">日期</th>
				<th style="text-align: center;">状态</th>
			</tr>
			<c:forEach items="${cashs }" var="cash">
				<tr align="center">
					<td>${cash.id }</td>
					<td>${cash.c_name }</td>
					<td>${cash.c_ID }</td>
					<td>${cash.c_card }</td>
					<td>${cash.c_address }</td>
					<td>${cash.c_total }</td>
					<td>${cash.c_date }</td>
					<c:if test="${cash.c_status==1 }">
						<td><font color="green">已处理</font></td>
					</c:if>
					<c:if test="${cash.c_status!=1 }">
						<td><font color="red">未处理</font></td>
					</c:if>
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
	window.location = "${APP_PATH }/adminServlet?method=showCashback&&currPage="+pageNum;
}
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