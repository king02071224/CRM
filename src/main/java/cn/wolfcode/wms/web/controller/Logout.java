package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.utils.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by king on 2017/12/7
 */
@Controller
public class Logout {

    @RequestMapping("logout")
    public String logout() throws Exception{
        UserContext.invalidateSession();
        return "redirect:/login.jsp";
    }
}
