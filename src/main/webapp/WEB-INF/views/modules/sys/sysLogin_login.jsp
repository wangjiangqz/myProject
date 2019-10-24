<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/head.jsp" %>
<html>
    <head>
        <title>${fns:getConfig('productName')}</title>
		<link href="${ctxStatic}/data/styles.css" type="text/css"
			rel="stylesheet" />
		<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
        <script src="${ctxStatic}/browserVerification/browser.js" type="text/javascript"></script>
		<%--<link href="${ctxStatic}/browserVerification/tips.css" type="text/css"--%>
			  <%--rel="stylesheet" />--%>
		<%--<script src="${ctxStatic}/browserVerification/tips.js" type="text/javascript"></script>--%>
        <!-- iCheck -->
        <link rel="stylesheet" href="${ctxStatic}/AdminLTE-2.4.5/plugins/iCheck/square/blue.css">
        <script type="text/javascript">
            $(document).ready(function() {

            	//获取cookie的值
                var username = $.cookie('username');
                var password = $.cookie('password');
                //将获取的值填充入输入框中
                $('#username').val(username);
                $('#password').val(password);
                if(username != null && username != '' && password != null && password != ''){//选中保存秘密的复选框
                    $("#ck").attr('checked',true);
                }
	            $("#loginForm").validate({
	                submitHandler: function(form){
	                    var uName =$('#username').val();
	                    var psw = $('#password').val();
	                    if(document.getElementById("ck").checked)
	                    {//保存密码
	                        $.cookie('username',uName, {expires:7,path:'/'});
	                        $.cookie('password',psw, {expires:7,path:'/'});
	                    }else{//删除cookie
	                        $.cookie('username', '', { expires: -1, path: '/' });
	                        $.cookie('password', '', { expires: -1, path: '/' });
	                    }
	                    form.submit();
	                },
	                rules: {
	                    validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
	                },
	                messages: {
	                    username: {required: "请填写用户名."},password: {required: "请填写密码."},
	                    validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
	                },
                    showErrors: function(errorMap, errorList) {

                          //console.log(errorMap.validateCode)
                        if(typeof errorMap.validateCode != "undefined" && errorMap.validateCode != null && errorMap.validateCode != ""&&errorMap.validateCode.length>2){
                            $("#messageBox").show();
                            $("#loginError").html("<span class=\"glyphicon glyphicon-remove-circle\"></span>"+errorMap.validateCode)
						}


						  //error.insertAfter("#loginError")
					}
	            });
	        });
	        // 如果在框架或在对话框中，则弹出提示并跳转到首页
	        if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
	            alert('未登录或登录超时。请重新登录，谢谢！');
	            top.location = "${ctx}";
	        }
            $(function () {
                // $('input').iCheck({
                //     checkboxClass: 'icheckbox_square-blue',
                //     radioClass: 'iradio_square-blue',
                //     increaseArea: '20%' /* optional */
                // });
                $('input[type="checkbox"].flat-blue, input[type="radio"].flat-blue').iCheck({
                    checkboxClass: 'icheckbox_flat-blue',
                    radioClass   : 'iradio_flat-blue'
                })
				$(".topC").click(function () {
					$("#messageBox").hide();
                })
				
				<%--var mes='${message}';--%>
                <%--if(typeof mes == "undefined" || mes == null || mes == "")--%>
            });

        </script>

        <style type="text/css">
            html{
				overflow:auto;
			}
			.icheckbox_flat-blue{
				margin: 0;
			}
			.topC{
				height: 33px;
				position: absolute;top: 0;left: 0;width: 100%;
				background: inherit;
				-webkit-filter: blur(5px);
				-moz-filter: blur(5px);
				-ms-filter: blur(5px);
				-o-filter: blur(5px);
				filter: blur(5px);
				filter: progid:DXImageTransform.Microsoft.Blur(PixelRadius=4, MakeShadow=false);
				background-color: black;
				filter:alpha(opacity=30);
				-moz-opacity:0.3;
				-khtml-opacity: 0.3;
				opacity: 0.3;
			}
			.toptop{
				text-align: center;position: absolute;top: 0;left: 0;width: 100%;
				background-repeat: no-repeat; background-attachment: fixed;
				overflow: hidden;
			}
        </style>
    </head>
    
    <body class="hold-transition skin-blue sidebar-mini" >
	   <div class="wrapper" style="overflow: auto">
        <div id="messageBox" class="toptop" style="${empty message?'display:none':'' }">
            <button data-dismiss="alert" class="close">×</button>
			<label id="loginError" style="color: #ea5200;margin-top: 5px" ><span class="glyphicon glyphicon-remove-circle"></span>${message}</label>
			<div class="topC"></div>
        </div>
        <%--<div style="background-color: #CCFFCC;text-align: center; " class="alert alert-right ${empty suc ? 'hide' : ''}">--%>
            <%--<button data-dismiss="alert" class="close">×</button>--%>
            <%--<label style="color: #33CC33">${suc}</label>--%>
        <%--</div>--%>

        <!-- 页面头部 -->
        <div style="width: 100%; height: 15%; background-color: #E8E7E4; min-width: 1366px; min-height: 115px;">

        </div>

        <!-- 页面中部 -->
        <div id="midDiv" style="width: 100%; height: 70%;  min-width: 1366px; min-height: 500px; background-image: url(${ctxStatic}/images/lgoin20170906/backgroundimage.jpg);background-repeat: no-repeat;background-size:100% 100%;" >


            	<div id="loginDiv" style="float: right;width: 480px; height: 410px; background-image: url(${ctxStatic}/images/lgoin20170906/inputbox.png);background-repeat: no-repeat;background-size:100% 100%;">

            		<div style="width: 15%; height: 100%; float: left;"></div>

            		<div style="width: 70%; height: 100%; float: left; ">
            			<div style="width: 100%; height: 13%; float: left;"></div>

	            		<div style="width: 100%; height: 70%; float: left;">

	            			<form id="loginForm"  action="${ctx}/login"   method="post">
								<div class="form-group has-feedback" style="margin-top: 2%">
									<span style="font-size: 2rem;font-weight: bold;margin-bottom: 5%">用户登录</span><span>（请使用IE10+,火狐浏览器）</span>
								</div>
								<div class="form-group has-feedback" style="margin-top: 5%" >
									<input type="text" class="form-control required" placeholder="用户名" id="username" name="username">
									<span class="glyphicon glyphicon-user form-control-feedback" ></span>
								</div>
								<div class="form-group has-feedback">
									<input type="password" class="form-control required" placeholder="密码" id="password"  name="password">
									<span class="glyphicon glyphicon-lock form-control-feedback"></span>
								</div>
								<div class="form-group has-feedback" style="margin-bottom: 0px">
									<sys:validateCode name="validateCode"
													  inputCssStyle="margin-bottom:0px;width:185px"
													  buttonCssStyle="line-height:30px;color: rgb(46, 130, 255);"/>
								</div>
								<div class="row">
									<div class="col-xs-2" style="padding-right: 0;width: 10.66666667%; " >
										<div class="checkbox icheck">
											<label >
												<input type="checkbox" id="ck" class="flat-blue">
												<%--<div style="display: inline-block;">记住密码（公共场所慎用）</div>--%>

											</label>
										</div>
									</div>
									<div class="col-xs-7" style="padding-left: 0">

											<label style="line-height: 3;font-weight: normal">

												记住密码（公共场所慎用）

											</label>

									</div>
									<!-- /.col -->
									<%--<div class="col-xs-4">--%>
										<%--<button type="submit" style="min-height: 40px" class="btn btn-primary btn-block btn-flat">登录</button>--%>

									<%--</div>--%>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<button type="submit" style="min-height: 40px;background:#3f89ec" class="btn btn-primary btn-block ">登录</button>

									</div>
								</div>
									<div class="row" style="margin-top: 2px">
										<div class="col-xs-6">
											<a href="${pageContext.request.contextPath}/base/tBaRegistered/forgetPwd" style="color: rgb(46, 130, 255);">忘记密码？</a>

										</div>
									<div class="col-xs-6">

									<a href="${pageContext.request.contextPath}/base/tBaRegistered/form" style="float: right;color: rgb(46, 130, 255);">立即注册</a>
									</div>
									<!-- /.col -->
								</div>
	            			</form>
	            		</div>

	            		<div style="width: 100%; height: 17%; float: left;"></div>

            		</div>

            		<div style="width: 15%; height: 100%; float: left;"></div>

            	</div>

            	<%--<div style="width: 8%; height: 100%; float: left; "></div>--%>

        </div>

        <!-- 页面底部 -->
        <div style="width: 100%; height: 15%; min-width: 1366px; min-height: 100px; background-color: #E8E7E4; " >

            <div style="width: 100%; height: 29%; float: left; font-size: 10px; text-align: center;">
					©2019-2019 版权
            </div>

            <div style="width: 100%; height: 5%; float: left;"></div>
        </div>
	   </div>
	<script>
        $(window).resize(function () {
            sizeLoging()
        });
        function sizeLoging(){
            var w=$("#midDiv").width();
            var h=$("#midDiv").height();
            $("#loginDiv").css("margin-right",w*0.1)
            $("#loginDiv").css("margin-top",(h-410)/2)
		}
        sizeLoging()
	</script>
    </body>
</html>