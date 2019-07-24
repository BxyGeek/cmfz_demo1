package com.baizhi.controller;

import com.baizhi.api.BaseApiService;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
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
 * 类描述信息 (轮播图接口)
 *
 * @author : buxiaoyu
 * @date : 2019-07-17 14:53
 * @version: V_1.0.0
 */
@Slf4j
@Controller
@RequestMapping("banner")
public class BannerController extends BaseApiService {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(Integer page,Integer rows){
        try {
            Map<String, Object> map = bannerService.queryAll(page, rows);
            return map;
        } catch (Exception e) {
            log.error("##########查询所有轮播图失败queryAll() ERROR ########", e);
            return setResultError("查询所有轮播图");
        }
    }

    @RequestMapping("option")
    @ResponseBody
    public Map<String,Object> option(String oper, Banner banner){
        Map<String, Object> map = null ;
        try {
            if ((!StringUtils.equals("add",oper ))&&(!StringUtils.equals("edit",oper ))&&(!StringUtils.equals("del",oper ))) {
                return setResultParamterError("参数错误oper:   "+oper);
            }
            if (StringUtils.equals("add",oper)){
                Integer i = bannerService.add(banner);
                map = setCheck(i, banner.getId());
            }
            if (StringUtils.equals("edit",oper)){
                Integer i = bannerService.edit(banner);
                map = setCheck(i, banner.getId());
            }
            if (StringUtils.equals("del",oper )) {
                Integer i = bannerService.del(banner);
                map = setCheck(i, banner.getId());
            }
            return map;
        }catch (Exception e){
            log.error("#######操作失败option########",e);
            return setResultParamterError("#######操作失败option########ERROR");
        }
    }

    @RequestMapping("upload")
    @ResponseBody
    public Map<String,Object> upload(String id, MultipartFile cover, HttpServletRequest request){
        try {
            Integer i = bannerService.upload(id, cover, request);
            return setCheck(i, id);
        } catch (IOException e) {
            log.error("######upload()上传失败#######",e);
            return setResultError("********upload()上传失败********");
        }
    }

}
