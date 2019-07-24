package com.baizhi.service;

import com.baizhi.api.BaseApiService;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import it.sauronsoftware.jave.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 类描述信息 (章节业务实现类)
 *
 * @author : buxiaoyu
 * @date : 2019-07-22 15:45
 * @version: V_1.0.0
 */
@Slf4j
@Transactional
@Service("chapterService")
public class ChapterServiceImpl extends BaseApiService implements ChapterService {

    @Autowired
    private ChapterDAO chapterDAO;
    @Autowired
    private AlbumService albumService;


    /*@Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryAll(Integer page, Integer size) {
        Chapter chapter = new Chapter();
        RowBounds rowBounds = getRowBounds(page, size);
        List<Chapter> chapters = chapterDAO.selectByRowBounds(chapter, rowBounds);
        Integer records = chapterDAO.selectCount(chapter);
        Integer total = records/size==0?records/size:records/size+1;
        return setResultSuccessDataByPage(chapters,page,total,records);
    }*/


    /**
     * 方法描述: (根据专辑ID查询所有章节)
     * @param albumId   专辑的ID
     * @param page      当前页
     * @param size      每页容量
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryChaptersByAlbumId(String albumId,Integer page, Integer size) {
        Chapter chapter = new Chapter();
        RowBounds rowBounds = getRowBounds(page, size);
        chapter.setAlbumId(albumId);
        List<Chapter> chapters = chapterDAO.selectByRowBounds(chapter, rowBounds);

        Integer records = chapterDAO.selectCount(chapter);
        Integer total = records/size==0?records/size:records/size+1;
        return setResultSuccessDataByPage(chapters,page,total,records);
    }

    @Override
    public Integer add(Chapter chapter,String albumId) {
        log.info("进入到添加的Service");
        log.info("chapter为：  "+chapter);
        log.info("albumId为：  "+albumId);
        chapter.setId(UUID.randomUUID().toString().replace("-",""));
        chapter.setCreateDate(new Date());
        chapter.setAlbumId(albumId);
        log.info("章节信息：   "+chapter);
        if (StringUtils.equals("",chapter.getFileName())){
            log.info("章节音频为空");
            chapter.setFileName(null);
        }
        log.info("即将添加到数据库的章节为：       "+chapter);
        int i = chapterDAO.insertSelective(chapter);
        if (i!=0){
            log.info("查询到了对应的专辑");
            Album album = albumService.queryOne(albumId);
            album.setAmount(album.getAmount()+1);
            album.setUpdateDate(new Date());
            log.info("添加章节后动态改变专辑的数量：      "+album);
            Integer edit = albumService.edit(album);
            log.info("修改了 "+edit+" 条数据");
        }
        return i;
    }

    @Override
    public Integer edit(Chapter chapter) {
        return null;
    }

    @Override
    public Integer del(Chapter chapter) {
        return null;
    }

    @Override
    public Integer upload(String id, MultipartFile fileName, HttpServletRequest request) throws Exception {
        log.info("进入上传的service");
        Chapter chapter = chapterDAO.selectByPrimaryKey(id);
        String realPath = request.getSession().getServletContext().getRealPath("/statics/audio/");
        File file = new File(realPath + fileName.getOriginalFilename());
        //上传
        if (!StringUtils.equals("", fileName.getOriginalFilename())) {
            log.info("*******上传的章节不为空 \" \" ");
            fileName.transferTo(file);
            log.info("OriginalFilename为：       "+fileName.getOriginalFilename());
            chapter.setFileName(fileName.getOriginalFilename());
            //设置时长
            Long s = fileName.getSize();
            BigDecimal bigDecimal = new BigDecimal(s);   //3276445623
            log.info("长度为：     "+s);
            BigDecimal decimal = new BigDecimal(1024); //1024
            BigDecimal divide = bigDecimal.divide(decimal).divide(decimal).setScale(2,BigDecimal.ROUND_HALF_UP);  //3276445623/1024/1024  == MB
            log.info("大小为：     "+divide+"MB");
            chapter.setSize(divide+"MB");
            //设置大小
            Encoder encoder = new Encoder();
            Long duration = encoder.getInfo(file).getDuration();//获取毫秒值
            log.info("时长为：     "+duration);
            String time = duration/1000/60+":"+duration/1000%60;
            chapter.setDuration(time);
            log.info("上传到数据库的chapter：            "+chapter);
        }
        return chapterDAO.updateByPrimaryKeySelective(chapter);

    }



    //测试测试啦

    //哎呀我又测试啦


    //当前切换到dev分支


    //当前处于dev分支


}
