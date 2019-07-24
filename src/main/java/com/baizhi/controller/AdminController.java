package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 类描述信息 (管理员接口)
 *
 * @author : buxiaoyu
 * @date : 2019-07-17 14:53
 * @version: V_1.0.0
 */
@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(Admin admin,HttpSession session,String enCode){
        Map<String, Object> map = adminService.login(admin,session,enCode);
        for (String s : map.keySet()) {
            log.info("============map集合中的元素：  "+map.get(s));
        }
        session.setAttribute("result",map);
        return map;
    }
    @RequestMapping("exit")
    public String exit(HttpSession session){
       session.removeAttribute("admin");
       session.removeAttribute("result");
        return "redirect:/login/login.jsp";
    }
}
