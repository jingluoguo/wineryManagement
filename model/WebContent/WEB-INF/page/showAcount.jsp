<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%
pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<title>收益明细</title>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
</head>
<body class="content">
	<c:if test="${sessionScope.userID =='371522199904018483' }">
		<jsp:include page="../admin/commonNav.jsp"></jsp:include>	
	</c:if>
	<c:if test="${sessionScope.userID !='371522199904018483' }">
		<jsp:include page="commonNav.jsp"></jsp:include>
	</c:if>
    	<form id="Cash">
    	<div class="table-responsive">
			<table cellpadding="2" class="col-sm-offset-1 col-md-10 table table-striped table-bordered">
		        <tr align="center">
		            <th style="text-align: center;">酒水消费</th>
		            <th style="text-align: center;">A区业绩奖励</th>
		            <th style="text-align: center;">B区业绩奖励</th>
		            <th style="text-align: center;">直接销售奖励</th>
		            <th style="text-align: center;">小区业绩奖励</th>
		            <th style="text-align: center;">奖励总额</th>
		            <th style="text-align: center;">复投基金</th>
		            <th style="text-align: center;">扣除5%平台费</th>
		            <th style="text-align: center;">账户余额</th>
		            <!-- <th style="text-align: center;">实际可提现余额</th> -->
		            <th style="text-align: center;">已提现现金</th>
		        </tr>
		
		        <tr align="center">
		            <td>${acount.s_Winconsume }</td>
		            <td>${acount.s_big }</td>
		            <td>${acount.s_small }</td>
		            <td>${acount.s_sale }</td>
		            <td>${acount.s_achievement }</td>
		            <td>${acount.s_balance }</td>
		            <td>${acount.s_fund }</td>
		            <td>${acount.s_tax }</td>
		            <td>${acount.s_total}</td>
		            <!-- <td>${acount.s_total }</td> -->
		            <td>${acount.s_Deconsume }</td>
		        </tr>
	    	</table>
	    </div>
    	<br/>
    	<br/>
    	<br/>
    	<br/>
		<div class="form-group">
			<label for="regigter-username" class="col-md-offset-4 col-md-1 control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提现金额:</label>
			<div class="col-md-2">
				<input class="form-control regigter-username" name="userCash" id="userCash" disabled="disabled" value="100" type="text">
				<input id="bt1" type="button" value="增" />
				<input id="bt2" type="button" value="减" />
			</div>
			<input id="btnCash" type="submit" class="btn btn-default btn-success" value="确认">
		</div>
		</form>
</body>
<script>
$("#bt1").click(function(){
	var userCash = parseFloat($("#userCash").val()) + 100;
	$("#userCash").val(userCash);
	});
$("#bt2").click(function(){
	var Cash = $("#userCash").val();
	if(Cash == 100){
		alert("已经达到最低额度!!!")
	}else{
		var userCash = parseFloat(Cash) - 100;
		$("#userCash").val(userCash);
	}
});
$("#btnCash").click(function(){
	var total = ${acount.s_total};
	var userCash= $("#userCash").val();
	//alert(userCash);
	if(total > userCash && total >= 100 && userCash >= 100){
	//ajax提交提现信息
		$.ajax({
			url:"${APP_PATH }/userServlet?method=myCash",
			type:"POST",
			data:"userCash=" + userCash,
			success:function(result){
				if(result == "false"){
					alert("提现失败，请检查银行卡信息是否正确");
					location.href ="${APP_PATH }/userServlet?method=userCount";
					return false;
				}
				if(result == "true"){
					//success.jsp只能在外部目录，不然无法加密提交的数据
					alert("提现申请中...");
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
</html>