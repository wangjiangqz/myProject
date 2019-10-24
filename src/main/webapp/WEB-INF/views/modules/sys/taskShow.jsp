<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<head>
    <title>图片配置</title>
    <meta name="decorator" content="default"/>
    <style>
        .defaultbar {
            margin-top: 50px;
            height: 5px;
            border: 1px solid #A9C9E2;
            position: absolute;
            width: 93%;
        }
        .jquery-completed {
            height: 3px;
            background-color: #0267bf;
            top: 1px;
            left: 1px;
            width: 87.5%;height: 3px;line-height: 0px;font-size: 0px;
        }
        .jieDian {
            background: url('${ctxStatic}/images/flowChart/jiedian.PNG') no-repeat;
        }
        .jieDian-d {
            background: url('${ctxStatic}/images/flowChart/jiedian-01.PNG') no-repeat;
        }
        .jieDian-dangqian {
            background: url(${ctxStatic}/images/flowChart/zhuangtai.png);
            height: 33px;
            width: 86px;
            top: -29px;
            left: -30px;
            position: relative;
        }
        .jieDian, .jieDian-d, .jieDian-s {
            width: 33px;
            height: 32px;
            top: -16px;
            left: -5px;
            position: relative;
        }
        .jieDian1 {
            line-height: normal;
            top: -72px;
            left: -35px;
            position: relative;
            width: 95px;
            text-align: center;

        }
        .jieDian2 {
            top: -33px;
            left: -35px;
            position: relative;
        }
        .jieDian-b{
            position: absolute;
        }

       .jieDian2  table   td{
            text-align: center;
        }
        .jieDian2  table{
           width: 95px;
        }
        #contentTable  a{
            color:#0b93d5;
        }
    </style>
