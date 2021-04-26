package com.yeepay.yop.showcase.shop.controller;

import com.yeepay.yop.showcase.shop.modules.member.entity.User;
import com.yeepay.yop.showcase.shop.modules.member.service.UserService;
import com.yeepay.yop.showcase.shop.modules.product.entity.Category;
import com.yeepay.yop.showcase.shop.modules.product.entity.Product;
import com.yeepay.yop.showcase.shop.modules.product.service.CategoryService;
import com.yeepay.yop.showcase.shop.modules.product.service.ProductImageService;
import com.yeepay.yop.showcase.shop.modules.product.service.ProductService;
import com.yeepay.yop.showcase.shop.support.OrderUtil;
import com.yeepay.yop.showcase.shop.support.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 主页
 *
 * @author
 */
@Controller
public class HomeController extends BaseController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    @Resource(name = "productService")
    private ProductService productService;

    @Resource(name = "productImageService")
    private ProductImageService productImageService;

    //转到前台XX商城-主页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        Object userId = checkUser(session);
        if (userId != null) {
            logger.debug("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }

        logger.debug("获取产品分类列表");
        List<Category> categoryList = categoryService.getList(null, null);
        logger.debug("获取每个分类下的产品列表");
        for (Category category : categoryList) {
            List<Product> productList = productService.getList(
                    new Product().setProduct_category(category),
                    new Byte[]{0, 2},
                    new OrderUtil("product_sale_count", true), new PageUtil(0, 8)
            );
            if (productList != null) {
                for (Product product : productList) {
                    Integer product_id = product.getProduct_id();
                    product.setSingleProductImageList(
                            productImageService.getList(
                                    product_id, (byte) 0, new PageUtil(0, 1)
                            )
                    );
                }
            }
            category.setProductList(productList);
        }
        map.put("categoryList", categoryList);

        return "fore/homePage";
    }

    //转到前台XX商城-错误页
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String goToErrorPage() {
        return "fore/errorPage";
    }

}
