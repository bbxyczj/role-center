<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li>
            <a class="delete" href="/role/deleteRelationAction?actionId={list_item_id}&roleId=${role.id}" target="ajaxTodo" title="确定要删除吗？" warn="请选择信息"><span>删除</span></a>
            </li>
        <li>
            <a class="add" href="/action/addActionToRoleList?roleId=${role.id}"  target="dialog"  rel="addRoleToUser"  width="800" height="500"><span>添加</span></a>
        </li>
        </ul>
    </div>

    <div id="w_list_print">
        <table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="116">
            <thead>
            <tr>
                <%--<th><input type="checkbox" group="actionIds" class="checkboxCtrl"></th>--%>
                <th width="3%">序号</th>
                <th width="20%">所属项目</th>
                <th width="20%">名称</th>
                <th width="45%">地址</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="bean" varStatus="status">
                <tr target="list_item_id" rel="${bean.id}">
                    <%--<td>--%>
                        <%--<input name="actionIds" value="${bean.id}" type="checkbox">--%>
                    <%--</td>--%>
                    <td>${status.index+1}</td>
                    <td>${bean.itemName}</td>
                    <td>${bean.name}</td>
                    <td>${bean.url}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
    <form  id="deleteActionForRoleForm" method="get" action="" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
    </form>
</div>







