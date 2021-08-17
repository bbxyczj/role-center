<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
        <li><a class="delete" href="/user/deleteRelationRole?roleId={list_item_id}&userId=${user.id}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个角色"><span>删除</span></a></li>
        <li>
            <a class="add" href="/role/addRoleToUserList?userId=${user.id}" target="dialog" rel="addRoleToUser"  width="800" height="500"><span>添加</span></a>
        </li>
        </ul>
    </div>

    <div id="w_list_print">
        <table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="116">
            <thead>
            <tr>
                <th width="3%">序号</th>
                <th width="20%">名称</th>
                <th width="45%">说明</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="bean" varStatus="status">
                <tr target="list_item_id" rel="${bean.id}">
                    <td>${status.index+1}</td>
                    <td>${bean.name}</td>
                    <td>${bean.note}</td>
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

</div>









