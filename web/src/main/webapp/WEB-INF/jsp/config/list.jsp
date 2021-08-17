<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>
<form id="pagerForm" method="post" action="/config/list">
    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}"/>
    <input type="hidden" name="pageSize" value="${pageInfo.pageSize}" />
    <input type="hidden" name="keyword" value="${model.keyword}">
</form>

<div class="pageHeader">
    <form id="deviceListForm" onsubmit="return navTabSearch(this);" action="/config/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>keyword：</label>
                    <input type="text"  name="keyword" value="${model.keyword}">
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
            <li><a class="add" href="/config/add" target="dialog"><span>添加</span></a></li>
            <li class="line">line</li>
            <li><a class="edit" href="/config/edit?keyword={keyword}" target="dialog" warn="请选择信息"><span>修改</span></a></li>
            <li class="line">line</li>
            <li><a class="delete" href="/config/remove?keyword={keyword}" target="ajaxTodo" title="确定要删除吗？" warn="请选择信息"><span>删除</span></a></li>
        </ul>
    </div>

    <div id="w_list_print">
        <table class="list" width="98%" targetType="navTab" asc="asc" desc="desc" layoutH="116">
            <thead>
            <tr>
                <th width="3%">序号</th>
                <th width="20%">keyword</th>
                <th width="30%">value</th>
                <th width="47%">说明</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="bean" varStatus="status">
                <tr target="keyword" rel="${bean.keyword}">
                    <td>${status.index+1}</td>
                    <td>${bean.keyword}</td>
                    <td>${bean.value}</td>
                    <td>${bean.comments}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <jsp:include page="../page.jsp"/>

</div>









