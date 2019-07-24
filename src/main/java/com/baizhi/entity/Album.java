package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 类描述信息 (专辑)
 *
 * @author : buxiaoyu
 * @date : 2019-07-22 11:56
 * @version: V_1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cmfz_album")
public class Album implements Serializable {
    @Id
    private String id;
    private String title;
    private String cover;
    private Integer amount;
    private Double score;
    private String author;
    private String broadcast;
    private String brief;
    private Date createDate;
    private Date updateDate;
}
