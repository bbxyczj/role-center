<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>
<form id="pagerForm" method="post" action="/user/list">
    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}"/>
    <input type="hidden" name="pageSize" value="${pageInfo.pageSize}" />
    <input type="hidden"  name="cardNum" value="${model.cardNum}">
    <input type="hidden"  name="nickname" value="${model.nickname}">
    <input type="hidden"  name="email" value="${model.email}">
</form>

<div class="pageHeader">
    <form id="deviceListForm" onsubmit="return navTabSearch(this);" action="/user/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>卡号：</label>
                    <input type="text"  name="cardNum" value="${model.cardNum}">
                </li>
                <li>
                    <label>用户名：</label>
                    <input type="text"  name="nickname" value="${model.nickname}">
                </li>
                <li>
                    <label>邮箱：</label>
                    <input type="text"  name="email" value="${model.email}">
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
            <li><a class="add" href="/user/addOrUpdate?id=0" target="dialog"><span>添加</span></a></li>
            <li class="line">line</li>
            <li><a class="edit" href="/user/addOrUpdate?id={list_item_id}" target="dialog" warn="请选择一个用户"><span>修改</span></a></li>
            <li class="line">line</li>
            <li><a class="delete" href="/user/delete?id={list_item_id}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个用户"><span>删除</span></a></li>
            <li class="line">line</li>
            <li>
                <a class="edit" href="/role/addRoleToUserList?userId={list_item_id}" target="dialog" rel="addRoleToUser"  width="800" height="500" warn="请选择一个用户"><span>为用户管理角色</span></a>
            </li>
        </ul>
    </div>

    <div id="w_list_print">
        <table class="list" width="98%" targetType="navTab" asc="asc" desc="desc" layoutH="116">
            <thead>
            <tr>
                <th width="3%">序号</th>
                <th width="10%">卡号</th>
                <th width="12%">用户名</th>
                <th width="12%">邮箱</th>
                <th width="5%">状态</th>
                <th width="15%">创建时间</th>
                <th width="15%">更新时间</th>
                <th width="15%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="bean" varStatus="status">
                <tr target="list_item_id" rel="${bean.id}">
                    <td>${status.index+1}</td>
                    <td>${bean.cardNum}</td>
                    <td>${bean.nickname}</td>
                    <td>${bean.email}</td>
                    <td>
                        <c:choose>
                            <c:when test="${bean.status==1}">
                                正常
                            </c:when>
                            <c:otherwise>
                                禁用
                            </c:otherwise>
                        </c:choose>
                    </td>
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
                        <a class="button" href="/role/relationRoleList?userId=${bean.id}" target="dialog" rel="relationRoleList" title="${bean.nickname}的关联角色" width="800" height="500"><span>关联角色</span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <jsp:include page="../page.jsp"/>

</div>









