<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common.jsp" %>
<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>权限管理中心</title>
	<link href="/resource/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
	<link rel="shortcut icon" href="/resource/favicon.ico"/>
	<link href="/resource/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="/resource/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="/resource/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="/resource/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
	<script src="/resource/dwz/js/jquery-2.1.4.js" type="text/javascript"></script>
	<script src="/resource/dwz/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="/resource/dwz/js/jquery.validate.js" type="text/javascript"></script>
	<script src="/resource/dwz/js/dwz.min.js" type="text/javascript"></script>
	<script src="/resource/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
</head>

<style type="text/css">
	.login_body {
		background: url("/resource/img/bg_body_ie.png") repeat-x #FFFFFF;
		height: 100%;
	}
</style>

<body class="login_body">

<%--<img src="/resource/img/login.jpg" style="position:absolute; width:100%; height:100%; left:0; top:0; z-index:-1;"/>--%>
<div id="login">
	<div id="login_header">
		<h1 class="login_logo">
			<strong style="font-size: 30px">权限管理平台</strong>
		</h1>

		<%--<div class="login_headerContent">--%>
			<%--<div class="navList">--%>
				<%--<ul>--%>
					<%--<li><a href="#">设为首页</a></li>--%>
					<%--<li><a href="http://bbs.dwzjs.com">反馈</a></li>--%>
					<%--<li><a href="doc/dwz-user-guide.pdf" target="_blank">帮助</a></li>--%>
				<%--</ul>--%>
			<%--</div>--%>
			<%--&lt;%&ndash;<h2 class="login_title"><img src="/resource/img/login.jpg" /></h2>&ndash;%&gt;--%>
		<%--</div>--%>
	</div>
	<div id="login_content">
		<div class="loginForm">
			<form method="post" action="/userLogin">
				<p>
					<label>账号：</label>
					<input type="text" name="cardNum"  onclick="clearErrorMessage()" size="30" class="login_input" />
				</p>
				<p>
					<label>密码：</label>
					<input type="password" name="password" onclick="clearErrorMessage()" size="30" class="login_input" />
				</p>
				<p>
					<label>验证码：</label>
					<input class="code" type="text" name="code" onclick="clearErrorMessage()" size="5" />
					<span><img src="/ImageMaskServlet" alt="" width="75" height="24" /></span>
				</p>
				<div class="login_bar">
					<button class="sub" type="submit" value="" />
				</div>
			</form>
		</div>
		<p style="padding-left: 5%">
			<span id="errorMessageInfo" style="color: #CC0000; font-size: 13px">${errorMessage}</span>
		</p>
		<%--<div class="login_banner"><img src="/resource/dwz/themes/default/images/login_banner.jpg" /></div>--%>
	</div>
	<%--<div id="login_footer">--%>
		<%--Copyright &copy; 2009 www.dwzjs.com Inc. All Rights Reserved.--%>
	<%--</div>--%>
</div>
</body>
<script>
	function clearErrorMessage() {
		$("#errorMessageInfo").html("");
    }
</script>
</html>