</head>
<body>
<div class="box box-primary">
    <div class="box-body">
        <div class="breadcrumb  bg-gray-light callout">
            <div class="row">
                <div class="col-md-12">
                    <label style="font-size: 16px">申请信息</label>
                </div>
            </div>
            <div class="row">
                <div class="col-md-1">

                </div>
                <div class="col-md-7">
                   企业名称： 五原县大丰粮油食品有限责任公司
                </div>
                <div class="col-md-3" >
                   法人： 赵飞云
                </div>
                <div class="col-md-1">

                </div>
            </div>
            <div class="row" style="margin-top: 8px">
                <div class="col-md-1">

                </div>
                <div class="col-md-7">
                    申请信息：<a href="javaScript:void(0)" style="color: #0b93d5">
                        查看</a>
                </div>
                <div class="col-md-3" >
                    申请时间： 2019-04-15
                </div>
                <div class="col-md-1">

                </div>
            </div>
        </div>
        <div class="breadcrumb form-search bg-gray-light callout">
            <div class="row">
                <div class="col-md-12" style="height: 150px;padding: 0 3%;">
                    <div class="defaultbar">
                        <div class="jquery-completed"></div>
                        <div class="jieDian-b" >
                            <div class="jieDian"></div>
                            <div class="jieDian1"><a class="jindu-font-0">企业申请</a></div>
                            <div class="jieDian2">
                                <table style="font-size: 12px;">
                                    <tbody>
                                    <tr>
                                        <td title="五原县大丰粮油食品有限责任公司"><a>五原县大丰...</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>2019-04-15</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>提交</a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="jieDian-b" style="left: 12.5%">
                            <div class="jieDian"></div>
                            <div class="jieDian1"><a class="jindu-font-0">备案受理</a></div>
                            <div class="jieDian2">
                                <table style="font-size: 12px;">
                                    <tbody>
                                    <tr>
                                        <td><a>李成志1</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>2019-04-17</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>予以受理</a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="jieDian-b" style="left: 25%">
                            <div class="jieDian"></div>
                            <div class="jieDian1" style="font-size: 13px;"><a class="jindu-font-0">评审组任务分配</a></div>
                            <div class="jieDian2">
                                <table style="font-size: 12px;">
                                    <tbody>
                                    <tr>
                                        <td><a>李成志1</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>2019-04-18</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>任务分配结束</a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="jieDian-b" style="left: 37.5%">
                            <div class="jieDian"></div>
                            <div class="jieDian1"><a class="jindu-font-0">文件审核</a></div>
                            <div class="jieDian2">
                                <table style="font-size: 12px;">
                                    <tbody>
                                    <tr>
                                        <td><a>吴小龙</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>2019-04-22</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>文件审核结束</a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="jieDian-b" style="left: 50%">
                            <div class="jieDian"></div>
                            <div class="jieDian1"><a class="jindu-font-0">现场检查</a></div>
                            <div class="jieDian2">
                                <table style="font-size: 12px;">
                                    <tbody>
                                    <tr>
                                        <td><a>吴小龙</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>2019-04-22</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>存在不符合项</a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="jieDian-b" style="left: 62.5%">
                            <div class="jieDian"></div>
                            <div class="jieDian1"><a class="jindu-font-0">跟踪检查</a></div>
                            <div class="jieDian2">
                                <table style="font-size: 12px;">
                                    <tbody>
                                    <tr>
                                        <td><a>吴小龙</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>2019-04-23</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>合格</a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="jieDian-b" style="left: 75%">
                            <div class="jieDian"></div>
                            <div class="jieDian1"><a class="jindu-font-0">备案材料终审</a></div>
                            <div class="jieDian2">
                                <table style="font-size: 12px;">
                                    <tbody>
                                    <tr>
                                        <td><a>游广文1</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>2019-04-24</a></td>
                                    </tr>
                                    <tr>
                                        <td><a>提交审批决定</a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="jieDian-b" style="left: 87.5%">
                            <div class="jieDian-d"></div>
                            <div class="jieDian1"><a class="jindu-font-1">备案审批决定</a></div>
                            <div class="jieDian-dangqian"></div>
                        </div>
                        <div class="jieDian-b"  style="left: 100%">
                            <div class="jieDian"></div>
                            <div class="jieDian1"><a class="jindu-font-0">归档</a></div>
                            <div class="jieDian2"></div>
                        </div>
                    </div>
                </div>
            </div>
            <%--<div class="row">--%>
            <%--<div class="col-md-1">--%>

            <%--</div>--%>
            <%--<div class="col-md-5">--%>
            <%--<label style="font-size: 15px">企业名称：	五原县大丰粮油食品有限责任公司</label>--%>
            <%--</div>--%>
            <%--<div class="col-md-3" style="text-align: right">--%>
            <%--<label style="font-size: 15px">法人：	赵飞云</label>--%>
            <%--</div>--%>
            <%--<div class="col-md-3">--%>

            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
            <%--<div class="col-md-1">--%>

            <%--</div>--%>
            <%--<div class="col-md-5" >--%>
            <%--<label style="font-size: 15px">申请信息：<a href="javaScript:void(0)" style="color: #0b93d5">	查看</a></label>--%>
            <%--</div>--%>
            <%--<div class="col-md-3" style="text-align: right">--%>
            <%--<label style="font-size: 15px">申请时间：	2019-04-15</label>--%>
            <%--</div>--%>
            <%--<div class="col-md-3">--%>

            <%--</div>--%>
            <%--</div>--%>
        </div>
        <sys:message content="${message}"/>
        <table id="contentTable" class="table table-striped table-bordered table-condensed">
            <thead>
            <tr>
                <th style="width: 40px">序号</th>
                <th>处理人</th>
                <th>处理机构</th>
                <th>处理节点</th>
                <th>处理决定</th>
                <th>处理意见</th>
                <th style="width: 150px">处理时间</th>
                <th>相关附件</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td>1</td>
                <td><a href="javascript:" onclick="lookDetail('0eb81fc17a3a47b389b41d87027c4aaa','cf2c7d0b71d541d7b776d33305e3b9ca','01','03','')">五原县大丰粮油食品有限责任公司</a></td>
                <td title="包头局本部">包头局本部</td>
                <td title="延续备案">延续备案</td>
                <td title="提交">提交</td>
                <td title=""></td>
                <td>2019-04-15</td>
                <td></td>
            </tr>

            <tr>
                <td>2</td>
                <td><a href="javascript:" onclick="lookDetail('b0743b191bc84ebf879e8708acf66f33','982900b482394bd895d64507bd4329c5','02','03','01')">李成志1</a></td>
                <td title="乌拉特局本部">乌拉特局本部</td>
                <td title="备案受理">备案受理</td>
                <td title="岗位内流转">岗位内流转</td>
                <td title="请审核。">请审核。</td>
                <td>2019-04-15</td>
                <td></td>
            </tr>

            <tr>
                <td>3</td>
                <td><a href="javascript:" onclick="lookDetail('b0743b191bc84ebf879e8708acf66f33','258b7ef2373242daa42cb916b3502bef','02','03','01')">魏晓松1</a></td>
                <td title="内蒙古局本部">内蒙古局本部</td>
                <td title="备案受理">备案受理</td>
                <td title="岗位内流转">岗位内流转</td>
                <td title="材料基本齐全.">材料基本齐全.</td>
                <td>2019-04-16</td>
                <td></td>
            </tr>

            <tr>
                <td>4</td>
                <td><a href="javascript:" onclick="lookDetail('b0743b191bc84ebf879e8708acf66f33','532a35bff06c474284e35150553ff494','02','03','01')">刘建华</a></td>
                <td title="内蒙古局本部">内蒙古局本部</td>
                <td title="备案受理">备案受理</td>
                <td title="岗位内流转">岗位内流转</td>
                <td title="-">-</td>
                <td>2019-04-16</td>
                <td></td>
            </tr>

            <tr>
                <td>5</td>
                <td><a href="javascript:" onclick="lookDetail('b0743b191bc84ebf879e8708acf66f33','270faea8d3804998a85262fdd64f7a41','02','03','01')">魏晓松1</a></td>
                <td title="内蒙古局本部">内蒙古局本部</td>
                <td title="备案受理">备案受理</td>
                <td title="岗位内流转">岗位内流转</td>
                <td title="请组织人员进行评审。">请组织人员进行评审。</td>
                <td>2019-04-16</td>
                <td></td>
            </tr>

            <tr>
                <td>6</td>
                <td><a href="javascript:" onclick="lookDetail('b0743b191bc84ebf879e8708acf66f33','b69b4a145e2f4d67a0be2baa666a4908','02','03','04')">李成志1</a></td>
                <td title="乌拉特局本部">乌拉特局本部</td>
                <td title="备案受理">备案受理</td>
                <td title="予以受理">予以受理</td>
                <td title="同意受理">同意受理</td>
                <td>2019-04-17</td>
                <td></td>
            </tr>

            <tr>
                <td>7</td>
                <td><a href="javascript:" onclick="lookDetail('bdba56cda86c4c2dab094adc0a6bdf6d','52e27e6f27634ac68674dbd879d9a397','03','03','')">李成志1</a></td>
                <td title="乌拉特局本部">乌拉特局本部</td>
                <td title="评审组任务分配">评审组任务分配</td>
                <td title="提交">提交</td>
                <td title=""></td>
                <td>2019-04-18</td>
                <td></td>
            </tr>

            <tr>
                <td>8</td>
                <td><a href="javascript:" onclick="lookDetail('67369f444b864cc39a49cf9e43aa7535','a390566b2884441ab39e603db4f62873','031','03','04')">吴小龙</a></td>
                <td title="乌拉特局本部">乌拉特局本部</td>
                <td title="文件审核">文件审核</td>
                <td title="文件审核结束">文件审核结束</td>
                <td title="请组织现场评审。">请组织现场评审。</td>
                <td>2019-04-22</td>
                <td><a href='javascript:downFile("a1b670b795b8447e9b91ff5c766d420e")'>文件评审记录-大丰.doc</a><br></td>
            </tr>

            <tr>
                <td>9</td>
                <td><a href="javascript:" onclick="lookDetail('8e7ed2a1790a43249c26e045846eb0cc','09b9a5e24e684342a16ce98a434f569c','033','03','0302')">吴小龙</a></td>
                <td title="乌拉特局本部">乌拉特局本部</td>
                <td title="现场检查">现场检查</td>
                <td title="存在不符合项,需整改">存在不符合项,需整改</td>
                <td title="请企业按要求整改。">请企业按要求整改。</td>
                <td>2019-04-22</td>
                <td><a href='javascript:downFile("c0e4506eba4b4683b3311a95a5230562")'>五原大丰-采信与监管联动记录表.doc</a><br><a href='javascript:downFile("021fe19636604d71b43adfaa41349ca9")'>文件评审记录-大丰.doc</a><br></td>
            </tr>

            <tr>
                <td>10</td>
                <td><a href="javascript:" onclick="lookDetail('6cb2ad03c0f7485a817bafb24ab44c5d','d698ed11b3e34366924669d09d680777','0330','03','')">五原县大丰粮油食品有限责任公司</a></td>
                <td title="乌拉特局本部">乌拉特局本部</td>
                <td title="现场检查整改">现场检查整改</td>
                <td title="提交">提交</td>
                <td title=""></td>
                <td>2019-04-22</td>
                <td></td>
            </tr>

            <tr>
                <td>11</td>
                <td><a href="javascript:" onclick="lookDetail('0b1936917ffe496e8f41ec098d66bd9d','a7e0b2b5c181428e95c4968f3d7e4d58','034','03','0301')">吴小龙</a></td>
                <td title="乌拉特局本部">乌拉特局本部</td>
                <td title="跟踪检查">跟踪检查</td>
                <td title="合格">合格</td>
                <td title="企业已按要求整改，整改合格。">企业已按要求整改，整改合格。</td>
                <td>2019-04-23</td>
                <td><a href='javascript:downFile("5317778bf92e4637bf97d85f26690086")'>4.不符合项及跟踪记录.doc</a><br><a href='javascript:downFile("bce50813bd7b4b4b825c1213910497ef")'>不符合项及跟踪记录.doc</a><br></td>
            </tr>

            <tr>
                <td>12</td>
                <td><a href="javascript:" onclick="lookDetail('1ff909305a6e40ca9e304ce9edb75254','da7be0afd37c440daae7b77115fb375f','041','03','01')">康健1</a></td>
                <td title="乌拉特局本部">乌拉特局本部</td>
                <td title="备案材料终审">备案材料终审</td>
                <td title="岗位内流转">岗位内流转</td>
                <td title="请游关审核！">请游关审核！</td>
                <td>2019-04-23</td>
                <td></td>
            </tr>

            <tr>
                <td>13</td>
                <td><a href="javascript:" onclick="lookDetail('1ff909305a6e40ca9e304ce9edb75254','6de3835b47914b5ab1a272f630b43d7b','041','03','05')">游广文1</a></td>
                <td title="乌拉特局本部">乌拉特局本部</td>
                <td title="备案材料终审">备案材料终审</td>
                <td title="提交审批决定">提交审批决定</td>
                <td title="建议予以备案.">建议予以备案.</td>
                <td>2019-04-24</td>
                <td></td>
            </tr>

            </tbody>

        </table>
    </div>
</div>


</body>
</html>