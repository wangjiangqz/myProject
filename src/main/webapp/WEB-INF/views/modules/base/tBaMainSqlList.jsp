<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>动态查询管理</title>
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
		<li class="active"><a href="${ctx}/base/tBaMainSql/">动态查询列表</a></li>
		<li><a href="${ctx}/base/tBaMainSql/mainSqlform">动态查询添加</a></li>
	</ul>
	</div>
	<div class="box-body">
	<form:form id="searchForm" modelAttribute="TBaMainSql" action="${ctx}/base/tBaMainSql/" method="post" class="breadcrumb form-search bg-gray-light callout">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row">
			<div class="col-xs-3">
				<div class="form-group">
					<label>标题查询：</label>
					<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium form-control"/>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
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
				<th>标题</th>
				<th>修改时间</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBaMainSql">
			<tr>
				<td>
					<a href="${ctx}/base/tBaMainSql/form?id=${tBaMainSql.id}">
						${tBaMainSql.title}
					</a>
				</td>
				<td>
					<fmt:formatDate value="${tBaMainSql.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tBaMainSql.remarks}
				</td>
				<td>
    				<a href="${ctx}/base/tBaMainSql/mainSqlform?id=${tBaMainSql.id}" class="btn  btn-success btn-xs"><i class="fa fa-edit"></i>修改</a>
					<a href="${ctx}/base/tBaMainSql/delete?id=${tBaMainSql.id}" onclick="return confirmx('确认要删除该动态查询吗？', this.href)" class="btn   btn-danger btn-xs"><i class="fa fa-close"></i>删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</div>
</body>
</html>