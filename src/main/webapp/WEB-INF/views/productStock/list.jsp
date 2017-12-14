<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery.artDialog.js?skin=blue"></script>
    <title>叩丁狼教育PSS（演示版）-即时库存表管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript" src="/js/common_js/commonJs.js"></script>
</head>
<body>
<form id="searchForm" action="/productStock/query.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">

                        商品名称或编码
                        <input type="text" class="ui_input_txt02" name="keywords" id="keywords"
                             value="${qo.keywords}"  />
                        所有仓库
                        <select class="ui_select01" name="depotId">
                            <option value="-1"> 所有仓库</option>
                            <c:forEach items="${depots}" var="depot">
                                <option value="${depot.id}" ${qo.depotId==depot.id?'selected':''}>${depot.name}</option>
                            </c:forEach>
                        </select>
                        货品品牌
                        <select class="ui_select01" name="brandId">
                            <option value="-1"> 所有品牌</option>
                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.id}" ${qo.brandId==brand.id?'selected':''}>${brand.name}</option>
                            </c:forEach>
                        </select>
                        库存阈值
                        <input type="text" class="ui_input_txt02" name="maxNumber" id="maxNumber"
                               value="${qo.maxNumber}"  />
                    </div>
                    <div id="box_bottom">
                        <input type="submit" value="查询" class="ui_input_btn01 btn_serch"/>
                    </div>

                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th>仓库</th>
                        <th>商品编码</th>
                        <th>商品名称</th>
                        <th>品牌</th>
                        <th>库存数量</th>
                        <th>成本</th>
                        <th>库存小计</th>
                    </tr>
                    <tbody>
                    <c:forEach items="${result.data}" var="d">
                        <tr>
                            <td>${d.depot.name}</td>
                            <td>${d.product.sn}</td>
                            <td>${d.product.name}</td>
                            <td>${d.product.brandName}</td>
                            <td>${d.storeNumber}</td>
                            <td>${d.price}</td>
                            <td>${d.amount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%--分页操作--%>
            <jsp:include page="/WEB-INF/views/commonPage/commonPage.jsp"></jsp:include>
        </div>
    </div>
</form>
</body>
</html>

