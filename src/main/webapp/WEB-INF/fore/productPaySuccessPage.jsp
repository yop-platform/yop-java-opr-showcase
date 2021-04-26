<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <link href="${pageContext.request.contextPath}/static/css/fore/fore_orderPaySuccess.css" rel="stylesheet"/>
    <title>易宝yeepay.com - 网上支付</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <a href="${pageContext.request.contextPath}"><img
                    src="${pageContext.request.contextPath}/static/images/fore/WebsiteImage/yeepayLogoA.png"></a>
        </div>
        <div class="shopSearchHeader">
            <form action="${pageContext.request.contextPath}/product" method="get">
                <div class="shopSearchInput">
                    <input type="text" class="searchInput" name="product_name" placeholder="搜索 易宝 商品/品牌/店铺"
                           value="${requestScope.searchValue}" maxlength="50">
                    <input type="submit" value="搜 索" class="searchBtn">
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <li>
                        <a href="${pageContext.request.contextPath}/product?category_id=${category.category_id}">${category.category_name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>
<div class="content">
    <div class="content_main">
        <div id="J_AmountList">
            <h2>您已成功付款</h2>
            <div class="summary_pay_done">
                <ul>
                    <li>
                        收货地址：<span>${requestScope.productOrder.productOrder_detail_address} ${requestScope.productOrder.productOrder_receiver} ${requestScope.productOrder.productOrder_mobile}</span>
                    </li>
                    <li>实付款：<span><em>￥${requestScope.orderTotalPrice}</em></span></li>
                </ul>
            </div>
        </div>
        <div id="J_ButtonList">
            <span class="info">您可以 </span>
            <a class="J_MakePoint" href="${pageContext.request.contextPath}/order/0/10">查看已买到的宝贝</a>
        </div>
        <div id="J_RemindList">
            <ul>
                <li class="alertLi">
                    <p>
                        <strong>安全提醒：</strong>
                        <span class="info">下单后，</span>
                        <span class="warn">用QQ给您发送链接办理退款的都是骗子</span>
                        <span class="info">！易宝不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！</span>
                    </p>
                </li>
            </ul>
        </div>
        <div id="J_Qrcode">
        </div>
    </div>
</div>
</body>