<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				rules: {
					type: {remote: "${ctx}/sys/dict/checkType?oldType=" + encodeURIComponent("${dict.type}")}
				},
				messages: {
					type: {remote: "字段项类型已存在"}
				},
				submitHandler: function(form){
                     var index=0;
					$("#dictList").find('tr').each(function () {
						$(this).find("#id").prop('name','dictList['+index+'].id');
						$(this).find("#value").prop('name','dictList['+index+'].value');
						$(this).find("#label").prop('name','dictList['+index+'].label');
						$(this).find("#description").prop('name','dictList['+index+'].description');
						$(this).find("#sort").prop('name','dictList['+index+'].sort');
						index++;
					})
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
            $('input[type="checkbox"].flat-blue, input[type="radio"].flat-blue').iCheck({
                checkboxClass: 'icheckbox_flat-blue',
                radioClass   : 'iradio_flat-blue'
            })
		});

		var delDict=function(del) {
			var tr=$(del).parent().parent();
			if(tr.find("#id").val().length>2){
				$.ajax({
					url:'${ctx}/sys/dict/ajaxDelete',
					async:false,
					type:"post",
					data:{id: tr.find("#id").val()},
					dataType:"json",
					success:function(data){
						if(data == true){
							tr.remove();
						}
					}
				});
			}else {
				tr.remove();
			}


		}
		function addDict() {
			$("#dictList").append("<tr>\n" +
					"\t\t\t\t\t<td>\n" +
					"\t\t\t\t\t\t<input name=\"id\" id=\"id\" value=\"\" type=\"hidden\" htmlEscape=\"false\" maxlength=\"50\" class=\" form-control\"/>\n" +
					"\t\t\t\t\t\t<input name=\"value\" id=\"value\" value=\"\" htmlEscape=\"false\" maxlength=\"50\" class=\"required form-control\"/>\n" +
					"\t\t\t\t\t\t<span class=\"help-inline\"><font color=\"red\">*</font> </span>\n" +
					"\t\t\t\t\t</td>\n" +
					"\t\t\t\t\t<td>\n" +
					"\t\t\t\t\t\t<input name=\"label\" id=\"label\" value=\"\" htmlEscape=\"false\" maxlength=\"50\" class=\"required form-control\"/>\n" +
					"\t\t\t\t\t\t<span class=\"help-inline\"><font color=\"red\">*</font> </span>\n" +
					"\t\t\t\t\t</td>\n" +
					// "\t\t\t\t\t<td>\n" +
					// "\t\t\t\t\t\t<input name=\"description\" id=\"description\" value=\"\" htmlEscape=\"false\" maxlength=\"50\" class=\"required form-control\"/>\n" +
					// "\t\t\t\t\t\t<span class=\"help-inline\"><font color=\"red\">*</font> </span>\n" +
					// "\t\t\t\t\t</td>\n" +
					"\t\t\t\t\t<td>\n" +
					"\t\t\t\t\t\t<input name=\"sort\" id=\"sort\" value=\"\" htmlEscape=\"false\" maxlength=\"50\" class=\"required form-control\"/>\n" +
					"\t\t\t\t\t\t<span class=\"help-inline\"><font color=\"red\">*</font> </span>\n" +
					"\t\t\t\t\t</td>\n" +
					"\t\t\t\t\t<td>\n" +
					"\t\t\t\t\t\t<a href=\"javacript:void(0);\" onclick=\"delDict(this)\" class=\"btn   btn-danger btn-xs\"><i class=\"fa fa-close\"></i>删除</a>\n" +
					"\t\t\t\t\t</td>\n" +
					"\t\t\t\t</tr>");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dict/">字典列表</a></li>
		<li class="active"><a href="${ctx}/sys/dict/form?id=${dict.id}">字典<shiro:hasPermission name="sys:dict:edit">${not empty dict.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dict" action="${ctx}/sys/dict/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<div class="form-group">
			<label class="control-label">字典项类型:</label>
			<div class="controls">
				<input id="oldType" name="oldType" type="hidden" value="${dict.type}">
				<form:input path="type" htmlEscape="false" maxlength="50" class="required abc form-control"/>
				<span class="help-inline"><font color="red">*</font> </span>&nbsp;
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">字典项描述:</label>
			<div class="controls">
				<input name="description" id="description" value="${dictList[0].description}" htmlEscape="false" maxlength="50" class="required form-control"/>
				<span class="help-inline"><font color="red">*</font> </span>&nbsp;
				<input id="add" class="btn btn-success" type="button" onclick="addDict()" value="新增字典项" />
			</div>
		</div>
	<div class="form-group" >
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>
				<th>字典项名称</th>
				<th>字典项含义</th>
				<th>字典项排序</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody id="dictList">
			<c:forEach items="${dictList}" var="dict">
				<tr>
					<td>
						<input name="id" id="id" value="${dict.id}" type="hidden" htmlEscape="false" maxlength="50" class=" form-control"/>
						<input name="value" id="value" value="${dict.value}" htmlEscape="false" maxlength="50" class="required form-control"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td>
						<input name="label" id="label" value="${dict.label}" htmlEscape="false" maxlength="50" class="required form-control"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td>
						<input name="sort" id="sort" value="${dict.sort}" htmlEscape="false" maxlength="50" class="required form-control"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td>
						<a href="javacript:void(0);" onclick="delDict(this)" class="btn   btn-danger btn-xs"><i class="fa fa-close"></i>删除</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>

	</div>
		<%--<div class="form-group">--%>
			<%--<label class="control-label">字典项名称:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="value" htmlEscape="false" maxlength="50" class="required form-control"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="form-group">--%>
			<%--<label class="control-label">字典项含义:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="label" htmlEscape="false" maxlength="50" class="required form-control"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="form-group">--%>
			<%--<label class="control-label">描述:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="description" htmlEscape="false" maxlength="50" class="required form-control"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="form-group">--%>
			<%--<label class="control-label">排序:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="sort" htmlEscape="false" maxlength="11" class="required digits form-control"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="form-group">--%>
			<%--<label class="control-label">备注:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-actions" style="padding-left: 44%">
			<shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>