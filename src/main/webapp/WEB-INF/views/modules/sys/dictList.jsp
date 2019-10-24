<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
		<li class="active"><a href="${ctx}/sys/dict/">字典列表</a></li>
		<shiro:hasPermission name="sys:dict:edit"><li><a href="${ctx}/sys/dict/form?sort=10">字典添加</a></li></shiro:hasPermission>
	</ul>
	</div>
	<div class="box-body">
	<form:form id="searchForm" modelAttribute="dict" action="${ctx}/sys/dict/" method="post" class="form-search bg-gray-light callout">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row">
			<div class="col-xs-3">
				<div class="form-group">
				<label>字典项类型：</label>
				<form:select id="type" path="type" class="input-medium form-control">
					<form:option value="" label=""/>
					<form:options items="${typeList}" htmlEscape="false"/>
				</form:select>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
				<label>描述 ：</label><form:input path="description" htmlEscape="false" maxlength="50" class="input-medium form-control"/>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
				</div>
			</div>
		</div>

	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>字典项类型</th>
			<th>字典项描述</th>
			<th>字典项名称</th>
			<th>字典项含义</th>
			<th>排序</th>
			<shiro:hasPermission name="sys:dict:edit"><th>操作</th></shiro:hasPermission>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dict">
			<tr>
				<td><a href="javascript:" onclick="$('#type').val('${dict.type}');$('#searchForm').submit();return false;">${dict.type}</a></td>
				<td>${dict.description}</td>
				<td>${dict.value}</td>
				<td><a href="${ctx}/sys/dict/form?id=${dict.id}&type=${dict.type}">${dict.label}</a></td>
				<td>${dict.sort}</td>
				<shiro:hasPermission name="sys:dict:edit"><td>
    				<a href="${ctx}/sys/dict/form?id=${dict.id}&type=${dict.type}" class="btn  btn-success btn-xs"><i class="fa fa-edit"></i>修改</a>
					<%--<a href="${ctx}/sys/dict/delete?id=${dict.id}&type=${dict.type}" onclick="return confirmx('确认要删除该字典吗？', this.href)" class="btn   btn-danger btn-xs"><i class="fa fa-close"></i>删除</a>--%>
    				<%--<a href="<c:url value='${fns:getAdminPath()}/sys/dict/form?type=${dict.type}&sort=${dict.sort+10}'><c:param name='description' value='${dict.description}'/></c:url>" class="btn  btn-primary btn-xs"><i class="fa fa-plus"></i>添加键值</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>