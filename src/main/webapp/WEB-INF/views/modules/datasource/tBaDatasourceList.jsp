<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据源管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
<div class="box box-primary" >
	<div class="box-header with-border">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/datasource/tBaDatasource/">数据源列表</a></li>
		<shiro:hasPermission name="datasource:tBaDatasource:edit"><li><a href="${ctx}/datasource/tBaDatasource/form">数据源添加</a></li></shiro:hasPermission>
	</ul>
	</div>
	<div class="box-body">
	<form:form id="searchForm" modelAttribute="TBaDatasource" action="${ctx}/datasource/tBaDatasource/" method="post" class="breadcrumb form-search bg-gray-light callout">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row">
			<div class="col-xs-3">
				<div class="form-group">
					<label style="font-size: 12px">数据源名称：</label>
					<form:input path="dataName" htmlEscape="false" maxlength="4000" class="input-medium form-control text " />
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label style="font-size: 12px">数据源类型：</label>
					<form:select path="dataType" class="input-medium form-control">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('datasource_datatype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label>结果类型：</label>
					<form:select path="resultType" class="input-medium form-control">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('datasource_resulttype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
				</div>
			</div>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>数据源名称</th>
				<th>数据源类型</th>
				<th>结果类型</th>
				<th>自定义表名</th>
				<th>唯一约束</th>
				<shiro:hasPermission name="datasource:tBaDatasource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBaDatasource">
			<tr>
				<td><a href="${ctx}/datasource/tBaDatasource/detailsForm?id=${tBaDatasource.id}">
					${tBaDatasource.dataName}
				</a></td>
				<td>
					${fns:getDictLabel(tBaDatasource.dataType, 'datasource_datatype', '')}
				</td>
				<td>
					${fns:getDictLabel(tBaDatasource.resultType, 'datasource_resulttype', '')}
				</td>
				<td>
					${tBaDatasource.customTableName}
				</td>
				<td>
					${tBaDatasource.uniqueConstraint}
				</td>
				<shiro:hasPermission name="datasource:tBaDatasource:edit"><td>
    				<a href="${ctx}/datasource/tBaDatasource/detailsForm?id=${tBaDatasource.id}" class="btn  btn-success btn-xs"><i class="fa fa-edit"></i>修改</a>
					<a href="${ctx}/datasource/tBaDatasource/delete?id=${tBaDatasource.id}" onclick="return confirmx('确认要删除该数据源吗？', this.href)" class="btn   btn-danger btn-xs"><i class="fa fa-close"></i>删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</div>
</body>
</html>