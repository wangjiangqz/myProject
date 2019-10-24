<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><%--
<html>
<head>
	<title>菜单导航</title>
	<meta name="decorator" content="blank"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".accordion-heading a").click(function(){
				$('.accordion-toggle i').removeClass('icon-chevron-down');
				$('.accordion-toggle i').addClass('icon-chevron-right');
				if(!$($(this).attr('href')).hasClass('in')){
					$(this).children('i').removeClass('icon-chevron-right');
					$(this).children('i').addClass('icon-chevron-down');
				}
			});
			$(".accordion-body a").click(function(){
				$("#menu-${param.parentId} li").removeClass("active");
				$("#menu-${param.parentId} li i").removeClass("glyphicon glyphicon-white");
				$(this).parent().addClass("active");
				$(this).children("i").addClass("glyphicon glyphicon-white");
				//loading('正在执行，请稍等...');
			});
			//$(".accordion-body a:first i").click();
			//$(".accordion-body li:first li:first a:first i").click();
		});
	</script>
</head>
<body> --%>
	<ul class="sidebar-menu"  data-widget="tree" id="menu-${param.parentId}">
		<c:set var="menuList" value="${fns:getMenuList()}"/>

		<c:set var="firstMenu1" value="true"/>


		<c:forEach items="${menuList}" var="menu" varStatus="idxStatus" >
			<c:if test="${menu.parent.id eq (not empty param.parentId ? param.parentId:1)&&menu.isShow eq '1'}">
		<li class="treeview ${not empty firstMenu1 && firstMenu1 ? 'active' : ''}">
		    	<a class="accordion-toggle menu" data-toggle="collapse" data-parent="#menu-${param.parentId}"
				   data-href="#collapse-${menu.id}" href="#collapse-${menu.id}"
				   title="${menu.remarks}"><i class="glyphicon glyphicon-${not empty menu.icon ? menu.icon : 'unchecked'}"></i>
					<span class="menuname">${menu.name}</span>
					<span class="pull-right-container">

                <i class="fa fa-angle-left pull-right"></i>
                </span></a>

			<ul class="treeview-menu">
				<c:set var="firstMenu" value="true"/>
				<c:forEach items="${menuList}" var="menu2">
					<c:set var="haveMenu3" value="false"/>
					<c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">
						<c:forEach items="${menuList}" var="menu3">
							<c:if test="${menu3.parent.id eq menu2.id&&menu3.isShow eq '1'}">
								<c:set var="haveMenu3" value="true"/>
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${not empty haveMenu3 && haveMenu3}">
								<li class=" secondMenu treeview ${not empty firstMenu1 && firstMenu1 && not empty firstMenu && firstMenu ? 'active' : ''}">
								<a class="accordion-toggle menu" data-toggle="collapse" data-parent="#menu2-${menu.id}"
								   data-href="#collapse-${menu2.id}" href="#collapse-${menu2.id}"
								   title="${menu.remarks}">
									<i class="glyphicon glyphicon-${not empty menu2.icon ? menu2.icon : 'unchecked'}"></i>${menu2.name}
									<span class="pull-right-container">
                      						<i class="fa fa-angle-left pull-right"></i>
                    						</span>
								</a>
									<c:set var="firstMenu2" value="true"/>
									<ul class="treeview-menu">
								<c:forEach items="${menuList}" var="menu3">

									<c:if test="${menu3.parent.id eq menu2.id&&menu3.isShow eq '1'}">
										<li class="lastMenu  ${not empty firstMenu2 && firstMenu2 && not empty firstMenu1 && firstMenu1 && not empty firstMenu && firstMenu? 'active' : ''}">
											<a data-href=".menu2-${menu3.id}" href="${fn:indexOf(menu3.href, '://') eq -1 ? ctx : ''}${not empty menu3.href ? menu3.href : '/404'}" target="${not empty menu3.target ? menu3.target : 'mainFrame'}" ><i class="glyphicon glyphicon-${not empty menu3.icon ? menu3.icon : 'unchecked'}"></i><span class="menuname">${menu3.name}</span></a>
										</li>
										<c:set var="firstMenu2" value="false"/>
									</c:if>


								</c:forEach>
									</ul>
							</li>
							</c:when>
							<c:otherwise>
								<li class="lastMenu ${not empty firstMenu && firstMenu ? 'active' : ''}">
									<a data-href=".menu2-${menu2.id}" href="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}" target="${not empty menu2.target ? menu2.target : 'mainFrame'}" ><i class="glyphicon glyphicon-${not empty menu2.icon ? menu2.icon : 'unchecked'}"></i><span class="menuname">${menu2.name}</span></a>
								</li>

							</c:otherwise>
						</c:choose>
						<c:set var="firstMenu" value="false"/>
					</c:if>
				</c:forEach>
			</ul>
		</li>
				<c:set var="firstMenu1" value="false"/>
	</c:if></c:forEach></ul><%--
</body>
</html> --%>