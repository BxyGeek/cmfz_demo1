package com.baizhi.service;

import com.baizhi.api.BaseApiService;
import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 类描述信息 ()
 *
 * @author : buxiaoyu
 * @date : 2019-07-22 12:07
 * @version: V_1.0.0
 */
@Slf4j
@Service("albumService")
@Transactional
public class AlbumServiceImpl extends BaseApiService implements AlbumService {

    @Autowired
    private AlbumDAO albumDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String,Object> queryAll(Integer page, Integer size) {
        RowBounds rowBounds = getRowBounds(page, size);
        Album album = new Album();
        List<Album> albums = albumDAO.selectByRowBounds(album, rowBounds);
        System.out.println("albums");
        Integer records = albumDAO.selectCount(album);
        System.out.println("records");
        Integer total = records/size==0?records/size:records/size+1;
        return setResultSuccessDataByPage(albums,page,total,records);
    }

    @Override
    public Album queryOne(String id) {
        return albumDAO.selectByPrimaryKey(id);
    }

    @Override
    public Integer add(Album album) {
        return null;
    }

    @Override
    public Integer edit(Album album) {
        return albumDAO.updateByPrimaryKeySelective(album);
    }

    @Override
    public Integer del(Album album) {
        return null;
    }

    @Override
    public Integer upload(String id, MultipartFile cover, HttpServletRequest request) {
        return null;
    }
}
