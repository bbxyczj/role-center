<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>首页</title>
    <link rel="shortcut icon" href="/resource/favicon.ico" />
    <link href="/resource/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="/resource/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="/resource/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
    <link href="/resource/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
    <script src="/resource/dwz/js/jquery-2.1.4.js" type="text/javascript"></script>
    <script src="/resource/dwz/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="/resource/dwz/js/jquery.validate.js" type="text/javascript"></script>
    <script src="/resource/dwz/js/dwz.min.js" type="text/javascript"></script>
    <script src="/resource/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>


    <script type="text/javascript">
        $(function(){
            $("#parentMenu").html('${indexMenu}');


            DWZ.init("../../resource/dwz/dwz.frag.xml", {
                loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
                statusCode:{ok:200, error:300, timeout:301}, //【可选】
                pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
                keys: {statusCode:"statusCode", message:"message"}, //【可选】
                ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
                debug:true,	// 调试模式 【true|false】
                callback:function(){
                    initEnv();
                    $("#themeList").theme({themeBase:"../../resource/dwz/themes"}); // themeBase 相对于index页面的主题base路径
                }
            });
        })

    </script>
</head>

<body>

<div id="layout">



    <div id="header">
        <div class="headerNav">
            <%--<a class="logo" href="http://j-ui.com">标志</a>--%>
            <ul class="nav">
                <li>你好 <span style="font-size:20px;color: #CC0000">${loginUser.nickname}</span></li>
                <li><a href="/logout">退出</a></li>
            </ul>
        </div>
    </div>

    <div id="leftside">
        <div id="sidebar_s">
            <div class="collapse">
                <div class="toggleCollapse"><div></div></div>
            </div>
        </div>
        <div id="sidebar">
            <div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

            <div id="parentMenu" class="accordion" fillSpace="sidebar">

                <%--<div class="accordionHeader">--%>
                    <%--<h2><span>Folder</span>用户管理</h2></div>--%>
                <%--<div class="accordionContent">--%>
                    <%--<ul class="tree treeFolder">--%>
                        <%--<li><a href="/user/list" target="navTab" rel="/user/list">用户列表</a></li>--%>
                        <%--<li><a href="/role/list" target="navTab" rel="/role/list">角色列表</a></li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
                <%--<div class="accordionHeader"><h2><span>Folder</span>权限管理</h2></div>--%>
                <%--<div class="accordionContent">--%>
                    <%--<ul class="tree treeFolder">--%>
                        <%--<li><a href="/item/list" target="navTab" rel="/item/list">项目列表</a></li>--%>
                        <%--<li><a href="/action/list" target="navTab" rel="/action/list">权限列表</a></li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
                <%--<div class="accordionHeader"><h2><span>Folder</span>配置管理</h2></div>--%>
                <%--<div class="accordionContent">--%>
                    <%--<ul class="tree treeFolder">--%>
                        <%--<li><a href="/config/list" target="navTab" rel="/config/list">配置列表</a></li>--%>
                    <%--</ul>--%>
                <%--</div>--%>


            </div>
        </div>
    </div>
    <div id="container">
        <div id="navTab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                    <ul class="navTab-tab">
                        <li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                <div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                <div class="tabsMore">more</div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">我的主页</a></li>
            </ul>
            <div class="navTab-panel tabsPageContent layoutBox">
                <div class="page unitBox">
                    <div id="divCurPage"></div>
                </div>
            </div>
        </div>
    </div>

</div>

<div id="footer">Copyright &copy; 2021 <a href="###" target="dialog">jack chen</a> 才华有限公司</div>

</body>
</html>
