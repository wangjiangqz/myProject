<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业人员信息管理</title>
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
		<li><a href="${ctx}/company/tBzCompanyPersonnel/">企业人员信息列表</a></li>
		<li class="active"><a href="${ctx}/company/tBzCompanyPersonnel/form?id=${tBzCompanyPersonnel.id}">企业人员信息<shiro:hasPermission name="company:tBzCompanyPersonnel:edit">${not empty tBzCompanyPersonnel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="company:tBzCompanyPersonnel:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tBzCompanyPersonnel" action="${ctx}/company/tBzCompanyPersonnel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="control-label">企业名称：</label>
			<div class="controls">
				<form:input path="companyName" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">企业负责人：</label>
			<div class="controls">
				<form:select path="type" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('personType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">出口备案编号：</label>
			<div class="controls">
				<form:input path="recodeCode" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">联系人姓名：</label>
			<div class="controls">
				<form:input path="personName" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">联系人号码：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>