<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>
<form id="pagerForm" method="post" action="/role/list">
    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}"/>
    <input type="hidden" name="pageSize" value="${pageInfo.pageSize}" />
    <input type="hidden"  name="name" value="${model.name}">

</form>

<div class="pageHeader">
    <form id="deviceListForm" onsubmit="return navTabSearch(this);" action="/role/list" method="post">

        <input type="hidden"  name="userId" value="${userId}">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>名称：</label>
                    <input type="text"  name="name" value="${model.name}">
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
            <li><a class="add" href="/role/addOrUpdate?id=0" target="dialog" rel="addOrUpdateRole"><span>添加</span></a></li>
            <li class="line">line</li>
            <li><a class="edit" href="/role/addOrUpdate?id={list_item_id}" target="dialog" rel="addOrUpdateRole" warn="请选择一个角色"><span>修改</span></a></li>
            <li class="line">line</li>
            <li><a class="delete" href="/role/delete?id={list_item_id}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个角色"><span>删除</span></a></li>
            <li class="line">line</li>
            <li>
                <a class="edit" href="/action/addActionToRoleList?roleId={list_item_id}" target="dialog" rel="addActionToRole"  width="800" height="500" warn="请选择一个角色"><span>为角色添加权限</span></a>
            </li>
        </ul>
    </div>

    <div id="w_list_print">
        <table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="116">
            <thead>
            <tr>
                <th width="3%">
                   序号
                </th>
                <th width="15%">名称</th>
                <th width="15%">说明</th>
                <th width="20%">创建时间</th>
                <th width="20%">更新时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="bean" varStatus="status">
                <tr target="list_item_id" rel="${bean.id}">
                    <td>
                       ${status.index+1}
                    </td>
                    <td>${bean.name}</td>
                    <td>${bean.note}</td>
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
                    <td>
                        <a class="button" href="/action/relationActionList?roleId=${bean.id}" target="dialog" rel="relationRoleList" title="${bean.name}的关联权限"  width="800" height="500"><span>关联权限</span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <jsp:include page="../page.jsp"/>


</div>









