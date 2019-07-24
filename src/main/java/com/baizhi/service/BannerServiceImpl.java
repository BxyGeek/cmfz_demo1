package com.baizhi.service;

import com.baizhi.api.BaseApiService;
import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 类描述信息 ()
 *
 * @author : buxiaoyu
 * @date : 2019-07-18 12:07
 * @version: V_1.0.0
 */
@Slf4j
@Service
@Transactional
public class BannerServiceImpl extends BaseApiService implements BannerService {

    @Autowired
    private BannerDAO bannerDAO;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> queryAll(Integer page, Integer size) {
        RowBounds rowBounds = getRowBounds(page, size);
        Example example = new Example(Banner.class);
        example.createCriteria().andEqualTo("deleteFlag", 1);
        List<Banner> banners = bannerDAO.selectByExampleAndRowBounds(example, rowBounds);
        //bannerDAO.selectByRowBounds();

        //总条数/总记录数
        int count = bannerDAO.selectCountByExample(example);
        //


        //总页数
        Integer total = count % size == 0 ? count / size : count / size + 1;
        return setResultSuccessDataByPage(banners, page, total, count);

    }

    @Override
    public Integer add(Banner banner) {

        //设置轮播图相关属性
        String id = UUID.randomUUID().toString().replace("-", "");
        banner.setId(id);
        banner.setCreateDate(new Date());
        banner.setUpdateDate(new Date());
        banner.setDeleteFlag(1);
        if (StringUtils.equals("", banner.getCover())) {
            log.info("未添加图片");


            banner.setCover("404.png");
        }
        //设置完成
        log.info("添加到数据库的轮播图:     " + banner);
        return bannerDAO.insertSelective(banner);
    }

    @Override
    public Integer edit(Banner banner) {
        if (StringUtils.equals("", banner.getCover())) {
            banner.setCover("404.png");
        }
        banner.setUpdateDate(new Date());
        return bannerDAO.updateByPrimaryKeySelective(banner);
    }

    @Override
    public Integer del(Banner banner) {
        banner.setDeleteFlag(0);
        banner.setUpdateDate(new Date());
        return bannerDAO.updateByPrimaryKeySelective(banner);
    }

    @Override
    public Integer upload(String id, MultipartFile cover, HttpServletRequest request) throws IOException {

        Banner banner = bannerDAO.selectByPrimaryKey(id);
        String realPath = request.getSession().getServletContext().getRealPath("/statics/image/picture/");
        if (!StringUtils.equals("", cover.getOriginalFilename())) {
            cover.transferTo(new File(realPath + cover.getOriginalFilename()));
            banner.setCover(cover.getOriginalFilename());
        }
        return bannerDAO.updateByPrimaryKeySelective(banner);
    }



}