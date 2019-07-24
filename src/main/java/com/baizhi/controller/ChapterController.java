package com.baizhi.controller;

import com.baizhi.api.BaseApiService;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 类描述信息 (章节前台接口)
 *
 * @author : buxiaoyu
 * @date : 2019-07-22 15:59
 * @version: V_1.0.0
 */
@Slf4j
@Controller
@RequestMapping("chapter")
public class ChapterController extends BaseApiService {

    @Autowired
    private ChapterService chapterService;

    @ResponseBody
    @RequestMapping("queryChaptersByAlbumId")
    public Map<String,Object> queryChaptersByAlbumId(String albumId,Integer page, Integer rows){
        try {
            if (albumId==null || StringUtils.equals("",albumId)) return setResultParamterError("参数错误：专辑ID为空！！！");
            if (page == null  || page == 0) return setResultParamterError("参数错误：当前页码page为空！！！");
            if (rows == null  || rows == 0) return setResultParamterError("参数错误：页面容量size为空！！！");
            Map<String, Object> map = chapterService.queryChaptersByAlbumId(albumId, page, rows);
            return map;
        } catch (Exception e){
            log.error("*****后台打印：   查询出错,内部异常*****ERROR",e);
            return setResultError("#####查询出错,内部异常#####ERROR ");
        }
    }

    @ResponseBody
    @RequestMapping("option")
    public Map<String,Object> option(Chapter chapter,String oper,String albumId){
        Map<String, Object> map = null ;
        try {
            if ((!StringUtils.equals("add",oper )) &&(!StringUtils.equals("edit",oper ))&&(!StringUtils.equals("del",oper ))) {
                log.info("");
                return setResultParamterError("参数错误oper:   "+oper);
            }
            if (StringUtils.equals("add",oper)){
                log.info("***add()***");
                Integer i = chapterService.add(chapter,albumId);
                map = setCheck(i, chapter.getId());
            }
            if (StringUtils.equals("edit",oper)){
                log.info("***edit()***");
                Integer i = chapterService.edit(chapter);
                map = setCheck(i, chapter.getId());
            }
            if (StringUtils.equals("del",oper )) {
                Integer i = chapterService.del(chapter);
                map = setCheck(i, chapter.getId());
            }
            return map;
        }catch (Exception e){
            log.error("#######操作失败option########",e);
            return setResultParamterError("#######操作失败option########ERROR");
        }
    }

    /**
     * 方法描述: (音频上传)
     * @param id
     * @param fileName   音频文件的名字
     * @param request
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */

    @RequestMapping("upload")
    @ResponseBody
    public Map<String, Object> upload(String id, MultipartFile fileName, HttpServletRequest request){
        try {
            if (id == null || StringUtils.equals("",id)){
                return setResultParamterError("###参数id为空###");
            }
            Integer i = chapterService.upload(id, fileName, request);
            Map<String, Object> map = setCheck(i, id);
            return map;
        } catch (Exception e){
            return setResultError("#####上传失败upload()#####ERROR");
        }
    }


}
