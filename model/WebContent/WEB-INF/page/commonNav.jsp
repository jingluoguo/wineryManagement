<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
	String username = (String) session.getAttribute("userName");
%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${APP_PATH }/userServlet?method=zhuye"> <img
				src="${APP_PATH}/img/content_logo.png">
			</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"> <span>欢迎 </span><font color="green">${sessionScope.userName}</font> ！
				</a></li>
				<li><a href="#"> <span>购物车</span> <i class="itemCount">${sessionScope.goodAmount}</i>
						<span>件商品</span>
				</a></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">个人中心 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li role="separator" class="divider"></li>
						<li><a href="${APP_PATH }/userServlet?method=userCount">
								<span class="glyphicon glyphicon-jpy"></span>我的账户
						</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="${APP_PATH }/userServlet?method=myShopCart">
								<span class="glyphicon glyphicon-shopping-cart"></span>我的购物车
						</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="${APP_PATH }/adminServlet?method=consumeSelect">
								<span class="glyphicon glyphicon-minus"></span>我的消费记录
						</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="${APP_PATH }/adminServlet?method=cashSelect">
								<span class="glyphicon glyphicon-minus"></span>我的提现记录
						</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="${APP_PATH }/userServlet?method=logout">
								<span class="glyphicon "></span>退出
						</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>