<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>等级关系表</title>
<jsp:include page="/common/cssAndjs.jsp"></jsp:include>
</head>
<body  class="content">
	<c:if test="${sessionScope.userID =='371522199904018483' }">
		<jsp:include page="../admin/commonNav.jsp"></jsp:include>	
	</c:if>
	<c:if test="${sessionScope.userID !='371522199904018483' }">
		<jsp:include page="commonNav.jsp"></jsp:include>
	</c:if>
<div class="container" style="margin-top:0 ">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="table-responsive">
			    <table border="1" cellpadding="2" class="table table-striped table-bordered">
			        <tr align="center" class="text-success">
			            <th style="text-align: center;">姓名</th>
			            <th style="text-align: center;">身份证</th>
			        </tr>
			
			        <tr align="center">
				        <td>
							${username } 
						</td>
				        <td>
				        	${userID } 
				        </td>
			        </tr>
				</table>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="table-responsive">
			   <table border="1" cellpadding="2" class="table table-striped table-bordered">
			       	<tr align="center">
			            <th style="text-align: center;"  class="text-info">A区人数</th>
			            <th style="text-align: center;"  class="text-info">A区总业绩</th>
			            <th style="text-align: center;"  class="text-info">姓名</th>
			            <th style="text-align: center;"  class="text-info">身份证</th>
			            <th style="text-align: center;"  class="text-info">姓名</th>
			            <th style="text-align: center;"  class="text-info">身份证</th>
			            <th style="text-align: center;"  class="text-info">B区人数</th>
			            <th style="text-align: center;"  class="text-info">B区总业绩</th>
			        </tr>
			
			        <tr align="center">
			        
				        <td>
							${lNumber==null?"无":lNumber } 
						</td>
						<td>
							${lAccount==null?"无":lAccount } 
						</td>
				        <td>
							${nName1==null?"无":nName1 } 
						</td>
				        <td id="lID">
				        	${nextID1==null?"无":nextID1 } 
				        </td>
				        <td>
							${nName2==null?"无":nName2 } 
						</td>
				        <td id="rID">
				        	${nextID2==null?"无":nextID2 } 
				        </td>
				        <td>
							${rNumber==null?"无":rNumber } 
						</td>
				        <td>
							${rAccount==null?"无":rAccount } 
						</td>
			        </tr>
		    	</table>
		    </div>
	   	</div>
	</div>
    <div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="table-responsive">
		    	<table border="1" cellpadding="2" class="table table-striped table-bordered">
			       	<tr align="center" >
			            <th style="text-align: center;" class="text-danger">姓名</th>
			            <th style="text-align: center;" class="text-danger">身份证</th>
			            <th style="text-align: center;" class="text-danger">姓名</th>
			            <th style="text-align: center;" class="text-danger">身份证</th>
			            <th style="text-align: center;" class="text-warning">姓名</th>
			            <th style="text-align: center;" class="text-warning">身份证</th>
			            <th style="text-align: center;" class="text-warning">姓名</th>
			            <th style="text-align: center;" class="text-warning">身份证</th>
			        </tr>
			        <tr align="center">
				        <td>
							${nnName1==null?"无":nnName1 } 
						</td>
				        <td>
				        	${nnextID1==null?"无":nnextID1 } 
				        </td>
				        <td>
							${nnName2==null?"无":nnName2 } 
						</td>
				        <td>
				        	${nnextID2==null?"无":nnextID2 } 
				        </td>
				        <td>
							${nnName3==null?"无":nnName3 } 
						</td>
				        <td>
				        	${nnextID3==null?"无":nnextID3 } 
				        </td>
				        <td>
							${nnName4==null?"无":nnName4 } 
						</td>
				        <td>
				        	${nnextID4==null?"无":nnextID4 } 
				        </td>
			        </tr>
		    	</table>
		    </div>
	    </div>
	  </div>
	  <div class="row">
		<c:if test="${empty requestScope.nextIDAlls }">
		<p class="lead text-danger">暂无下级信息</p>
		</c:if>
		<c:if test="${!empty requestScope.nextIDAlls }">
			<p class="lead text-success">下级信息如下：</p>
			<div class="table-responsive">
				<table id="main-grid-print" data-toggle="main-grid-print" cellpadding="2" class="table table-striped table-bordered table-hover">
					<tr align="center">
						<th style="text-align: center;">用户姓名</th>
						<th style="text-align: center;">身份证</th>
						<th style="text-align: center;">操作</th>
					</tr>
					<c:forEach items="${nextIDAlls }" var="nextIDAll">
						<tr align="center">
							<td>${nextIDAll.uName }</td>
							<td class="uID">${nextIDAll.uID }</td>
							<td>
								<button class="updateOrder" class="btn btn-info updateStatus">替换</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	  </div>
	  <form action="" method="post">
		<div id="change_modal" class="modal fade">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-body">
						<label for="" class="col-sm-2 col-sm-2 control-label">身份证:</label>
						<div class="col-sm-10">
							<input class="form-control" name="ChangeFID" id="ChangeFID" placeholder="身份证" type="text">
						</div>
					</div>
					<br/>
					<br/>
					<div class="modal-footer">
						<div style="text-align: center;">
						<input type="submit" id="ChangeID" class="btn btn-default btn-success" value="确认">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</div>
    <%
    	pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
<script type="text/javascript">
var userId = "";
$(document).on("click",".updateOrder",function(){
	userId = $(this).parent().parent().find(".uID").text();
	var flag = false;
	$.ajax({
		url:"${APP_PATH}/userServlet?method=checkNID",
		data:{"userID":userId},
		type:"POST",
		success:function(result){
			if(result == "true"){
				flag = true;
				$("#change_modal").modal("show");
			}
		}
	});
});

$("#ChangeID").click(function(){
	var tag = false;
	var ChangeFID = $("#ChangeFID").val().trim();
	var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	if(!reg.test(ChangeFID)||ChangeFID == ""||ChangeFID == null){
		tag = false;
		alert("请检查输入格式！")
		return;
	}else{
		$.ajax({
			url:"${APP_PATH}/userServlet?method=checkALID",
			data:"ChangeFID=" + ChangeFID,
			type:"POST",
			success:function(result){
				if(result == "true"){
					$.ajax({
						url:"${APP_PATH}/userServlet?method=updateFID",
						data:{"userID":userId,"ChangeFID":ChangeFID},
						type:"POST",
						success:function(result){
							if(result == "true"){
								alert("修改成功！");
								window.location = "${APP_PATH }/userServlet?method=showLevel";
							}else{
								alert("修改失败，该用户左右分支已满！");
							}
						}
					});
				}else{
					alert("该用户不是本人的下级");
				}
			}
		});
	}
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