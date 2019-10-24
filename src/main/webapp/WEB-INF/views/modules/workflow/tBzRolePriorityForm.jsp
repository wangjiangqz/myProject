<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构评分权重定义</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
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
			
		});
		
		//根据当前的用户查询该用户下的角色
		function updateRoles(){
			//根据选择的节点找到人员       
			var userId = $("#userId").val();
			if(userId == null || userId == ""){
				top.$.jBox.tip("请先选择用户，再刷新该用户下角色!", "warning");
				return ;
			}
       	    $.ajax({
                url:'${ctx}/sys/user/getRoles',
                async:false, 
                type:"post",
                data:{"userId":userId},
                dataType:"json",
                success:function(data){
             	   var userArray = data.msgArray;
                      $("#roleId").empty();
                      $("#roleId").append("<option value=''></option>");
                      for(var i=0;i<userArray.length;i++){                                
                         $("#roleId").append('<option value="'+userArray[i].id+'">'+userArray[i].name+'</option>');
                      }                    
                      $("#roleId").val("");
                      $("#roleId").prev().find("span:first").html("请选择");
                }   
           });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/workflow/tBzRolePriority/">机构评分权重定义列表</a></li>
		<li class="active">
		   <a href="${ctx}/workflow/tBzRolePriority/form?id=${tBzRolePriority.id}">机构评分权重定义${not empty tBzRolePriority.id?'修改':'添加'}
		   </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tBzRolePriority" action="${ctx}/workflow/tBzRolePriority/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">用户：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${tBzRolePriority.user.id}" labelName="user.name" labelValue="${tBzRolePriority.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">角色：</label>
			<div class="controls">
				<form:select path="roleId" class="input-large required">
                    <form:option value="" label=""/>       
                    <c:forEach items="${allRoles}" var="roleObj">
                    	<form:option value="${roleObj.id}" label="${roleObj.name}"/>  
                    </c:forEach>              
                </form:select> 
                &nbsp;&nbsp;
                <input id="btnSubmit" class="btn btn-primary" type="button" value="刷新角色" onclick="updateRoles()"/>&nbsp;
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">优先级：</label>
			<div class="controls">
				<form:input path="priority" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>