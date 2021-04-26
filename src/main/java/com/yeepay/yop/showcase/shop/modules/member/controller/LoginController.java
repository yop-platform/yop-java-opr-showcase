package com.yeepay.yop.showcase.shop.modules.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.yeepay.yop.showcase.shop.controller.BaseController;
import com.yeepay.yop.showcase.shop.modules.member.entity.User;
import com.yeepay.yop.showcase.shop.modules.member.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登陆页
 *
 * @author
 */
@Controller
public class LoginController extends BaseController {

    @Resource(name = "userService")
    private UserService userService;

    //转到前台XX商城-登录页
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public Object goToPage(HttpSession session, Map<String, Object> map, HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        String url = request.getParameter("url");
        view.addObject("redirectUrl", url);
        logger.info("转到前台XX商城-登录页");
        view.setViewName("fore/loginPage");
        return view;
    }

    //登陆验证-ajax
    @ResponseBody
    @RequestMapping(value = "login/doLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String checkLogin(HttpSession session, @RequestParam String username, @RequestParam String password) {
        logger.info("用户验证登录");
        User user = userService.login(username, password);

        JSONObject jsonObject = new JSONObject();
        if (user == null) {
            logger.info("登录验证失败");
            jsonObject.put("success", false);
        } else {
            logger.info("登录验证成功,用户ID传入会话");
            session.setAttribute("userId", user.getUser_id());
            jsonObject.put("success", true);
        }
        return jsonObject.toJSONString();
    }

    //退出当前账号
    @RequestMapping(value = "login/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        Object o = session.getAttribute("userId");
        if (o != null) {
            session.removeAttribute("userId");
            session.invalidate();
            logger.info("登录信息已清除，返回用户登录页");
        }
        return "redirect:/login";
    }
}
