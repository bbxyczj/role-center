<%@ page pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<div class="panelBar">
    <div class="pages">
        <span>显示</span>
        <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
            <option value="10" <c:if test="${pageInfo.pageSize==10}">selected</c:if>>10</option>
            <option value="20" <c:if test="${pageInfo.pageSize==20}">selected</c:if>>20</option>
            <option value="30" <c:if test="${pageInfo.pageSize==30}">selected</c:if>>30</option>
            <option value="50" <c:if test="${pageInfo.pageSize==50}">selected</c:if>>50</option>
            <option value="100" <c:if test="${pageInfo.pageSize==100}">selected</c:if>>100</option>
        </select>
        <span>条，共${pageInfo.total}条</span>
    </div>
    <div class="pagination" targetType="navTab" totalCount="${pageInfo.total}"  numPerPage="${pageInfo.pageSize}" pageNumShown="10" currentPage="${pageInfo.pageNum}"></div>
</div>
