<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<style type="text/css">

	.nav_menu-item ul{display: none;}
</style>
<link rel="Stylesheet" href="${ctxStatic}/sysmenu/css/nav_menu.css" />
<script type="text/javascript" src="${ctxStatic}/sysmenu/js/nav_menu.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<div class="left_menu">
	<c:set var="menuList" value="${fns:getMenuList()}"/><c:set var="firstMenu" value="true"/>
	<ul>
		<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
			<c:if test="${menu.parent.id eq (not empty param.parentId ? param.parentId:1)&&menu.isShow eq '1'}">
				<li style="position: relative;">
					<img sicon="/base/tBaFile/showImage?id=${menu.siconUrl }" licon="/base/tBaFile/showImage?id=${menu.liconUrl }" src="${ctx}/base/tBaFile/showImage?id=${menu.liconUrl }" style="position: absolute;top: 15px;left: 16px;max-width: 40px;" />
					<a  id="show_${menu.id}" title="${menu.remarks}"><span style="position: absolute;top: 25px;left: 0px;width: 100%;visibility: hidden;">${menu.name}</span></a></li>
			</c:if>
		</c:forEach>
	</ul>
</div>
<div  class="right_menu nav_menu">
	<ul id="parent_ul">
		<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
			<c:if test="${menu.parent.id eq (not empty param.parentId ? param.parentId:1)&&menu.isShow eq '1'}">
				<li class="nav_menu-item first_level" style="display:none;">
					<a href="javascript:;" id="show_${menu.id}_true"><span>${menu.name}</span></a>
					<ul>
						<c:forEach items="${menuList}" var="menu2">
							<c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">
								<li class="nav_menu-item second_level"></i><a href="${not empty menu2.href ? menu2.href : 'javascript:;'}" target="${not empty menu2.target ? menu2.target : 'mainFrame'}" ><span>${menu2.name}</span></a>
									<ul>
										<c:forEach items="${menuList}" var="menu3">
											<c:if test="${menu3.parent.id eq menu2.id&&menu3.isShow eq '1'}">
												<li class="third_level"><a class="true_a" style="padding-left:30px;"  href="${fn:indexOf(menu3.href, '://') eq -1 ? ctx : ''}${not empty menu3.href ? menu3.href : 'javascript:;'}" target="${not empty menu3.target ? menu3.target : 'mainFrame'}" ><span>${menu3.name}</span></a>
												</li>
											</c:if>
										</c:forEach>

									</ul>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</div>