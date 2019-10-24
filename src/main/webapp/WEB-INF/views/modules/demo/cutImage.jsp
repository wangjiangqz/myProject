<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>imgAreaSelect图片截图</title>
	<%--<meta name="decorator" content="default"/>--%>
	<script type="text/javascript" src="${ctxStatic}/imageAreaSelect/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/imageAreaSelect/scripts/jquery.imgareaselect.pack.js"></script>
	<link href="${ctxStatic}/imageAreaSelect/css/imgareaselect-default.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
        $(function () {

            function preview(img, selection) {
                if (!selection.width || !selection.height)
                    return;
                var scaleX = 100 / (selection.width || 1);
                var scaleY = 100 / (selection.height || 1);

                var realHeight = 0;
                var realWidth = 0;

                //动态获取要截取的图片的像素
                var image = new Image();
                image.src = $("#photo").attr("src");
                if (image.complete) {
                    realWidth = image.width;
                    realHeight = image.height;
                }else {
                    image.onload = function() {
                        realWidth = image.width;
                        realHeight = image.height;
                    };
                }

				//截取的区域预览
                $('#preview img').css({
                    width: Math.round(scaleX * realWidth) + 'px',
                    height: Math.round(scaleY * realHeight) + 'px',
                    marginLeft: '-' + Math.round(scaleX * selection.x1) + 'px',
                    marginTop: '-' + Math.round(scaleY * selection.y1) + 'px'
                });
                //完成后对表单进行赋值
                $('input[name="x1"]').val(selection.x1);
                $('input[name="y1"]').val(selection.y1);
                $('input[name="x2"]').val(selection.x2);
                $('input[name="y2"]').val(selection.y2);
                $('input[name="path"]').val($('img#photo').attr("src"));

                $('#x1').val(selection.x1);
                $('#y1').val(selection.y1);
                $('#x2').val(selection.x2);
                $('#y2').val(selection.y2);
                $('#w').val(selection.width);
                $('#h').val(selection.height);

                $('#btn').show();
            }

            //对要截取的图片进行初始化
            $('#photo').imgAreaSelect({
                aspectRatio: '1:1',     //设置区域的比例
                handles: true,          //设置区域的四角显示
                fadeSpeed: 200,         //动画效果时间
                onSelectChange: preview //选择完成后调用的方法
            });

            //表单提交事件
            $('#btn').click(function () {
                if ($('#x1').val() == '') {
                    alert('还没有进行截图');
                    return;
                }
                $.ajax({
                    type: 'POST',
                    url: '${ctx}/servlet/CutImageServlet?operate=2',
                    data: $('#dataForm').serialize(),
                    dataType: 'json',
                    cache: false,
                    success: function (data) {
                        if (data.success) {
                            /*var imgLi = $("<li></li>");
                            var newImg = $('<div" class=\"img_box ' + '">' +
                                '<img src="/' + data.imgs + '" />').appendTo(imgLi);
                            $("#show_list ul").append(imgLi);*/
                            alert("截取成功，图片保存在D:/upload目录下");
                        }
                    },
					error: function (data) {
                        //alert(111);
					}
                });
            });
        });

	</script>
</head>
<body>
<h2 style="text-align: center">截图演示</h2>
<div id="cutDiv" >
	<div class="container demo">
		<div style="float: left; width: 50%;">
			<p class="instructions">
				点击这里，选择截图区域，<font color="red">必须为等比例图片</font>
			</p>

			<div class="frame" style="margin: 0 0.3em; width: 300px; height: 300px;">
				<img id="photo" src="${ctxStatic}/imageAreaSelect/ferret.jpg">
			</div>
		</div>
		<div style="float: left; width: 50%;">
			<p style="font-size: 110%; font-weight: bold; padding-left: 0.1em;">
				选择区域预览
			</p>

			<div class="frame" style="margin: 0 1em; width: 100px; height: 100px;">
				<div id="preview" style="width: 100px; height: 100px; overflow: hidden;">
					<img src="${ctxStatic}/imageAreaSelect/ferret.jpg" >
				</div>
			</div>
			<%--表单数据--%>
			<form id="dataForm" style="position: absolute;top:25%;left:60%;" class="form-horizontal">
				<input type="hidden" name="x1" value="" class="form-control"/>
				　　<input type="hidden" name="y1" value="" class="form-control"/>
				　　<input type="hidden" name="x2" value="" class="form-control"/>
				　　<input type="hidden" name="y2" value="" class="form-control"/>
				<input type="hidden" name="path" class="form-control"/>
				<input type="button" id="btn" style="display: none;" value="进行截图" class="form-control"/>
			</form>

			<table style="margin-top: 1em;">
				<thead>
				<tr>
					<th colspan="2" style="font-size: 110%; font-weight: bold; text-align: left; padding-left: 0.1em;">
						坐标
					</th>
					<th colspan="2" style="font-size: 110%; font-weight: bold; text-align: left; padding-left: 0.1em;">
						尺寸
					</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td style="width: 10%;"><b>X<sub>1</sub>:</b></td>
					<td style="width: 30%;"><input type="text" id="x1" value="-" class="form-control"></td>
					<td style="width: 20%;"><b>Width:</b></td>
					<td><input type="text" value="-" id="w" readonly="readonly" class="form-control"></td>
				</tr>
				<tr>
					<td><b>Y<sub>1</sub>:</b></td>
					<td><input type="text" id="y1" value="-" readonly="readonly" class="form-control"></td>
					<td><b>Height:</b></td>
					<td><input type="text" id="h" value="-" readonly="readonly" class="form-control"></td>
				</tr>
				<tr>
					<td><b>X<sub>2</sub>:</b></td>
					<td><input type="text" id="x2" value="-" readonly="readonly" class="form-control"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><b>Y<sub>2</sub>:</b></td>
					<td><input type="text" id="y2" value="-" readonly="readonly" class="form-control"></td>
					<td></td>
					<td></td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div style="margin: 0 auto;width:960px;">
		<h3>所得截图</h3>

		<div id="show_list">
			<ul>

			</ul>
		</div>
	</div>
</div>
</body>
</html>