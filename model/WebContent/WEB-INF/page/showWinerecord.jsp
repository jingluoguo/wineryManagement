<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>查看酒水消费</title>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
	
%>
</head>
<body class="content">
<jsp:include page="commonNav.jsp"></jsp:include>
<div class="container" style="margin-top: 0px;">
<h1 class="page-header" style="margin-top: 0;">个人酒水消费</h1>

	<c:if test="${empty requestScope.list }">
	<p class="lead text-danger">暂无消费记录</p>
	</c:if>
	<c:if test="${!empty requestScope.list }">
		<p class="lead text-success">消费记录如下：</p>
		<div class="row">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover" id="candidates_table">
					<thead>
						<tr align="center">
							<th style="text-align: center;">姓名</th>
							<th style="text-align: center;">身份证号</th>
							<th style="text-align: center;">用户等级</th>
							<th style="text-align: center;">购买酒水</th>
							<th style="text-align: center;">消费额度</th>
							<th style="text-align: center;">消费日期</th>
							<th style="text-align: center;">折扣</th>
						</tr>
					</thead>	
					<tbody>
					<c:forEach items="${requestScope.list }" var="ce">
						<tr align="center">
							<td>${ce.uName }</td>
							<td>${ce.uID }</td>
							<td>${ce.uLevel }</td>
							<td>${ce.cWin }</td>
							<td>${ce.cPrice }</td>
							<td>${ce.cDate }</td>
							<td>${ce.cAgio }</td>
						</tr>
					</c:forEach>	
					</tbody>
				</table>
			</div>
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

<script type="text/javascript">
function nextPage(pageNum){
	window.location = "${APP_PATH }/adminServlet?method=consumeSelect&&currPage="+pageNum;
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