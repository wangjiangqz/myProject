<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业人员信息管理</title>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/company/tBzCompanyPersonnel/">企业人员信息列表</a></li>
		<li><a href="${ctx}/company/tBzCompanyPersonnel/form">企业人员信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="TBzCompanyPersonnel" action="${ctx}/company/tBzCompanyPersonnel/" method="post" class="breadcrumb form-search bg-gray-light callout">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业名称：</label>
				<form:input path="companyName" htmlEscape="false" maxlength="255" class="input-medium form-control"/>
			</li>
			<li><label>出口备案编号：</label>
				<form:input path="recodeCode" htmlEscape="false" maxlength="255" class="input-medium form-control"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>企业名称</th>
				<th>企业负责人</th>
				<th>出口备案编号</th>
				<th>联系人姓名</th>
				<th>联系人号码</th>
				<th>更新者</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBzCompanyPersonnel">
			<tr>
				<td><a href="${ctx}/company/tBzCompanyPersonnel/form?id=${tBzCompanyPersonnel.id}">
					${tBzCompanyPersonnel.companyName}
				</a></td>
				<td>
					${fns:getDictLabel(tBzCompanyPersonnel.type, 'personType', '')}
				</td>
				<td>
					${tBzCompanyPersonnel.recodeCode}
				</td>
				<td>
					${tBzCompanyPersonnel.personName}
				</td>
				<td>
					${tBzCompanyPersonnel.phone}
				</td>
				<td>
					${tBzCompanyPersonnel.updateBy.id}
				</td>
				<td>
    				<a href="${ctx}/company/tBzCompanyPersonnel/form?id=${tBzCompanyPersonnel.id}">修改</a>
					<a href="${ctx}/company/tBzCompanyPersonnel/delete?id=${tBzCompanyPersonnel.id}" onclick="return confirmx('确认要删除该企业人员信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>