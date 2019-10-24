<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参数管理</title>
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
		<li class="active">
		    <a href="${ctx}/base/tBaParameter/">参数列表</a>
		</li>
		<shiro:hasPermission name="base:tBaParameter:edit">
		    <li>
		         <a href="${ctx}/base/tBaParameter/form">参数添加</a>
		    </li>
		</shiro:hasPermission>
	</ul>
	</div>
	<div class="box-body">
	<form:form id="searchForm" modelAttribute="TBaParameter" action="${ctx}/base/tBaParameter/" 
	    method="post" class="breadcrumb form-search bg-gray-light callout">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row">
			<div class="col-xs-3">
				<div class="form-group">
					<label>参数名称：</label>
					<form:input path="paraName" htmlEscape="false" maxlength="600" class="input-medium form-control"/>
				</div>
			</div>
			<%--<div class="col-xs-3">--%>
				<%--<div class="form-group">--%>
					<%--<label>模块名称：</label>--%>
					<%--<form:select path="tempId" class="input-medium form-control">--%>
						<%--<form:option value="" label=""/>--%>
						<%--<form:options items="${fns:getDictList('temp_name')}" itemLabel="label"--%>
									  <%--itemValue="id" htmlEscape="false"/>--%>
					<%--</form:select>--%>
				<%--</div>--%>
			<%--</div>--%>
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
				<th>参数名称</th>
				<th>参数键</th>
				<th>参数值</th>
				<th>使用状态</th>
				<th>参数类型</th>
				<th>修改时间</th>   
				<th>顺序</th>
				<th>备注</th>
				<shiro:hasPermission name="base:tBaParameter:edit">
				    <th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBaParameter">
			<tr>
				<td>
				    <a href="${ctx}/base/tBaParameter/form?id=${tBaParameter.id}">
					    ${tBaParameter.paraName}
				    </a>
				</td>
				<td>
					${tBaParameter.paraKey}
				</td>
				<td>
					${tBaParameter.paraValue}
				</td>
				<td>
					<c:if test="${tBaParameter.useState eq '0' }">启用</c:if>
                    <c:if test="${tBaParameter.useState eq '1' }">停用</c:if>
				</td>
				<td>
						${fns:getDictLabel(tBaParameter.paraType,'para_type','')}

				</td>
				<td>
					<fmt:formatDate value="${tBaParameter.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<td>
					${tBaParameter.sort}
				</td>
				<td>
					${tBaParameter.remarks}
				</td>
				<shiro:hasPermission name="base:tBaParameter:edit"><td>
    				<a href="${ctx}/base/tBaParameter/form?id=${tBaParameter.id}" class="btn  btn-success btn-xs"><i class="fa fa-edit"></i>修改</a>
					<a href="${ctx}/base/tBaParameter/delete?id=${tBaParameter.id}" 
					    onclick="return confirmx('确认要删除该参数吗？', this.href)" class="btn  btn-danger btn-xs" ><i class="fa fa-close"></i>删除
					</a>
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