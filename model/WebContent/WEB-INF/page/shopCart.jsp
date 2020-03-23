<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的购物车</title>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>

</head>
<%
pageContext.setAttribute("APP_PATH", request.getContextPath());
String level = (String) request.getSession().getAttribute("level");
%>
<body class="content">
	<c:if test="${sessionScope.userID =='371522199904018483' }">
		<jsp:include page="../admin/commonNav.jsp"></jsp:include>	
	</c:if>
	<c:if test="${sessionScope.userID !='371522199904018483' }">
		<jsp:include page="commonNav.jsp"></jsp:include>
	</c:if>
	<div class="content-body">
		<div class="table-responsive">
			<table class="table table-hover book-list">
				<thead>
					<tr>
						<th>
							<input type="checkbox">
						</th>
						<th>酒水名称</th>
						<th>酒水价格</th>
						<th>查看详情</th>
						<th>购买数量</th>
						<th>操 作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="shopCart">
						<c:set value="${amountPrice + shopCart.wineInfo.winePrice*shopCart.amount }" var="amountPrice" ></c:set>
						<tr>
							<td>
								<input type="checkbox" class="addToResultArea">
							</td>
							<td >${shopCart.wineInfo.wineName}</td>
							<td style="color: red;">${shopCart.wineInfo.winePrice}
								<span>元</span>
							</td>
							<td><button class="btn btn-waring wine_detail" wineId="${shopCart.wineInfo.wineId }">商品详情</button> </td>
							<td>
								<div class="row">
									<div class="input-group show-count">
										<div class="input-group-addon control-count min">
											<span class="glyphicon glyphicon-minus"></span>
										</div>
										<input type="text" class="form-control amount" style="width: 195px;text-align: center;margin-right:0 " value="${shopCart.amount}" amount="${shopCart.amount}" winePrice="${shopCart.wineInfo.winePrice }">
										<div class="input-group-addon control-count add">
											<span class="glyphicon glyphicon-plus"></span>
										</div>
									</div>
								</div>
								<td>
								<a href="" shopCartId="${shopCart.id }" class="modifyAmount btn btn-warning" style="text-decoration:none;">提交</a>
								</td>
							</td>
							<td>
								<a href="${APP_PATH}/cartServlet?method=deleteWine&shopCartId=${shopCart.id }" class="btn btn-warning deleteWine"  aria-haspopup="true" aria-expanded="false">移除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<!-- 商品详情的模态框 -->
		<div id="wine_modal" class="modal fade">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<h1>
							<span class='glyphicon glyphicon-equalizer'></span> 商品详情
						</h1>
					</div>
					<div class="modal-body">
						<form action="" class="form-horizontal">
							<div class="form-group">
								<label class="control-label col-md-5 text-success" id="detail_wineName"></label>
							</div>
							<div class="form-group">
								<div class="col-md-8 col-md-offset-2">
									<img id="detail_winePic" alt="商品图片" src="" width="100%">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-5 text-info">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</label>
								<div class="col-md-7">
									<!--目前知道的只能是p标签-->
									<p class="form-control-static lead" id="detail_winePrice"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-5 text-info">&nbsp;商品类别：</label>
								<div class="col-md-7">
									<!--目前知道的只能是p标签-->
									<p class="form-control-static text-warning" id="detail_wineCategory"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-5 text-info" >剩余库存：</label>
								<div class="col-md-7 text-danger">
									<!--目前知道的只能是p标签-->
									<p class="form-control-static lead" id="detail_wineNumber"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-5 text-info">&nbsp;商品简介：</label>
								<div class="col-md-7">
									<!--目前知道的只能是p标签-->
									<p class="form-control-static" id="detail_wineDesc"></p>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<div class="row">
							<button class="btn btn-danger col-md-offset-5 col-md-2" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
	</div>
	
	</div>
	
	
	<script>
	//显示商品详情,ajax请求返回json对象
	$(".wine_detail").click(function(){
		var wineId = $(this).attr("wineId");
		$.getJSON("${APP_PATH}/cartServlet?method=wineDetail", {"wineId":wineId}, function(result){
			$("#detail_wineName").empty().text(result.wineName);
			$("#detail_wineNumber").empty().text(result.wineNumber);
			$("#detail_wineDesc").empty().text(result.wineDec);
			$("#detail_wineCategory").empty().text(result.wineCategory);
			$("#detail_winePrice").empty().text(result.winePrice);
			$("#detail_winePic").attr("src",result.wineImg);
			$("#wine_modal").modal({
				backdrop:"static",
			});
		});
		
		
	});
	
	//点击按钮实现增加和减少商品数量
		var addButton = $('.add');
		var minButton = $('.min');
		//增加按钮
		for (var i = 0; i < addButton.length; i++) {
			$(addButton[i]).click(function () {
				var oldValue = $(this).prev().val();
				oldValue++;
				$(this).prev().val(oldValue);
			});
		}
		//减少按钮
		for (var i = 0; i < minButton.length; i++) {
			$(minButton[i]).click(function () {
				var oldValue = $(this).next().val();
				oldValue--;
				if (oldValue < 1) {
					oldValue = 1;
				}
				$(this).next().val(oldValue);
			});
		}
	
		//修改商品数量的Ajax
		$(document).on("click",".modifyAmount",function(){
			var shopCartId = $(this).attr("shopCartId");
			//原来的数量
			var wineAmount1 = $(this).parent().parent().find(".amount").attr("amount");
			var wineAmount = $(this).parent().parent().find(".amount").val();
			//这件商品的单价
			var winePrice = $(this).parent().parent().find(".amount").attr("winePrice");
			//如果数量真的发生了变化
			if(wineAmount1 != wineAmount){
				$.ajax({
					url:"${APP_PATH}/cartServlet?method=modifyAmount",
					data:{"shopCartId":shopCartId,"wineAmount":wineAmount,"wineAmount2":wineAmount1},
					type:"POST",
					success:function(result){
						if(result == "false"){
							alert("库存量不足！");
						}
						window.location = "${APP_PATH }/userServlet?method=myShopCart";
					}
				});
			}
			return false;
		});
	</script>
	
		<form action="" class="order-area" method="post" style="position: fixed;">
			<!-- 显示订单总金额 -->
			<p>
				您的购物车中共有
				<span class="order-count" id="wineKind">${list.size() }</span> 件商品 总金额为：
				<span class="order-money" id="amountPrice">
					<fmt:formatNumber type="number" value="${amountPrice!=null?amountPrice:0}" maxFractionDigits="2"/> 
				</span> 元
			</p>
			<!-- 结算按钮 -->
			<button type="submit" id="settlementBtn" class="btn btn-default btn-success">
				去结算
				<span class="glyphicon glyphicon-shopping-cart"></span>
			</button>
		</form>
	<!-- 结算页面的模态框 -->
	<div id="consumeModal" class="modal fade modal_mark">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h2><span class="glyphicon glyphicon-pencil"></span> 确认订单</h2>
					</div>
					<div class="modal-body">
						<form action="" id="consumeInfo" class="form-horizontal">
							<c:forEach items="${list}" var="shopCart2">
								
								<div class="form-group wine" shopCartId="${shopCart2.id }" >
									<label class="control-label col-md-2">${shopCart2.wineInfo.wineName }:</label>
									<div class="col-md-10">
										<!--目前知道的只能是p标签-->
										<p class="form-control-static">${shopCart2.wineInfo.winePrice }￥ X <span class="wineAmount">${shopCart2.amount }</span></p>
									</div>
								</div>
								
							</c:forEach>
							<div class="form-group">
								<label class="control-label col-md-3 text-success">总计：</label>
								<div class="col-md-9 text-danger">
									<p class="lead">
										<span>
											<fmt:formatNumber type="number" value="${amountPrice!=null?(amountPrice-deposit):0}" maxFractionDigits="2"/> 
										</span> 元
										<c:if test="${deposit != 0.0 }">
											<span class="small">&nbsp;&nbsp;(定金抵消${deposit }￥)</span>
										</c:if>
									</p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 text-success">支付方式：
								</label>
								<input name="pay" checked="checked"  type="radio" value="平台支付" >平台支付
								<!-- <input name="pay" checked="checked" type="radio" value="在线支付" >在线支付 -->
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-success" id="saveConsume" value="确认支付" />
						<input type="button" class="btn btn-danger closeModal" value="关闭" />
					</div>
				</div>
			</div>
		</div>
	<script type="text/javascript">
	$(".closeModal").click(function(){
		$("#consumeModal").modal("hide");
	});
	$("#settlementBtn").click(function(){
		var t =  ${amountPrice-deposit};
		var deposit = ${deposit };
		if(t < 0){
			alert("首次消费必须大于" + deposit + "元！");
		}else{
			$("#consumeModal").modal({
				backdrop:"static"
			});
		}
		return false;
	});
	
	//确认支付后台操作
	$("#saveConsume").click(function(){
		var $wines = $(".wine");
		var shopCartIds = "";
		var payway = $("input[type='radio']:checked").val();
		var total = ${amountPrice-deposit};
		//遍历结算页面的元素，把购物车的id号组合成字符串传到后台
		$wines.each(function(index){
			if(index == 0){
				shopCartIds = shopCartIds + $(this).attr("shopCartId");
			}else{
				shopCartIds = shopCartIds + "-" + $(this).attr("shopCartId");
			}
		});
		$.ajax({
			url:"${APP_PATH}/consumeServlet?method=saveConsumeRecord",
			data:{"shopCartIds": shopCartIds,"payway":payway,"total":total},
			type:"POST",
			success:function(result){
				if(result == "false"){
					window.location.href="fail.jsp";
				}
				if(result == "true"){
					//success.jsp只能在外部目录，不然无法加密提交的数据
					window.location.href="success.jsp";
				}
			}
		});
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