<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<meta http-equiv="X-UA-Compatible" content="ie=edge">
			<title>龙酒公司</title>
			<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
			<%
				pageContext.setAttribute("APP_PATH", request.getContextPath());
			%>

		</head>

<body class="content">
	<c:if test="${sessionScope.userID =='371522199904018483' }">
		<jsp:include page="../admin/commonNav.jsp"></jsp:include>	
	</c:if>
	<c:if test="${sessionScope.userID !='371522199904018483' }">
		<jsp:include page="commonNav.jsp"></jsp:include>
	</c:if>
	<div class="container" style="background-color: #e8ffe8;">
		<form action="${APP_PATH }/cartServlet?method=selectBycondition" class="form-horizontal" style="margin-top: 15px;" method="POST">
			<div class="row">
				<div class="form-group col-md-5">
					<label class="col-md-3 control-label text-info" style="font-size: 20px;">商品类别：</label>
					<div class="col-md-5">
						<input type="text" class="form-control " placeholder="查找类别" name="category" id="category" value="${param.category }">
					</div>
				</div>
				<div class="form-group col-md-5">
					<label class="col-md-3 control-label text-info" style="font-size: 20px;">价格区间：</label>
					<div class="col-md-4">
						<input type="text" class="form-control " placeholder="最低价格" name="minPrice" id="minPrice" value="${param.minPrice }">
					</div>
					<div class="col-md-1 text-info">
						<span class="glyphicon glyphicon-minus"></span>
					</div>
					<div class="col-md-4">
						<input type="text" class="form-control " placeholder="最高价格" name="maxPrice" id="maxPrice" value="${param.maxPrice }">
					</div>
				</div>
				<div class="form-group col-md-2">
					<input type="submit" value="搜索" class="btn btn-danger" id="selectBycondition">
				</div>
			</div>
		</form> 
		<script type="text/javascript">
			$("#selectBycondition").click(function(){
				var minPrice = $("#minPrice").val().trim();
				var maxPrice = $("#maxPrice").val().trim();
				//isNaN(数字类型)：返回false
				if(isNaN(minPrice) || isNaN(maxPrice)){
					return false;
				}
			});
		</script>
		
		<div class="row">
			<c:if test="${empty wineInfos}">
				<p class="text-danger" style="font-size: 25px; margin: 15px;">没有搜索到您想要的商品！</p>
			</c:if>
				<c:forEach items="${wineInfos}" var="wineInfo">
					<div class="col-md-3" style="padding-top: 15px; ">
						<a href="#">
							<img class="img-rounded" alt="" src="${wineInfo.wineImg }" style="width:100%;height:250px;">
						</a>
						<div class="row" style="padding-top: 15px;margin:0 0px ; background: #DDDDDD;">
							<span class="col-md-6 text-paimary">
								${wineInfo.wineName }
							</span>
							<span class="text-info col-md-6">
								库存量：<span class="wineNumber">${wineInfo.wineNumber }</span>
							</span>
							<h6 class="lead col-md-12" style="font-size: 17px;">
								价格：<span class="text-danger">￥${wineInfo.winePrice }</span>
							</h6>
							<button class="btn btn-danger btn-sm col-md-4 addShopCart" wineId="${wineInfo.wineId }" style="float: right;">加入购物车</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	<script type="text/javascript">
	$(".addShopCart").click(function(){
		var b = $(this).parent().find(".wineNumber").text();
		if(b == '0' || b == 0){
			alert("库存量不足！");
			return false;
		}
		var $a = $(this);
		var wineId = $a.attr("wineId");
		$.ajax({
			url:"${APP_PATH }/cartServlet?method=addShopCart",
			data:"wineId=" + wineId,
			type:"POST",
			success:function(result){
				//减少库存量，并且提示加入购物车成功
				var $td = $a.parent().find(".wineNumber");
				var number = $td.text();
				number = number - 1;
				$td.text(number);
				//改变右上角的购物车商品数量,该商品没有在购物车中就会加1
				if(result == "false"){
					var goodAmount = parseInt($(".itemCount").text().trim());
					$(".itemCount").text(goodAmount+1);
				}
				alert("加入购物车成功！");
			}
		});
		return false;
	});
	
	document.addEventListener('plusready', function(a) {
	    var first = null;
	    plus.key.addEventListener('backbutton', function() {
	            //首次按键，提示‘再按一次退出应用’
	            if (!first) {
	                first = new Date().getTime();
	                //alert("再按一次返回键退出")//用自定义toast提示最好
	                setTimeout(function() {
	                    first = null;
	                }, 1000);
	            } else {
	                if (new Date().getTime() - first < 1000) {
	                    plus.runtime.quit();
	                }
	            }
	        }, false);
	});
	
	</script>
</body>


		</html>