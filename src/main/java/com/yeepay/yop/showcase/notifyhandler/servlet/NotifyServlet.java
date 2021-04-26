package com.yeepay.yop.showcase.notifyhandler.servlet;

import com.yeepay.yop.showcase.notifyhandler.handler.NotifyHandlerFactory;
import com.yeepay.yop.showcase.shop.config.YopOprShowcaseConfig;
import com.yeepay.yop.sdk.security.DigitalEnvelopeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/29上午11:34
 */
@Slf4j
@WebServlet(name = "notify", urlPatterns = "/notify/*", asyncSupported = true)
public class NotifyServlet extends HttpServlet {

    @Autowired
    private YopOprShowcaseConfig yopOprShowcaseConfig;

    /**
     * 1 数据解密
     * 2 调用各自业务通知处理
     * 3 返回通知结果
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String notifyUrl = request.getPathInfo();
            String notifyInfo = notifyDecrypt(request);
            NotifyHandlerFactory.get(notifyUrl).handle(notifyInfo);
            processNotifyCall(response, "SUCCESS");
        } catch (Exception e) {
            processNotifyCall(response, "FAIL");
        }
    }

    private void processNotifyCall(HttpServletResponse response, String status) throws IOException {
        final PrintWriter writer = response.getWriter();
        StringBuilder responseBuffer = new StringBuilder();
        responseBuffer.append(status);
        try {
            writer.println(responseBuffer.toString());
        } finally {
            writer.close();
        }
    }

    private String notifyDecrypt(HttpServletRequest request) throws Exception {
        String appKey = request.getParameter("customerIdentification");
        String content = request.getParameter("response");
        log.info("获取到的appKey为{},response为{}", appKey, content);
        return DigitalEnvelopeUtils.decrypt(content, appKey, "RSA2048");
    }

}
