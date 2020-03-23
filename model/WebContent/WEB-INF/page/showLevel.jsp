<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>�ȼ���ϵ��</title>
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
			            <th style="text-align: center;">����</th>
			            <th style="text-align: center;">���֤</th>
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
			            <th style="text-align: center;"  class="text-info">A������</th>
			            <th style="text-align: center;"  class="text-info">A����ҵ��</th>
			            <th style="text-align: center;"  class="text-info">����</th>
			            <th style="text-align: center;"  class="text-info">���֤</th>
			            <th style="text-align: center;"  class="text-info">����</th>
			            <th style="text-align: center;"  class="text-info">���֤</th>
			            <th style="text-align: center;"  class="text-info">B������</th>
			            <th style="text-align: center;"  class="text-info">B����ҵ��</th>
			        </tr>
			
			        <tr align="center">
			        
				        <td>
							${lNumber==null?"��":lNumber } 
						</td>
						<td>
							${lAccount==null?"��":lAccount } 
						</td>
				        <td>
							${nName1==null?"��":nName1 } 
						</td>
				        <td id="lID">
				        	${nextID1==null?"��":nextID1 } 
				        </td>
				        <td>
							${nName2==null?"��":nName2 } 
						</td>
				        <td id="rID">
				        	${nextID2==null?"��":nextID2 } 
				        </td>
				        <td>
							${rNumber==null?"��":rNumber } 
						</td>
				        <td>
							${rAccount==null?"��":rAccount } 
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
			            <th style="text-align: center;" class="text-danger">����</th>
			            <th style="text-align: center;" class="text-danger">���֤</th>
			            <th style="text-align: center;" class="text-danger">����</th>
			            <th style="text-align: center;" class="text-danger">���֤</th>
			            <th style="text-align: center;" class="text-warning">����</th>
			            <th style="text-align: center;" class="text-warning">���֤</th>
			            <th style="text-align: center;" class="text-warning">����</th>
			            <th style="text-align: center;" class="text-warning">���֤</th>
			        </tr>
			        <tr align="center">
				        <td>
							${nnName1==null?"��":nnName1 } 
						</td>
				        <td>
				        	${nnextID1==null?"��":nnextID1 } 
				        </td>
				        <td>
							${nnName2==null?"��":nnName2 } 
						</td>
				        <td>
				        	${nnextID2==null?"��":nnextID2 } 
				        </td>
				        <td>
							${nnName3==null?"��":nnName3 } 
						</td>
				        <td>
				        	${nnextID3==null?"��":nnextID3 } 
				        </td>
				        <td>
							${nnName4==null?"��":nnName4 } 
						</td>
				        <td>
				        	${nnextID4==null?"��":nnextID4 } 
				        </td>
			        </tr>
		    	</table>
		    </div>
	    </div>
	  </div>
	  <div class="row">
		<c:if test="${empty requestScope.nextIDAlls }">
		<p class="lead text-danger">�����¼���Ϣ</p>
		</c:if>
		<c:if test="${!empty requestScope.nextIDAlls }">
			<p class="lead text-success">�¼���Ϣ���£�</p>
			<div class="table-responsive">
				<table id="main-grid-print" data-toggle="main-grid-print" cellpadding="2" class="table table-striped table-bordered table-hover">
					<tr align="center">
						<th style="text-align: center;">�û�����</th>
						<th style="text-align: center;">���֤</th>
						<th style="text-align: center;">����</th>
					</tr>
					<c:forEach items="${nextIDAlls }" var="nextIDAll">
						<tr align="center">
							<td>${nextIDAll.uName }</td>
							<td class="uID">${nextIDAll.uID }</td>
							<td>
								<button class="updateOrder" class="btn btn-info updateStatus">�滻</button>
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
						<label for="" class="col-sm-2 col-sm-2 control-label">���֤:</label>
						<div class="col-sm-10">
							<input class="form-control" name="ChangeFID" id="ChangeFID" placeholder="���֤" type="text">
						</div>
					</div>
					<br/>
					<br/>
					<div class="modal-footer">
						<div style="text-align: center;">
						<input type="submit" id="ChangeID" class="btn btn-default btn-success" value="ȷ��">
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
		alert("���������ʽ��")
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
								alert("�޸ĳɹ���");
								window.location = "${APP_PATH }/userServlet?method=showLevel";
							}else{
								alert("�޸�ʧ�ܣ����û����ҷ�֧������");
							}
						}
					});
				}else{
					alert("���û����Ǳ��˵��¼�");
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