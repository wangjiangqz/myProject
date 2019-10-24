<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>
	<title>修改密码</title>
 	
 	    <link href="${ctxStatic}/pwd/pwd.css" rel="stylesheet" type="text/css" />
	<script src="${ctxStatic}/pwd/pwd.js" type="text/javascript"></script> 
	<meta name="decorator" content="default"/> 
	<script type="text/javascript">
		$(document).ready(function() {
			$("#oldPassword").focus();
            
           
            $("#btnBack").click(function () {
                if(confirm("取消修改密码将强制退出系统，是否取消？")){
              
                    window.parent.location.href="${ctx}/logout";
   
       
                }else{
                    return false;
                }
            });

			$("#inputForm").validate({
				rules: {
				},
				messages: {
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
				/* 	loading('正在提交，请稍等...');
                    parent.$("#myModal").modal('hide');
                    parent.$(".hidden_div").css("display","none");
					form.submit(); */
				var isCheckPwd="${isCheckPassWord}";
					if("p01"==isCheckPwd) {
			 
						var value=$("#password").val();
						debugger;
		 
			   var fhRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[`~!@#$%^*()=|{}':;',\\\\\\\\.<>\/?~])[A-Za-z\d`~!@#%$^*()=|{}':;',\\\\\\\\.<>\/?~]{8,20}$/gi;
			 
						if(value!="" && fhRegex.test(value)){
							loading('正在提交，请稍等...');
							form.submit();
								parent.$("#myModal").modal('hide');
						}else{
							top.$.jBox.tip("登录密码由8-20位数字+字母+英文特殊字符（`~!@#$^*()=|{}':;',\\.<>/?）组成", "warning");
						}
					}else{
						loading('正在提交，请稍等...');
						form.submit();
								parent.$("#myModal").modal('hide');
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

        	//密码强度校验
			/*
			 返回密码的强度级别
			 */
       /*      function checkStrong(sPW) {
                if (sPW.length < 8||sPW.length > 20)
                    return 0; //密码太短或太长
                Modes = 0;
                for (i = 0; i < sPW.length; i++) {
                    //测试每一个字符的类别并统计一共有多少种模式.
                    Modes |= CharMode(sPW.charCodeAt(i));
                }
                return bitTotal(Modes);
            } */
			/*
			 判断字符类型
			 */
            function CharMode(iN) {
                if (iN >= 48 && iN <= 57) //数字
                    return 1;
                if (iN >= 65 && iN <= 90) //大写字母
                    return 2;
                if (iN >= 97 && iN <= 122) //小写
                    return 4;
                else
                    return 8; //特殊字符
            }
			/*
			 统计字符类型
			 */
            function bitTotal(num) {

                modes = 0;
                for (i = 0; i < 4; i++) {
                    if (num & 1) modes++;
                    num >>>= 1;
                }
                return modes;
            }

	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="TBaRegistered" action="${ctx}/base/tBaRegistered/forceModifyPwd" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<input type="hidden">
		<div class="form-group" style="padding-left: 5% ;height: 50px">
			<label class="control-label"><font color="red">*</font>旧密码：</label>
			<div class="controls">
				<input id="oldPassword" name="oldPassword" type="password" value="" maxlength="20" minlength="6" class="required form-control"/>
			</div>
		</div>

		<div class="form-group " style="padding-left: 5% ;height: 100px">
			<label class="control-label"><font color="red">*</font>新密码：</label>
			<div class="controls">
				<input id="password" name="newPassword" type="password" value="" maxlength="20"  class="required newPassword form-control"/>
			
			    <c:if test="${isCheckPassWord eq 'p01'}">
                              <div class="pwdPower" style="height: 47px;">
                            <span style="color: red;font-size: 15px;" >  密码强度：<span class="power"></span></span>
                           <em id="strength"></em>
                            <div id="strengthLevel" class="strengthLv0"></div>
                             </div>
 
                   <input type="hidden" value="${ctxStatic}" class="ctxStatic">
                  <ul id="TANGRAM__PSP_3__pwdChecklist" class="pwd-checklist" style="position: absolute;top:65px;left: 165px">
                  <li id="TANGRAM__PSP_3__pwd_checklist_len" data-rule="len" class="pwd-checklist-item pwd-checklist-item-error"><img class="x1" src="${ctxStatic}/images/x.png">  &nbsp;&nbsp;&nbsp;长度为8~20个字符</li>
                  <li id="TANGRAM__PSP_3__pwd_checklist_cha" data-rule="cha" class="pwd-checklist-item pwd-checklist-item-success"><img class="x2"  src="${ctxStatic}/images/x.png">  &nbsp;&nbsp;&nbsp;支持数字,大小写字母和标点符号</li>
                  <li id="TANGRAM__PSP_3__pwd_checklist_spa" data-rule="spa" class="pwd-checklist-item pwd-checklist-item-success"><img class="x3" src="${ctxStatic}/images/x.png"> &nbsp;&nbsp;&nbsp;英文特殊字符（`~!@#$^*()=|{}':;',.<>/?）</li>
                </ul>
            </c:if>
			
			</div>
			    
			      
			      
			      
		</div>

		<div class="form-group" style="padding-left: 5% ;height: 50px">
			<label class="control-label"><font color="red">*</font>确认新密码：</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="20" minlength="8" class="required newPassword  form-control" equalTo="#password"/>
			</div>
		</div>

		<div class=""  style="padding-left:48%;">
		    <div>
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="btnBack" class="btn" type="button" value="返 回"/>
		    </div>
		</div>
	</form:form>
</body>
</html>