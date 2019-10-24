]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<head>
	<title>图片配置</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div class="box box-primary" >
	<div class="box-header with-border">

	</div>
	<div class="box-body">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-striped ui">
		<thead>
		<tr>
			<th>主题名称</th>
			<th>图片预览</th>
			<th>操作</th>
		</thead>
		<tbody>
		<tr>
			<td style="vertical-align:middle" width="10%">蓝色天空</td>
			<td>
				<img   style="height: 260px" src="${ctx}/base/tBaFile/showImage?id=preview&folderPath=/theme0&fileName=preview&fileType=.png">
			</td>
			<td style="vertical-align:middle" width="175px">
				<a href="${ctx}/sys/picConfig/form?id=0" class="btn btn-primary">修改</a>
				<a href="javacript:void(0);" class="btn btn-success"
				   onclick="switchOn(0)">切换主题</a>
			</td>
		</tr>
		<tr>
			<td style="vertical-align:middle" width="10%">黑色时尚</td>
			<td>
				<img   style="height: 260px" src="${ctx}/base/tBaFile/showImage?id=preview&folderPath=/theme1&fileName=preview&fileType=.png">
			</td>
			<td style="vertical-align:middle" width="175px">
				<a href="${ctx}/sys/picConfig/form?id=1" class="btn btn-primary">修改</a>
				<a href="javacript:void(0);" class="btn btn-success"
				   onclick="switchOn(1)">切换主题</a>
			</td>
		</tr>
		<tr>
			<td style="vertical-align:middle" width="10%">绿色宁静</td>
			<td>
				<img   style="height: 260px" src="${ctx}/base/tBaFile/showImage?id=preview&folderPath=/theme2&fileName=preview&fileType=.png">
			</td>
			<td style="vertical-align:middle" width="175px">
				<a href="${ctx}/sys/picConfig/form?id=2" class="btn btn-primary">修改</a>
				<a href="javacript:void(0);" class="btn btn-success"
				   onclick="switchOn(2)">切换主题</a>
			</td>
		</tr>
		</tbody>
	</table>
	</div>
</div>




		<script>



			/**
			 * Store a new settings in the browser
			 *
			 * @param String name Name of the setting
			 * @param String val Value of the setting
			 * @returns void
			 */
			function store(name, val) {
				if (typeof (Storage) !== 'undefined') {
					localStorage.setItem(name, val)
				} else {
					window.alert('请检查您的浏览器!')
				}
			}

			function switchOn(id,themeName) {
				top.$.jBox.confirm("确认要切换此主题吗",'系统提示',function(v,h,f){
					if(v=='ok'){
						loading('正在执行，请稍等...');
						$.ajax({
							url : '${ctx}/sys/picConfig/switchOn?id='+id,
							async : true,
							success : function(data){
								if(data=='1'){
									top.$.jBox.closeTip();
									showTip("切换成功！");
									// store('skin', themeName);
									parent.location.reload();

								}
							}
						})
					}
				})
			}

		</script>

</body>
</html>