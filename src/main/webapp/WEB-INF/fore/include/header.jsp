<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">--%>
    <link href="${pageContext.request.contextPath}/static/images/fore/WebsiteImage/yeepay_48px_1193412_easyicon.net.ico" rel="icon" type="image/x-ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-select.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore/fore_main.css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/defaults-zh_CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/base.js"></script>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}" scope="session"/>
</head>
<script type="text/javascript">
    var session=window.sessionStorage;
    var GV = {
        ctxPath: '${ctxPath}',
        jsPath: '${ctxPath}/static/js/order'
    };
    sessionStorage.setItem("redirectUrl",'${redirectUrl}');
    session.setItem("contextUrl", "${pageContext.request.contextPath}");
</script>