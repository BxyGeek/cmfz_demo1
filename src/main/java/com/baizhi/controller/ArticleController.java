package com.baizhi.controller;

import com.baizhi.api.BaseApiService;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 类描述信息 (文章控制器接口)
 *
 * @author : buxiaoyu
 * @date : 2019-07-23 16:06
 * @version: V_1.0.0
 */
@Slf4j
@Controller
@RequestMapping("article")
public class ArticleController  extends BaseApiService {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping("queryAllArticles")
    public Map<String, Object> queryAllArticles(Integer page, Integer rows) {
        try {
            if (page == 0 || page == null) return setResultParamterError("参数page为空");
            if (rows == 0 || rows == null) return setResultParamterError("参数rows为空");
            Map<String, Object> map = articleService.queryAllArticles(page, rows);
            return map;
        } catch (Exception e) {
            log.error("查询失败##ERROR", e);
            return setResultError("查询失败，内部异常");
        }
    }


    /**
     * 方法描述: (访问图片空间，查询上传的图片)
     *
     * @param
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */

    @ResponseBody
    @RequestMapping("browser")
    public Map<String, Object> browser(HttpServletRequest request) {
        try {
            Map<String, Object> map = articleService.browser(request);
            return map;
        } catch (IOException e) {
            return setResultError("查询失败，内部异常");
        }
    }

    @RequestMapping("option")
    @ResponseBody
    public Map<String, Object> option(String oper, Article article) {
        Map<String, Object> map = null;
        try {
            if ((!StringUtils.equals("add", oper)) && (!StringUtils.equals("edit", oper)) && (!StringUtils.equals("del", oper))) {
                System.err.println();
                return setResultParamterError("参数错误oper:   " + oper);
            }
            if (StringUtils.equals("add", oper)) {
                System.err.println("************(  add  )***********");
                Integer i = articleService.add(article);
                map = setCheck(i, article.getId());
            }
            if (StringUtils.equals("edit", oper)) {
                System.err.println("************(  edit  )***********");
                Integer i = articleService.edit(article);
                map = setCheck(i, article.getId());
            }
            if (StringUtils.equals("del", oper)) {
                System.err.println("************(  del  )***********");
                Integer i = articleService.del(article);
                map = setCheck(i, article.getId());
            }
            return map;
        } catch (Exception e) {
            log.error("#######操作失败option########", e);
            return setResultParamterError("#######操作失败option########ERROR");
        }
    }

    @RequestMapping("upload")
    @ResponseBody
    public Map<String, Object> upload(String id, MultipartFile imgFile, HttpServletRequest request) {
        Map<String, Object> map =null ;
        try {
            map = articleService.upload(id, imgFile, request);
            System.err.println("##########上传成功#############");

            return map;
        } catch (IOException e) {
            System.err.println("##########上传出错#############");
            map.put("error",1);
        }
        return map;
    }
}