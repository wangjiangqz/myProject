<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>修改密码</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/pwd/pwd.js"></script>
	<link rel="Stylesheet" href="${ctxStatic}/pwd/pwd.css" />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#oldPassword").focus();
			$("#inputForm").validate({
				rules: {
				},
				messages: {
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					var isCheckPwd="${isCheckPassWord}";
					if("p01"==isCheckPwd) {
						var value=$("#newPassword").val();
						var fhRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[`~!@#$%^*()=|{}':;',\\\\\\\\.<>\/?~])[A-Za-z\d`~!@#%$^*()=|{}':;',\\\\\\\\.<>\/?~]{8,20}$/gi;

						if(value!="" && fhRegex.test(value)){
							loading('正在提交，请稍等...');
							form.submit();
						}else{
							top.$.jBox.tip("登录密码由8-20位数字+字母+英文特殊字符（`~!@#$^*()=|{}':;',\\.<>/?）组成", "warning");
						}
					}else{
						loading('正在提交，请稍等...');
						form.submit();

					}
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
		});
	</script>
</head>
<body>
<div class="box box-primary" >
	<div class="box-header with-border">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li class="active"><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
	</ul>
	</div>
	<div class="box-body">
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/modifyPwd" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="form-group">
			<label class="control-label">旧密码:</label>
			<div class="controls">
				<input id="oldPassword" name="oldPassword" type="password" value="" maxlength="50" minlength="6" class="required form-control"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-group" >
			<label class="control-label">新密码:</label>
			<div class="controls" style="position: relative">
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="6" class="required form-control "/>
				<span class="help-inline"><font color="red">*</font> </span>
				<c:if test="${isCheckPassWord eq 'p01'}">
					<div class="pwdPower" style="height: 88px">
						<span style="color: red;font-size: 15px;" >  密码强度：<span class="power"></span></span>
						<em id="strength"></em>
						<div id="strengthLevel" class="strengthLv0"></div>
					</div>

					<input type="hidden" value="${ctxStatic}" class="ctxStatic">
					<ul id="TANGRAM__PSP_3__pwdChecklist" class="pwd-checklist" style="position: absolute;top:0px;left: -50px">
						<li id="TANGRAM__PSP_3__pwd_checklist_len" data-rule="len" class="pwd-checklist-item pwd-checklist-item-error"><img class="x1" src="${ctxStatic}/images/x.png">  &nbsp;&nbsp;&nbsp;长度为8~20个字符</li>
						<li id="TANGRAM__PSP_3__pwd_checklist_cha" data-rule="cha" class="pwd-checklist-item pwd-checklist-item-success"><img class="x2"  src="${ctxStatic}/images/x.png">  &nbsp;&nbsp;&nbsp;支持数字,大小写字母和标点符号</li>
						<li id="TANGRAM__PSP_3__pwd_checklist_spa" data-rule="spa" class="pwd-checklist-item pwd-checklist-item-success"><img class="x3" src="${ctxStatic}/images/x.png"> &nbsp;&nbsp;&nbsp;英文特殊字符（`~!@#$^*()=|{}':;',.<>/?）</li>
					</ul>
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">确认新密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="6" class="required form-control" equalTo="#newPassword"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
	</div>
</div>
</body>
</html>