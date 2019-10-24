<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代码管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/code/tBzCode/">代码列表</a></li>
		<li class="active"><a href="${ctx}/code/tBzCode/form?id=${tBzCode.id}">代码<shiro:hasPermission name="code:tBzCode:edit">${not empty tBzCode.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="code:tBzCode:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tBzCode" action="${ctx}/code/tBzCode/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="control-label">column1：</label>
			<div class="controls">
				<form:input path="column1" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column2：</label>
			<div class="controls">
				<form:input path="column2" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column3：</label>
			<div class="controls">
				<form:input path="column3" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column4：</label>
			<div class="controls">
				<form:input path="column4" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column5：</label>
			<div class="controls">
				<form:input path="column5" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column6：</label>
			<div class="controls">
				<form:input path="column6" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column7：</label>
			<div class="controls">
				<form:input path="column7" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column8：</label>
			<div class="controls">
				<form:input path="column8" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column9：</label>
			<div class="controls">
				<form:input path="column9" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column10：</label>
			<div class="controls">
				<form:input path="column10" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column11：</label>
			<div class="controls">
				<form:input path="column11" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column12：</label>
			<div class="controls">
				<form:input path="column12" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column13：</label>
			<div class="controls">
				<form:input path="column13" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column14：</label>
			<div class="controls">
				<form:input path="column14" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column15：</label>
			<div class="controls">
				<form:input path="column15" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column16：</label>
			<div class="controls">
				<form:input path="column16" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column17：</label>
			<div class="controls">
				<form:input path="column17" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column18：</label>
			<div class="controls">
				<form:input path="column18" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column19：</label>
			<div class="controls">
				<form:input path="column19" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column20：</label>
			<div class="controls">
				<form:input path="column20" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column21：</label>
			<div class="controls">
				<form:input path="column21" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column22：</label>
			<div class="controls">
				<form:input path="column22" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column23：</label>
			<div class="controls">
				<form:input path="column23" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column24：</label>
			<div class="controls">
				<form:input path="column24" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column25：</label>
			<div class="controls">
				<form:input path="column25" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column26：</label>
			<div class="controls">
				<form:input path="column26" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column27：</label>
			<div class="controls">
				<form:input path="column27" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column28：</label>
			<div class="controls">
				<form:input path="column28" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column29：</label>
			<div class="controls">
				<form:input path="column29" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">column30：</label>
			<div class="controls">
				<form:input path="column30" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="code:tBzCode:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>