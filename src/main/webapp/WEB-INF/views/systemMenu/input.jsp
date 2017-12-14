<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#editForm").ajaxForm(function (data) {
                //在表单提交完成之后执行回调函数
                if (data.flag){
                    $.dialog({
                        title:"温馨提示",
                        content:"保存成功",
                        icon:"face-smile",
                        ok:function () {
                            window.location.href="/"+data.mark+"/query.do?parentId=${parentId}"
                        },
                        okValue:"朕知道了"
                    })
                }
            })
        })
    </script>
</head>
<body>
<form name="editForm" action="/systemMenu/saveOrUpdate.do" method="post" id="editForm">
    <input type="hidden" name="parent.id" value="${parentId}">
    <input type="hidden" name="id" value="${systemMenu.id}">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">系统菜单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">父级菜单</td>
                    <td class="ui_text_lt">
                        <input name="parent.name" value="${parentName}" class="ui_input_txt02" disabled/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">菜单名称</td>
                    <td class="ui_text_lt">
                        <input type="text" name="name" value="${systemMenu.name}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">菜单编码</td>
                    <td class="ui_text_lt">
                        <input type="text" name="sn" value="${systemMenu.sn}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">URL</td>
                    <td class="ui_text_lt">
                        <input type="text" name="url" value="${systemMenu.url}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>
