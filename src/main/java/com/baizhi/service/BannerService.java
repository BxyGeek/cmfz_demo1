package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 接口描述信息 (轮播图业务)
 *
 * @author : buxiaoyu
 * @date : 2019-07-18 12:06
 * @version: V_1.0.0
 */
public interface BannerService {
    /**
     * 方法描述: (查询所有轮播图)
     * @param page
     * @param size
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */

    Map<String,Object> queryAll(Integer page,Integer size);

    /**
     * 方法描述: (添加轮播图)
     * @param banner
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    Integer add(Banner banner);

    /**
     * 方法描述: (修改轮播图)
     * @param banner
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    Integer edit(Banner banner);

    /**
     * 方法描述: (删除轮播图)
     * @param banner
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    Integer del(Banner banner);


    /**
     * 方法描述: (文件上传)
     * @param id
     * @param cover
     * @param request
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */

    Integer upload(String id, MultipartFile cover, HttpServletRequest request) throws IOException;




}
