<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>
    <title>${fns:getConfig('productName')}</title>
    <!-- WPA start -->
    <script id="qd2852153601554f0229e450d762b140966cab5264f8" src="https://wp.qiye.qq.com/qidian/2852153601/554f0229e450d762b140966cab5264f8" charset="utf-8" async defer></script>
    <!-- WPA end -->
    <link href="${ctxStatic}/data/styles.css" type="text/css"
          rel="stylesheet"/>
    <script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
    <script src="${ctxStatic}/browserVerification/browser.js" type="text/javascript"></script>
    <%--<link href="${ctxStatic}/browserVerification/tips.css" type="text/css"--%>
    <%--rel="stylesheet" />--%>
    <%--<script src="${ctxStatic}/browserVerification/tips.js" type="text/javascript"></script>--%>
    <!-- iCheck -->
    <link rel="stylesheet" href="${ctxStatic}/AdminLTE-2.4.5/plugins/iCheck/square/blue.css">
    <script type="text/javascript">
        $(document).ready(function () {

            //获取cookie的值
            var username = $.cookie('username');
            var password = $.cookie('password');
            //将获取的值填充入输入框中
            $('#username').val(username);
            $('#password').val(password);
            if (username != null && username != '' && password != null && password != '') {//选中保存秘密的复选框
                $("#ck").attr('checked', true);
            }
            $("#loginForm").validate({
                submitHandler: function (form) {
                    var uName = $('#username').val();
                    var psw = $('#password').val();
                    if (document.getElementById("ck").checked) {//保存密码
                        $.cookie('username', uName, {expires: 7, path: '/'});
                        $.cookie('password', psw, {expires: 7, path: '/'});
                    } else {//删除cookie
                        $.cookie('username', '', {expires: -1, path: '/'});
                        $.cookie('password', '', {expires: -1, path: '/'});
                    }
                    form.submit();
                },
                rules: {
                    validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
                },
                messages: {
                    username: {required: "请填写用户名."}, password: {required: "请填写密码."},
                    validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
                },
                showErrors: function (errorMap, errorList) {

                    //console.log(errorMap.validateCode)
                    if (typeof errorMap.validateCode != "undefined" && errorMap.validateCode != null && errorMap.validateCode != "" && errorMap.validateCode.length > 2) {
                        $("#messageBox").show();
                        $("#loginError").html("<span class=\"glyphicon glyphicon-remove-circle\"></span>" + errorMap.validateCode)
                    }


                    //error.insertAfter("#loginError")
                }
            });
        });
        // 如果在框架或在对话框中，则弹出提示并跳转到首页
        if (self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0) {
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
                radioClass: 'iradio_flat-blue'
            })
            $(".topC").click(function () {
                $("#messageBox").hide();
            })

            <%--var mes='${message}';--%>
            <%--if(typeof mes == "undefined" || mes == null || mes == "")--%>
        });

    </script>

    <style type="text/css">
        .qidian_wpa_img {
            top: 3%!important;
            right: 40px!important;
            height: 5.5% !important;
            left: auto !important;
            width: 11% !important;
        }
        html {
            overflow: auto;
        }

        .icheckbox_flat-blue {
            margin: 0;
        }

        .topC {
            height: 33px;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            background: inherit;
            -webkit-filter: blur(5px);
            -moz-filter: blur(5px);
            -ms-filter: blur(5px);
            -o-filter: blur(5px);
            filter: blur(5px);
            filter: progid:DXImageTransform.Microsoft.Blur(PixelRadius=4, MakeShadow=false);
            background-color: black;
            filter: alpha(opacity=30);
            -moz-opacity: 0.3;
            -khtml-opacity: 0.3;
            opacity: 0.3;
        }

        .toptop {
            text-align: center;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            background-repeat: no-repeat;
            background-attachment: fixed;
            overflow: hidden;
        }

        #loginDiv {
            position: relative;
            z-index: 1130;
            /*background-repeat: repeat-x;*/
            /*-moz-box-shadow: 6px 6px 6px rgba(6, 6, 6, .4);*/
            /*-webkit-box-shadow: 6px 6px 6px rgba(6, 6, 6, .4);*/
        }

        .skin-green-light #loginDiv > div > div:nth-child(2) > div:nth-child(1){
            border-bottom: 3px solid #589c3a; !important;
        }
        .skin-blue-light #loginDiv > div > div:nth-child(2) > div:nth-child(1){
            border-bottom: 3px solid #3f89ec; !important;
        }
        .skin-blue #loginDiv > div > div:nth-child(2) > div:nth-child(1){
            border-bottom: 3px solid #232e3a; !important;
        }
        .skin-green-light #loginForm > div:nth-child(5) > div > button{
            background:#589c3a;
        }
        .skin-blue-light #loginForm > div:nth-child(5) > div > button{
            background:#3f89ec;
        }
        .skin-blue #loginForm > div:nth-child(5) > div > button{
            background:#232e3a;
        }
        /*#footH{*/
            /*position: relative;*/
            /*z-index: 1030;*/
            /*background-repeat: repeat-x;*/
            /*-moz-box-shadow: -3px -3px 3px rgba(100, 100,100, .4);*/
            /*-webkit-box-shadow: -3px -3px 3px rgba(100, 100, 100, .4);*/
        /*}*/
        /*#headH{*/
            /*position: relative;*/
            /*z-index: 1080;*/
            /*background-repeat: repeat-x;*/
            /*-moz-box-shadow: 3px 3px 3px rgba(100, 100,100, .4);*/
            /*-webkit-box-shadow: 3px 3px 3px rgba(100, 100, 100, .4);*/
        /*}*/
    </style>
