<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据源管理</title>
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
<div class="box box-primary" >
	<div class="box-header with-border">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/datasource/tBaDatasource/">数据源列表</a></li>
		<li class="active"><a href="${ctx}/datasource/tBaDatasource/form?id=${tBaDatasource.id}">数据源<shiro:hasPermission name="datasource:tBaDatasource:edit">${not empty tBaDatasource.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="datasource:tBaDatasource:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	</div>
	<div class="box-body">
	<form:form id="inputForm" modelAttribute="tBaDatasource" action="${ctx}/datasource/tBaDatasource/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		

		<div class="form-group">
			<label class="control-label">数据源名称：</label>
			<div class="controls">
				<form:input path="dataName" htmlEscape="false" maxlength="4000" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">数据源类型：</label>
			<div class="controls">
				<form:select path="dataType" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('datasource_datatype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">结果类型：</label>
			<div class="controls">
				<form:select path="resultType" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('datasource_resulttype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label"><font color="red">*</font>数据源标识（可以用sql,也可以是表名）：</label>
			<div class="controls">
				<form:textarea path="dataSourceIdentifier" htmlEscape="false" rows="4" maxlength="4000"  class="input-xxlarge required form-control"/><font color="red">*</font>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label"><font color="red">*</font>自定义表名（多个表名用,分隔开来）：</label>
			<div class="controls">
				<form:textarea path="customTableName" htmlEscape="false" rows="4" maxlength="4000" class="input-xxlarge required form-control"/><font color="red">*</font>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">唯一约束：</label>
			<div class="controls">
				<form:textarea path="uniqueConstraint" htmlEscape="false" rows="4" maxlength="4000" class="input-xxlarge form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">删除约束：</label>
			<div class="controls">
				<form:textarea path="deletingConstraint" htmlEscape="false" rows="4" maxlength="4000" class="input-xxlarge form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge form-control"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="datasource:tBaDatasource:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	</div>
</div>
</body>
</html>