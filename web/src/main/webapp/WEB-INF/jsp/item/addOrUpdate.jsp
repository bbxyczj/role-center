<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>
<h2 class="contentTitle"></h2>

<div class="pageContent">
    <form method="post" action="/item/doAddOrUpdate" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
            <div class="pageFormContent" layoutH="97">
                <input type="hidden" name="id" value="${item.id}"/>
                <p>
                    <label>名称：</label>
                    <input type="text" name="name" maxlength="50"  class="required" value="${item.name}"/>
                </p>
                <c:if test="${item!=null}">
                    <p>
                        <label>编号：</label>
                        <input type="text" name="no" readonly alt="不可修改" maxlength="50" class="required" value="${item.no}"/>
                    </p>
                </c:if>
                <p>
                    <label>状态：</label>
                    <select class="combox" name="status" class="required">
                        <option value="1" <c:if test="${item.status==1}">selected</c:if>>正常</option>
                        <option value="2" <c:if test="${item.status==2}">selected</c:if>>禁用</option>
                    </select>
                </p>


            </div>
            <div class="formBar">
                <ul>
                    <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
                    <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
                </ul>
            </div>
        </form>
</div>
