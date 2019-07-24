package com.baizhi.service;


import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 接口描述信息 (文章接口)
 *
 * @author : buxiaoyu
 * @date : 2019-07-23 15:48
 * @version: V_1.0.0
 */
public interface ArticleService {
    /**
     * 方法描述: (查询所有文章)
     * @param page
     * @param size
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */

    Map<String,Object> queryAllArticles(Integer page, Integer size);


    /**
     * 方法描述: (查询图片空间)
     * @param request
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */

    Map<String,Object> browser(HttpServletRequest request) throws IOException;


    /**
     * 方法描述: (添加文章)
     * @param article
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    Integer add(Article article);

    /**
     * 方法描述: (修改文章)
     * @param article
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    Integer edit(Article article);

    /**
     * 方法描述: (删除文章)
     * @param article
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    Integer del(Article article);

    /**
     * 方法描述: (文件上传)
     * @param id
     * @param cover
     * @param request
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */

    Map<String, Object> upload(String id, MultipartFile imgFile, HttpServletRequest request) throws IOException;

}
