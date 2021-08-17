<%@ page pageEncoding="utf-8"%>
<div class="pageContent">
    <form method="post" action="/config/doAdd" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
        <div class="pageFormContent" layoutH="57">
            <dl>
                <dt>keyword：</dt>
                <dd><input name="keyword"  class="required" remote="/config/checkKeyword" type="text" size="32"   alt="请输入keyword"/></dd>
            </dl>
            <dl class="nowrap">
                <dt>value：</dt>
                <dd><textarea name="value" class="required" maxlength="512" cols="80" rows="3"/></dd>
            </dl>

            <dl class="nowrap">
                <dt>说明：</dt>
                <dd> <textarea name="comments" class="required" maxlength="64" cols="80" rows="3"></textarea></dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form>
</div>




