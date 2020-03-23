<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>用户订单管理</title>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
	
%>
</head>
<body class="content">
<jsp:include page="commonNav.jsp"></jsp:include>
<div class="container" style="margin-top: 0px;">
<h1 class="page-header" style="margin-top: 0;">用户订单管理</h1>
<form class="form-horizontal adminex-form" action="${APP_PATH }/adminServlet?method=wineSelect" method="post">				
		<div class="form-group">
			<label for="regigter-username"
				class="col-sm-2 col-sm-2 control-label" style="font-size: 17px;">
				订单号：
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control regigter-username"
					name="wineId" id="wineId"
					placeholder="订单号" value="${param.wineId }">
			</div>
		</div>
		
		<div class="form-group">
			<label for="regigter-username"
				class="col-sm-2 col-sm-2 control-label" style="font-size: 17px;">
				手机号：
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control regigter-username"
					name="Phone" id="Phone"
					placeholder="手机号" value="${param.Phone }">
			</div>
		</div>
		
		<div class="form-group">
			<label for="regigter-username"
				class="col-sm-2 col-sm-2 control-label" style="font-size: 17px;">
				日期：
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control regigter-username"
					name="Date" id="Date"
					placeholder="日期" value="${param.Date }">
			</div>
		</div>
		<button type="submit"
			class="btn btn-default btn-success btn-lg btn-block" id="selectBtn">查询</button>
	</form>
	<hr />
	<form action="${APP_PATH }/adminServlet?method=exportOrderExcel" method="post">
		<input type="hidden" name="wineId" value="${param.wineId }">
		<input type="hidden" name="phone" value="${param.Phone }">
		<input type="hidden" name="Date" value="${param.Date }">
		<input id="btnExport1" type="submit" value="导出Excel">
	</form>
	<c:if test="${empty requestScope.orders }">
	<p class="lead text-danger">暂无查询信息</p>
	</c:if>
	<c:if test="${!empty requestScope.orders }">
		<p class="lead text-success">查询信息如下：</p>
		<div class="table-responsive">
			<table cellpadding="2" class="table table-striped table-bordered table-hover">
				<tr align="center">
					<th style="text-align: center;">订单号</th>
					<th style="text-align: center;">姓名</th>
					<th style="text-align: center;">手机号</th>
					<th style="text-align: center;">酒水名称</th>
					<th style="text-align: center;">酒水单价</th>
					<th style="text-align: center;">酒品数量</th>
					<th style="text-align: center;">总消费</th>
					<th style="text-align: center;">收货地址</th>
					<th style="text-align: center;">订单日期</th>
					<th style="text-align: center;">备注</th>
					<th style="text-align: center;">状态</th>
					<th></th>
				</tr>
				<c:forEach items="${orders }" var="order">
					<tr align="center">
						<td>${order.orderId }</td>
						<td>${order.orderName }</td>
						<td>${order.orderPhone }</td>
						<td>${order.orderWineName }</td>
						<td>${order.orderSimple }</td>
						<td>${order.orderNumber }</td>
						<td>${order.orderPrice }</td>
						<td>${order.orderAddress }</td>
						<td>${order.orderDate }</td>
						<td>${order.orderStatus }</td>
						<c:if test="${order.orderCope==false }">
							<td><font color="red">未发货</font></td>
						</c:if>
						<c:if test="${order.orderCope==true }">
							<td><font color="green">已发货</font></td>
						</c:if>
						<td>
							<button class="btn btn-info updateStatus">订单处理</button>
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
	window.location = "${APP_PATH }/adminServlet?method=showOrder&&currPage="+pageNum;
}
$(document).on("click",".updateStatus",function(){
	var wineID = $(this).parent().parent().find('td:eq(0)').text().trim();
	var wineStatus = $(this).parent().parent().find("td:eq(10)").find("font").text().trim();
	var $btn = $(this);
	if(wineStatus == "未发货"){
		$.ajax({
			url:"${APP_PATH }/adminServlet?method=updateOrderStatu",
			data:{"wID":wineID,"wStatus":"false"},
			type:"POST",
			success:function(result){
				alert("审核成功");
				$btn.parent().parent().find("td:eq(10)").find("font").attr("color","green");
				$btn.parent().parent().find("td:eq(10)").find("font").text("已发货");
			}
		});
	}else if(wineStatus == "已发货"){
		return false;
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
