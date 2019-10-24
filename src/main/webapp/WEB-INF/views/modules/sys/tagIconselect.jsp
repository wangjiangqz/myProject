<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
	    <title>图标选择</title>
		<meta name="decorator" content="blank"/>
	    <style type="text/css">
	    	.page-header {clear:both;margin:0 20px;padding-top:20px;}
			.the-icons {padding:25px 10px 15px;list-style:none;}
			.the-icons li {float:left;width:30%;line-height:25px;margin:2px 5px;cursor:pointer;}
			.the-icons i {margin:1px 5px;font-size:16px;} .the-icons li:hover {background-color:#efefef;}
	        .the-icons li.active {background-color:#0088CC;color:#ffffff;}
	        .the-icons li:hover i{font-size:20px;}
	    </style>
	    <script type="text/javascript">
		    $(document).ready(function(){
		    	$("#icons li").click(function(){
		    		$("#icons li").removeClass("active");
		    		$("#icons li i").removeClass( "glyphicon-white");
		    		$(this).addClass("active");
		    		$(this).children("i").addClass( "glyphicon-white");
		    		$("#icon").val($(this).text());
		    	});
		    	$("#icons li").each(function(){
		    		if ($(this).text()=="${value}"){
		    			$(this).click();
		    		}
		    	});
		    	$("#icons li").dblclick(function(){
		    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
		    	});
		    });
	    </script>
	</head>
	
	<body>
		<input type="hidden" id="icon" value="${value}" />
		<div id="icons" style="overflow: auto; height: 322px;">
		    <h2 class="page-header"> Web 应用的图标</h2>
		    <ul class="the-icons">
		        <li><i class="glyphicon glyphicon-adjust"></i> icon-adjust</li>
		        <li><i class="glyphicon glyphicon-asterisk"></i> icon-asterisk</li>
		        <li><i class="glyphicon glyphicon-ban-circle"></i> icon-ban-circle</li>
		        
		        <%-- 
		        <li><i class="glyphicon glyphicon-bar-chart"></i> glyphicon glyphicon-bar-chart</li>
		        --%>
		        
		        <li><i class="glyphicon glyphicon-barcode"></i> icon-barcode</li>
		        
		        <%--
		        <li><i class="glyphicon glyphicon-beaker"></i> glyphicon glyphicon-beaker</li>
				<li><i class="glyphicon glyphicon-beer"></i> glyphicon glyphicon-beer</li>
				--%>
				
				<li><i class="glyphicon glyphicon-bell"></i> icon-bell</li>
				
				<%--
				<li><i class="glyphicon glyphicon-bell-alt"></i> glyphicon glyphicon-bell-alt</li>
				<li><i class="glyphicon glyphicon-bolt"></i> glyphicon glyphicon-bolt</li>
				--%>
				
				<li><i class="glyphicon glyphicon-book"></i> icon-book</li>
				<li><i class="glyphicon glyphicon-bookmark"></i> icon-bookmark</li>
				
				<%--
				<li><i class="glyphicon glyphicon-bookmark-empty"></i> glyphicon glyphicon-bookmark-empty</li>
				--%>
				
				<li><i class="glyphicon glyphicon-briefcase"></i> icon-briefcase</li>
				<li><i class="glyphicon glyphicon-bullhorn"></i> icon-bullhorn</li>
				<li><i class="glyphicon glyphicon-calendar"></i> icon-calendar</li>
				<li><i class="glyphicon glyphicon-camera"></i> icon-camera</li>
				
				<%--
				<li><i class="glyphicon glyphicon-camera-retro"></i> glyphicon glyphicon-camera-retro</li>
				--%>
				
				<li><i class="glyphicon glyphicon-certificate"></i> icon-certificate</li>
				<li><i class="glyphicon glyphicon-check"></i> icon-check</li>
				
				<%--
				<li><i class="glyphicon glyphicon-check-empty"></i> glyphicon glyphicon-check-empty</li>
				<li><i class="glyphicon glyphicon-circle"></i> glyphicon glyphicon-circle</li>
				<li><i class="glyphicon glyphicon-circle-blank"></i> glyphicon glyphicon-circle-blank</li>
				<li><i class="glyphicon glyphicon-cloud"></i> glyphicon glyphicon-cloud</li>
				<li><i class="glyphicon glyphicon-cloud-download"></i> glyphicon glyphicon-cloud-download</li>
				<li><i class="glyphicon glyphicon-cloud-upload"></i> glyphicon glyphicon-cloud-upload</li>
				<li><i class="glyphicon glyphicon-coffee"></i> glyphicon glyphicon-coffee</li>
				--%>
				
				<li><i class="glyphicon glyphicon-cog"></i> icon-cog</li>
				
				<%--
				<li><i class="glyphicon glyphicon-cogs"></i> glyphicon glyphicon-cogs</li>
				--%>
				
				<li><i class="glyphicon glyphicon-comment"></i> icon-comment</li>
				
				<%--
				<li><i class="glyphicon glyphicon-comment-alt"></i> glyphicon glyphicon-comment-alt</li>
				<li><i class="glyphicon glyphicon-comments"></i> glyphicon glyphicon-comments</li>
				<li><i class="glyphicon glyphicon-comments-alt"></i> glyphicon glyphicon-comments-alt</li>
				<li><i class="glyphicon glyphicon-credit-card"></i> glyphicon glyphicon-credit-card</li>
				<li><i class="glyphicon glyphicon-dashboard"></i> glyphicon glyphicon-dashboard</li>
				<li><i class="glyphicon glyphicon-desktop"></i> glyphicon glyphicon-desktop</li>
				--%>
				
				<li><i class="glyphicon glyphicon-download"></i> icon-download</li>
				<li><i class="glyphicon glyphicon-download-alt"></i> icon-download-alt</li>
				<li><i class="glyphicon glyphicon-edit"></i> icon-edit</li>
				<li><i class="glyphicon glyphicon-envelope"></i> icon-envelope</li>
				
				<%--
				<li><i class="glyphicon glyphicon-envelope-alt"></i> glyphicon glyphicon-envelope-alt</li>
				<li><i class="glyphicon glyphicon-exchange"></i> glyphicon glyphicon-exchange</li>
				--%>
				
				<li><i class="glyphicon glyphicon-exclamation-sign"></i> icon-exclamation-sign</li>
				
				<%--
				<li><i class="glyphicon glyphicon-external-link"></i> glyphicon glyphicon-external-link</li>
				--%>
				
				<li><i class="glyphicon glyphicon-eye-close"></i> icon-eye-close</li>
				<li><i class="glyphicon glyphicon-eye-open"></i> icon-eye-open</li>
				<li><i class="glyphicon glyphicon-facetime-video"></i> icon-facetime-video</li>
				
				<%--
				<li><i class="glyphicon glyphicon-fighter-jet"></i> glyphicon glyphicon-fighter-jet</li>
				--%>
				
				<li><i class="glyphicon glyphicon-film"></i> icon-film</li>
				<li><i class="glyphicon glyphicon-filter"></i> icon-filter</li>
				<li><i class="glyphicon glyphicon-fire"></i> icon-fire</li>
				<li><i class="glyphicon glyphicon-flag"></i> icon-flag</li>
				<li><i class="glyphicon glyphicon-folder-close"></i> icon-folder-close</li>
				<li><i class="glyphicon glyphicon-folder-open"></i> icon-folder-open</li>
				
				<%--
				<li><i class="glyphicon glyphicon-folder-close-alt"></i> glyphicon glyphicon-folder-close-alt</li>
				<li><i class="glyphicon glyphicon-folder-open-alt"></i> glyphicon glyphicon-folder-open-alt</li>
				<li><i class="glyphicon glyphicon-food"></i> glyphicon glyphicon-food</li>
				--%>
				
				<li><i class="glyphicon glyphicon-gift"></i> icon-gift</li>
				<li><i class="glyphicon glyphicon-glass"></i> icon-glass</li>
				<li><i class="glyphicon glyphicon-globe"></i> icon-globe</li>
				
				<%--
				<li><i class="glyphicon glyphicon-group"></i> glyphicon glyphicon-group</li>
				--%>
				
				<li><i class="glyphicon glyphicon-hdd"></i> icon-hdd</li>
				<li><i class="glyphicon glyphicon-headphones"></i> icon-headphones</li>
				<li><i class="glyphicon glyphicon-heart"></i> icon-heart</li>
				
				<%--
				<li><i class="glyphicon glyphicon-heart-empty"></i> glyphicon glyphicon-heart-empty</li>
				--%>
				
				<li><i class="glyphicon glyphicon-home"></i> icon-home</li>
				<li><i class="glyphicon glyphicon-inbox"></i> icon-inbox</li>
				<li><i class="glyphicon glyphicon-info-sign"></i> icon-info-sign</li>
				
				<%--
				<li><i class="glyphicon glyphicon-key"></i> glyphicon glyphicon-key</li>
				--%>
				
				<li><i class="glyphicon glyphicon-leaf"></i> icon-leaf</li>
				
				<%--
				<li><i class="glyphicon glyphicon-laptop"></i> glyphicon glyphicon-laptop</li>
				<li><i class="glyphicon glyphicon-legal"></i> glyphicon glyphicon-legal</li>
				<li><i class="glyphicon glyphicon-lemon"></i> glyphicon glyphicon-lemon</li>
				<li><i class="glyphicon glyphicon-lightbulb"></i> glyphicon glyphicon-lightbulb</li>
				--%>
				
				<li><i class="glyphicon glyphicon-lock"></i> icon-lock</li>
				
				<%--
				<li><i class="glyphicon glyphicon-unlock"></i> glyphicon glyphicon-unlock</li>
				<li><i class="glyphicon glyphicon-magic"></i> glyphicon glyphicon-magic</li>
				--%>
				
				<li><i class="glyphicon glyphicon-magnet"></i> icon-magnet</li>
				<li><i class="glyphicon glyphicon-map-marker"></i> icon-map-marker</li>
				<li><i class="glyphicon glyphicon-minus"></i> icon-minus</li>
				<li><i class="glyphicon glyphicon-minus-sign"></i> icon-minus-sign</li>
				
				<%--
				<li><i class="glyphicon glyphicon-mobile-phone"></i> glyphicon glyphicon-mobile-phone</li>
				<li><i class="glyphicon glyphicon-money"></i> glyphicon glyphicon-money</li>
				--%>
				
				<li><i class="glyphicon glyphicon-move"></i> icon-move</li>
				<li><i class="glyphicon glyphicon-music"></i> icon-music</li>
				<li><i class="glyphicon glyphicon-off"></i> icon-off</li>
				<li><i class="glyphicon glyphicon-ok"></i> icon-ok</li>
				<li><i class="glyphicon glyphicon-ok-circle"></i> icon-ok-circle</li>
				<li><i class="glyphicon glyphicon-ok-sign"></i> icon-ok-sign</li>
				<li><i class="glyphicon glyphicon-pencil"></i> icon-pencil</li>
				<li><i class="glyphicon glyphicon-picture"></i> icon-picture</li>
				<li><i class="glyphicon glyphicon-plane"></i> icon-plane</li>
				<li><i class="glyphicon glyphicon-plus"></i> icon-plus</li>
				<li><i class="glyphicon glyphicon-plus-sign"></i> icon-plus-sign</li>
				<li><i class="glyphicon glyphicon-print"></i> icon-print</li>
				
				<%--
				<li><i class="glyphicon glyphicon-pushpin"></i> glyphicon glyphicon-pushpin</li>
				--%>
				
				<li><i class="glyphicon glyphicon-qrcode"></i> icon-qrcode</li>
				<li><i class="glyphicon glyphicon-question-sign"></i> icon-question-sign</li>
				
				<%--
				<li><i class="glyphicon glyphicon-quote-left"></i> glyphicon glyphicon-quote-left</li>
				<li><i class="glyphicon glyphicon-quote-right"></i> glyphicon glyphicon-quote-right</li>
				--%>
				
				<li><i class="glyphicon glyphicon-random"></i> icon-random</li>
				<li><i class="glyphicon glyphicon-refresh"></i> icon-refresh</li>
				<li><i class="glyphicon glyphicon-remove"></i> icon-remove</li>
				<li><i class="glyphicon glyphicon-remove-circle"></i> icon-remove-circle</li>
				<li><i class="glyphicon glyphicon-remove-sign"></i> icon-remove-sign</li>
				
				<%--
				<li><i class="glyphicon glyphicon-reorder"></i> glyphicon glyphicon-reorder</li>
				<li><i class="glyphicon glyphicon-reply"></i> glyphicon glyphicon-reply</li>
				--%>
				
				<li><i class="glyphicon glyphicon-resize-horizontal"></i> icon-resize-horizontal</li>
				<li><i class="glyphicon glyphicon-resize-vertical"></i> icon-resize-vertical</li>
				<li><i class="glyphicon glyphicon-retweet"></i> icon-retweet</li>
				<li><i class="glyphicon glyphicon-road"></i> icon-road</li>
				
				<%--
				<li><i class="glyphicon glyphicon-rss"></i> glyphicon glyphicon-rss</li>
				--%>
				
				<li><i class="glyphicon glyphicon-screenshot"></i> icon-screenshot</li>
				<li><i class="glyphicon glyphicon-search"></i> icon-search</li>
				<li><i class="glyphicon glyphicon-share"></i> icon-share</li>
				<li><i class="glyphicon glyphicon-share-alt"></i> icon-share-alt</li>
				<li><i class="glyphicon glyphicon-shopping-cart"></i> icon-shopping-cart</li>
				<li><i class="glyphicon glyphicon-signal"></i> icon-signal</li>
				
				<%--
				<li><i class="glyphicon glyphicon-signin"></i> glyphicon glyphicon-signin</li>
				<li><i class="glyphicon glyphicon-signout"></i> glyphicon glyphicon-signout</li>
				<li><i class="glyphicon glyphicon-sitemap"></i> glyphicon glyphicon-sitemap</li>
				<li><i class="glyphicon glyphicon-sort"></i> glyphicon glyphicon-sort</li>
				<li><i class="glyphicon glyphicon-sort-down"></i> glyphicon glyphicon-sort-down</li>
				<li><i class="glyphicon glyphicon-sort-up"></i> glyphicon glyphicon-sort-up</li>
				<li><i class="glyphicon glyphicon-spinner"></i> glyphicon glyphicon-spinner</li>
				--%>
				
				<li><i class="glyphicon glyphicon-star"></i> icon-star</li>
				<li><i class="glyphicon glyphicon-star-empty"></i> icon-star-empty</li>
				
				<%--
				<li><i class="glyphicon glyphicon-star-half"></i> glyphicon glyphicon-star-half</li>
				<li><i class="glyphicon glyphicon-tablet"></i> glyphicon glyphicon-tablet</li>
				--%>
				
				<li><i class="glyphicon glyphicon-tag"></i> icon-tag</li>
				<li><i class="glyphicon glyphicon-tags"></i> icon-tags</li>
				<li><i class="glyphicon glyphicon-tasks"></i> icon-tasks</li>
				<li><i class="glyphicon glyphicon-thumbs-down"></i> icon-thumbs-down</li>
				<li><i class="glyphicon glyphicon-thumbs-up"></i> icon-thumbs-up</li>
				<li><i class="glyphicon glyphicon-time"></i> icon-time</li>
				<li><i class="glyphicon glyphicon-tint"></i> icon-tint</li>
				<li><i class="glyphicon glyphicon-trash"></i> icon-trash</li>
				
				<%--
				<li><i class="glyphicon glyphicon-trophy"></i> glyphicon glyphicon-trophy</li>
				<li><i class="glyphicon glyphicon-truck"></i> glyphicon glyphicon-truck</li>
				<li><i class="glyphicon glyphicon-umbrella"></i> glyphicon glyphicon-umbrella</li>
				--%>
				
				<li><i class="glyphicon glyphicon-upload"></i> icon-upload</li>
				
				<%--
				<li><i class="glyphicon glyphicon-upload-alt"></i> glyphicon glyphicon-upload-alt</li>
				--%>
				
				<li><i class="glyphicon glyphicon-user"></i> icon-user</li>
				
				<%--
				<li><i class="glyphicon glyphicon-user-md"></i> glyphicon glyphicon-user-md</li>
				--%>
				
				<li><i class="glyphicon glyphicon-volume-off"></i> icon-volume-off</li>
				<li><i class="glyphicon glyphicon-volume-down"></i> icon-volume-down</li>
				<li><i class="glyphicon glyphicon-volume-up"></i> icon-volume-up</li>
				<li><i class="glyphicon glyphicon-warning-sign"></i> icon-warning-sign</li>
				<li><i class="glyphicon glyphicon-wrench"></i> iconn-wrench</li>
				<li><i class="glyphicon glyphicon-zoom-in"></i> icon-zoom-in</li>
				<li><i class="glyphicon glyphicon-zoom-out"></i> icon-zoom-out</li>
		    </ul>
		  
		    <h2 class="page-header">文本编辑器图标</h2>
		  
		    <ul class="the-icons">
		        <li><i class="glyphicon glyphicon-file"></i> icon-file</li>
				
				<%-- 
				<li><i class="glyphicon glyphicon-file-alt"></i> glyphicon glyphicon-file-alt</li>
				<li><i class="glyphicon glyphicon-cut"></i> glyphicon glyphicon-cut</li>
				<li><i class="glyphicon glyphicon-copy"></i> glyphicon glyphicon-copy</li>
				<li><i class="glyphicon glyphicon-paste"></i> glyphicon glyphicon-paste</li>
				<li><i class="glyphicon glyphicon-save"></i> glyphicon glyphicon-save</li>
				<li><i class="glyphicon glyphicon-undo"></i> glyphicon glyphicon-undo</li>
				--%>
				
				<li><i class="glyphicon glyphicon-repeat"></i> icon-repeat</li>
				<li><i class="glyphicon glyphicon-text-height"></i> icon-text-height</li>
				<li><i class="glyphicon glyphicon-text-width"></i> icon-text-width</li>
				<li><i class="glyphicon glyphicon-align-left"></i> icon-align-left</li>
				<li><i class="glyphicon glyphicon-align-center"></i> icon-align-center</li>
				<li><i class="glyphicon glyphicon-align-right"></i> icon-align-right</li>
				<li><i class="glyphicon glyphicon-align-justify"></i> icon-align-justify</li>
				<li><i class="glyphicon glyphicon-indent-left"></i> icon-indent-left</li>
				<li><i class="glyphicon glyphicon-indent-right"></i> icon-indent-right</li>
				<li><i class="glyphicon glyphicon-font"></i> icon-font</li>
				<li><i class="glyphicon glyphicon-bold"></i> icon-bold</li>
				<li><i class="glyphicon glyphicon-italic"></i> icon-italic</li>
				
				<%-- 
				<li><i class="glyphicon glyphicon-strikethrough"></i> glyphicon glyphicon-strikethrough</li>
				<li><i class="glyphicon glyphicon-underline"></i> glyphicon glyphicon-underline</li>
				<li><i class="glyphicon glyphicon-link"></i> glyphicon glyphicon-link</li>
				<li><i class="glyphicon glyphicon-paper-clip"></i> glyphicon glyphicon-paper-clip</li>
				<li><i class="glyphicon glyphicon-columns"></i> glyphicon glyphicon-columns</li>
				<li><i class="glyphicon glyphicon-table"></i> glyphicon glyphicon-table</li>
				--%>
				
				<li><i class="glyphicon glyphicon-th-large"></i> icon-th-large</li>
				<li><i class="glyphicon glyphicon-th"></i> icon-th</li>
				<li><i class="glyphicon glyphicon-th-list"></i> icon-th-list</li>
				<li><i class="glyphicon glyphicon-list"></i> icon-list</li>
				
				<%-- 
				<li><i class="glyphicon glyphicon-list-ol"></i> glyphicon glyphicon-list-ol</li>
				<li><i class="glyphicon glyphicon-list-ul"></i> glyphicon glyphicon-list-ul</li>
				--%>
				
				<li><i class="glyphicon glyphicon-list-alt"></i> icon-list-alt</li>
		    </ul>
		
		    <h2 class="page-header">指示方向的图标</h2>
		  
		    <ul class="the-icons">
				
				<%-- 
				<li><i class="glyphicon glyphicon-angle-left"></i> glyphicon glyphicon-angle-left</li>
				<li><i class="glyphicon glyphicon-angle-right"></i> glyphicon glyphicon-angle-right</li>
				<li><i class="glyphicon glyphicon-angle-up"></i> glyphicon glyphicon-angle-up</li>
				<li><i class="glyphicon glyphicon-angle-down"></i> glyphicon glyphicon-angle-down</li>
				--%>
				
				<li><i class="glyphicon glyphicon-arrow-down"></i> icon-arrow-down</li>
				<li><i class="glyphicon glyphicon-arrow-left"></i> icon-arrow-left</li>
				<li><i class="glyphicon glyphicon-arrow-right"></i> icon-arrow-right</li>
				<li><i class="glyphicon glyphicon-arrow-up"></i> icon-arrow-up</li>
				
				<%-- 
				<li><i class="glyphicon glyphicon-caret-down"></i> glyphicon glyphicon-caret-down</li>
				<li><i class="glyphicon glyphicon-caret-left"></i> glyphicon glyphicon-caret-left</li>
				<li><i class="glyphicon glyphicon-caret-right"></i> glyphicon glyphicon-caret-right</li>
				<li><i class="glyphicon glyphicon-caret-up"></i> glyphicon glyphicon-caret-up</li>
				--%>
				
				<li><i class="glyphicon glyphicon-chevron-down"></i> icon-chevron-down</li>
				<li><i class="glyphicon glyphicon-chevron-left"></i> icon-chevron-left</li>
				<li><i class="glyphicon glyphicon-chevron-right"></i> icon-chevron-right</li>
				<li><i class="glyphicon glyphicon-chevron-up"></i> icon-chevron-up</li>
				
				<li><i class="glyphicon glyphicon-circle-arrow-down"></i> icon-circle-arrow-down</li>
				<li><i class="glyphicon glyphicon-circle-arrow-left"></i> icon-circle-arrow-left</li>
				<li><i class="glyphicon glyphicon-circle-arrow-right"></i> icon-circle-arrow-right</li>
				<li><i class="glyphicon glyphicon-circle-arrow-up"></i> icon-circle-arrow-up</li>
				
				<%-- 
				<li><i class="glyphicon glyphicon-double-angle-left"></i> glyphicon glyphicon-double-angle-left</li>
				<li><i class="glyphicon glyphicon-double-angle-right"></i> glyphicon glyphicon-double-angle-right</li>
				<li><i class="glyphicon glyphicon-double-angle-up"></i> glyphicon glyphicon-double-angle-up</li>
				<li><i class="glyphicon glyphicon-double-angle-down"></i> glyphicon glyphicon-double-angle-down</li>
				--%>
				
				<li><i class="glyphicon glyphicon-hand-down"></i> icon-hand-down</li>
				<li><i class="glyphicon glyphicon-hand-left"></i> icon-hand-left</li>
				<li><i class="glyphicon glyphicon-hand-right"></i> icon-hand-right</li>
				<li><i class="glyphicon glyphicon-hand-up"></i> icon-hand-up</li>
				
				<%-- 
				<li><i class="glyphicon glyphicon-circle"></i> glyphicon glyphicon-circle</li>
				<li><i class="glyphicon glyphicon-circle-blank"></i> glyphicon glyphicon-circle-blank</li>
				--%>
				
		    </ul>
		
		    <h2 class="page-header">视频播放器图标</h2>
		  
		    <ul class="the-icons">
				<li><i class="glyphicon glyphicon-play-circle"></i> icon-play-circle</li>
				<li><i class="glyphicon glyphicon-play"></i> icon-play</li>
				<li><i class="glyphicon glyphicon-pause"></i> icon-pause</li>
				<li><i class="glyphicon glyphicon-stop"></i> icon-stop</li>
				
				<li><i class="glyphicon glyphicon-step-backward"></i> icon-step-backward</li>
				<li><i class="glyphicon glyphicon-fast-backward"></i> icon-fast-backward</li>
				<li><i class="glyphicon glyphicon-backward"></i> icon-backward</li>
				<li><i class="glyphicon glyphicon-forward"></i> icon-forward</li>
				
				<li><i class="glyphicon glyphicon-fast-forward"></i> icon-fast-forward</li>
				<li><i class="glyphicon glyphicon-step-forward"></i> icon-step-forward</li>
				<li><i class="glyphicon glyphicon-eject"></i> icon-eject</li>
				
				<li><i class="glyphicon glyphicon-fullscreen"></i> icon-fullscreen</li>
				<li><i class="glyphicon glyphicon-resize-full"></i> icon-resize-full</li>
				<li><i class="glyphicon glyphicon-resize-small"></i> icon-resize-small</li>
		    </ul>
		
		    <%-- 
		    <h2 class="page-header">SNS图标</h2>
		  
		    <ul class="the-icons">
				<li><i class="glyphicon glyphicon-phone"></i> glyphicon glyphicon-phone</li>
				<li><i class="glyphicon glyphicon-phone-sign"></i> glyphicon glyphicon-phone-sign</li>
				<li><i class="glyphicon glyphicon-facebook"></i> glyphicon glyphicon-facebook</li>
				<li><i class="glyphicon glyphicon-facebook-sign"></i> glyphicon glyphicon-facebook-sign</li>
				<li><i class="glyphicon glyphicon-twitter"></i> glyphicon glyphicon-twitter</li>
				<li><i class="glyphicon glyphicon-twitter-sign"></i> glyphicon glyphicon-twitter-sign</li>
				<li><i class="glyphicon glyphicon-github"></i> glyphicon glyphicon-github</li>
				<li><i class="glyphicon glyphicon-github-alt"></i> glyphicon glyphicon-github-alt</li>
				<li><i class="glyphicon glyphicon-github-sign"></i> glyphicon glyphicon-github-sign</li>
				<li><i class="glyphicon glyphicon-linkedin"></i> glyphicon glyphicon-linkedin</li>
				<li><i class="glyphicon glyphicon-linkedin-sign"></i> glyphicon glyphicon-linkedin-sign</li>
				<li><i class="glyphicon glyphicon-pinterest"></i> glyphicon glyphicon-pinterest</li>
				<li><i class="glyphicon glyphicon-pinterest-sign"></i> glyphicon glyphicon-pinterest-sign</li>
				<li><i class="glyphicon glyphicon-google-plus"></i> glyphicon glyphicon-google-plus</li>
				<li><i class="glyphicon glyphicon-google-plus-sign"></i> glyphicon glyphicon-google-plus-sign</li>
				<li><i class="glyphicon glyphicon-sign-blank"></i> glyphicon glyphicon-sign-blank</li>
		    </ul>
		  
		    <h2 class="page-header">医疗图标</h2>
		  
		    <ul class="the-icons">
				<li><i class="glyphicon glyphicon-ambulance"></i> glyphicon glyphicon-ambulance</li>
				<li><i class="glyphicon glyphicon-beaker"></i> glyphicon glyphicon-beaker</li>
				<li><i class="glyphicon glyphicon-h-sign"></i> glyphicon glyphicon-h-sign</li>
				<li><i class="glyphicon glyphicon-hospital"></i> glyphicon glyphicon-hospital</li>
				<li><i class="glyphicon glyphicon-medkit"></i> glyphicon glyphicon-medkit</li>
				<li><i class="glyphicon glyphicon-plus-sign-alt"></i> glyphicon glyphicon-plus-sign-alt</li>
				<li><i class="glyphicon glyphicon-stethoscope"></i> glyphicon glyphicon-stethoscope</li>
				<li><i class="glyphicon glyphicon-user-md"></i> glyphicon glyphicon-user-md</li>
		    </ul>
			
		    --%>
		    
		    <h4 class="page-header">&nbsp;</h4>
		</div>
	</body>