</head>

<body class="hold-transition skin-blue sidebar-mini" style="overflow: hidden">
<div class="wrapper" style="overflow: auto">
    <div id="messageBox" class="toptop" style="${empty message?'display:none':'' }">
        <button data-dismiss="alert" class="close">×</button>
        <label id="loginError" style="color: #ea5200;margin-top: 5px"><span
                class="glyphicon glyphicon-remove-circle"></span>${message}</label>
        <div class="topC"></div>
    </div>
    <%--<div style="background-color: #CCFFCC;text-align: center; " class="alert alert-right ${empty suc ? 'hide' : ''}">--%>
    <%--<button data-dismiss="alert" class="close">×</button>--%>
    <%--<label style="color: #33CC33">${suc}</label>--%>
    <%--</div>--%>

    <!-- 页面头部 -->
    <div id="headH"
         style="width: 100%; height: 10%; background-color: white; min-width: 1366px; min-height: 55px;padding-left: 4%">
        <div style="width: 400px;height:55px;background-image: url(${ctxStatic}/images/lgoin20170906/logo1.png?v=${fns:getParaValue("theme","02" ,"" )});
                background-repeat: no-repeat;background-size:100% 100%;float:left" ></div>
        <div id="headLogo"
             style="height: 55px;width: 100%;line-height:55px;font-size: 2.6em;padding-left:7%;
            font-weight: bolder;color: #01469f;font-family: 宋体;letter-spacing: 1px">
        </div>

    </div>

    <!-- 页面中部 -->
    <div id="midDiv"
         style="width: 100%; height: 81%;  min-width: 1366px; min-height: 500px;
                 background-image: url(${ctxStatic}/images/lgoin20170906/backgroundimage1.png?v=${fns:getParaValue("theme","02" ,"" )}),
                 url(${ctxStatic}/images/lgoin20170906/backgroundimage2.png?v=${fns:getParaValue("theme","02" ,"" )}),
                 url(${ctxStatic}/images/lgoin20170906/backgroundimage3.png?v=${fns:getParaValue("theme","02" ,"" )}),
                 url(${ctxStatic}/images/lgoin20170906/backgroundimage4.png?v=${fns:getParaValue("theme","02" ,"" )});
                 background-repeat: no-repeat, no-repeat, no-repeat, no-repeat;background-position: 0 0, 100% 0, 0 100%,100% 100%;
                 background-size: 50.1% 50.1%,50% 50.1%,50.1% 50%,50% 50%">

        <div id="loginDiv" style="background-image: url(${ctxStatic}/images/lgoin20170906/loginbar.png?v=${fns:getParaValue("theme","02" ,"" )});
                background-repeat: no-repeat;background-size:100% 100%;width: 470px; height:408px;float: right;padding: 28px 17px">
        <div  style="width: 436px; height:350px; background-color:white;">
            <div style="width: 100%; height: 10px;">

            </div>
            <div style="width: 100%; height: 42px;border-bottom: 1px solid #dddddd">
                <div style="width: 100px;line-height: 39px;margin-left: 9%;font-size: 1.26em;font-weight: bold;float: left">
                    &nbsp;&nbsp;用户登录&nbsp;&nbsp;
                </div>
                <div style="line-height:50px;color: red;">
                    （建议使用IE10+、Firefox、Chrome等浏览器）
                </div>


            </div>
            <div style="width: 100%; height:330px;">

                <div style="width: 82%;margin-left: 9%;margin-top: 20px">

                    <form id="loginForm" action="${ctx}/login" method="post" >

                        <div class="form-group has-feedback" style="margin-top: 9%">
                            <input type="text" class="form-control required" placeholder="用户名" id="username"
                                   name="username">
                            <span class="glyphicon glyphicon-user form-control-feedback"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <input type="password" class="form-control required" placeholder="密码" id="password"
                                   name="password">
                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <div class="form-group has-feedback" style="margin-bottom: 0px">
                            <sys:validateCode name="validateCode"
                                              inputCssStyle="margin-bottom:0px;width:185px"
                                              buttonCssStyle="line-height:30px;color: rgb(46, 130, 255);"/>
                        </div>
                        <div class="row">
                            <div class="col-xs-2" style="padding-right: 0;width: 10.66666667%; ">
                                <div class="checkbox icheck">
                                    <label>
                                        <input type="checkbox" id="ck" class="flat-blue">
                                        <%--<div style="display: inline-block;">记住密码（公共场所慎用）</div>--%>

                                    </label>
                                </div>
                            </div>
                            <div class="col-xs-7" style="padding-left: 0">

                                <label style="line-height: 3;font-weight: normal;margin-bottom:0">

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
                                <button type="submit"
                                        style="min-height: 40px;font-size: 1.1em;font-weight: bold"
                                        class="btn btn-primary btn-block ">登&nbsp;&nbsp;录
                                </button>

                            </div>
                        </div>
                        <div class="row" style="margin-top: 2px">
                            <div class="col-xs-6">
                                <a href="${pageContext.request.contextPath}/base/tBaRegistered/forgetPwd"
                                   style="color: rgb(46, 130, 255);">忘记密码？</a>

                            </div>
                            <div class="col-xs-6">

                                <a href="${pageContext.request.contextPath}/base/tBaRegistered/form"
                                   style="float: right;color: rgb(46, 130, 255);">立即注册</a>
                            </div>
                            <!-- /.col -->
                        </div>
                    </form>
                </div>


            </div>

        </div>

        </div>
    </div>

    <!-- 页面底部 -->
    <div id="footH" style="width: 100%; height: 9%; min-width: 1366px; min-height: 75px; background-color: white; ">

        <div style="width: 100%; height: 56%; padding-top: 0.5%">
            <div style="width: 50%; height: 100%; float: left; text-align: center">
                <img alt="" src="${ctxStatic}/images/lgoin20170906/icon5.png?v=${fns:getParaValue("theme","02" ,"" )}">
                <span style="color: black">联系电话：${fns:getParaValue('phone',"01" ,"" )}</span>

            </div>

            <%--<div style="width: 34%; height: 100%; float: left; text-align: center">--%>
                <%--<img alt="" src="${ctxStatic}/images/lgoin20170906/icon6.png?v=${fns:getParaValue("theme","02" ,"" )}">--%>
                <%--<span style="color: black">服务QQ群：${fns:getParaValue('QQ',"01" ,"" )}</span>--%>

            <%--</div>--%>
            <div style="width: 50%; height: 100%; float: left;text-align: center">

                <img alt="" src="${ctxStatic}/images/lgoin20170906/icon7.png?v=${fns:getParaValue("theme","02" ,"" )}" style="width: 50px">

                <span style="color: black">微信扫一扫 &nbsp; 关注我们</span>

            </div>
        </div>

        <div style="width: 100%;  font-size: 10px; text-align: center;padding-top: 0.5%">
            ©2019-2019 版权
        </div>
    </div>
</div>
<script>
    $(window).resize(function () {
        sizeLoging()
    });

    function sizeLoging() {
        var w = $("#midDiv").width();
        var h = $("#midDiv").height();
        var hw = $("#headH").width();
        var hh = $("#headH").height();
        console.log("h",h);
        console.log("$(\"#loginDiv\").height()",$("#loginDiv").height());
        $("#loginDiv").css("margin-right", w * 0.1)
        $("#loginDiv").css("margin-top", (h - $("#loginDiv").height()-56) / 2)
        $("#headH").css("padding-top", (hh - $("#headLogo").height()) / 2)
    }

    sizeLoging()

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
</body>
</html>