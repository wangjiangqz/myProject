<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<head>
	<title>图片配置</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div class="box box-primary" style="overflow-x: auto">
	<div class="box-header with-border">

	</div>
	<div class="box-body">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-striped ui">
		<thead>
		<tr>
			<th>图片位置</th>
			<th>图片预览</th>
			<th>操作</th>
		</thead>
		<tbody>
		<tr>
			<td style="vertical-align:middle" width="10%">预览图片<span style="color: red">(png)</span></td>
			<td  width="33%"><img id="previewImg"  style="height: 100px"></td>
			<td style="vertical-align:middle" width="5%"><sys:uploadPic name="preview" id="preview"  imgUrl="/theme${id}"
																		buttonName="图片上传" kind="single" filetype="png" filesize="100000">
			</sys:uploadPic></td>
		</tr>
			<tr>
				<td style="vertical-align:middle" width="10%">登录页LOGO<span style="color: red">(png)</span></td>
				<td  width="33%"><img id="logo1Img"  style="height: 50px"></td>
				<td style="vertical-align:middle" width="5%"><sys:uploadPic name="logo1" id="logo1"  imgUrl="/theme${id}"
																			 buttonName="图片上传" kind="single" filetype="png" filesize="100000">
				</sys:uploadPic></td>
			</tr>
			<tr>
				<td style="vertical-align:middle" width="10%">登录页背景左上角<span style="color: red">(png)</span></td>
				<td  width="33%"><img id="backgroundimage1Img"  style="height: 100px"></td>
				<td style="vertical-align:middle" width="5%"><sys:uploadPic name="backgroundimage1" id="backgroundimage1"  imgUrl="/theme${id}"
																			buttonName="图片上传" kind="single" filetype="png" filesize="100000">
				</sys:uploadPic></td>
			</tr>
			<tr>
				<td style="vertical-align:middle" width="10%">登录页背景右上角<span style="color: red">(png)</span></td>
				<td  width="33%"><img id="backgroundimage2Img"  style="height: 100px"></td>
				<td style="vertical-align:middle" width="5%"><sys:uploadPic name="backgroundimage2" id="backgroundimage2"  imgUrl="/theme${id}"
																			buttonName="图片上传" kind="single" filetype="png" filesize="100000">
				</sys:uploadPic></td>
			</tr>
			<tr>
				<td style="vertical-align:middle" width="10%">登录页背景左下角<span style="color: red">(png)</span></td>
				<td  width="33%"><img id="backgroundimage3Img"  style="height: 100px"></td>
				<td style="vertical-align:middle" width="5%"><sys:uploadPic name="backgroundimage3" id="backgroundimage3"  imgUrl="/theme${id}"
																			buttonName="图片上传" kind="single" filetype="png" filesize="100000">
				</sys:uploadPic></td>
			</tr>
			<tr>
				<td style="vertical-align:middle" width="10%">登录页背景右下角<span style="color: red">(png)</span></td>
				<td  width="33%"><img id="backgroundimage4Img"  style="height: 100px"></td>
				<td style="vertical-align:middle" width="5%"><sys:uploadPic name="backgroundimage4" id="backgroundimage4"  imgUrl="/theme${id}"
																			buttonName="图片上传" kind="single" filetype="png" filesize="100000">
				</sys:uploadPic></td>
			</tr>
			<tr>
				<td style="vertical-align:middle" width="10%">登录页电话图标<span style="color: red">(png)</span></td>
				<td  width="33%"><img id="icon5Img"  style="height: 80px"></td>
				<td style="vertical-align:middle" width="5%"><sys:uploadPic name="icon5" id="icon5"  imgUrl="/theme${id}"
																			buttonName="图片上传" kind="single" filetype="png" filesize="100000">
				</sys:uploadPic></td>
			</tr>
			<tr>
				<td style="vertical-align:middle" width="10%">登录页QQ图标<span style="color: red">(png)</span></td>
				<td  width="33%"><img id="icon6Img"  style="height: 80px"></td>
				<td style="vertical-align:middle" width="5%"><sys:uploadPic name="icon6" id="icon6"  imgUrl="/theme${id}"
																			buttonName="图片上传" kind="single" filetype="png" filesize="100000">
				</sys:uploadPic></td>
			</tr>
			<%--<tr>--%>
				<%--<td style="vertical-align:middle" width="10%">登录页二维码图标<span style="color: red">(png)</span></td>--%>
				<%--<td  width="33%"><img id="icon7Img"  style="height: 80px"></td>--%>
				<%--<td style="vertical-align:middle" width="5%"><sys:uploadPic name="icon7" id="icon7"  imgUrl="/theme${id}"--%>
																			<%--buttonName="图片上传" kind="single" filetype="png" filesize="100000">--%>
				<%--</sys:uploadPic></td>--%>
			<%--</tr>--%>

			<tr>
				<td style="vertical-align:middle" width="10%">首页LOGO<span style="color: red">(png)</span></td>
				<td  width="33%"><img id="logoImg"  style="height: 80px"></td>
				<td style="vertical-align:middle" width="5%"><sys:uploadPic name="logo" id="logo"  imgUrl="/theme${id}"
																			buttonName="图片上传" kind="single" filetype="png" filesize="100000">
				</sys:uploadPic></td>
			</tr>
			<tr>
				<td style="vertical-align:middle" width="10%">首页抬头<span style="color: red">(png)</span></td>
				<td  width="33%"><img id="titletopImg"  style="width: 700px"></td>
				<td style="vertical-align:middle" width="5%"><sys:uploadPic name="titletop" id="titletop"  imgUrl="/theme${id}"
																			buttonName="图片上传" kind="single" filetype="png" filesize="100000">
				</sys:uploadPic></td>
			</tr>
			<tr>
				<td style="vertical-align:middle" width="10%">主页流程<span style="color: red">(png)</span></td>
				<td  width="33%"><img id="procedureImg"  style="width: 700px"></td>
				<td style="vertical-align:middle" width="5%"><sys:uploadPic name="procedure" id="procedure"  imgUrl="/theme${id}"
																			buttonName="图片上传" kind="single" filetype="png" filesize="100000">
				</sys:uploadPic></td>
			</tr>
		</tbody>
	</table>
	</div>
</div>


</body>
</html>