package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 接口描述信息 (章节业务)
 *
 * @author : buxiaoyu
 * @date : 2019-07-22 15:43
 * @version: V_1.0.0
 */
public interface ChapterService {
    /**
     * 方法描述: (查询所有章节)
     * @param page
     * @param size
     * @return java.util.Map<java.lang.String, java.lang.Object>

        Map<String,Object> queryAll(Integer page, Integer size);
     */


    /**
     * 方法描述: (根据专辑ID查询所有章节)
     * @param albumId
     * @param page
     * @param size
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    Map<String,Object> queryChaptersByAlbumId(String albumId,Integer page, Integer size);


    /**
     * 方法描述: (添加章节)
     * @param chapter
     * @param albumId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    Integer add(Chapter chapter,String albumId);

    /**
     * 方法描述: (修改章节)
     * @param chapter
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    Integer edit(Chapter chapter);

    /**
     * 方法描述: (删除章节)
     * @param chapter
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    Integer del(Chapter chapter);


    /**
     * 方法描述: (文件上传)
     * @param id
     * @param fileName     音频文件
     * @param request
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */

    Integer upload(String id, MultipartFile fileName, HttpServletRequest request) throws Exception;




}
