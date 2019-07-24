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
 * 类描述信息 (文章实体类)
 *
 * @author : buxiaoyu
 * @date : 2019-07-23 15:44
 * @version: V_1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_article")
public class Article implements Serializable {
    @Id
    private String id;
    private String articleName;
    private String content;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private String guruId;
}
