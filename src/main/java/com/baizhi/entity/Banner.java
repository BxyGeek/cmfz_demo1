package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 类描述信息 (轮播图)
 *
 * @author : buxiaoyu
 * @date : 2019-07-17 11:47
 * @version: V_1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cmfz_banner")
public class Banner implements Serializable {
    @Id
    private String id;
    private String bannerName;
    private String cover;
    private String description;
    private Integer status;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    private Integer deleteFlag;
}
