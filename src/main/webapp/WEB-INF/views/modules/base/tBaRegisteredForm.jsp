<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/head.jsp" %>
<html style="overflow: hidden">
<head>
    <title>${fns:getConfig('productName')}</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery.messager.js"></script>
    <script type="text/javascript" src="${ctxStatic}/pwd/pwd.js"></script>
    <link rel="Stylesheet" href="${ctxStatic}/pwd/pwd.css" />
    <style type="text/css">
        .dropdown-toggle{
            height: 70px;padding-top: 29px !important;
        }
        .logo:hover{
            background-color:red;
        }
        .dropdown-toggle:hover{
            background-color: red;
        }
        .main-footer {
            /*margin: 8px 0 0 0;*/
            /*padding: 3px 0 0 0;*/
            font-size: 11px;
            text-align: center;
            border-top: 2px solid #0663A2;
        }
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
                rules: {
                    orgCode: {remote: "${ctx}/base/tBaRegistered/checkOrgCode"},
                    name: {remote: "${ctx}/base/tBaRegistered/checkName"},
                    username:{remote: "${ctx}/base/tBaRegistered/checkUserName?flag=0"},
                    validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
                },
                messages: {
                    orgCode: {remote: "组织机构代码已存在！"},
                    name: {remote: "机构名称已存在！"},
                    username:{remote:"机构账号已存在！"},
                    validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
                },
				submitHandler: function(form){
                    var isCheckPwd="${isCheckPassWord}";
                    if("p01"==isCheckPwd) {
                        var value=$("#password").val();
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
			
			$("#btnMenu").hide();// 注册页面隐藏菜单按钮
			
			// 发送验证码的手机号码
			jQuery.validator.addMethod("mobilePhone", 
                function(value, element) {          
                    var flag = true;
                    var reg= /^[1][3578]\d{9}$/; //验证手机号码  
                    if(!reg.test(value)){
                       flag = false;
                    }
                    return flag;
                }, 
                "手机号码格式错误！"
            );
            
            // 验证码
            jQuery.validator.addMethod("checkcode", 
                function(value, element) {          
                    var flag = true;
                    var checkCode = $("#checkcode").val();
                    var phone = $("#phone").val();
                    $.ajax({
                        url:'${ctx}/base/tBaRegistered/checkCode',
                        async:false, 
                        type:"post",
                        data:{"code":checkCode,"phone":phone},
                        dataType:"json",
                        success:function(data){
	                        if(data == 0){
	                            flag = true;
	                        }else if(data == 2){
	                            flag = false;
	                        }else if(data==1){
	                            //未被注册
	                            flag = false;
	                        }
                        }   
                    });
                    return flag;
                }, 
                "验证码错误！"
            );
            
            // 机构账号
            jQuery.validator.addMethod("username", 
                function(value, element) {          
                    var flag = true;
                    var reg = /^[A-Za-z0-9]+$/;
                    if(!reg.test(value)){
                        flag = false;
                    }
                    return flag;
                }, 
                "用户名只能是数字或者英文！"
            );
            
            // 密码
            // jQuery.validator.addMethod("password",
            //     function(value, element) {
            //         var flag = true;
            //         var reg = /^[A-Za-z0-9]+$/;
            //         var password = $("#password").val();
            //         var crimpassword = $("#pwdAgain").val();
            //         if(password != crimpassword){
            //             $("#pwdAgain").val("");
            //         }
            //         if(!reg.test(value)){
            //             flag = false;
            //         }
            //         return flag;
            //     },
            //     "密码只能是数字或者英文！"
            // );
            
            // 确认密码
            jQuery.validator.addMethod("pwdAgain", 
                function(value, element) {          
                    var flag = true;
                    var pwd = $("#password").val();
                    if("" != pwd && value != pwd){
                        flag = false;
                    }
                    return flag;
                }, 
                "两次密码不一致！"
            );


            // jQuery.validator.addMethod("newPassword",function(value, element) {
            //     var numRegex = /.*\d+.*/gi;
            //     var zmRegex = /.*[a-zA-Z]+.*/gi;
            //     var fhRegex = /.*[`~!@#$^*()=|{}':;',\\\\\\\\.<>\/?~]+.*/gi;
            //     if(value!="" && numRegex.test(value) && zmRegex.test(value) && fhRegex.test(value)){
            //         return true;
            //     }
            //     return false;
            // },"登录密码由8-20位数字+字母+英文特殊字符（`~!@#$^*()=|{}':;',\.<>/?）组成");
		});
		
		// 发送短信验证码
        var InterValObj;    // timer变量，控制时间  
        var count = 120;    // 间隔函数，1秒执行  
        var curCount;       // 当前剩余秒数  
        var code = "";      // 验证码  
        var codeLength = 6; // 验证码长度
        
        //timer处理函数  
        function SetRemainTime(){  
            if(curCount == 0){                  
                window.clearInterval(InterValObj);//停止计时器  
                $("#sendSmsBtn").removeAttr("disabled");//启用按钮  
                $("#sendSmsBtn").val("重新发送验证码");  
                code="";//清除验证码。如果不清除，过时间后，输入收到的验证码依然有效  
            }else{  
                curCount--;  
                $("#sendSmsBtn").val("请在" + curCount + "秒内输入验证码");  
            }  
        }
        // 获取验证码处理
        function getCode(){
            debugger
            var isCheckPwd="${isCheckPassWord}";
            if("p01"==isCheckPwd) {
                var value=$("#password").val();
                var fhRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[`~!@#$%^*()=|{}':;',\\\\\\\\.<>\/?~])[A-Za-z\d`~!@#%$^*()=|{}':;',\\\\\\\\.<>\/?~]{8,20}$/gi;

                if(value!="" && fhRegex.test(value)){
                    getCode1();
                }else{
                    top.$.jBox.tip("登录密码由8-20位数字+字母+英文特殊字符（`~!@#$^*()=|{}':;',\\.<>/?）组成", "warning");
                }
            }else{
                getCode1();

            }
        }
        
		// 获取验证码处理
		function getCode1() {

            //判断有没有填写图形验证码
            var validateCode = $("#validateCode").val();
            if(validateCode==null||validateCode==""||validateCode==undefined){
                top.$.jBox.tip("请填写图片验证码！", "warning");
                return false;
            }
            var subFlag =false;
            $.ajax({
                url:'${ctx}/base/tBaRegistered/checkImgCode',
                async:false,
                type:"post",
                data:{"validateCode":validateCode},
                dataType:"json",
                success:function(data){
                    subFlag=data;
                }
            });
            var codeClass = $("#checkcode").attr("class");
            $("#checkcode").attr("class", "");
            $("#checkcode").attr("class", codeClass);
            if(!subFlag){
                return false;
            }
		    curCount = count;
		    var mobilePhone = $("#phone").val();
		    var reg= /^[1][3578]\d{9}$/; //验证手机号码
		    if("" == mobilePhone) {
                  // 触发提醒输入提示信息
                  $("#phone").val("checkPhoneNotNull");
                  $("#phone").focus();
                  $("#phone").blur();
                  $("#phone").val("");
            }
            if(!reg.test(mobilePhone)) {
               $("#phone").focus();
               $("#phone").blur();
               return false;
            }
            for( var i = 0; i < codeLength; i++){
                code += parseInt(Math.random() * 9).toString();
            }
            //设置button效果，开始计时
            $("#sendSmsBtn").attr("disabled", "true");
            $("#sendSmsBtn").val("请在" + curCount + "秒内输入验证码");
            InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次
            //向后台发送处理数据
            $.ajax({
                type:"POST",                              //用POST方式传输
                dataType:"text",                          //数据格式:JSON
                url:"${ctx}/base/tBaRegistered/sendCode", //目标地址
                data:"phone="+ mobilePhone + "&code=" + code,
                error:function(XMLHttpRequest, textStatus, errorThrown) {

                },
                success: function (data){
                    data = parseInt(data, 10);
                    if(data == 1){
                        //alert("成功");
                    }else if(data == 0){
                        //alert("失败");
                    }
                }
            });
		}
		
	</script>
</head>
<body class="hold-transition skin-blue-light sidebar-mini" style="overflow: hidden">
<div class="wrapper" style="overflow: hidden;">

    <header class="main-header" style="background-image: url('${ctxStatic}/images/lgoin20170906/titletop.png?v=${fns:getParaValue("theme","02" ,"" )}');background-size: 100% 100%;">
        <!-- Logo -->
        <a href="${ctx}" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <%--<span class="logo-mini"><b>${fns:getConfig('productName')} </b></span>--%>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><img style="width: 192px;" src="${ctxStatic}/images/lgoin20170906/logo.png?v=${fns:getParaValue("theme","02" ,"" )}"><b>&nbsp;</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
        </nav>
    </header>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                <b>用户注册</b>
                <small>用户注册</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${ctx}"><i class="fa fa-dashboard"></i> 用户注册</a></li>
                <%--<li class="active">Dashboard</li>--%>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box box-primary" >
                <div class="box-header with-border">
                    申请机构账户注册
                </div>
                <div class="box-body">
            <form:form id="inputForm" modelAttribute="TBaRegistered" action="${ctx}/base/tBaRegistered/save"
                       method="post" class="form-horizontal">
                <form:hidden path="id"/>
                <sys:message content="${message}"/>

                <div class="form-group">
                    <label class="control-label">机构名称：</label>
                    <div class="controls">
                        <form:input path="name" htmlEscape="false" maxlength="200" class="form-control required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label">组织机构代码：</label>
                    <div class="controls">
                        <form:input path="orgCode" htmlEscape="false" maxlength="50" class="form-control required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label">机构账号：</label>
                    <div class="controls">
                        <input id="username" name="username" type="text" htmlEscape="false" maxlength="200"
                               class="form-control required username"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label">密码：</label>
                    <div class="controls" style="position: relative">
                        <input id="password" name="password"  type="password" placeholder="请输入8~20位字母+数字+特殊符号" htmlEscape="false"
                               minlength="8" maxlength="20" class="form-control input-xlarge required newPassword"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                        <%--<script>--%>
                            <%--alert('${isCheckPassWord}')--%>
                        <%--</script>--%>

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
                    <label class="control-label">确认密码：</label>
                    <div class="controls">
                        <input  id="pwdAgain" name="pwdAgain" type="password" minlength="8" htmlEscape="false"
                                class="form-control input-xlarge required " maxlength="20" equalTo="#password"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label">短信校验号码：</label>
                    <div class="controls">
                        <form:input path="phone" htmlEscape="false" maxlength="11" class="form-control required mobilePhone"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label">图片验证码：</label>
                    <div class="controls" style="width: 365px;">
                        <sys:validateCode name="validateCode"
                                          inputCssStyle="margin-bottom:0px;width:100px"
                                          buttonCssStyle="line-height:30px;color: rgb(46, 130, 255);"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label">短信验证：</label>
                    <div class="controls">
                        <input id="checkcode" name="checkcode" class="form-control required checkcode" type="text" htmlEscape="false"
                               maxlength="6"  >
                        <span name="tip" id="codeTip" style="color: red">*</span>
                        <input class="btn btn-primary" type="button" id="sendSmsBtn"
                               value="点击获取验证码" onclick="getCode()">
                    </div>
                </div>
                <div class="form-actions">
                    <input id="btnSubmit" class="btn btn-primary" type="submit" value="注 册"/>
                    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>

                    <%--<a href="${ctx}/base/userlogin/qqlogin"><img alt="" src="${ctxStatic}/images/qq.png"></a>--%>
                    <%--<span id="qq_login_btn"--%>
                          <%--_origtext="get_user_info 加载昵称中..."--%>
                          <%--title="nickname">--%>
			    <%--<a href="javascript:;"--%>
                   <%--onclick="return window.open('https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101205389&amp;response_type=token&amp;scope=all&amp;redirect_uri=http%3A%2F%2Ftj.jaiq.org%2Fh%2Fintro%2Flogin%2FredirectURI',  'oauth2Login_10591' ,'height=525,width=585, toolbar=no, menubar=no, scrollbars=no, status=no, location=yes, resizable=yes');">--%>
			        <%--<img src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_7.png" alt="QQ登录" border="0"></a>--%>
			        <%--</span>--%>
                </div>
            </form:form>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer" style="height: 20px;">

        Copyright © 2019-2019 基础快速开发平台 - Powered By <a href="#" target="_blank">版权</a> V1.0

    </footer>
</div>
<!-- ./wrapper -->
<script type="text/javascript">
    var headHeight = 70; // 抬头的高度
    var htmlObj = $("html"), mainObj = $(".content-wrapper");
    var headerObj = $(".content-header"), footerObj = $(".main-footer");
    var frameObj = $(".wrapper");
    function wSize(){
        var minHeight = 500, minWidth = 1366;
        var strs = getWindowSize().toString().split(",");
        $(".content-wrapper").height(strs[0]-80-footerObj.height());
        $(".content-wrapper").css({"overflow-y":"auto"});
        $(".content-wrapper").css({"min-height":"0px"});
        $(".main-sidebar").height(strs[0]-80-footerObj.height());
        $(".main-sidebar").css({"overflow-y":"auto"});
        $(".wrapper").height(strs[0]);
        $(".content-wrapper").width(strs[1]-$(".main-sidebar").width()-1);
        mainObj.css("width",strs[1] < minWidth ? minWidth-$(".main-sidebar").width()-1: "auto");
        $("#mainFrame").attr("height",mainObj.height()-headerObj.height()-45);
        htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden","min-width":minWidth});
        //htmlObj.css({"min-width":minWidth});
        //$("#mainFrame").height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));

        //$("#mainFrame").css({"min-width":minWidth-$(".main-sidebar").width()-1});
    }
    var getWindowSize = function () {
        return ["Height", "Width"].map(function (a) {
            return window["inner" + a] || document.compatMode === "CSS1Compat" && document.documentElement["client" + a] || document.body["client" + a]
        })
    };
    $(window).resize(function () {
        wSize()
    });
    $(document).ready(function() {
        wSize();
    });


    var mySkins = [
        'skin-blue-light',
        'skin-blue',
        'skin-green-light',
        'skin-black',
        'skin-red',
        'skin-yellow',
        'skin-purple',
        'skin-green',
        'skin-blue-light',
        'skin-black-light',
        'skin-red-light',
        'skin-yellow-light',
        'skin-purple-light',
        'skin-green-light'
    ]

    /**
     * Get a prestored setting
     *
     * @param String name Name of of the setting
     * @returns String The value of the setting | null
     */
    function get(name) {
        if (typeof (Storage) !== 'undefined') {
            return localStorage.getItem(name)
        } else {
            window.alert('请检查您的浏览器!')
        }
    }

    /**
     * Replaces the old skin with the new skin
     * @param String cls the new skin class
     * @returns Boolean false to prevent link's default action
     */
    function changeSkin(cls) {
        $.each(mySkins, function (i) {
            $('body').removeClass(mySkins[i])
        })

        $('body').addClass(mySkins[cls])
    }

    // $.each(mySkins, function (i) {
    //     if(get('skin')==mySkins[i]){
    //         changeSkin(get('skin'))
    //     }
    // })
    changeSkin('${fns:getParaValue("theme","02" ,"" )}')

</script>
<%--<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>--%>


</body>

</html>