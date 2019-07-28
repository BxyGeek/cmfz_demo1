package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 类描述信息 (管理员)
 *
 * @author : buxiaoyu
 * @date : 2019-07-17 11:47
 * @version: V_1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cmfz_admin")
public class Admin implements Serializable {

    private String id;
    @Id
    private String adminName;
    private String password;
    private String salt;
    //AuthenticationException
}
