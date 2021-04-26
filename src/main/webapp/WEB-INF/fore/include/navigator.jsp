<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore/fore_nav.css"/>
<script>
    $(function () {
        $(".quick_li").find("li").hover(
            function () {
                $(this).find(".sn_menu").addClass("sn_menu_hover");
                $(this).find(".quick_menu,.quick_qrcode,.quick_DirectPromoDiv,.quick_sitmap_div").css("display", "block");
            }, function () {
                $(this).find(".sn_menu").removeClass("sn_menu_hover");
                $(this).find(".quick_menu,.quick_qrcode,.quick_DirectPromoDiv,.quick_sitmap_div").css("display", "none");
            }
        );
    });
</script>
<div id="nav">
    <div class="nav_main">
        <p id="container_login">
            <c:choose>
                <c:when test="${requestScope.user.user_name==null}">
                    <em>哈喽，欢迎来易宝</em>
                    <a href="${pageContext.request.contextPath}/login">请登录</a>
                    <a href="${pageContext.request.contextPath}/register">免费注册</a>
                </c:when>
                <c:otherwise>
                    <em>Hi，</em>
                    <a href="${pageContext.request.contextPath}/userDetails" class="userName"
                       target="_blank">${requestScope.user.user_name}</a>
                    <a href="${pageContext.request.contextPath}/login/logout">退出</a>
                </c:otherwise>
            </c:choose>
        </p>
        <ul class="quick_li">
            <li class="quick_li_MyTaobao">
                <div class="sn_menu">
                    <a href="${pageContext.request.contextPath}/userDetails">我的易宝<b></b></a>
                    <div class="quick_menu">
                        <a href="${pageContext.request.contextPath}/order/0/10">已买到的宝贝</a>
                        <a href="#">已卖出的宝贝</a>
                        <a href="${ctxPath}/payOrder/list">支付订单</a><br/>
                        <a href="${ctxPath}/refundOrder/list">退款订单</a>
                    </div>
                </div>
            </li>
            <li class="quick_li_cart">
                <img src="${pageContext.request.contextPath}/static/images/fore/WebsiteImage/buyCar.png">
                <a href="${pageContext.request.contextPath}/cart">购物车</a>
            </li>
            <li class="quick_li_menuItem">
                <div class="sn_menu">
                    <a href="#">收藏夹<b></b></a>
                    <div class="quick_menu">
                        <a href="#">收藏的宝贝</a>
                        <a href="#">收藏的店铺</a>
                    </div>
                </div>
            </li>
            <li class="quick_li_separator"></li>
            <li class="quick_li_mobile">
                <img src="${pageContext.request.contextPath}/static/images/fore/WebsiteImage/mobile.png">
                <a href="#" title="易宝无线">手机版</a>
                <div class="quick_qrcode">
                    <img src="${pageContext.request.contextPath}/static/images/fore/WebsiteImage/qrcode.png">
                    <b></b>
                </div>
            </li>
            <li class="quick_home"><a href="${pageContext.request.contextPath}">易宝网</a></li>
            <li class="quick_DirectPromo">
                <div class="sn_menu">
                    <a href="#">商家支持<b></b></a>
                    <div class="quick_DirectPromoDiv">
                        <ul>
                            <li>
                                <h3>商家：</h3>
                                <a href="">商家中心</a>
                                <a href="">易宝规则</a>
                                <a href="">商家入驻</a>
                                <a href="">运营服务</a>
                                <a href="">商家品控</a>
                                <a href="">商家工具</a>
                                <a href="">易宝智库</a>
                                <a href="">喵言喵语</a>
                            </li>
                            <li>
                                <h3>帮助：</h3>
                                <a href="">帮助中心</a>
                                <a href="">问商友</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>