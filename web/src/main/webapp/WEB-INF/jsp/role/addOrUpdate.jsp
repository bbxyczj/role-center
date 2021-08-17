<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>
<h2 class="contentTitle"></h2>

<div class="pageContent">
    <form method="post" action="/role/doAddOrUpdate" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
            <div class="pageFormContent" layoutH="97">
                <input type="hidden" name="id" value="${role.id}"/>
                <p>
                    <label>名称：</label>
                    <input type="text" name="name" maxlength="50" class="required" value="${role.name}"/>
                </p>

                <dl class="nowrap">
                    <dt>说明：</dt>
                    <dd> <textarea name="note" class="required" maxlength="64" cols="80" rows="3">${role.note}</textarea></dd>
                </dl>

            </div>
            <div class="formBar">
                <ul>
                    <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
                    <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
                </ul>
            </div>
        </form>
</div>
