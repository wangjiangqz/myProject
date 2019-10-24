<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
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
        .dropdown-toggle {
            height: 70px;
            padding-top: 29px !important;
        }

        .logo:hover {
            background-color: red;
        }

        .dropdown-toggle:hover {
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
        $(document).ready(function () {

            var isCheck="${isCheckPassWord}";

            if("p01"==isCheck){

                var vaildPwd= "${vaildPwd}";

                if(vaildPwd == '1'){

                    $("#myModal").modal('show');
                    $(".hidden_div").css("display","block");
                }else{
                    $("#myModal").modal('hide');
                    $(".hidden_div").css("display","none");
                }
            }

            //showTip();
            //setInterval("showTip();",15*60*1000);
            // <c:if test="${tabmode eq '1'}"> 初始化页签
            $.fn.initJerichoTab({
                renderTo: '#right', uniqueId: 'jerichotab',
                contentCss: {'height': $('#right').height() - tabTitleHeight},
                tabs: [], loadOnce: true, tabWidth: 110, titleHeight: tabTitleHeight
            });//</c:if>
            // 绑定菜单单击事件
            $("#menu a.menu").click(function () {
                //debugger;
                // 一级菜单焦点
                $("#menu li.menu").removeClass("active");
                $(this).parent().addClass("active");
                // 显示二级菜单
                var menuId = "#menu-" + $(this).attr("data-id");
                if ($(menuId).length > 0) {

                } else {
                    // 获取二级菜单数据
                    $.get($(this).attr("data-href") + "&tm=" + new Date().getTime(), function (data) {
                        //debugger
                        if (data.indexOf("id=\"loginForm\"") != -1) {
                            alert('未登录或登录超时。请重新登录，谢谢！');
                            top.location = "${ctx}";
                            return false;
                        }
                        $("#left").append(data);
                        $(".sidebar-menu").tree();
                        // // 二级标题
                        $(".sidebar-menu li.treeview").click(function () {
                            // $(" .sidebar-menu li").removeClass("active");
                            // $(this).addClass("active");
                            //alert(111)
                            // if($(this).hasClass("menu-open")){
                            //     $(this).removeClass("active");
                            // }else{
                            //     $(this).addClass("active");
                            // }
                            //console.log("1212",$(this).find(".menuname").html());
                            $(".content-header h1 b").html($(this).find(".menuname").html())
                        });
                        // // 二级内容
                        $(menuId + " .treeview-menu li.lastMenu").click(function () {
                            $(".treeview-menu li.lastMenu").removeClass("active");
                            $(this).addClass("active");
                            $(".content-header h1 small").html($(this).find(".menuname").html())
                            $(".breadcrumb li.active").html($(this).find(".menuname").html())
                        });
                        // 展现三级
                        $(menuId + " .accordion-inner a").click(function () {
                            var href = $(this).attr("data-href");
                            if ($(href).length > 0) {
                                $(href).toggle().parent().toggle();
                                return false;
                            }
                            // <c:if test="${tabmode eq '1'}"> 打开显示页签
                            return addTab($(this)); // </c:if>
                        });
                        // 默认选中第一个菜单
                        //console.log("2323",$(" .sidebar-menu li:first").html())
                        //$(menuId +" li.accordion-group:first").addClass("active");
                        $(menuId + " .treeview-menu li.lastMenu a:first i").click();
                    });
                }
                // 大小宽度调整
                //wSizeWidth();
                return false;
            });
            // 初始化点击第一个一级菜单
            $("#menu a.menu:first span").click();
            // $("#userInfo .dropdown-menu a").mouseup(function(){
            //     return addTab($(this), true);
            // });
            $("#userTag").click(function () {
                $(".content-header h1 small").html("个人信息")
                $(".content-header h1 b").html("个人信息")
            });


        });

        function addTab($this, refresh) {
            debugger
            $(".jericho_tab").show();
            $("#mainFrame").hide();
            $.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: $this.text(),
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
            return false;
        }

    </script>
    <script type="text/javascript">

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
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- Messages: style can be found in dropdown.less-->
                    <li class="dropdown messages-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-envelope-o"></i>
                            <span class="label label-success">4</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">您有4条信息</li>
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu">
                                    <li><!-- start message -->
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="${ctxStatic}/AdminLTE-2.4.5/dist/img/user2-160x160.jpg"
                                                     class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Support Team
                                                <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <!-- end message -->
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="${ctxStatic}/AdminLTE-2.4.5/dist/img/user3-128x128.jpg"
                                                     class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                AdminLTE Design Team
                                                <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="${ctxStatic}/AdminLTE-2.4.5/dist/img/user4-128x128.jpg"
                                                     class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Developers
                                                <small><i class="fa fa-clock-o"></i> Today</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="${ctxStatic}/AdminLTE-2.4.5/dist/img/user3-128x128.jpg"
                                                     class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Sales Department
                                                <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="${ctxStatic}/AdminLTE-2.4.5/dist/img/user4-128x128.jpg"
                                                     class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Reviewers
                                                <small><i class="fa fa-clock-o"></i> 2 days</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="footer"><a href="#">查看全部信息</a></li>
                        </ul>
                    </li>
                    <!-- Notifications: style can be found in dropdown.less -->
                    <li class="dropdown notifications-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-bell-o"></i>
                            <span class="label label-warning">10</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">您有10条提醒</li>
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu">
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-warning text-yellow"></i> Very long description here that
                                            may not fit into the
                                            page and may cause design problems
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-users text-red"></i> 5 new members joined
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-user text-red"></i> You changed your username
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="footer"><a href="#">查看全部</a></li>
                        </ul>
                    </li>
                    <!-- Tasks: style can be found in dropdown.less -->
                    <li class="dropdown tasks-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-flag-o"></i>
                            <span class="label label-danger">9</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">您有9条任务</li>
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu">
                                    <li><!-- Task item -->
                                        <a href="#">
                                            <h3>
                                                Design some buttons
                                                <small class="pull-right">20%</small>
                                            </h3>
                                            <div class="progress xs">
                                                <div class="progress-bar progress-bar-aqua" style="width: 20%"
                                                     role="progressbar"
                                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                    <span class="sr-only">20% Complete</span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <!-- end task item -->
                                    <li><!-- Task item -->
                                        <a href="#">
                                            <h3>
                                                Create a nice theme
                                                <small class="pull-right">40%</small>
                                            </h3>
                                            <div class="progress xs">
                                                <div class="progress-bar progress-bar-green" style="width: 40%"
                                                     role="progressbar"
                                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                    <span class="sr-only">40% Complete</span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <!-- end task item -->
                                    <li><!-- Task item -->
                                        <a href="#">
                                            <h3>
                                                Some task I need to do
                                                <small class="pull-right">60%</small>
                                            </h3>
                                            <div class="progress xs">
                                                <div class="progress-bar progress-bar-red" style="width: 60%"
                                                     role="progressbar"
                                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                    <span class="sr-only">60% Complete</span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <!-- end task item -->
                                    <li><!-- Task item -->
                                        <a href="#">
                                            <h3>
                                                Make beautiful transitions
                                                <small class="pull-right">80%</small>
                                            </h3>
                                            <div class="progress xs">
                                                <div class="progress-bar progress-bar-yellow" style="width: 80%"
                                                     role="progressbar"
                                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                    <span class="sr-only">80% Complete</span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <!-- end task item -->
                                </ul>
                            </li>
                            <li class="footer">
                                <a href="#">查看全部任务</a>
                            </li>
                        </ul>
                    </li>
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="${ctxStatic}/AdminLTE-2.4.5/dist/img/user_me.jpg" class="user-image"
                                 alt="User Image">
                            <span class="hidden-xs">您好, ${fns:getUser().name}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="${ctxStatic}/AdminLTE-2.4.5/dist/img/user_me.jpg" class="img-circle"
                                     alt="User Image">

                                <p>
                                    ${fns:getUser().name} - 欢迎您
                                    <small>${fns:getDate('yyyy-MM-dd HH:mm:ss')}</small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <div class="row">
                                    <div class="col-xs-4 text-center">
                                        <%--<a href="#">Followers</a>--%>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <%--<a href="#">Sales</a>--%>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <%--<a href="#">Friends</a>--%>
                                    </div>
                                </div>
                                <!-- /.row -->
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a id="userTag" target="mainFrame" href="${ctx}/sys/user/info"
                                       class="btn btn-default btn-flat">个人信息</a>
                                </div>
                                <div class="pull-right">
                                    <a href="${ctx}/logout" class="btn btn-default btn-flat">退出</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <%--<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>--%>
                        <%--<a href="${ctx}/logout"  title="退出登录">退出</a>--%>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${ctxStatic}/AdminLTE-2.4.5/dist/img/user_me.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${fns:getUser().name}</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <ul id="menu" style="display: none">
                <c:set var="firstMenu" value="true"/>
                <c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
                    <c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
                        <li class="menu treeview ${not empty firstMenu && firstMenu ? ' active' : ''}">
                            <c:if test="${empty menu.href}">
                                <a class="menu fa fa-dashboard" href="javascript:"
                                   data-href="${ctx}/sys/menu/tree?parentId=${menu.id}"
                                   data-id="${menu.id}"><span>${menu.name}</span></a>
                            </c:if>
                            <c:if test="${not empty menu.href}">
                                <a class="menu fa fa-dashboard"
                                   href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}"
                                   data-id="${menu.id}" target="mainFrame"><span>${menu.name}</span></a>
                            </c:if>
                        </li>
                        <c:if test="${firstMenu}">
                            <c:set var="firstMenuId" value="${menu.id}"/>
                        </c:if>
                        <c:set var="firstMenu" value="false"/>
                    </c:if>
                </c:forEach>

            </ul>
            <left id="left">

            </left>


        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                <b>Dashboard</b>
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${ctx}"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">Dashboard</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible" scrolling="yes" frameborder="no"
                    width="100%" height="500"></iframe>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer" style="height: 20px;padding:0;margin-left: 230px">

        Copyright © 2019-2019 基础快速开发平台 - Powered By <a href="#" target="_blank">版权</a> V1.0

    </footer>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
         style="z-index:1980;width: 100%;display: none;" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 800px">
                <div class="modal-header">
                    <h3 class="modal-title" id="myModalLabel">
                        您的密码为弱密码，不满足信息安全的要求，请按如下规则设置密码<br>
                        <span style="color: red">（密码长度8-20位，且应为字母、数字、英文特殊字符（`~!@#$^*()=|{}':;',\.<>[表情]）的组合）</span>
                    </h3>
                </div>
                <div class="modal-body">
                    <iframe src="${ctx}/base/tBaRegistered/forceModifyPwd" id="NoPermissioniframe" width="100%" height="350px" frameborder="0" style="margin-top: 2%;"></iframe>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
<!-- ./wrapper -->
<script type="text/javascript">
    var headHeight = 70; // 抬头的高度
    var htmlObj = $("html"), mainObj = $(".content-wrapper");
    var headerObj = $(".content-header"), footerObj = $(".main-footer");
    var frameObj = $(".wrapper");

    function wSize() {
        var minHeight = 500, minWidth = 1366;
        var strs = getWindowSize().toString().split(",");
        $(".content-wrapper").height(strs[0] - headHeight - footerObj.height());
        $(".content-wrapper").css({"overflow-y": "auto"});
        $(".content-wrapper").css({"min-height": "0px"});
        $(".main-sidebar").height(strs[0] - headHeight - footerObj.height());
        $(".main-sidebar").css({"overflow-y": "auto"});
        $(".wrapper").height(strs[0]);
        $(".content-wrapper").width(strs[1] - $(".main-sidebar").width() - 5);
        mainObj.css("width", strs[1] < minWidth ? minWidth - $(".main-sidebar").width() - 5 : "auto");
        $("#mainFrame").attr("height", mainObj.height() - headerObj.height() - 45);
        htmlObj.css({"overflow-x": strs[1] < minWidth ? "auto" : "hidden", "min-width": minWidth});
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
    $(document).ready(function () {
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