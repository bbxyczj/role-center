<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>
<h2 class="contentTitle"></h2>

<div class="pageContent">
    <form method="post" action="/user/doAddOrUpdate" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
            <div class="pageFormContent" layoutH="97">
                <input type="hidden" name="id" value="${user.id}"/>
                <p>
                    <label>卡号：</label>
                    <input type="text" name="cardNum" maxlength="50"  class="required" value="${user.cardNum}"/>
                </p>
                <p>
                    <label>密码：</label>
                    <input type="text" name="password" maxlength="50"  class="required" value="${user.password}"/>
                </p>
                <p>
                    <label>邮箱：</label>
                    <input type="text" name="email" maxlength="50" class="required" value="${user.email}"/>
                </p>
                <p>
                    <label>用户名：</label>
                    <input type="text" name="nickname" maxlength="50" class="required" value="${user.nickname}"/>
                </p>
                <p>
                    <label>状态：</label>
                    <select class="combox" name="status" class="required">
                        <option value="1" <c:if test="${user.status==1}">selected</c:if>>正常</option>
                        <option value="2" <c:if test="${user.status==2}">selected</c:if>>禁用</option>
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
