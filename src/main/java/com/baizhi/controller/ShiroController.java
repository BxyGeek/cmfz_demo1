package com.baizhi.controller;

import com.baizhi.api.BaseApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 类描述信息 (验证后台登陆)
 *
 * @author : buxiaoyu
 * @date : 2019-07-28 15:28
 * @version: V_1.0.0
 */
@Slf4j
@Controller
@RequestMapping("shiroAdmin")
public class ShiroController extends BaseApiService {

    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(String adminName, String password, String enCode, HttpServletRequest request){
        //获取判断验证码
        HttpSession session = request.getSession();
        String validateCode = (String) session.getAttribute("validateCode");
        if (enCode == null || StringUtils.equals("",enCode)){
            //request.setAttribute("msg","验证码为空！！！");
            log.error("验证码为空！！！");
            return setResultError("验证码为空");
        }if (!StringUtils.equals(validateCode,enCode)){
            //request.setAttribute("msg","验证码错误！！！");
            log.error("验证码错误！！！");
            return setResultError("验证码错误");
        }
        //获得主体对象
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(adminName,password);
        try {
            subject.login(token);
            return setResultSuccess("登陆成功");
        } catch (UnknownAccountException e) {
            log.error("帐号错误！！！");
            return setResultError("账号错误");
        }catch (IncorrectCredentialsException e){
            log.error("密码错误！！！");
            return setResultError("密码错误");
        }catch (AuthenticationException e){
            log.error("账号不存在");
            return setResultError("账号不存在");
        }
    }
}
