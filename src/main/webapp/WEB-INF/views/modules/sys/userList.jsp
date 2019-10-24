<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});

            // $('#contentTable').DataTable({
            //     'paging'      : false,
            //     'lengthChange': false,
            //     'searching'   : false,
            //     'ordering'    : true,
            //     'info'        : false,
            //     'autoWidth'   : true
            // })

		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/user/import" method="post" enctype="multipart/form-data"
			class="form-search bg-gray-light callout" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/>
			<button id="btnImportSubmit" class="btn btn-primary" type="submit" ><i class="glyphicon glyphicon-import"></i>导    入</button>
			<br/>
			<a href="${ctx}/sys/user/import/template">下载模板</a>
		</form>
	</div>
	<div class="box box-primary" >
		<div class="box-header with-border">
			<ul class="nav nav-tabs">
				<li class="active"><a href="${ctx}/sys/user/list">用户列表</a></li>
				<shiro:hasPermission name="sys:user:edit"><li><a href="${ctx}/sys/user/form">用户添加</a></li></shiro:hasPermission>
			</ul>
		</div>
		<div class="box-body">
		<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="form-search bg-gray-light callout" >

			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<div class="row">
				<div class="col-xs-5">
					<div class="form-group">
						<label >归属机构：</label>
						<sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}"
										title="机构" url="/sys/office/treeData?type=1" cssClass="form-control" allowClear="true" smallBtn="true"/>
					</div>
					<!-- /.form-group -->
					<div class="form-group">
						<label >归属部门：</label>
						<sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
										title="部门" url="/sys/office/treeData?type=2" cssClass="form-control" allowClear="true" notAllowSelectParent="true" smallBtn="true"/>
					</div>
					<!-- /.form-group -->
				</div>
				<div class="col-xs-4">
					<div class="form-group">
						<label style="width: 70px">登录名：</label>
						<form:input path="loginName" htmlEscape="false" maxlength="50" class="form-control"/>
					</div>
					<!-- /.form-group -->
					<div class="form-group">
						<label style="width: 70px">姓名：</label>
						<form:input path="name" htmlEscape="false" maxlength="50" class="form-control"/>
					</div>
					<!-- /.form-group -->
				</div>
				<div class="col-xs-3">
					<div class="form-group">
						<button id="btnSubmit" class="btn btn-primary" type="submit" onclick="return page();"><i class="glyphicon glyphicon-search"></i>查询</button>
						<button id="btnExport" class="btn btn-primary" type="button"><i class="glyphicon glyphicon-export"></i>导出</button>
						<button id="btnImport" class="btn btn-primary" type="button"><i class="glyphicon glyphicon-import"></i>导入</button>
					</div>
					<!-- /.form-group -->
					<div class="form-group">

					</div>
					<!-- /.form-group -->
				</div>
			</div>

		</form:form>
		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-bordered table-striped ui">
			<thead><tr><th>归属公司</th><th>归属部门</th><th class="sort-column login_name">登录名</th><th class="sort-column name">姓名</th><th>电话</th><th>手机</th><%--<th>角色</th> --%><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
			<tbody>
			<c:forEach items="${page.list}" var="user">
				<tr>
					<td>${user.company.name}</td>
					<td>${user.office.name}</td>
					<td><a href="${ctx}/sys/user/form?id=${user.id}">${user.loginName}</a></td>
					<td>${user.name}</td>
					<td>${user.phone}</td>
					<td>${user.mobile}</td><%--
				<td>${user.roleNames}</td> --%>
					<shiro:hasPermission name="sys:user:edit"><td>
						<a href="${ctx}/sys/user/form?id=${user.id}" class="btn  btn-success btn-xs"><i class="fa fa-edit"></i>修改</a>
						<a href="${ctx}/sys/user/delete?id=${user.id}" class="btn  btn-danger btn-xs" onclick="return confirmx('确认要删除该用户吗？', this.href)"><i class="fa fa-close"></i>删除</a>
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