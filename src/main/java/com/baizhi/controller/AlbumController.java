package com.baizhi.controller;

import com.baizhi.api.BaseApiService;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 类描述信息 ()
 *
 * @author : buxiaoyu
 * @date : 2019-07-22 12:10
 * @version: V_1.0.0
 */
@Slf4j
@Controller
@RequestMapping("album")
public class AlbumController extends BaseApiService {

    @Autowired
    private AlbumService albumService;
    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(Integer page,Integer rows){
        try {
            return albumService.queryAll(page, rows);
        } catch (Exception e){
            log.error("******查询失败,内部异常 queryAll()  ERROR ******",e);
            return setResultError("######查询失败,内部异常 queryAll()  ERROR #####");
        }

    }

}
