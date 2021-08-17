<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>
<div class="pageContent">
    <form method="post" action="/action/deleteItemAction" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
    <div class="pageFormContent" layoutH="58">
        <input type="hidden" name="ids">
        <ul id="actionTree" class="tree treeFolder treeCheck expand" oncheck="updateSelect">

        </ul>

    </div>

        <div class="formBar">
            <ul>
                <c:if test="${actions!='null'}">
                    <li><div class="buttonActive"><div class="buttonContent"><button type="submit">删除</button></div></div></li>
                </c:if>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
            </ul>
        </div>
    </form>
</div>

<script>
    $(function () {
        console.info("开始")
        $("#actionTree").html(initRelationAction(${actions}));
    })

    function initRelationAction(actions) {
        console.info(actions);
        if(actions==null){
            return "<li><a  tname=\"action\" tvalue=\"0\" >无</a></li>";
        }
        var html='';
        for(var i=0;i<actions.length;i++){
            var action= actions[i];
            html+="<li><a tname='"+action.name+"' tvalue='"+action.id+"' >"+action.name+":"+action.url+"</a>";
            if(action.actionList!=null){
               html+="<ul>"+initRelationAction(action.actionList)+"</ul>";
            }
            html+="</li>"
        }
        return html;
    }

    function updateSelect(json){
        console.log(json);
        var ids=new Array();
        var actions=$("input[name=ids]").val();
        if(actions!=null&&actions!=undefined){
            $(actions).each(function (a) {
                ids.push(a);
            })
        }
        $(json.items).each(function(i){
            if(json.checked){
                ids.push(this.value);
            }else {
                ids.pop(this.value);
            }
        });
        console.log(ids);
        $("input[name=ids]").val(ids);
    }

</script>








