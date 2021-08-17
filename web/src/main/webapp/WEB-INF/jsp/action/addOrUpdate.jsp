<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>
<h2 class="contentTitle"></h2>

<div class="pageContent">
    <form method="post" action="/action/doAddOrUpdate" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
            <div class="pageFormContent" layoutH="97">
                <input type="hidden" name="id" value="${action.id}"/>
                <input type="hidden" name="pid" value="${action.id}"/>
                <dl>
                    <dt>所属项目:</dt>
                    <dd style="width: 200px;">


                        <input type="hidden" class="required" name="itemName" value="${action.itemName}"/>

                        <select class="combox" name="itemId" ref="w_combox_parent" onchange="setItemName(this)">
                            <option value="0" selected="selected">请选择</option>
                            <c:forEach var="item" items="${itemList}">
                                <c:choose>
                                    <c:when test="${action.itemId == item.id||myItem.id==item.id}">
                                        <option value="${item.id}" selected="selected">${item.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${item.id}">${item.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </dd>
                </dl>
                <dl>
                    <dt>父级id：</dt>
                    <dd style="width: 200px;">
                        <input id="actionPid" class="required" name="action.id" value="${action.pid}" type="text" readonly/>
                        <a id="goActionTree" class="btnLook" href="item/treeActionBack?itemId=${myItem.id}" lookupGroup="action">查询父id</a>
                    </dd>
                </dl>

                <dl>
                    <dt>权限名称：</dt>
                    <dd style="width: 200px;">
                        <input name="name" class="required" type="text" value="${action.name}"/>
                    </dd>
                </dl>

                <dl>
                    <dt>权限地址：</dt>
                    <dd style="width: 200px;">
                        <input name="url" class="required" type="text" value="${action.url}"/>
                    </dd>
                </dl>
                <dl>
                    <dt>权限顺序(desc)：</dt>
                    <dd style="width: 200px;">
                        <input name="sort" class="required" type="text" value="${action.sort}"/>
                    </dd>
                </dl>
                <dl>
                    <dt>是否是导航页：</dt>
                    <dd style="width: 200px;">
                        <select class="combox" name="isIndex" class="required">
                            <option value="2" <c:if test="${action.isIndex==2}">selected</c:if>>否</option>
                            <option value="1" <c:if test="${action.isIndex==1}">selected</c:if>>是</option>
                        </select>
                    </dd>
                </dl>

            </div>
            <div class="formBar">
                <ul>
                    <li><div class="buttonActive"><div class="buttonContent"><button onclick="setPid()" type="submit">提交</button></div></div></li>
                    <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
                </ul>
            </div>
        </form>
</div>
<script>

   $(function () {
       var itemName;
       if(${myItem!=null}){
           itemName="${myItem.name}";
       }
       if(${action!=null}){
           itemName="${action.name}";
       }
       $("input[name=itemName]").val(itemName);

   })

   function setPid() {
       var pid= $("#actionPid").val();
       $("input[name=pid]").val(pid);
   }

    function setItemName(select) {
        var sItem=$("select[name=itemId]").find("option:selected");
       var itemName= sItem.text();
       if(itemName === "请选择"){
           $("input[name=itemName]").val("");
       }else {
           $("input[name=itemName]").val(itemName);
           $("#goActionTree").attr("href","item/treeActionBack?itemId="+sItem.val())
       }
    }
</script>