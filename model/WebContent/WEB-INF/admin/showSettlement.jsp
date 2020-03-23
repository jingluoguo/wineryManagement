<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>人员收益明细</title>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
	int sale = 0;
	int deposit = 0;
%>
<script src="js/tableExport.js"></script>
<script src="js/jquery.base64.js"></script>
<script src="js/html2canvas.js"></script>
<script src="js/jspdf/libs/sprintf.js"></script>
<script src="js/jspdf/jspdf.js"></script>
<script src="js/jspdf/libs/base64.js"></script>
</head>
<body class="content">
<jsp:include page="commonNav.jsp"></jsp:include>
<div class="container" style="margin-top: 0px;">
<h1 class="page-header" style="margin-top: 0;">收益明细</h1>
<form class="form-horizontal adminex-form" action="${APP_PATH }/adminServlet?method=settlementSelect" method="post">				
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
			<label for="regigterPhone"
				class="col-sm-2 col-sm-2 control-label" style="font-size: 17px;">
				手机号：
			</label>
			<div class="col-sm-9" >
				<input type="text" class="form-control regigter-username"
					name="regigterPhone" id="regigterPhone"
					placeholder="手机号" value="${param.regigterPhone }">
			</div>
		</div>
		
		<div class="form-group">
			<label for="date"
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
	<form action="${APP_PATH }/adminServlet?method=outPdf" method="post">
		<input type="hidden" name="userNo" value="${param.userId }">
		<input type="hidden" name="phone" value="${param.regigterPhone }">
		<input type="hidden" name="userID" value="${param.regigteruserID }">
		<input type="hidden" name="date" value="${param.date }">
		<input id="btnExport1" type="submit" value="导出PDF">
	</form>
	<form action="${APP_PATH }/adminServlet?method=exportExcel" method="post">
		<input type="hidden" name="userNo" value="${param.userId }">
		<input type="hidden" name="phone" value="${param.regigterPhone }">
		<input type="hidden" name="userID" value="${param.regigteruserID }">
		<input type="hidden" name="date" value="${param.date }">
		<input id="btnExport1" type="submit" value="导出Excel">
	</form>
	
	<c:if test="${empty requestScope.settlements }">
	<p class="lead text-danger">暂无查询信息</p>
	</c:if>
	<c:if test="${!empty requestScope.settlements }">
		<p class="lead text-success">查询信息如下：</p>
		<div class="table-responsive">
		
			<table id="main-grid-print" data-toggle="main-grid-print" cellpadding="2" class="table table-striped table-bordered table-hover">
				<tr align="center">
					<th style="text-align: center;">用户编号</th>
					<th style="text-align: center;">用户姓名</th>
					<th style="text-align: center;">身份证</th>
					<th style="text-align: center;">酒水消费</th>
					<th style="text-align: center;">A区业绩总消费</th>
					<th style="text-align: center;">B区业绩总消费</th>
					<th style="text-align: center;">直接销售奖励</th>
					<th style="text-align: center;">小区奖励</th>
					<th style="text-align: center;">奖励总额</th>
					<th style="text-align: center;">复投基金</th>
					<th style="text-align: center;">扣除5%平台费</th>
					<th style="text-align: center;">账户余额</th>
					<th style="text-align: center;">现金已提取</th>
					<th style="text-align: center;">注册日期</th>
					<th style="text-align: center;">注册资金</th>
					<th style="text-align: center;">结余</th>
				</tr>
				<c:forEach items="${settlements }" var="settlement">
					<tr align="center">
						<td>${settlement.s_id }</td>
						<td>${settlement.s_name }</td>
						<td>${settlement.s_ID }</td>
						<td>${settlement.s_Winconsume }</td>
						<td>${settlement.s_big }</td>
						<td>${settlement.s_small }</td>
						<td>${settlement.s_sale }</td>
						<td>${settlement.s_achievement }</td>
						<td>${settlement.s_balance }</td>
						<td>${settlement.s_fund }</td>
						<td>${settlement.s_tax }</td>
						<td>${settlement.s_total }</td>
						<td>${settlement.s_Deconsume }</td>
						<td>${settlement.s_date }</td>
						<td>${settlement.s_as_deposit }</td>
						<td>${settlement.s_cba }</td>
					</tr>
				</c:forEach>
				<tr align="center">
					<td>总和</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>${balance }</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>${deposit }</td>
				</tr>
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
<script type="text/javascript">
function nextPage(pageNum){
	window.location = "${APP_PATH }/adminServlet?method=showSettlement&&currPage="+pageNum;
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