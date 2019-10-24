<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代码管理</title>
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

        function changeFlag() {
		    $("#errorMess").text('');
		}
	</script>
</head>
<body>
<div class="box box-primary" >
	<div class="box-header with-border">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/code/tBzCode/">代码列表</a></li>
		<shiro:hasPermission name="code:tBzCode:edit"><li><a href="${ctx}/code/tBzCode/form">代码添加</a></li></shiro:hasPermission>
	</ul>
	</div>
	<div class="box-body">
	<form:form id="searchForm" modelAttribute="tBzCode" action="${ctx}/code/tBzCode/" method="post" class="breadcrumb form-search bg-gray-light callout">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row">
			<div class="col-xs-3">
				<div class="form-group">
					<li style="height: 100%;"><label>查询sql：</label><textarea name="sql" class="form-control required" rows="8" maxlength="600" style="width: 400px;height: 180px;" oninput="changeFlag()">${tBzCode.sql}</textarea></li>
				</div>
				<div class="form-group">
					<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
				</div>
				<div class="form-group">
					<li class="clearfix"></li>
					<span style="color: red;margin: 10px;">例:select a,b from table</span>
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
			<div class="col-xs-3">
				<div class="form-group">
				</div>
			</div>
		</div>

	</form:form>
	<%--<sys:message content="${message}"/>--%>
	<span id="errorMess" style="color: red;margin-left: 100px">${message}</span>
	<c:if test="${page ne null}">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<c:forEach items="${fields}" var="field">
						<td>
							${field}
						</td>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="map">
				<tr>
					<c:forEach items="${map.tBzCode.selectedFields}" var="field">
						<td>
							${field}
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</c:if>
	</div>
</div>

</body>
</html>