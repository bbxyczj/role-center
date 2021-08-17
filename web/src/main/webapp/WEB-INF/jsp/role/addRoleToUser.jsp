<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>

<div class="pageHeader">
    <form id="deviceListForm" onsubmit="return dialogSearch(this);" action="/role/addRoleToUserList" method="post">

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
    <div id="w_list_print">
        <table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="166">
            <thead>
            <tr>
                <th width="3%">
                    <input type="checkbox" group="ids" class="checkboxCtrl">
                </th>
                <th width="15%">名称</th>
                <th width="35%">说明</th>
                <th width="20%">创建时间</th>
                <th width="20%">更新时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="bean" varStatus="status">
                <tr target="list_item_id" rel="${bean.id}">
                    <td>
                        <input name="ids" value="${bean.id}" type="checkbox">
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
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="formBar">
        <ul>
            <li><div class="buttonActive"><div class="buttonContent"><button onclick="addRoleToUser()">提交</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
        </ul>
    </div>
    <form  id="addRoleToUserForm" method="post" action="" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
    </form>
</div>
<script>
    function addRoleToUser() {
        var ids=[];
        $("input:checkbox[name=ids]:checked").each(function () {
            ids.push($(this).val())

        });
        var form= $("#addRoleToUserForm");

        form.attr("action","/user/doAddRole?ids="+ids+"&userId="+${userId});
        form.submit();
        $.pdialog.closeCurrent();
    }

</script>









