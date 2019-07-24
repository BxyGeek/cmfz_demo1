package com.baizhi.controller;

import com.baizhi.api.BaseApiService;
import com.baizhi.util.ValidateImageCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 类描述信息 (获取验证码)
 *
 * @author : buxiaoyu
 * @date : 2019-07-17 11:51
 * @version: V_1.0.0
 */
@Slf4j
@Controller
@RequestMapping("code")
public class ValidateCodeController extends BaseApiService {

    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session){
        try {
            String validateCode = ValidateImageCodeUtils.getSecurityCode();
            log.info("验证码为：   " + validateCode);
            //验证码存入session
            session.setAttribute("validateCode",validateCode);
            BufferedImage image = ValidateImageCodeUtils.createImage(validateCode);
            response.setContentType("image/png");
            ImageIO.write(image,"png",response.getOutputStream());
            setResultSuccess("验证码为："+validateCode);
        } catch (IOException e) {
            setResultError("验证码获取失败");
            log.error("**********验证码获取失败！getCode()***********",e);
        }

    }

}
