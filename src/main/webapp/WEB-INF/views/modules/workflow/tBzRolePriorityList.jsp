<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构评分权重定义</title>
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
		
		function resetAll(){
			$("#roleId").val("");
			$("#roleId").prev().find("span:first").html("");
			$("#userId").val("");
			$("#userName").val("");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/workflow/tBzRolePriority/">机构评分权重定义列表</a></li>
		<li><a href="${ctx}/workflow/tBzRolePriority/form">机构评分权重定义添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="TBzRolePriority" action="${ctx}/workflow/tBzRolePriority/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
		        <label>角色：</label>
				<form:select path="roleId" class="input-large required">
                    <form:option value="" label=""/>       
                    <c:forEach items="${allRoles}" var="roleObj">
                    	<form:option value="${roleObj.id}" label="${roleObj.name}"/>  
                    </c:forEach>              
                </form:select> 
			</li>
			<li>
			    <label class="control-label">用户：</label>
				<sys:treeselect id="user" name="user.id" value="${TBzRolePriority.user.id}" labelName="user.name" labelValue="${TBzRolePriority.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary btnReset" type="button" value="重置" onclick="resetAll()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>用户名称</th>
			    <th>角色名称</th>
				<th>修改时间</th>
				<th>权重</th>
				<th>排序</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBzRolePriority">
			<tr>
			    <td>
				    <a href="${ctx}/workflow/tBzRolePriority/form?id=${tBzRolePriority.id}">
				    	${tBzRolePriority.user.name}
				    </a>
			    </td>
			    <td>${tBzRolePriority.role.name}</td>
				<td>
					<fmt:formatDate value="${tBzRolePriority.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>${tBzRolePriority.priority}</td>
				<td>${tBzRolePriority.sort}</td>
				<td>
    				<a href="${ctx}/workflow/tBzRolePriority/form?id=${tBzRolePriority.id}">修改</a>
					<a href="${ctx}/workflow/tBzRolePriority/delete?id=${tBzRolePriority.id}" onclick="return confirmx('确认要删除该角色定义权重管理吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>