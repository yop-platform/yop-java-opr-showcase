<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../fore/include/header.jsp" %>
<head>
    <link href= "${ctxPath}/static/css/fore/fore_orderList.css" rel="stylesheet"/>
    <script src="${ctxPath}/static/js/order/refund.js"></script>
    <title>退款订单</title>
    <script>
        $(function () {
            $('#btn-ok').click(function () {
                $.ajax({
                    url: "${ctxPath}/order/close/" + $("#order_id_hidden").val(),
                    type: "PUT",
                    data: null,
                    dataType: "json",
                    success: function (data) {
                        if (data.success !== true) {
                            alert("订单处理异常，请稍候再试！");
                        }
                        location.href = "${ctxPath}/order/0/10";
                    },
                    beforeSend: function () {

                    },
                    error: function () {
                        alert("订单取消出现问题，请稍后再试！");
                        location.href = "${ctxPath}/order/0/10";
                    }
                });
            });
        });

        function closeOrder(orderCode) {
            if (isNaN(orderCode) || orderCode === null) {
                return;
            }
            $("#order_id_hidden").val(orderCode);
            $('#modalDiv').modal();
        }

        function getPage(index) {
            var name = $(".tab_select").children("a").attr("name");
            if (name === undefined) {
                name = "";
            }
            location.href = "${ctxPath}/order/" + index + "/10" + "?" + name;
        }
    </script>
</head>
<body>
<nav>
    <%@ include file="../fore/include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <a href="${ctxPath}"><img
                    src="${ctxPath}/static/images/fore/WebsiteImage/yeepayLogoA.png"></a>
        </div>
<%--        <div class="shopSearchHeader">--%>
<%--            <form action="${pageContext.request.contextPath}/product" method="get">--%>
<%--                <div class="shopSearchInput">--%>
<%--                    <input type="text" class="searchInput" name="product_name" placeholder="搜索 XX商城 商品/品牌/店铺"--%>
<%--                           maxlength="50">--%>
<%--                    <input type="submit" value="搜 索" class="searchBtn">--%>
<%--                </div>--%>
<%--            </form>--%>
<%--            <ul>--%>
<%--                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">--%>
<%--                    <li>--%>
<%--                        <a href="${pageContext.request.contextPath}/product?category_id=${category.category_id}">${category.category_name}</a>--%>
<%--                    </li>--%>
<%--                </c:forEach>--%>
<%--            </ul>--%>
<%--        </div>--%>
    </div>
</nav>
<div class="content">
    <ul class="tabs_ul">
        <li class="tab_select">退款订单</li>
        <li><a href="${ctxPath}/payOrder/list">支付订单</a></li>
    </ul>
    <table class="table_orderList">
        <thead>
        <tr>
            <th>商编</th>
            <th>商户订单号</th>
            <th>易宝订单号</th>
            <th>申请退款金额</th>
            <th>实际退款金额</th>
            <th>原支付订单号</th>
            <th>原支付易宝订单号</th>
            <th>错误码</th>
            <th>错误信息</th>
            <th>订单状态</th>
            <th>退款完成时间</th>
            <th>交易操作</th>
        </tr>
        </thead>
        <c:choose>
            <c:when test="${orderList != null && fn:length(orderList)>0}">
                    <tbody>
                    <c:forEach items="${orderList}" var="order" varStatus="i">
                        <tr class="tr_orderItem_info">
                            <td>${order.merchantNo}</td>
                            <td>${order.refundRequestId}</td>
                            <td>${order.uniqueRefundNo}</td>
                            <td>${order.refundAmount}</td>
                            <td>${order.realRefundAmount}</td>
                            <td>${order.payOrderId}</td>
                            <td>${order.payUniqueOrderNo}</td>
                            <td>${order.errorCode}</td>
                            <td>${order.errorMsg}</td>
                            <td>${order.status}</td>
                            <td>${order.refundSuccessDate}</td>
                            <td>
                                <c:if test="${order.status != 'SUCCESS' and order.status != 'FAIL'}">
                                    <div class="btn">
                                        <input type="button" class="btn_sure fw" id="updateStatus"
                                               onclick="Refund.updateStatus('${order.payOrderId}',
                                                       '${order.refundRequestId}', '${order.uniqueRefundNo}')" value="更新状态"/>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
            </c:when>
            <c:otherwise>
                <tbody>
                <tr>
                    <td colspan="6" class="no_search_result"><img
                            src="${ctxPath}/static/images/fore/WebsiteImage/T1MQ1cXhtiXXXXXXXX-78-120.png"/><span
                            class="error_msg">没有符合条件的退款订单，请尝试其他搜索条件。</span></td>
                </tr>
                </tbody>
            </c:otherwise>
        </c:choose>
    </table>
    <%@include file="../fore/include/page.jsp" %>
</div>
<%-- 模态框 --%>
<div class="modal fade" id="modalDiv" tabindex="-1" role="dialog" aria-labelledby="modalDiv" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">您确定要取消该订单吗？取消订单后，不能恢复。</div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" id="btn-ok">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="btn-close">关闭</button>
                <input type="hidden" id="order_id_hidden">
            </div>
        </div>
        <%-- /.modal-content --%>
    </div>
    <%-- /.modal --%>
</div>
</body>
