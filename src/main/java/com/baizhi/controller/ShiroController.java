package com.baizhi.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 类描述信息 (验证后台登陆)
 *
 * @author : buxiaoyu
 * @date : 2019-07-28 15:28
 * @version: V_1.0.0
 */
@Controller
@RequestMapping("shiroAdmin")
public class ShiroController {

    @RequestMapping("login")
    public String login(String adminName, String password, String enCode, HttpServletRequest request){
        //获取判断验证码
        HttpSession session = request.getSession();
        String validateCode = (String) session.getAttribute("validateCode");
        if (enCode == null || StringUtils.equals("",enCode)){
            request.setAttribute("msg","验证码为空！！！");
            System.err.println("验证码为空！！！");
            return "/login/login";
        }if (!StringUtils.equals(validateCode,enCode)){
            request.setAttribute("msg","验证码错误！！！");
            System.err.println("验证码错误！！！");
            return "/login/login";
        }
        //获得主体对象
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(adminName,password);
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
//            e.printStackTrace();
            System.err.println("帐号错误！！！");
            return "/login/login";
        }catch (IncorrectCredentialsException e){
            System.err.println("密码错误！！！");
//            e.printStackTrace();
            return "/login/login";
        }catch (AuthenticationException e){
            System.err.println("账号不存在");
//            e.printStackTrace();
            return "/login/login";
        }
    }
}
