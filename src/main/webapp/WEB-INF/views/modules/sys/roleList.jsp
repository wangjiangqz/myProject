]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div class="box box-primary" >
	<div class="box-header with-border">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/role/">角色列表</a></li>
		<shiro:hasPermission name="sys:role:edit"><li><a href="${ctx}/sys/role/form">角色添加</a></li></shiro:hasPermission>
	</ul>
	</div>
	<div class="box-body">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-striped ui">
		<thead>
		<tr><th>角色名称</th><th>英文名称</th><th>归属机构</th>
			<%--<th>数据范围</th>--%>
			<shiro:hasPermission name="sys:role:edit"><th>操作</th></shiro:hasPermission></tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="role">
			<tr>
				<td><a href="form?id=${role.id}">${role.name}</a></td>
				<td><a href="form?id=${role.id}">${role.enname}</a></td>
				<td>${role.office.name}</td>
				<%--<td>${fns:getDictLabel(role.dataScope, 'sys_data_scope', '无')}</td>--%>
				<shiro:hasPermission name="sys:role:edit"><td>
					<a href="${ctx}/sys/role/assign?id=${role.id}" class="btn  btn-primary btn-xs"><i class="fa fa-plus"></i>分配</a>
					<c:if test="${(role.sysData eq fns:getDictValue('是', 'yes_no', '1') && fns:getUser().admin)||!(role.sysData eq fns:getDictValue('是', 'yes_no', '1'))}">
						<a href="${ctx}/sys/role/form?id=${role.id}" class="btn  btn-success btn-xs"><i class="fa fa-edit"></i>修改</a>
					</c:if>
					<a href="${ctx}/sys/role/delete?id=${role.id}" onclick="return confirmx('确认要删除该角色吗？', this.href)" class="btn   btn-danger btn-xs"><i class="fa fa-close"></i>删除</a>
				</td></shiro:hasPermission>	
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</div>
</body>
</html>