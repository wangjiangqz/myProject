<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业首页</title>
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

	</script>
	<style>
		.procedure{
			width:70%;
		}
        .xtjs img{
            padding: 0 0.3rem
        }
		/*body > section > div:nth-child(1) > div{*/
			/*width:19%;margin: 0 3%;*/
		/*}*/
		/*body > section > div{*/
			/*padding: 0 2%;*/
		/*}*/
	</style>
</head>
<body>

   <!-- Main content -->
   <section class="content" style="padding: 0px">
	   <%--<div class="row" style="padding-bottom: 10px">--%>



	   <%--</div>--%>
	   <!-- Small boxes (Stat box) -->
	   <div class="row" >
		   <div class="col-lg-3 col-xs-3">
			   <!-- small box -->
			   <div class="small-box" style="background-color: #ff8761;color: white">
				   <div class="inner">
					   <h3>150</h3>

					   <p>待办事项</p>
				   </div>
				   <div class="icon">
					   <i class="ion ion-bag"></i>
				   </div>
				   <a href="#" class="small-box-footer">点击查看 <i class="fa fa-arrow-circle-right"></i></a>
			   </div>
		   </div>
		   <!-- ./col -->
		   <div class="col-lg-3 col-xs-3">
			   <!-- small box -->
			   <div class="small-box" style="background-color: #57bdde;color: white">
				   <div class="inner">
					   <h3>53<sup style="font-size: 20px">%</sup></h3>

					   <p>未读系统消息</p>
				   </div>
				   <div class="icon">
					   <i class="ion ion-stats-bars"></i>
				   </div>
				   <a href="#" class="small-box-footer">点击查看 <i class="fa fa-arrow-circle-right"></i></a>
			   </div>
		   </div>
		   <!-- ./col -->
		   <div class="col-lg-3 col-xs-3">
			   <!-- small box -->
			   <div class="small-box" style="background-color: #b198dc;color: white">
				   <div class="inner">
					   <h3>44</h3>

					   <p>个有效自我声明证书</p>
				   </div>
				   <div class="icon">
					   <i class="ion ion-person-add"></i>
				   </div>
				   <a href="#" class="small-box-footer">点击查看 <i class="fa fa-arrow-circle-right"></i></a>
			   </div>
		   </div>
		   <!-- ./col -->
		   <div class="col-lg-3 col-xs-3">
			   <!-- small box -->
			   <div class="small-box" style="background-color: #6dc7be;color: white">
				   <div class="inner">
					   <h3>65</h3>

					   <p>待办事项</p>
				   </div>
				   <div class="icon">
					   <i class="ion ion-pie-graph"></i>
				   </div>
				   <a href="#" class="small-box-footer">点击查看 <i class="fa fa-arrow-circle-right"></i></a>
			   </div>
		   </div>
		   <!-- ./col -->
	   </div>
	   <!-- /.row -->
	   <div class="row" >
		   <div class="col-lg-12 col-xs-12">
			   <div class="box box-primary">


			   <div class="box-header with-border">
				   <h3 class="box-title" style="color: black">系统简介及使用说明</h3>

			   </div>

					   <%--<div  style="text-align: right;float: left;padding: 0 15px">--%>
					   <%--<img src="${ctxStatic}/images/!.png" style="height:100%">--%>
					   <%--</div>--%>
					   <%--<div >--%>

					   <%--</div>--%>
				   <div style="font-weight: bold;color: #dd4b39;text-indent:2.2em;padding:0 10px;">
					   声明：本系统内所有信息仅用于强制性产品认证自我声明符合性信息报送与监管，自我声明符合性信息由企业及相关实验室提供，
					   数据的真实性、准确性由企业和实验室负责，如有疑问请联系相关企业和实验室。如需投诉或举报请联系国家市场监管总局。
				   </div>

			   <!-- /.box-header -->
			   <!-- form start -->
				   <div class="box-body xtjs" style="padding: 0 135px">
					   <div style="text-align: left;width: 100%;height:240px;
							   background-image: url('${ctxStatic}/images/lgoin20170906/procedure.png');background-size: 100% 100%">
					   </div>

				   </div>

			   </div>
		   </div>
	   </div>

	   <div class="row" >
		   <div class="col-lg-12 col-xs-12">
			   <div class="box box-primary">
				   <div class="box-header with-border">
					   <h3 class="box-title" style="color: black">相关模板下载</h3>
				   </div>
				   <!-- /.box-header -->
				   <!-- form start -->
				   <div class="box-body">
					   <table class="table table-striped table-bordered table-condensed" >
						   <thead>
						   <tr>
							   <th colspan="1" style="font-family:方正小标宋简体;font-size:15px;width:5%">
								   <strong>序号</strong>
							   </th>
							   <th colspan="1" style="font-family:方正小标宋简体;font-size:15px;width:40%">
								   <strong>附件名称</strong>
							   </th>
							   <th colspan="1" style="font-family:方正小标宋简体;font-size:15px;width:40%">
								   <strong>附件详情</strong>
							   </th>

							   <th colspan="1" style="font-family:方正小标宋简体;font-size:15px;width:15%">
								   <strong>操作</strong>
							   </th>
						   </tr>
						   </thead>
						   <c:forEach items="${tBaAtts}" var="tBaAtt"  varStatus="i" begin="0" step="1">
							   <tr>
								   <td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">
										   ${i.index+1}
								   </td>
								   <td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">
										   ${tBaAtt.name}
								   </td>
								   <td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">
										   ${tBaAtt.remarks}
								   </td>

								   <td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">
									   <c:if test="${tBaAtt.fileId ne null }">
										   <a href="${ctx}/base/tBaFile/downFile?id=${tBaAtt.fileId}">下载</a>
									   </c:if>
								   </td>
							   </tr>
						   </c:forEach>


                           <%--<tr>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                       <%--1--%>
                               <%--</td>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                   <%--系统使用说明--%>
                               <%--</td>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                   <%--系统使用说明--%>
                               <%--</td>--%>

                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>

                                       <%--<a href="${ctx}/base/tBaFile/downFile?id=${tBaAtt.fileId}">下载</a>--%>

                               <%--</td>--%>
                           <%--</tr>--%>
                           <%--<tr>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                   <%--2--%>
                               <%--</td>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                   <%--系统使用说明--%>
                               <%--</td>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                   <%--系统使用说明--%>
                               <%--</td>--%>

                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>

                                   <%--<a href="${ctx}/base/tBaFile/downFile?id=${tBaAtt.fileId}">下载</a>--%>

                               <%--</td>--%>
                           <%--</tr>--%>
                           <%--<tr>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                  <%--3--%>
                               <%--</td>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                   <%--系统使用说明--%>
                               <%--</td>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                   <%--系统使用说明--%>
                               <%--</td>--%>

                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>

                                   <%--<a href="${ctx}/base/tBaFile/downFile?id=${tBaAtt.fileId}">下载</a>--%>

                               <%--</td>--%>
                           <%--</tr>--%>
                           <%--<tr>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                   <%--4--%>
                               <%--</td>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                   <%--系统使用说明--%>
                               <%--</td>--%>
                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>
                                   <%--系统使用说明--%>
                               <%--</td>--%>

                               <%--<td colspan="1" style="font-family:方正小标宋简体;font-size:15px;">--%>

                                   <%--<a href="${ctx}/base/tBaFile/downFile?id=${tBaAtt.fileId}">下载</a>--%>

                               <%--</td>--%>
                           <%--</tr>--%>
					   </table>
				   </div>
				   </div>
		   </div>
	   </div>

   </section>
   <!-- /.content -->
</body>
</html>
