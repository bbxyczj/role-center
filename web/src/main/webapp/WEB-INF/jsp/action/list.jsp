<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>
<form id="pagerForm" method="post" action="/action/list">
    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}"/>
    <input type="hidden" name="numPerPage" value="${pageInfo.pageSize}" />
    <input type="hidden"  name="itemName" value="${model.itemName}">
    <input type="hidden"  name="name" value="${model.name}">
    <input type="hidden"  name="pid" value="${model.pid}">
    <input type="hidden"  name="url" value="${model.url}">




</form>

<div class="pageHeader">
    <form id="deviceListForm" onsubmit="return navTabSearch(this);" action="/action/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>项目名称：</label>
                    <input type="text"  name="itemName" value="${model.itemName}">
                </li>
                <li>
                    <label>权限名称：</label>
                    <input type="text"  name="name" value="${model.name}">
                </li>
                <li>
                    <label>父id：</label>
                    <input type="text"  name="pid" value="${model.pid}">
                </li>
                <li>
                    <label>url：</label>
                    <input type="text"  name="url" value="${model.url}">
                </li>
            </ul>

            <div class="subBar">
                <ul>
                    <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
                </ul>
            </div>
        </div>
    </form>
</div>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="/action/addOrUpdate?id=0" target="dialog" rel="addAction" title="添加权限" ><span>添加</span></a></li>
            <li class="line">line</li>
            <li><a class="edit" href="/action/addOrUpdate?id={list_item_id}" target="dialog" warn="请选择信息"><span>修改</span></a></li>
            <li class="line">line</li>
            <li><a class="delete" href="/action/delete?id={list_item_id}" target="ajaxTodo" title="确定要删除吗？" warn="请选择信息"><span>删除</span></a></li>
            <li class="line">line</li>
            <%--<li>--%>
                <%--<a class="edit" href="/role/addRoleToUserList.do?userId={list_item_id}" target="dialog" rel="addRoleToUser"  width="800" height="500" warn="请选择一个用户"><span>为用户添加角色</span></a>--%>
            <%--</li>--%>
        </ul>
    </div>

    <div id="w_list_print">
        <table class="list" width="98%" targetType="navTab" asc="asc" desc="desc" layoutH="116">
            <thead>
            <tr>
                <th width="3%">id</th>
                <th width="10%">项目名称</th>
                <th width="12%">权限名称</th>
                <th width="12%">权限地址</th>
                <th width="15%">创建时间</th>
                <th width="15%">更新时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="bean" varStatus="status">
                <tr target="list_item_id" rel="${bean.id}">
                    <td>${bean.id}</td>
                    <td>${bean.itemName}</td>
                    <td>${bean.name}</td>
                    <td>${bean.url}</td>
                    <td>
                        <jsp:useBean id="created" class="java.util.Date" />
                        <jsp:setProperty name="created" property="time" value="${bean.created}"/>
                        <fmt:formatDate  value="${created}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <jsp:useBean id="updated" class="java.util.Date" />
                        <jsp:setProperty name="updated" property="time" value="${bean.updated}"/>
                        <fmt:formatDate  value="${updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <jsp:include page="../page.jsp"/>

</div>









