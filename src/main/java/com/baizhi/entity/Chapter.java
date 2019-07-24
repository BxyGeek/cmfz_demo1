package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 类描述信息 (章节)
 *
 * @author : buxiaoyu
 * @date : 2019-07-22 14:45
 * @version: V_1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cmfz_chapter")
public class Chapter implements Serializable {
    @Id
    private String id;
    private String chapterName; //'章节名称'
    private String  size; //'章节大小'
    private String duration;//'章节时常'
    private String fileName; //'文件名';
    private Date createDate;
    private String albumId; //'专辑id'
}
