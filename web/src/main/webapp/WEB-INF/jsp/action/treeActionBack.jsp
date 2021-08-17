<%@ page pageEncoding="utf-8"%>
<%@include  file="/WEB-INF/jsp/common.jsp" %>
<div class="pageContent">
    <div class="pageFormContent" layoutH="58">
        <ul id="treeActionBack" class="tree expand">
        </ul>
    </div>

    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
        </ul>
    </div>
</div>

<script>
    $(function () {
        console.info("开始");
        var actions=${actions};
        var html="<li><a href=\"javascript:\" onclick=\"$.bringBack({id:'0'})\">${item.name}</a>";
        if(actions!='null'&&actions!=null){
            html+="<ul>"+initTreeAction(actions)+"</ul>";
        }
        html+="</li>";
        $("#treeActionBack").html(html);
    })


    function initTreeAction(actions) {
        console.info(actions)
        var h='';
        for(var i=0;i<actions.length;i++){
           var action= actions[i];
            h+="<li><a href=\"javascript:\" onclick=$.bringBack({id:"+action.id+"})>"+action.name+"</a>";
            if(action.actionList!=null){
                h+="<ul>"+initTreeAction(action.actionList)+"</ul>";
            }
            h+="</li>"
        }
        return h;
    }
</script>