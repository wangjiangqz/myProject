<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;height:100%}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	<div id="content" class="row">
		<div id="left" class="col-xs-2">
			<div class="box box-primary"  >
				<div class="box-header with-border">
					<h3 class="box-title">组织机构<i class="glyphicon glyphicon-refresh pull-right" onclick="refreshTree();"></i></h3>
				</div>
				<div id="ztree" class="ztree" ></div>
			</div>
			<%--<div class="accordion-heading">--%>
		    	<%--<a class="accordion-toggle"></a>--%>
		    <%--</div>--%>

		</div>
		<%--<div id="openClose" class="col-md-1">&nbsp;</div>--%>
		<div id="right" class="col-xs-10">
			<iframe id="officeContent" src="${ctx}/sys/user/list" width="100%" height="91%" frameborder="0"></iframe>
		</div>
	</div>
	<script type="text/javascript">
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
					var id = treeNode.id == '0' ? '' :treeNode.id;
					$('#officeContent').attr("src","${ctx}/sys/user/list?office.id="+id+"&office.name="+treeNode.name);
				}
			}
		};
		
		function refreshTree(){
			$.getJSON("${ctx}/sys/office/treeData",function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(false);
			});
		}
		refreshTree();
		 
		var leftWidth = 180; // 左侧窗口大小
		var htmlObj = $("html,body"), mainObj = $("#main");
		var frameObj = $("iframe");
		function wSize(){
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":"hidden", "overflow-y":"hidden"});
			mainObj.css("width","auto");
			frameObj.height(strs[0] - 5);
            $("#ztree").height(strs[0] - 52);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			// $("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			// $(".ztree").width(leftWidth - 10).height(frameObj.height() - 46);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>