package com.baizhi.service;

import com.baizhi.annotation.RedisCache;
import com.baizhi.api.BaseApiService;
import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 类描述信息 (文章接口实现类)
 *
 * @author : buxiaoyu
 * @date : 2019-07-23 15:50
 * @version: V_1.0.0
 */
@Slf4j
@Service("articleService")
@Transactional
public class ArticleServiceImpl extends BaseApiService implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    //配置redis缓存
    @RedisCache
    public Map<String, Object> queryAllArticles(Integer page, Integer size) {
        RowBounds rowBounds = getRowBounds(page, size);
        Article article = new Article();
        List<Article> articles = articleDAO.selectByRowBounds(article, rowBounds);
        System.out.println();
        Integer records = articleDAO.selectCount(article);
        Integer total = records/size==0?records/size:records/size+1;
        System.out.println();
        return setResultSuccessDataByPage(articles,page,total,records);
    }

    @Override
    @Transactional
    public Map<String, Object> browser(HttpServletRequest request){
        //   /Library/local/IdeaProjects/cmfz/cmfz_demo1/src/main/webapp/image/picture/1.jpg
       //    /Library/local/IdeaProjects/cmfz/cmfz_demo1/src/main/webapp/statics/image
        String realPath = request.getSession().getServletContext().getRealPath("statics/image/picture/");
        // file.getName()              picture
        // file.getParent()            /Library/local/IdeaProjects/cmfz/cmfz_demo1/src/main/webapp/image
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdir();
        }
        //返回结果
        Map<String, Object> map = new HashMap<>();
        String current_url = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/statics/image/"+file.getName()+"/";
        //设置返回值
        map.put("current_url",current_url);
        List<Object> list = new ArrayList<>();
        File[] files = file.listFiles();
        map.put("total_count",files.length);
        for (int i = 0; i < files.length; i++) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("is_dir",false);
            map1.put("has_file",false);
            map1.put("filesize",files[i].length());
            map1.put("is_photo",true);
            //获取后缀名
            map1.put("filetype",FilenameUtils.getExtension(files[i].getName()));
            map1.put("filename",files[i].getName());
            map1.put("datetime",new Date());
            list.add(map1);
        }

        map.put("file_list",list);
        return map;

    /**
        // http://localhost:8989/cmfz/article/browser
        log.info("RequestURL        :      "+request.getRequestURL());
        // localhost
        log.info("ServerName        :      "+request.getServerName());
        //GET
        log.info("Method            :      "+request.getMethod());
        // /cmfz
        log.info("ContextPath       :      "+request.getContextPath());
        // path=&order=NAME&dir=image&1563875101169
        log.info("QueryString       :      "+request.getQueryString());
        // /article/browser
        log.info("ServletPath       :      "+request.getServletPath());
        // 8989
        log.info("LocalPort         :      "+request.getLocalPort());
        // 8989
        log.info("ServerPort        :      "+request.getServerPort());
    */
    }


    /**
     * 方法描述: (添加文章)
     * @param article
     * @return java.lang.Integer
     */

    @Override
    public Integer add(Article article) {
        article.setId(UUID.randomUUID().toString().replace("-",""));
        article.setCreateDate(new Date());
        return articleDAO.insertSelective(article);
    }

    @Override
    public Integer edit(Article article) {
       return articleDAO.updateByPrimaryKeySelective(article);
    }

    @Override
    public Integer del(Article article) {
        return articleDAO.deleteByPrimaryKey(article.getId());
    }

    @Override
    public Map<String, Object> upload(String id, MultipartFile imgFile, HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<>();
        imgFile.transferTo(new File(request.getSession().getServletContext().getRealPath("statics/image/picture"),imgFile.getOriginalFilename()));
        String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/statics/image/picture/" + imgFile.getOriginalFilename();
        map.put("error",0);
        map.put("url",url);
        return map;
    }
}
